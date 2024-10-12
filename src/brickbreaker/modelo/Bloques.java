package brickbreaker.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Bloques {
    private int x, y;
    private int ancho, alto;
    private int dureza;
    private int identificador;
    private int velocidadX;
    private boolean destruido;
    private Rectangle rectangulo;
    private int anchoPanel;
    private Image imagen;

    public Bloques(int x, int y, int ancho, int alto, int dureza, int identificador, boolean destruido, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.dureza = dureza;
        this.identificador = identificador;
        this.velocidadX = 2;
        this.destruido = destruido;
        this.rectangulo = new Rectangle();
        this.anchoPanel = 700;
        this.imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
    }

    public void golpear() {
        if (dureza > 0) {
            dureza--;
        }
        if (dureza == 0) {
            destruido = true;
        }
    }
    
    public void moverDerecha() {
        x += velocidadX + 1.5;
        
        if (x > anchoPanel) {
            x = -ancho;
        }
        
        rectangulo.setLocation(x, y);
    }
    
    public void moverIzquierda() {
        x -= velocidadX;

        if (x + ancho < 0) {
            x  = anchoPanel;
        }

        rectangulo.setLocation(x, y);
    }
    
    public void reducirDureza(){
        this. dureza -= 1;
        if(identificador == 2){
            if(dureza == 1){
                this.imagen = new ImageIcon(getClass().getResource("/recursos/imagenes/Ladrillos/LadriRojo02.png")).getImage();
            }else if(dureza == 0){
                this.imagen = new ImageIcon(getClass().getResource("/recursos/imagenes/Ladrillos/LadriRojo03.png")).getImage();
            }
        }else if(identificador == 1){
            this.imagen = new ImageIcon(getClass().getResource("/recursos/imagenes/Ladrillos/LadriGris02.png")).getImage();
        }
    }

    public boolean estaDestruido() {
        return destruido;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    public boolean getDestruido(){
        return destruido;
    }
    
    public Image getImagen(){
        return imagen;
    }
    
    public int getDureza(){
        return dureza;
    }
    
    public int getIdentificador(){
        return identificador;
    }
}
