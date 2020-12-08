package projetocg;

import java.util.ArrayList;
import java.util.Arrays; 

public class Bresenham {
    public ArrayList<ArrayList<Integer>> desenharLinha(int xInicial, int yInicial, int xFinal, int yFinal){
        ArrayList<ArrayList<Integer>> pontos = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> ponto = new ArrayList<Integer>();
        
        ponto.add(0);
        ponto.add(31);
        
        pontos.add(ponto);
        
        ArrayList<Integer> ponto2 = new ArrayList<Integer>();
        
        ponto2.add(2);
        ponto2.add(31);
        
        pontos.add(ponto2);
        
        return pontos;
    }
}
