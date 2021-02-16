package projetocg;

import java.util.ArrayList;

public class Curva {
    //ArrayList<PontosFloat> controle = new ArrayList<>();
    int n;
    //double t;
    
    public PontosFloat pontos_bezier(double t, PontosFloat[] controle){
        ArrayList<PontosFloat> pts = new ArrayList<>();
        
        for(int i = 0; i <= n; i++){
            pts.add(controle[i]);
        }
        
        for(int r = 1; r <= n; r++){
            for(int i = 0; i <= n-r; i++){
                pts.get(i).x = ((1 - t)*pts.get(i).x + t*pts.get(i+1).x);
                pts.get(i).y = ((1 - t)*pts.get(i).y + t*pts.get(i+1).y);
            }
        }
        
        //System.out.println("pts bezier x: "+pts.get(0).x+", y: "+pts.get(0).y);
        return pts.get(0);
    }
    
    public ArrayList<PontosFloat> curva_bezier(int n1, int n_pts, ArrayList<PontosFloat> controle, double t1){
        PontosFloat[] pts_controle = new PontosFloat[controle.size()];
        PontosFloat[] pts_curva = new PontosFloat[n_pts-1];
        n = n1;
        double t = t1/n_pts;
        ArrayList<PontosFloat> curva = new ArrayList<>();
        //ArrayList<PontosFloat> pts_curva = new ArrayList<>();
        
        for(int i = 0; i < controle.size(); i++){
            pts_controle[i] = controle.get(i);
        }
        
        //System.out.println("controle x: "+controle.get(0).x+", y: "+controle.get(0).y);
        
        for(int i = 0; i < n_pts-1; i++){
            //System.out.println("ponto curva antes x: "+pontos_bezier(t, pts_controle).x+", y: "+pontos_bezier(t, pts_controle).y);
            PontosFloat ponto_curva = pontos_bezier(t, pts_controle);
            System.out.println("ponto curva x: "+ponto_curva.x+", y: "+ponto_curva.y);
            pts_curva[i] = ponto_curva;
            System.out.println("pontos curva x: "+pts_curva[i].x+", y: "+pts_curva[i].y);
            t = t + t;
        }
        pts_curva[0] = new PontosFloat(0,0);
        
        //curva.add(new PontosFloat(pts_controle[0].x, pts_controle[0].y));
        //for(int i = 0; i < pts_curva.size(); i++){
         //   curva.add(pts_curva.get(i));
        //}
        //System.out.println("controle x: "+controle.get(0).x+", y: "+controle.get(0).y);
        //curva.add(pts_controle[pts_controle.length-1]);
        
        //for(int i = 0; i < pts_curva.size(); i++){
            System.out.println("pts curva x: "+pts_curva[0].x+", y: "+pts_curva[0].y);
            System.out.println("pts curva x: "+pts_curva[1].x+", y: "+pts_curva[1].y);
        //}
        
        for(int i = 0; i < pts_controle.length; i++){
            System.out.println("\n\ncontrole x: "+pts_controle[i].x+", y: "+pts_controle[i].y);
        }
        
        /*switch(n){
            case 1:
                curva.add(controle.get(0));
                System.out.println("\nPontos na função bezir:\n\nx: "+curva.get(0).x+", y: "+curva.get(0).y);
                for(int i = 0; i < n_pts-1; i++){
                    pts_curva.add(pontos_bezier(controle));
                    System.out.println("x: "+pts_curva.get(i).x+", y: "+pts_curva.get(i).y);
                    //controle.get(0).setX(pts_curva.get(i).x);
                    //controle.get(0).setY(pts_curva.get(i).y);
                }
                for(int i = 0; i < pts_curva.size(); i++){
                    System.out.println("pts x: "+pts_curva.get(i).x+", y: "+pts_curva.get(i).y);
                }
                for(int i = 0; i < pts_curva.size(); i++){
                    curva.add(pts_curva.get(i));
                }
                curva.add(controle.get(1));
                System.out.println("x: "+curva.get(curva.size()-1).x+", y: "+curva.get(curva.size()-1).y);
        }*/
        
        return curva;
    }
}
