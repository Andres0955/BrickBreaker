package brickbreaker.modelo;

import java.awt.Color;

public class Bloques {
    private int x, y;
    private int ancho, alto;
    private int dureza;
    private boolean destruido;

    public Bloques(int x, int y, int ancho, int alto, int dureza, boolean destruido) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.dureza = dureza;
        this.destruido = destruido;
    }

    public void golpear() {
        if (dureza > 0) {
            dureza--;
        }
        if (dureza == 0) {
            destruido = true;
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
}
