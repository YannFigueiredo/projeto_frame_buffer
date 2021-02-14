package projetocg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Transformacao {
    Bresenham bresenham = new Bresenham();
    
    public void translacao(ArrayList<Pontos> poligono, int xT, int yT, int tam, int qtde, Graphics g){
        for(int i = 0;  i < poligono.size(); i++){
            Pontos p1 = poligono.get(i);
            Pontos p2 = poligono.get((i+1)%poligono.size());
            
            //Desenha o polígono deslocado
            g.setColor(Color.red);
            bresenham.iniciar_breserham(p1.x+xT, p2.x+xT, p1.y+yT, p2.y+yT, tam, qtde, g);
        }
    }
    
    public void escala(ArrayList<Pontos> poligono, float xE, float yE, int tam, int qtde, Graphics g){
        for(int i = 0;  i < poligono.size(); i++){
            Pontos p1 = poligono.get(i);
            Pontos p2 = poligono.get((i+1)%poligono.size());
            
            int x1 = Math.round(p1.x*xE);
            int y1 = Math.round(p1.y*yE);
            int x2 = Math.round(p2.x*xE);
            int y2 = Math.round(p2.y*yE);
            
            //Desenha o polígono com escala alterada
            g.setColor(Color.red);
            System.out.println("Pintado pontos ("+x1+", "+y1+")("+x2+", "+y2+")");
            bresenham.iniciar_breserham(x1, x2, y1, y2, tam, qtde, g);
        }
    }
    
    public void rotacao(ArrayList<Pontos> poligono, double ang, int tam, int qtde, Graphics g){
        for(int i = 0;  i < poligono.size(); i++){
            Pontos p1 = poligono.get(i);
            Pontos p2 = poligono.get((i+1)%poligono.size());
            
            int x1 = (int) Math.round(p1.x*Math.cos(ang)-p1.y*Math.sin(ang));
            int y1 = (int) Math.round(p1.x*Math.sin(ang)+p1.y*Math.cos(ang));
            int x2 = (int) Math.round(p2.x*Math.cos(ang)-p2.y*Math.sin(ang));
            int y2 = (int) Math.round(p2.x*Math.sin(ang)+p2.y*Math.cos(ang));
            
            System.out.println("Pintado pontos antes ("+x1+", "+y1+")("+x2+", "+y2+")");
            
            if(x1 < 0){
                x1 = x1*-1;
            }
            if(y1 < 0){
                y1 = y1*-1;
            }
            if(x2 < 0){
                x2 = x2*-1;
            }
            if(y2 < 0){
                y2 = y2*-1;
            }
            
            //Desenha o polígono rotacionado
            g.setColor(Color.red);
            System.out.println("Pintado pontos ("+x1+", "+y1+")("+x2+", "+y2+")");
            bresenham.iniciar_breserham(x1, x2, y1, y2, tam, qtde, g);
        }
    }
}
