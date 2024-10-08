package brickbreaker.controlador;

import brickbreaker.vista.*;
import brickbreaker.modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Control {
    private Pelota pelota;
    private static JpJuego jpJuego;
    private Barra barra;
    private Bloques[] bloques;
    private ArrayList<Pelota> vidasPelotas;
    private static javax.swing.Timer temporizador;
    private Random random;
    private boolean nivel1Completo;
    private int vidas;
    private static JPanel panelActual;
    private static JFrame frame;
    private int nivelSeleccionado;
    

    public Control(JFrame marco) {
        this.barra = new Barra();
        this.pelota = new Pelota(350, 290, 25, 10, 10);
        this.jpJuego = new JpJuego(pelota, barra, this);
        this.bloques = new Bloques[32];
        this.random = new Random();
        this.nivel1Completo = false;
        this.vidas = 3;
        this.vidasPelotas = new ArrayList<>();
        this.frame = marco;
        
        
        crearBloques();
        JpNiveles.setControl(this);
        jpJuego.setBloques(bloques);
        
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
                jpJuego.moverBloquesHorizontales();
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
                jpJuego.actualizar();
                
            }
            
        });
        
        marco.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    iniciar();
                    System.out.println("Clic izquierdo: El juego ha iniciado.");
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    detener();

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });

       
        
        marco.setFocusable(true);
        marco.requestFocusInWindow();
    }
    
    public void crearBloques(){
        
        if(nivelSeleccionado == 1 || nivelSeleccionado == 3){
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
        }else if(nivelSeleccionado == 2){
           int centroX1 = 150; // Coordenada X del centro del primer círculo
           int centroX2 = 450; // Coordenada X del centro del segundo círculo
           int centroY = 200;  // Coordenada Y común para ambos círculos
           int radio = 100;    // Radio común para ambos círculos

           // Crear los 32 bloques distribuidos en dos círculos de 16 bloques cada uno
           for (int i = 0; i < 32; i++) {
               // Determinar en qué círculo se debe colocar el bloque
               int centroX = (i < 16) ? centroX1 : centroX2;

               // Calcular el ángulo para cada bloque en función de su índice
               double angulo = 2 * Math.PI * (i % 16) / 16;

               // Calcular la posición de cada bloque usando coordenadas polares
               int ejeX = (int) (centroX + radio * Math.cos(angulo));
               int ejeY = (int) (centroY + radio * Math.sin(angulo));

               // Crear y almacenar el bloque en la posición calculada
               bloques[i] = new Bloques(ejeX, ejeY, 70, 30, random.nextInt(3), false);
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
    }
    
    public static void cambiarPanel(JPanel newPanel) {
        if (panelActual != null) {
            frame.remove(panelActual); // Elimina el panel actual
        }

        panelActual = newPanel; // Establece el nuevo panel como actual
        frame.add(panelActual); // Agrega el nuevo panel al marco
        frame.revalidate(); // Actualiza la interfaz
        frame.repaint(); // Vuelve a dibujar la interfaz
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
    
    public static JpJuego getJpJuego(){
        return jpJuego;
    }
    
    public void setNivelSeleccionado(int nivel){
        this.nivelSeleccionado = nivel;
    }
    
}
