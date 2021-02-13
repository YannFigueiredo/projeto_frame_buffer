package projetocg;

import java.awt.Graphics;
import java.util.ArrayList;

public class Recorte {
    public ArrayList<Pontos> sutherland_hodgman(ArrayList<Pontos> poligono, int xmin, int xmax, int ymin, int ymax){
        System.out.println("Polígono\n");
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
        System.out.println("\nNovo polígono (após verificar xmin)\n");
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
        
        //desenharPoligonoRecortado(novo_poligono3, g);
        return novo_poligono3;
    }
    
    public boolean verificaX(int x,  int xmin, int xmax){
        if(x >= xmin && x <= xmax){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean verificaY(int y, int ymin, int ymax){
        if(y >= ymin && y <= ymax){
            return true;
        }else{
            return false;
        }
    }
    
    public void cohen_sutherland(Pontos p1, Pontos p2, int xmin, int xmax, int ymin, int ymax, int tam, int qtde, Graphics g){
        Bresenham bresenham = new Bresenham();
        if(verificaX(p1.x, xmin, xmax) == true && verificaX(p2.x, xmin, xmax) == true && verificaY(p1.y, ymin, ymax) == true && verificaY(p2.y, ymin, ymax) == true){
            bresenham.iniciar_breserham(p1.x, p2.x, p1.y, p2.y, tam, qtde, g);
        }else if(verificaX(p1.x, xmin, xmax) == false && verificaX(p2.x, xmin, xmax) == false && verificaY(p1.y, ymin, ymax) == false && verificaY(p2.y, ymin, ymax) == false){
            //Os pontos estão todos fora da janela de recorte
        }else{
            //Verifica se os pontos estão fora quanto a xmin e xmax, senão estão fora quanto a ymin e ymax
            if(verificaX(p1.x, xmin, xmax) == false || verificaX(p2.x, xmin, xmax) == false){
                if(p1.x < xmin){
                    Pontos pi = new Pontos(xmin, Math.round((xmin-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y));
                    cohen_sutherland(pi, p2, xmin, xmax, ymin, ymax, tam, qtde, g);
                }else if(p1.x > xmax){
                    Pontos pi = new Pontos(xmax, Math.round((xmax-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y));
                    cohen_sutherland(pi, p2, xmin, xmax, ymin, ymax, tam, qtde, g);
                }else if(p2.x < xmin){
                    Pontos pi = new Pontos(xmin, Math.round((xmin-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y));
                    cohen_sutherland(p1, pi, xmin, xmax, ymin, ymax, tam, qtde, g);
                }else if(p2.x > xmax){
                    Pontos pi = new Pontos(xmax, Math.round((xmax-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y));
                    cohen_sutherland(p1, pi, xmin, xmax, ymin, ymax, tam, qtde, g);
                }
            }else{
                if(p1.y < ymin){
                    Pontos pi = new Pontos(Math.round((ymin-p1.y)*(p2.x-p1.x*1.f)/(p2.y*1.f-p1.y)+p1.x), ymin);
                    cohen_sutherland(pi, p2, xmin, xmax, ymin, ymax, tam, qtde, g);
                }else if(p1.y > ymax){
                    Pontos pi = new Pontos(Math.round((ymax-p1.y)*(p2.x-p1.x*1.f)/(p2.y*1.f-p1.y)+p1.x), ymax);
                    cohen_sutherland(pi, p2, xmin, xmax, ymin, ymax, tam, qtde, g);
                }else if(p2.y < ymin){
                    Pontos pi = new Pontos(Math.round((ymin-p1.y)*(p2.x-p1.x*1.f)/(p2.y*1.f-p1.y)+p1.x), ymin);
                    cohen_sutherland(p1, pi, xmin, xmax, ymin, ymax, tam, qtde, g);
                }else if(p2.y > ymax){
                    Pontos pi = new Pontos(Math.round((ymax-p1.y)*(p2.x-p1.x*1.f)/(p2.y*1.f-p1.y)+p1.x), ymax);
                    cohen_sutherland(p1, pi, xmin, xmax, ymin, ymax, tam, qtde, g);
                }
            }
        }
    }
}
