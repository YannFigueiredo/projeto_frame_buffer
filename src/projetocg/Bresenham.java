package projetocg;

import java.awt.Color;
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
        //g.fillRect(xInicial*TAMPIXEL, Math.abs((yInicial-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        //g.fillRect(xFinal*TAMPIXEL, Math.abs((yFinal-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
            
        //g.setColor(Color.RED);
            
        //g.drawLine(xInicial*TAMPIXEL, Math.abs((yInicial-qtde_pixels)*TAMPIXEL), xFinal*TAMPIXEL, Math.abs((yFinal-qtde_pixels)*TAMPIXEL));
            
        //g.setColor(Color.BLACK);
        
        System.out.println("\nBresenham\n");
        System.out.println("x1: "+xInicial+", y1: "+yInicial+", x2: "+xFinal+", y2: "+yFinal);
            
        desenharLinha(xInicial, yInicial, xFinal, yFinal);
        
        for(int i=0; i<pontos.size(); i++){
            x = pontos.get(i).x;
            y = pontos.get(i).y;
            
            g.fillRect(x*TAMPIXEL, Math.abs((y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        }
        
        return pontos;
    }
    
    public void iniciar_breserham_recorte(Pontos p1, Pontos p2, int TAMPIXEL, int qtde_pixels, Graphics g, int xmin, int xmax, int ymin, int ymax){
        //g.fillRect(p1.x*TAMPIXEL, Math.abs((p1.y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        //g.fillRect(p2.x*TAMPIXEL, Math.abs((p2.y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
            
        g.setColor(Color.RED);
            
        g.drawLine(p1.x*TAMPIXEL, Math.abs((p1.y-qtde_pixels)*TAMPIXEL), p2.x*TAMPIXEL, Math.abs((p2.y-qtde_pixels)*TAMPIXEL));
            
        g.setColor(Color.BLACK);
            
        desenharLinha(p1.x, p1.y, p2.x, p2.y);
            
        for(int i=0; i<pontos.size(); i++){
            x = pontos.get(i).x;
            y = pontos.get(i).y;
            
            if(x >= xmin && x <= xmax && y >= ymin && y <= ymax){
                g.fillRect(x*TAMPIXEL, Math.abs((y-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
            }
        }
    }
}
