package brickbreaker.controlador;

import brickbreaker.vista.*;
import brickbreaker.modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Control {
    private Pelota pelota;
    private JpJuego jpJuego;
    private Barra barra;
    private Bloques[] bloques;
    private ArrayList<Pelota> vidasPelotas;
    private static javax.swing.Timer temporizador;
    private Random random;
    private boolean nivel1Completo;
    private int vidas;

    public Control(Pelota pelota, JpJuego jpJuego, Barra barra, JFrame marco) {
        this.pelota = pelota;
        this.jpJuego = jpJuego;
        this.barra = barra;
        this.bloques = new Bloques[32];
        this.random = new Random();
        this.nivel1Completo = false;
        this.vidas = 3;
        this.vidasPelotas = new ArrayList<>();
        crearBloques();
        
        if (jpJuego != null) {
            jpJuego.setBloques(bloques);
        }
        

        temporizador = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pelota.mover(barra);
                verificarColision();
                detectarbordeInferior();
                colisionConBloque();
                bloquesDestruidos();
                bloquesNoDestuidos();
                jpJuego.actualizar();
                
            }
        });

        marco.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                    barra.moverIzquierda();
                } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                    barra.moverDerecha();
                }
                jpJuego.actualizar();

            }
        });

        marco.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                barra.mover(e.getX());
            }
        });
        
        marco.setFocusable(true);
        marco.requestFocusInWindow();


    }
    
    public void crearBloques(){
        
        int ejeX = 50;
        int ejeY = 100;
        for (int i = 1; i <= 32; i++) {
            bloques[i - 1] = new Bloques(ejeX, ejeY, 70, 30, random.nextInt(3), false);
            ejeX = ejeX + 70 + 2;
            if (i % 8 == 0){
                ejeY = ejeY + 30 + 2;
                ejeX = 50;
            }
        }
        
        jpJuego.setBloques(bloques);
        cantidadVidas();
    }
    
    
    
    private void colisionConBloque() {
        for (int i = 0; i < 32; i++) {
            if (!bloques[i].getDestruido() &&
                    pelota.getX() + pelota.getRadio() > bloques[i].getX() &&
                    pelota.getX() < (bloques[i].getX() + bloques[i].getAncho()) &&
                    pelota.getY() + pelota.getRadio() > bloques[i].getY() &&
                    pelota.getY() < bloques[i].getY() + bloques[i].getAlto()
            ) {
                //points = points + bloques[i].getColorValue();
                //music.play("breakbrick.wav");

                // Pelota se mueve al sureste y golpea el borde izquierdo o el borde superior del bloque
                if (pelota.getVelocidadX() > 0 && pelota.getVelocidadY() > 0) {
                    if (pelota.getX() + pelota.getRadio() > bloques[i].getX() && pelota.getY() + pelota.getRadio() > bloques[i].getY() + bloques[i].getAlto()) {
                        pelota.rebotar(1);
                        bloques[i] = new Bloques(0, 0, 0, 0, 0, true);
                        break;
                    } else if ((pelota.getY() + pelota.getRadio()) > bloques[i].getY()) {
                        pelota.rebotar(2);
                        bloques[i] = new Bloques(0, 0, 0, 0, 0, true);
                        break;
                    }
                }
                // Pelota se mueve al suroeste y golpea el borde derecho o el borde superior del bloque
                else if (pelota.getVelocidadX() < 0 && pelota.getVelocidadY() > 0) {
                    if (pelota.getX() < (bloques[i].getX() + bloques[i].getAncho()) && pelota.getY() + pelota.getRadio() > bloques[i].getY() + bloques[i].getAlto()) {
                        pelota.rebotar(1);
                        bloques[i] = new Bloques(0, 0, 0, 0, 0, true);
                        break;
                    } else if ((pelota.getY() + pelota.getRadio()) > bloques[i].getY()) {
                        pelota.rebotar(2);
                        bloques[i] = new Bloques(0, 0, 0, 0, 0, true);
                        break;
                    }
                }
                // Pelota se mueve al noreste y golpea el borde izquierdo del bloque
                else if (pelota.getVelocidadX() > 0 && pelota.getVelocidadY() < 0) {
                    if ((pelota.getX() + pelota.getRadio()) > bloques[i].getX() && pelota.getY() + pelota.getRadio() < bloques[i].getY() + bloques[i].getAlto()) {
                        pelota.rebotar(1);
                        bloques[i] = new Bloques(0, 0, 0, 0, 0, true);
                        break;
                    } else if (pelota.getY() < (bloques[i].getY() + bloques[i].getAlto())) {
                        pelota.rebotar(2);
                        bloques[i] = new Bloques(0, 0, 0, 0, 0, true);
                        break;
                    }
                }
                // Pelota se mueve al noroeste y golpea el borde derecho del bloque
                else if (pelota.getVelocidadX() < 0 && pelota.getVelocidadY() < 0) {
                    if (pelota.getX() < (bloques[i].getX() + bloques[i].getAncho()) && pelota.getY() + pelota.getRadio() < bloques[i].getY() + bloques[i].getAlto()) {
                        pelota.rebotar(1);
                        bloques[i] = new Bloques(0, 0, 0, 0, 0, true);
                        break;
                    } else if (pelota.getY() < (bloques[i].getY() + bloques[i].getAlto())) {
                        pelota.rebotar(2);
                        bloques[i] = new Bloques(0, 0, 0, 0, 0, true);
                        break;
                    }
                }
            }
        }
    }
    
    private void bloquesDestruidos(){
        
        for (int i = 0; i < 32; i++) {
            if (bloques[i].getDestruido()){
                nivel1Completo = true;
            } else {
                nivel1Completo = false;
                return;
            }
        }
        if (nivel1Completo){
            detener();
            jpJuego.setNivel1Completo(true);
        }
    }
    
    private void bloquesNoDestuidos(){
        if(vidas == 0){
            detener();
            jpJuego.setPerder(true);
        }
    }


    private void verificarColision(){
        if(pelota.getY() + pelota.getRadio() >= barra.getY() && pelota.getY() + pelota.getRadio() <= barra.getY() + barra.getAltura() &&
                pelota.getX() + pelota.getRadio() >= barra.getX() && pelota.getX() - pelota.getRadio() <= barra.getX() + barra.getBase()){
            pelota.rebotarVerticalemente();
        }
    }
    
    

    private void detectarbordeInferior(){
        if(pelota.getY() + pelota.getRadio() > pelota.getAltoPanel()){
            this.vidas--;
            if (!vidasPelotas.isEmpty()){
                vidasPelotas.remove(vidasPelotas.size() - 1);
            }
            pelota.moverPelotaAlCentro();
        }
    }
    
    private void cantidadVidas(){
        vidasPelotas.clear();
        int ubicacionEnX = 90;
        for (int i = 0; i<vidas; i++){
            vidasPelotas.add(new Pelota(ubicacionEnX, 18, 7, 0, 0));
            ubicacionEnX = ubicacionEnX + 20;
        }
        
        jpJuego.setVidasPelotas(vidasPelotas);
    }
    
    
    public void reiniciarJuego() {
        crearBloques();
        this.vidas = 3;
        cantidadVidas();
        pelota.moverPelotaAlCentro();
        this.nivel1Completo = false;
        jpJuego.setPerder(false);
        jpJuego.setNivel1Completo(false);  
        jpJuego.actualizar();
        reiniciar();
    }

    public void iniciar() {
        temporizador.start();
    }

    public void detener() {
        temporizador.stop();
    }
    
    public void reiniciar(){
        temporizador.restart();
    }
    
}
