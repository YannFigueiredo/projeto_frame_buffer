package projetocg;

import java.awt.Graphics;

public class PontoMedio {
    int xCentral, yCentral, TAMPIXEL;
    Graphics g;
    
    public void desenha8(int x, int y){
        g.fillRect((x+xCentral)*TAMPIXEL, (y+yCentral)*TAMPIXEL, TAMPIXEL, TAMPIXEL);
        g.fillRect((y+xCentral)*TAMPIXEL, (x+yCentral)*TAMPIXEL, TAMPIXEL, TAMPIXEL);
        g.fillRect((y+xCentral)*TAMPIXEL, (-x+yCentral)*TAMPIXEL, TAMPIXEL, TAMPIXEL);
        g.fillRect((x+xCentral)*TAMPIXEL, (-y+yCentral)*TAMPIXEL, TAMPIXEL, TAMPIXEL);
        g.fillRect((-x+xCentral)*TAMPIXEL, (-y+yCentral)*TAMPIXEL, TAMPIXEL, TAMPIXEL);
        g.fillRect((-y+xCentral)*TAMPIXEL, (-x+yCentral)*TAMPIXEL, TAMPIXEL, TAMPIXEL);
        g.fillRect((-y+xCentral)*TAMPIXEL, (x+yCentral)*TAMPIXEL, TAMPIXEL, TAMPIXEL);
        g.fillRect((-x+xCentral)*TAMPIXEL, (y+yCentral)*TAMPIXEL, TAMPIXEL, TAMPIXEL);
    }
    
    public void desenharCirculo(int xCentral, int yCentral, int r, int TAMPIXEL, Graphics g){
        g = g;
        xCentral = xCentral;
        yCentral = yCentral;
        TAMPIXEL = TAMPIXEL;
        
        int x = 0;
        int y = r;
        int e = -r; //Erro
        
        desenha8(x ,y);
        while(x<=y){
            e = e+(2*x)+1;
            x++;
            if(e>=0){
                e = e+2-(2*y);
                y--;
            }
            desenha8(x, y);
        }
    }
}
