package projetocg;

import java.util.ArrayList;

public class Recorte {
    public void sutherland_hodgman(ArrayList<Pontos> poligono, int xmin, int xmax, int ymin, int ymax){
        ArrayList<Pontos> novo_poligono = new ArrayList<>();
        
        for(int i=0; i<poligono.size(); i++){
            Pontos p1 = poligono.get(i);
            Pontos p2 = poligono.get((i+1)%poligono.size());
            
            //Testando para xmin
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
            
            //Testando para xmax
            if(p1.x <= xmax){
                if(p2.x <= xmax){
                    novo_poligono.add(p2);
                }else{
                    novo_poligono.add(new Pontos(xmax, Math.round((xmin-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y)));
                }
            }else{
                if(p2.x <= xmax){
                    novo_poligono.add(new Pontos(xmax, Math.round((xmin-p1.x)*(p2.y-p1.y*1.f)/(p2.x*1.f-p1.x)+p1.y)));
                    novo_poligono.add(p2);
                }else{
                    //Não adiciona ponto
                }
            }
        }
    }
}
