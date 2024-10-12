package brickbreaker.modelo;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Barra {
    private int x, y;
    private int base, altura;
    private int velocidadx;
    private int anchoPanel, altoPanel;
    private Image imagen;

    public Barra(int x, int y, int base, int altura, int velocidad, String rutaImagen) {
        this.x = x;
        this.y = y;        
        this.base = base;
        this.altura = altura;
        this.velocidadx = velocidad;
        this.anchoPanel = 700;
        this.altoPanel = 600;
        this.imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
    }

    public void mover(int nuevaX) {
        if (nuevaX < 0) {
            x = 0;
        } else if (nuevaX + base > anchoPanel) {
            x = anchoPanel - base;
        } else {
            x = nuevaX;
        }
    }


    public void moverIzquierda() {
        if(x - velocidadx > 0){
            x = x - velocidadx;
        }else{
            x = 0;
        }
    }

    public void moverDerecha(){
        if(x + base + velocidadx < anchoPanel){
            x = x + velocidadx;
        }else{
            x = anchoPanel - base;
        }
    }

    public int getX(){
        return x;
    }


    public int getBase(){
        return base;
    }

    public int getAltura(){
        return altura;
    }

    public int getY() {
        return y;
    }
    
    public Image getImagen(){
        return imagen;
    }
    
    public void setBase(int nuevaBase){
        this.base = nuevaBase;
    }
    
}
