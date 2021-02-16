package projetocg;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BresenhamFloat {
    ArrayList<PontosFloat> pontos = new ArrayList<>();
    boolean trocaxy = false, trocax = false, trocay = false;
    double e, m, m_reflexao;
    double x1, x2, y1, y2, x, y, aux;
    
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
    
    public void desenharLinha(double xInicial, double yInicial, double xFinal, double yFinal){
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
        pontos.add(new PontosFloat(x, y));
     
        while(x < x2){
            if(e > 0){
                y++;
                e--;
            }
            
            x = x + 1;
            e = e + m;
            
            pontos.add(new PontosFloat(x ,y));
        }
        
        reflexao_inversa();
    }
    
    public ArrayList<PontosFloat> iniciar_breserham(double xInicial, double xFinal, double yInicial, double yFinal, int TAMPIXEL, int qtde_pixels, Graphics2D g){
        pontos.clear();
        trocax = false;
        trocay = false;
        trocaxy = false;
        
        Graphics2D g2d = (Graphics2D)g.create();
            
        desenharLinha(xInicial, yInicial, xFinal, yFinal);
        
        for(int i=0; i<pontos.size(); i++){
            x = pontos.get(i).x;
            y = pontos.get(i).y;
            
            //g2d.fillRect(x*TAMPIXEL, Math.abs((y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
            //g.
        }
        
        return pontos;
    }
}
