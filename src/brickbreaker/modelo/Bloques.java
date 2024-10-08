package brickbreaker.modelo;

import java.awt.Color;
import java.awt.Rectangle;

public class Bloques {
    private int x, y;
    private int ancho, alto;
    private int dureza;
    private int velocidadX;
    private boolean destruido;
    private Rectangle rectangulo;
    private int anchoPanel;

    public Bloques(int x, int y, int ancho, int alto, int dureza, boolean destruido) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.dureza = dureza;
        this.velocidadX = 1;
        this.destruido = destruido;
        this.rectangulo = new Rectangle();
        this.anchoPanel = 700;
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
        x += velocidadX; // Actualiza la posición en X

        // Si el bloque sale por el borde derecho del panel, reaparece en el borde izquierdo
        if (x > anchoPanel) {
            x = -ancho; // Coloca el bloque justo fuera del panel por la izquierda
        }

        // Actualiza la posición del rectángulo
        rectangulo.setLocation(x, y);
    }
    
    public void moverIzquierda() {
        x -= velocidadX; // Actualiza la posición en X

        // Si el bloque sale por el borde derecho del panel, reaparece en el borde izquierdo
        if (x + ancho < 0) {
            x  = anchoPanel; // Coloca el bloque justo fuera del panel por la izquierda
        }

        // Actualiza la posición del rectángulo
        rectangulo.setLocation(x, y);
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
}
