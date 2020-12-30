package projetocg;

import java.awt.Color;
import java.awt.Graphics;

public class PontoMedio {
    int xCentro, yCentro, TAMANHOPIXEL, qtdePixels;
    Graphics graf;
    
    public void desenha8(int x, int y){
        graf.fillRect((x+xCentro)*TAMANHOPIXEL, Math.abs(((y+yCentro)-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
        graf.fillRect((y+xCentro)*TAMANHOPIXEL, Math.abs(((x+yCentro)-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
        graf.fillRect((y+xCentro)*TAMANHOPIXEL, Math.abs(((-x+yCentro)-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
        graf.fillRect((x+xCentro)*TAMANHOPIXEL, Math.abs(((-y+yCentro)-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
        graf.fillRect((-x+xCentro)*TAMANHOPIXEL, Math.abs(((-y+yCentro)-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
        graf.fillRect((-y+xCentro)*TAMANHOPIXEL, Math.abs(((-x+yCentro)-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
        graf.fillRect((-y+xCentro)*TAMANHOPIXEL, Math.abs(((x+yCentro)-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
        graf.fillRect((-x+xCentro)*TAMANHOPIXEL, Math.abs(((y+yCentro)-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
    }
    
    public void desenharCirculo(int xCentral, int yCentral, int r, int TAMPIXEL, int qtde_pixels, Graphics g){
        graf = g;
        xCentro = xCentral;
        yCentro = yCentral;
        qtdePixels = qtde_pixels;
        TAMANHOPIXEL = TAMPIXEL;
        
        int x = 0;
        int y = r;
        int e = -r; //Erro
        
        desenha8(x ,y);
        graf.setColor(Color.red);
        //Centro
        graf.fillRect(xCentro*TAMANHOPIXEL, Math.abs(yCentro-qtdePixels)*TAMANHOPIXEL, TAMANHOPIXEL, TAMANHOPIXEL);
        //Raio
        graf.drawLine(xCentro*TAMANHOPIXEL, Math.abs(yCentro-qtdePixels)*TAMANHOPIXEL, (yCentro+r)*TAMANHOPIXEL, Math.abs((yCentro+r)-qtdePixels)*TAMANHOPIXEL);
        graf.setColor(Color.black);
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
