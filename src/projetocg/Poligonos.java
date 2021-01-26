package projetocg;

import java.awt.Graphics;
import java.util.ArrayList;


public class Poligonos {
    ArrayList<Integer> pontos = new ArrayList<>();
    
    static class Pts_criticos{
        int index, dir;
        float x_intersection, inv_slope;
    }
    
    public void desenharLosango(int xInicial, int xFinal, int yInicial, int yFinal, int TAMPIXEL, int qtde_pixels, Graphics g){
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
        
        /*
        int recuar_linha = 0;
        for(int i = 0; i<=(xFinal-xInicial); i++){
            avancar_linha++;
            System.out.println("av="+avancar_linha+",rec="+recuar_linha);
            g.fillRect((xInicial+avancar_linha)*TAMPIXEL, Math.abs((yFinal+recuar_linha-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
            recuar_linha--;
        }*/
        
    }
    
    public void varredura_pts_criticos(){
        /*int y_min = Integer.MAX_VALUE, y_max = Integer.MIN_VALUE;
        ArrayList<Pts_criticos> criticos = new ArrayList<>();
        
        for(int i = 0; i < pontos.length; i++){
            if(pontos[i].y < y_min){
                y_min = pontos[i].y;
            }else if(pontos[i].y > y_max){
                y_max = pontos[i].y;
            }
            
            Point p_aux = pontos[(i+1)% pontos.length];
            if(pontos[i].y < p_aux.y){
                criticos.add(new Pts_criticos(i, pontos[i].x))
            }
        }*/
    }
}
