package projetocg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;


public class Poligonos{
    int y_intersection = 5;
    ArrayList<Integer> pontos = new ArrayList<>();
    ArrayList<Pontos> lista_pontos = new ArrayList<>();
    int TAMPIXEL1, qtde_pixels1;
    
    /*static class Pts_criticos{
        int index;
        int dir;
        float x_intersection;
        float inv_slope;
    }*/
    
    public void desenharLosango(int xInicial, int xFinal, int yInicial, int yFinal, int TAMPIXEL, int qtde_pixels, Graphics g){
        TAMPIXEL1 = TAMPIXEL;
        qtde_pixels1 = qtde_pixels;
        /*int avancar_linha = 0;
        g.fillRect(xInicial*TAMPIXEL, Math.abs((yInicial-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        for(int i = 0; i<(xFinal-xInicial); i++){
            avancar_linha++;
            g.fillRect((xInicial+avancar_linha)*TAMPIXEL, Math.abs((yInicial+avancar_linha-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        }*/
        
        /*g.fillRect(0*TAMPIXEL, Math.abs((4-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        g.fillRect(1*TAMPIXEL, Math.abs((5-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        g.fillRect(2*TAMPIXEL, Math.abs((6-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        g.fillRect(3*TAMPIXEL, Math.abs((5-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        g.fillRect(4*TAMPIXEL, Math.abs((4-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        g.fillRect(3*TAMPIXEL, Math.abs((3-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        g.fillRect(2*TAMPIXEL, Math.abs((2-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        g.fillRect(1*TAMPIXEL, Math.abs((3-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);*/
        
        //Rasterizando um polígono manualmente
        g.fillRect(3*TAMPIXEL, Math.abs((1-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(3);
        pontos.add(1);
        g.fillRect(2*TAMPIXEL, Math.abs((2-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(2);
        pontos.add(2);
        g.fillRect(1*TAMPIXEL, Math.abs((3-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(1);
        pontos.add(3);
        g.fillRect(0*TAMPIXEL, Math.abs((4-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(0);
        pontos.add(4);
        g.fillRect(1*TAMPIXEL, Math.abs((5-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(1);
        pontos.add(5);
        g.fillRect(2*TAMPIXEL, Math.abs((6-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(2);
        pontos.add(6);
        g.fillRect(3*TAMPIXEL, Math.abs((7-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(3);
        pontos.add(7);
        g.fillRect(4*TAMPIXEL, Math.abs((6-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(4);
        pontos.add(6);
        g.fillRect(5*TAMPIXEL, Math.abs((5-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(5);
        pontos.add(5);
        g.fillRect(6*TAMPIXEL, Math.abs((4-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(6);
        pontos.add(4);
        g.fillRect(5*TAMPIXEL, Math.abs((3-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(5);
        pontos.add(3);
        g.fillRect(4*TAMPIXEL, Math.abs((2-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        pontos.add(4);
        pontos.add(2);
        
        for(int i = 0; i < pontos.size(); i = i+2){
            lista_pontos.add(new Pontos(pontos.get(i), pontos.get(i+1)));
        }
        
        //System.out.println(pontos.size());
        /*
        int recuar_linha = 0;
        for(int i = 0; i<=(xFinal-xInicial); i++){
            avancar_linha++;
            System.out.println("av="+avancar_linha+",rec="+recuar_linha);
            g.fillRect((xInicial+avancar_linha)*TAMPIXEL, Math.abs((yFinal+recuar_linha-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
            recuar_linha--;
        }*/
        for(int i = 0; i < lista_pontos.size(); i++){
            System.out.println("X="+lista_pontos.get(i).x+", Y="+lista_pontos.get(i).y+"\n");
        }
        
        varredura_pts_criticos(g);
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
                (p_aux[0]-lista_pontos.get(i).x*1.0f)/(p_aux[1]-lista_pontos.get(i).y*1.0f)));
            }
            
            p_aux[0] = lista_pontos.get((i-1+lista_pontos.size())% lista_pontos.size()).x;
            p_aux[1] = lista_pontos.get((i-1+lista_pontos.size())% lista_pontos.size()).y;
            
            if(lista_pontos.get(i).y < p_aux[1]){
                criticos.add(new Pts_criticos(i, -1, lista_pontos.get(i).x, 
                (p_aux[0]-lista_pontos.get(i).x*1.0f)/(p_aux[1]-lista_pontos.get(i).y*1.0f)));
            }
        }
        
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

            Collections.sort(criticos_ativos);

            for(int i = 0; i < criticos_ativos.size(); i += 2){
                int x_start = Math.round(criticos_ativos.get(i).x_intersection);
                int x_end = Math.round(criticos_ativos.get(i+1).x_intersection);
                for(int x = x_start; x <= x_end; x++){
                    g.fillRect(x*TAMPIXEL1, Math.abs((y-qtde_pixels1)*TAMPIXEL1), TAMPIXEL1, TAMPIXEL1);
                }
            }
        }
    }
}

