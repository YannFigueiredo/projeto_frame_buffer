package projetocg;

import java.util.ArrayList;
import java.util.Arrays; 

public class Bresenham {
    ArrayList<Integer> pontos = new ArrayList<Integer>();
    boolean trocaxy = false, trocax = false, trocay = false;
    double m, m_reflexao;
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
            
            m_reflexao = (double) (y2 - y1)/(x2 - x1);
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
            for(int i=1; i<pontos.size(); i=i+2){
                pontos.set(i, pontos.get(i)*-1);
            }
        }
        if(trocax == true){
            for(int i=0; i<pontos.size(); i=i+2){
                pontos.set(i, pontos.get(i)*-1);
            }
        }
        if(trocaxy == true){
            for(int i=0; i<pontos.size(); i=i+2){
                aux = pontos.get(i);
                pontos.set(i, pontos.get(i+1));
                pontos.set(i+1, aux);
            }
        }
    }
    
    public ArrayList<Integer> desenharLinha(int xInicial, int yInicial, int xFinal, int yFinal){
        double e = m - 0.5;
        m = (double)(yFinal - yInicial)/(xFinal - xInicial);
        x1 = xInicial;
        x2 = xFinal;
        y1 = yInicial;
        y2 = yFinal;
        
        reflexao();
        
        if(trocaxy == true)
            m = m_reflexao;
        
        x = x1;
        y = y1;
        
        //Adiciona ponto a lista de pontos
        pontos.add(x);
        pontos.add(y);
        
        while(x < x2){
            if(e >= 0){
                y = y + 1;
                e = e - 1;
            }
            x = x + 1;
            e = e + m;
            
            pontos.add(x);
            pontos.add(y);
        }
        
        reflexao_inversa();
        
        return pontos;
    }
}
