package projetocg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class Preenchimento{
    int y_intersection = 4;
    ArrayList<Pontos> lista_pontos = new ArrayList<>();
    int TAMPIXEL, qtde_pixels;
    
    public void preenchimento(ArrayList<Pontos> pontos, String tipo_preenchimento, int TAMPIXEL1, int qtde_pixels1, Graphics g){
        lista_pontos = pontos;
        TAMPIXEL = TAMPIXEL1;
        qtde_pixels = qtde_pixels1;
        
        if(tipo_preenchimento == "recursivo"){
            preenchimento_recursivo(3, 4, g);
        }else{
            //Preenchimento por pts críticos
        }
        
    }
    
    public void varredura_pts_criticos(Graphics g){
        g.setColor(Color.RED);
        int[] p_aux = new int[2];
        
        int y_min = Integer.MAX_VALUE, y_max = Integer.MIN_VALUE;
        ArrayList<Pts_criticos> criticos = new ArrayList<>();
        ArrayList<Pts_criticos> criticos_ativos = new ArrayList<>();
        
        //Encontrando bounding box e pontos críticos de y
        for(int i = 0; i < lista_pontos.size(); i++){
            if(lista_pontos.get(i).y < y_min){
                y_min = lista_pontos.get(i).y;
            }else if(lista_pontos.get(i).y > y_max){
                y_max = lista_pontos.get(i).y;
            }
            
            p_aux[0] = lista_pontos.get((i+1)% lista_pontos.size()).x;
            p_aux[1] = lista_pontos.get((i+1)% lista_pontos.size()).y;
            
            if(lista_pontos.get(i).y < p_aux[1]){
                criticos.add(new Pts_criticos(i, 1, lista_pontos.get(i).x, 
                ((p_aux[0]-lista_pontos.get(i).x)*1.0f)/((p_aux[1]-lista_pontos.get(i).y)*1.0f)));
            }
            
            p_aux[0] = lista_pontos.get((i-1+lista_pontos.size())% lista_pontos.size()).x;
            p_aux[1] = lista_pontos.get((i-1+lista_pontos.size())% lista_pontos.size()).y;
            
            if(lista_pontos.get(i).y < p_aux[1]){
                criticos.add(new Pts_criticos(i, -1, lista_pontos.get(i).x, 
                ((p_aux[0]-lista_pontos.get(i).x)*1.0f)/((p_aux[1]-lista_pontos.get(i).y)*1.0f)));
            }
        }
        
        //System.out.println("y_min: "+y_min+", y_max:"+y_max);
        
        /*for(int i = 0; i < criticos.size(); i++){
            System.out.println(criticos.get(i).x_intersection);
        }*/
        
        int[] p_max = new int[2];
        
        //Varredura
        for(int y = y_min; y <= y_max; y++){
            for(Pts_criticos e : criticos_ativos){
                e.x_intersection += e.inv_slope;
            }
            
            for(Pts_criticos e : criticos){
                if(lista_pontos.get(e.index).y == y_intersection){
                    criticos_ativos.add(e);
                }
            }
        
            for(int i = criticos_ativos.size()-1; i >= 0; i--){
                Pts_criticos e = criticos_ativos.get(i);
                p_max[0] = lista_pontos.get((e.index+e.dir+lista_pontos.size())%lista_pontos.size()).x;
                p_max[1] = lista_pontos.get((e.index+e.dir+lista_pontos.size())%lista_pontos.size()).y;

                if(p_max[1] == y_intersection){
                    criticos_ativos.remove(i);
                }
            }

            //Collections.sort(criticos_ativos);
            /*ArrayList<Float> lista_criticos_ativos = new ArrayList<>();
            for(int ind = 0; ind < criticos_ativos.size(); ind++){
                lista_criticos_ativos.add(criticos_ativos.get(ind).x_intersection);
            }*/
            
            /*System.out.println("Antes do sort");
            for(int ind = 0; ind < criticos_ativos.size(); ind++){
                System.out.println(criticos_ativos.get(ind).x_intersection);
            }*/
            
            //Collections.sort(lista_criticos_ativos);
            Collections.sort(criticos_ativos);
            
            System.out.println("Depois do sort");
            for(int ind = 0; ind < criticos_ativos.size(); ind++){
                System.out.println(criticos_ativos.get(ind).x_intersection);
            }

            for(int i = 0; i < criticos_ativos.size(); i += 2){
                int x_start = Math.round(criticos_ativos.get(i).x_intersection);
                int x_end = Math.round(criticos_ativos.get(i+1).x_intersection);
                //int x_start = Math.round(lista_criticos_ativos.get(i));
                //int x_end = Math.round(lista_criticos_ativos.get(i+1));
                for(int x = x_start; x <= x_end; x++){
                    g.fillRect(x*TAMPIXEL, Math.abs((y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
                }
            }
        }
    }
    
    public boolean verificar_borda(int x, int y){
        for(int i = 0; i < lista_pontos.size(); i++){
            if(lista_pontos.get(i).x == x && lista_pontos.get(i).y == y){
                return true;
            }
        }
        return false;
    }
    
    public boolean verificar_pixels_pintados(int x, int y, ArrayList<Pontos> pontos_pintados){
        for(int i = 0; i < pontos_pintados.size(); i++){
            if(pontos_pintados.get(i).x == x && pontos_pintados.get(i).y == y){
                return true;
            }
        }
        return false;
    }
    
    ArrayList<Pontos> pontos_pintados = new ArrayList<>();
    public void preenchimento_recursivo(int x, int y, Graphics g){
        g.setColor(Color.RED);
        if(verificar_borda(x ,y) == false && verificar_pixels_pintados(x, y, pontos_pintados) == false){
            g.fillRect(x*TAMPIXEL, Math.abs((y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
            pontos_pintados.add(new Pontos(x, y));
            preenchimento_recursivo(x+1, y, g);
            preenchimento_recursivo(x, y+1, g);
            preenchimento_recursivo(x-1, y, g);
            preenchimento_recursivo(x, y-1, g);
        }
    }
}

