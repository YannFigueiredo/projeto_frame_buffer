package projetocg;

import java.util.ArrayList;
import java.awt.Graphics;

public class Bresenham {
    ArrayList<Pontos> pontos = new ArrayList<>();
    boolean trocaxy = false, trocax = false, trocay = false;
    double e, m, m_reflexao;
    int x1, x2, y1, y2, x, y, aux;
    
    public void reflexao(){
        m_reflexao = m;
        if(m_reflexao>1 || m_reflexao<-1){
            aux = x1;
            x1 = y1;
            y1 = aux;
            aux = x2;
            x2 = y2;
            y2 = aux;
            trocaxy = true;
        }
        if(x1>x2){
            x1 = (x1)*-1;
            x2 = (x2)*-1;
            trocax = true;
        }
        if(y1>y2){
            y1 = (y1)*-1;
            y2 = (y2)*-1;
            trocay = true;
        }
    }
    
    public void reflexao_inversa(){
        if(trocay == true){
            for(int i=1; i<pontos.size(); i++){    
                pontos.get(i).y = pontos.get(i).y*-1;
            }
        }
        if(trocax == true){
            for(int i=0; i<pontos.size(); i++){
                pontos.get(i).x = pontos.get(i).x*-1;
            }
        }
        if(trocaxy == true){
            for(int i=0; i<pontos.size(); i++){
                aux = pontos.get(i).x;
                pontos.get(i).x = pontos.get(i).y;
                pontos.get(i).y = aux;
            }
        }
    }
    
    public void desenharLinha(int xInicial, int yInicial, int xFinal, int yFinal){
        x1 = xInicial;
        x2 = xFinal;
        y1 = yInicial;
        y2 = yFinal;
        
        m = (double)(y2 - y1)/(x2 - x1);
        e = m - 0.5;
        
        //System.out.println("Pré-reflexão\nm = "+m+", e = "+e);
        //System.out.println("x: "+x1+", y: "+y1);
        //System.out.println("x: "+x2+", y: "+y2);
        
        reflexao();
        
        x = x1;
        y = y1;
        
        if(trocaxy == true || trocax == true || trocay == true){
            m = (double)(y2 - y1)/(x2 - x1);
            e = m - 0.5;
        }
        
        //System.out.println("\nPós-reflexão\nm = "+m+", e = "+e);
        //System.out.println("x: "+x1+", y: "+y1);
        //System.out.println("x: "+x2+", y: "+y2);
        
        //Adiciona ponto a lista de pontos
        pontos.add(new Pontos(x, y));
     
        while(x < x2){
            if(e > 0){
                y++;
                e--;
            }
            
            x = x + 1;
            e = e + m;
            
            pontos.add(new Pontos(x ,y));
        }
        
        reflexao_inversa();
    }
    
    public ArrayList<Pontos> iniciar_breserham(int xInicial, int xFinal, int yInicial, int yFinal, int TAMPIXEL, int qtde_pixels, Graphics g){
        pontos.clear();
        trocax = false;
        trocay = false;
        trocaxy = false;
            
        desenharLinha(xInicial, yInicial, xFinal, yFinal);
        
        for(int i=0; i<pontos.size(); i++){
            x = pontos.get(i).x;
            y = pontos.get(i).y;
            
            g.fillRect(x*TAMPIXEL, Math.abs((y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        }
        
        return pontos;
    }
}
