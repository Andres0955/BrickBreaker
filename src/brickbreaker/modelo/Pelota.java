package brickbreaker.modelo;

public class Pelota {
    private int x, y;
    private int radio;
    private int velocidadX, velocidadY;
    private int anchoPanel, altoPanel;

    public Pelota(int x, int y, int radio, int velocidadX, int velocidadY) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.anchoPanel = 700;
        this.altoPanel = 600;
    }

    public void mover(Barra barra){
        x += velocidadX;
        y += velocidadY;
        System.out.println("Pelota: X = " + x + ", Y = " + y);

        //Detectar bordes y cambian direccion
        if(x - radio < 0 || x + radio > anchoPanel){
            velocidadX = -velocidadX;
        }
        if(y - radio < 0 ){
            velocidadY = -velocidadY;
        }
    }

    public void rebotarVerticalemente(){
        velocidadY = -velocidadY;
    }

    public void moverPelotaAlCentro(){
        x = anchoPanel / 2;
        y = altoPanel / 2;
        velocidadY = 10;
        velocidadX = 10;
    }
    
    public void rebotar(int direccion){
        if(direccion == 1){
            velocidadX = -velocidadX;  
        }
        if(direccion == 2) {
            velocidadY = -velocidadY; 
        }
    }



    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getRadio(){
        return radio;
    }

    public int getAnchoPanel(){
        return anchoPanel;
    }

    public int getAltoPanel(){
        return altoPanel;
    }
    
    public int getVelocidadX(){
        return velocidadX;
    }
    
    public int getVelocidadY(){
        return velocidadY;
    }
}
