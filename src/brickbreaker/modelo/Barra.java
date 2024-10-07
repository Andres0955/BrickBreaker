package brickbreaker.modelo;

public class Barra {
    private int x, y;
    private int base, altura;
    private int velocidadx;
    private int anchoPanel, altoPanel;

    public Barra() {
        this.x = 100;
        this.base = 150;
        this.altura = 20;
        this.velocidadx = 25;
        this.anchoPanel = 700;
        this.altoPanel = 550;
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
        return (altoPanel + 30) - altura;
    }
}
