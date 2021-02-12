package projetocg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Recorte {
    int TAMPIXEL, qtde_pixels;
    
    public void sutherland_hodgman(ArrayList<Pontos> poligono, int xmin, int xmax, int ymin, int ymax, int tam,  int qtde, Graphics g){
        TAMPIXEL = tam;
        qtde_pixels = qtde;
        
        System.out.println("Polígono\n");
        //Construir um poligono nos moldes ((p1,p2)(p1,p2)(p1,p2))
        for(int i=0; i<poligono.size(); i++){
            System.out.println("x: "+poligono.get(i).x+", y: "+poligono.get(i).y);
        }
        
        //Verificação do xmin
        ArrayList<Pontos> novo_poligono = new ArrayList<>();
        for(int i=0; i<poligono.size(); i++){
            Pontos p1 = poligono.get(i);
            Pontos p2 = poligono.get((i+1)%poligono.size());
            
            if(p1.x >= xmin){
                if(p2.x >= xmin){
                    novo_poligono.add(p2);
                }else{
                    novo_poligono.add(new Pontos(xmin, Math.round((xmin-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y)));
                }
            }else{
                if(p2.x >= xmin){
                    novo_poligono.add(new Pontos(xmin, Math.round((xmin-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y)));
                    novo_poligono.add(p2);
                }else{
                    //Não adiciona ponto
                }
            }
        }
        System.out.println("Novo polígono (após verificar xmin)\n");
        for(int i=0; i<novo_poligono.size(); i++){
            System.out.println("x: "+novo_poligono.get(i).x+", y: "+novo_poligono.get(i).y);
        }
        
        //Verificação do xmax
        ArrayList<Pontos> novo_poligono1 = new ArrayList<>();
        for(int i=0; i<novo_poligono.size(); i++){
            Pontos p1 = novo_poligono.get(i);
            Pontos p2 = novo_poligono.get((i+1)%novo_poligono.size());
            
            if(p1.x <= xmax){
                if(p2.x <= xmax){
                    novo_poligono1.add(p2);
                }else{
                    novo_poligono1.add(new Pontos(xmax, Math.round((xmax-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y)));
                }
            }else{
                if(p2.x <= xmax){
                    novo_poligono1.add(new Pontos(xmax, Math.round((xmax-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y)));
                    novo_poligono1.add(p2);
                }else{
                    //Não adiciona ponto
                }
            }
        }
        System.out.println("\nNovo polígono (após verificar xmax)\n");
        for(int i=0; i<novo_poligono1.size(); i++){
            System.out.println("x: "+novo_poligono1.get(i).x+", y: "+novo_poligono1.get(i).y);
        }
        
        //Verificação do ymin
        ArrayList<Pontos> novo_poligono2 = new ArrayList<>();
        for(int i=0; i<novo_poligono1.size(); i++){
            Pontos p1 = novo_poligono1.get(i);
            Pontos p2 = novo_poligono1.get((i+1)%novo_poligono1.size());
            
            if(p1.y >= ymin){
                if(p2.y >= ymin){
                    novo_poligono2.add(p2);
                }else{
                    novo_poligono2.add(new Pontos(Math.round((ymin-p1.y)*(p2.x-p1.x*1.f)/(p2.y*1.f-p1.y)+p1.x), ymin));
                }
            }else{
                if(p2.y >= ymin){
                    novo_poligono2.add(new Pontos(Math.round((ymin-p1.y)*(p2.x-p1.x*1.f)/(p2.y*1.f-p1.y)+p1.x), ymin));
                    novo_poligono2.add(p2);
                }else{
                    //Não adiciona ponto
                }
            }
        }
        System.out.println("\nNovo polígono (após verificar ymin)\n");
        for(int i=0; i<novo_poligono2.size(); i++){
            System.out.println("x: "+novo_poligono2.get(i).x+", y: "+novo_poligono2.get(i).y);
        }
        
        //Verificação do ymax
        ArrayList<Pontos> novo_poligono3 = new ArrayList<>();
        for(int i=0; i<novo_poligono2.size(); i++){
            Pontos p1 = novo_poligono2.get(i);
            Pontos p2 = novo_poligono2.get((i+1)%novo_poligono2.size());
            
            if(p1.y <= ymax){
                if(p2.y <= ymax){
                    novo_poligono3.add(p2);
                }else{
                    novo_poligono3.add(new Pontos(Math.round((ymax-p1.y)*(p2.x-p1.x*1.f)/(p2.y*1.f-p1.y)+p1.x), ymax));
                }
            }else{
                if(p2.y <= ymax){
                    novo_poligono3.add(new Pontos(Math.round((ymax-p1.y)*(p2.x-p1.x*1.f)/(p2.y*1.f-p1.y)+p1.x), ymax));
                    novo_poligono3.add(p2);
                }else{
                    //Não adiciona ponto
                }
            }
        }
        System.out.println("\nNovo polígono (após verificar ymax)\n");
        for(int i=0; i<novo_poligono3.size(); i++){
            System.out.println("x: "+novo_poligono3.get(i).x+", y: "+novo_poligono3.get(i).y);
        }
        
        desenharPoligonoRecortado(novo_poligono3, g);
    }

    private void desenharPoligonoRecortado(ArrayList<Pontos> novo_poligono, Graphics g) {
        Bresenham bresenham = new Bresenham();
        ArrayList<Pontos> poligono_desenhado = new ArrayList<>();
        ArrayList<Pontos> poligono_adaptado = new ArrayList<>(); //Poligono com arestas nos  moldes do bresenham
        //g.setColor(Color.blue);
        
        //Construir um poligono nos moldes ((p1,p2)(p1,p2)(p1,p2))
        for(int i=0; i<novo_poligono.size(); i++){
            Pontos p1 = novo_poligono.get(i);
            Pontos p2 = novo_poligono.get((i+1)%novo_poligono.size());
            poligono_adaptado.add(p1);
            poligono_adaptado.add(p2);
        }
        
        System.out.println("Novo polígono adaptado\n");
        for(int i=0; i<poligono_adaptado.size(); i++){
            System.out.println("x: "+poligono_adaptado.get(i).x+", y: "+poligono_adaptado.get(i).y);
        }
        
        //System.out.println("\nPontos\n");
        for(int i=0; i<poligono_adaptado.size(); i=i+2){
            int x1 = poligono_adaptado.get(i).x;
            int y1 = poligono_adaptado.get(i).y;
            int x2 = poligono_adaptado.get(i+1).x;
            int y2 = poligono_adaptado.get(i+1).y;
            //System.out.println("x1: "+poligono_adaptado.get(i).x+", y1: "+poligono_adaptado.get(i).y+", x2: "+poligono_adaptado.get(i+1).x+", y2: "+poligono_adaptado.get(i+1).y);
            poligono_desenhado = bresenham.iniciar_breserham(x1, x2, y1, y2, TAMPIXEL, qtde_pixels, g);
        }
    }
}
