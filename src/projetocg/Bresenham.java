package projetocg;

import java.util.ArrayList;
import java.util.Arrays; 

public class Bresenham {
    public ArrayList<Integer> desenharLinha(int xInicial, int yInicial, int xFinal, int yFinal){
        ArrayList<Integer> pontos = new ArrayList<Integer>();
        
        double m = (double)(yFinal - yInicial)/(xFinal - xInicial);
        int x = xInicial, y = yInicial;
        double e = m - 0.5;
        
        //Adiciona ponto a lista de pontos
        pontos.add(x);
        pontos.add(y);
        
        while(x < xFinal){
            if(e >= 0){
                y = y + 1;
                e = e - 1;
            }
            x = x + 1;
            e = e + m;
            
            pontos.add(x);
            pontos.add(y);
        }
        System.out.println(pontos);
        return pontos;
    }
}
