package projetocg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class Preenchimento{
    ArrayList<Pontos> lista_pontos = new ArrayList<>();
    ArrayList<Pontos> lista_arestas = new ArrayList<>();
    int TAMPIXEL, qtde_pixels;
    
    public void preenchimento(ArrayList<Pontos> pontos, ArrayList<Pontos> arestas, String tipo_preenchimento, Pontos pt_poligono, int TAMPIXEL1, int qtde_pixels1, Graphics g){
        lista_pontos = pontos;
        lista_arestas = arestas;
        TAMPIXEL = TAMPIXEL1;
        qtde_pixels = qtde_pixels1;
        
        if(tipo_preenchimento == "recursivo"){
            System.out.println("Executando preenchimento recursivo.");
            preenchimento_recursivo(pt_poligono.x, pt_poligono.y, g);
        }else{
            System.out.println("Executando preenchimento por varredura.");
            varredura_pts_criticos(g);
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
    
    public void varredura_pts_criticos(Graphics g){
        g.setColor(Color.RED);
        int[] p_aux = new int[2];
        
        int y_min = Integer.MAX_VALUE, y_max = Integer.MIN_VALUE;
        ArrayList<Pts_criticos> criticos = new ArrayList<>();
        ArrayList<Pts_criticos> criticos_ativos = new ArrayList<>();
        
        //Encontrando bounding box e pontos críticos de y
        for(int i = 0; i < lista_arestas.size(); i++){
            if(lista_arestas.get(i).y < y_min){
                y_min = lista_arestas.get(i).y;
            }
            if(lista_arestas.get(i).y > y_max){
                y_max = lista_arestas.get(i).y;
            }
            
            p_aux[0] = lista_arestas.get((i+1)% lista_arestas.size()).x;
            p_aux[1] = lista_arestas.get((i+1)% lista_arestas.size()).y;
            
            if(lista_arestas.get(i).y < p_aux[1]){
                criticos.add(new Pts_criticos(i, 1, lista_arestas.get(i).x, 
                ((p_aux[0]-lista_arestas.get(i).x)*1.0f)/((p_aux[1]-lista_arestas.get(i).y)*1.0f)));
            }
            
            p_aux[0] = lista_arestas.get((i-1+lista_arestas.size())% lista_arestas.size()).x;
            p_aux[1] = lista_arestas.get((i-1+lista_arestas.size())% lista_arestas.size()).y;
            
            if(lista_arestas.get(i).y < p_aux[1]){
                criticos.add(new Pts_criticos(i, -1, lista_arestas.get(i).x, 
                ((p_aux[0]-lista_arestas.get(i).x)*1.0f)/((p_aux[1]-lista_arestas.get(i).y)*1.0f)));
            }
        }
        
        int[] p_max = new int[2];
        
        //Varredura
        for(int y = y_min; y <= y_max; y++){
            for(Pts_criticos e : criticos_ativos){
                e.x_intersection += e.inv_slope;
            }
            
            for(Pts_criticos e : criticos){
                if(lista_arestas.get(e.index).y == y){
                    criticos_ativos.add(e);
                }
            }
        
            for(int i = criticos_ativos.size()-1; i >= 0; i--){
                Pts_criticos e = criticos_ativos.get(i);
                p_max[0] = lista_arestas.get((e.index+e.dir+lista_arestas.size())%lista_arestas.size()).x;
                p_max[1] = lista_arestas.get((e.index+e.dir+lista_arestas.size())%lista_arestas.size()).y;

                if(p_max[1] == y){
                    criticos_ativos.remove(i);
                }
            }
            
            Collections.sort(criticos_ativos);

            for(int i = 0; i < criticos_ativos.size(); i += 2){
                int x_start = Math.round(criticos_ativos.get(i).x_intersection);
                int x_end = Math.round(criticos_ativos.get(i+1).x_intersection);
  
                for(int x = x_start; x <= x_end; x++){
                    if(verificar_borda(x ,y) == false)
                        g.fillRect(x*TAMPIXEL, Math.abs((y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
                }
            }
        }
    }
}

