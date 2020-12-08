package projetocg;

import java.util.ArrayList;
import java.util.Arrays; 

public class Bresenham {
    public ArrayList<ArrayList<Integer>> desenharLinha(int xInicial, int yInicial, int xFinal, int yFinal){
        ArrayList<ArrayList<Integer>> pontos = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> ponto = new ArrayList<Integer>();
        
        float m = (yFinal - yInicial)/(xFinal - xInicial);
        int x = xInicial, y = yInicial;
        float e = (m - 1)/2;
        
        //Adiciona ponto a lista de pontos
        ponto.add(x);
        ponto.add(y);
        pontos.add(ponto);
        ponto.remove(0);
        ponto.remove(1);
        
        while(x < xFinal){
            if(e >= 0){
                y = y + 1;
                e = e - 1;
            }
            x = x + 1;
            e = e + m;
            
            ponto.add(x);
            ponto.add(y);
            pontos.add(ponto);
            ponto.remove(0);
            ponto.remove(1);
        }
        
        return pontos;
    }
}
