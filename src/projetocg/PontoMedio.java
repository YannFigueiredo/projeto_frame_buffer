package projetocg;

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
        //g.fillRect(0*TAMPIXEL, Math.abs((10-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
    }
    
    public void desenharCirculo(int xCentral, int yCentral, int r, int TAMPIXEL, int qtde_pixels, Graphics g){
        graf = g;
        xCentro = xCentral;
        yCentro = yCentral;
        qtdePixels = qtde_pixels;
        TAMANHOPIXEL = TAMPIXEL;
        
        //graf.fillRect(0*TAMANHOPIXEL, Math.abs((7-qtdePixels)*TAMANHOPIXEL), TAMANHOPIXEL, TAMANHOPIXEL);
        //g.fillRect(0*TAMPIXEL, Math.abs((7-qtde_pixels)*TAMPIXEL), TAMPIXEL, TAMPIXEL);
        
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
