package brickbreaker.controlador;

import brickbreaker.vista.*;
import brickbreaker.modelo.*;
import brickbreaker.utils.*;
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
    private static javax.swing.Timer temporizadorJuego;
    private Random random;
    private boolean nivel1Completo;
    private int vidas;
    private static JPanel panelActual;
    private static JFrame frame;
    private int nivelSeleccionado = 1;
    private static JpNiveles jpNiveles;
    private int puntos;
    private Sonido sonido;
    private static Control instancia;
    private int tiempoRestante;
    

    public Control(JFrame marco) {
        this.barra = new Barra(100, 570, 150, 20, 25, "/recursos/imagenes/BarraYPelota/Barra.png");
        this.pelota = new Pelota(350, 300, 25, 5, 5, "/recursos/imagenes/BarraYPelota/Pelota.png");
        this.sonido = new Sonido();
        this.jpJuego = new JpJuego(pelota, barra, this, sonido);
        this.bloques = new Bloques[32];
        this.random = new Random();
        this.nivel1Completo = false;
        this.vidas = 3;
        this.vidasPelotas = new ArrayList<>();
        this.frame = marco;
        this.jpNiveles = new JpNiveles(this, jpJuego);
        this.puntos = 0;
        this.instancia = this;
        this.tiempoRestante = 300;
        
        sonido.reproducir("menuPrincipal");
        crearBloques();
        temporizadorDelJuego();
        
        temporizador = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pelota.mover(barra);
                verificarColisionBarra();
                detectarbordeInferior();
                colisionConBloque();
                bloquesDestruidos();
                bloquesNoDestuidos();
                if(nivelSeleccionado == 3){
                    jpJuego.moverBloquesHorizontales();
                }
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
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    
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
    }
    
    public void crearBloques(){
        if(nivelSeleccionado == 1 || nivelSeleccionado == 3){
            int ejeX = 50;
            int ejeY = 100;
            for (int i = 1; i <= 32; i++) {
                int dureza = random.nextInt(3);
                switch(dureza){
                    case 0:
                        bloques[i - 1] = new Bloques(ejeX, ejeY, 70, 30, dureza, 0,  false, "/recursos/imagenes/Ladrillos/LadriAmarillo01.png");
                        break;
                    case 1:
                        bloques[i - 1] = new Bloques(ejeX, ejeY, 70, 30, dureza, 1, false, "/recursos/imagenes/Ladrillos/LadriGris01.png");
                        break;
                    case 2:
                        bloques[i - 1] = new Bloques(ejeX, ejeY, 70, 30, dureza, 2, false, "/recursos/imagenes/Ladrillos/LadriRojo01.png");
                }
                
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
               int dureza = random.nextInt(3);
                switch(dureza){
                    case 0:
                        bloques[i] = new Bloques(ejeX, ejeY, 70, 30, dureza, 0, false, "/recursos/imagenes/Ladrillos/LadriAmarillo01.png");
                        break;
                    case 1:
                        bloques[i] = new Bloques(ejeX, ejeY, 70, 30, dureza, 1, false, "/recursos/imagenes/Ladrillos/LadriGris01.png");
                        break;
                    case 2:
                        bloques[i] = new Bloques(ejeX, ejeY, 70, 30, dureza, 2, false, "/recursos/imagenes/Ladrillos/LadriRojo01.png");
                }
           }
        }
 
        if(nivelSeleccionado == 2){
             pelota.setVelocidades(8,8);
        }else if(nivelSeleccionado == 3){
            pelota.setVelocidades(11, 11);
        }
        jpJuego.setBloques(bloques);
        cantidadVidas();
    }
    
    private void colisionConBloque() {
        for (int i = 0; i < 32; i++) {
            if (bloques[i] != null && !bloques[i].getDestruido() &&
                pelota.getX() + pelota.getRadio() > bloques[i].getX() &&
                pelota.getX() < (bloques[i].getX() + bloques[i].getAncho()) &&
                pelota.getY() + pelota.getRadio() > bloques[i].getY() &&
                pelota.getY() < bloques[i].getY() + bloques[i].getAlto())
            {
                manejarRebote(i);
                sonido.reproducirSonidoRebote();
                if(bloques[i].getDureza() < 0){
                    destruirBloque(i);
                }
                break;
            }
        }
    }
    
    private void manejarRebote(int i) {
        if(pelota.getVelocidadX() > 0 && pelota.getVelocidadY() > 0) { // Sureste
            if (pelota.getX() + pelota.getRadio() > bloques[i].getX() && pelota.getY() + pelota.getRadio() > bloques[i].getY() + bloques[i].getAlto()) {
                pelota.rebotar(1);
            } else {
                pelota.rebotar(2);
            }
            bloques[i].reducirDureza();
        }else if(pelota.getVelocidadX() < 0 && pelota.getVelocidadY() > 0) { // Suroeste
            if (pelota.getX() < (bloques[i].getX() + bloques[i].getAncho()) && pelota.getY() + pelota.getRadio() > bloques[i].getY() + bloques[i].getAlto()) {
                pelota.rebotar(1);
            } else {
                pelota.rebotar(2);
            }
            bloques[i].reducirDureza();
        }else if(pelota.getVelocidadX() > 0 && pelota.getVelocidadY() < 0) { // Noreste
            if ((pelota.getX() + pelota.getRadio()) > bloques[i].getX() && pelota.getY() + pelota.getRadio() < bloques[i].getY() + bloques[i].getAlto()) {
                pelota.rebotar(1);
            }else{
                pelota.rebotar(2);
            }
            bloques[i].reducirDureza();
        }else if(pelota.getVelocidadX() < 0 && pelota.getVelocidadY() < 0) { // Noroeste
            if (pelota.getX() < (bloques[i].getX() + bloques[i].getAncho()) && pelota.getY() + pelota.getRadio() < bloques[i].getY() + bloques[i].getAlto()) {
                pelota.rebotar(1);
            }else{
                pelota.rebotar(2);
            }
            bloques[i].reducirDureza();
        }
        controlPuntos(i);
    }
    
    private void controlPuntos(int i){
        if(bloques[i].getIdentificador() == 0){
            puntos += 50;
        }else if(bloques[i].getIdentificador() == 1){
            puntos += 100;
        }else{
            puntos += 150;
        }
        jpJuego.actualizarPuntos(puntos);
    }

    private void destruirBloque(int i) {
        bloques[i] = new Bloques(0, 0, 0, 0, 0, 0, true, "/recursos/imagenes/Ladrillos/LadriGris01.png");
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
            detener(0);
            jpJuego.setNivel1Completo(true);
        }
    }
    
    private void bloquesNoDestuidos(){
        if(vidas == 0){
            detener(0);
            jpJuego.setPerder(true);
        }
    }

    private void verificarColisionBarra(){
        if(pelota.getY() + pelota.getRadio() >= barra.getY() && pelota.getY() + pelota.getRadio() <= barra.getY() + barra.getAltura() &&
                pelota.getX() + pelota.getRadio() >= barra.getX() && pelota.getX() - pelota.getRadio() <= barra.getX() + barra.getBase()){
            pelota.rebotarVerticalemente();
            pelota.setY(barra.getY() - pelota.getRadio());
        }
    }

    private void detectarbordeInferior(){
        if(pelota.getY() + pelota.getRadio() > pelota.getAltoPanel()){
            this.vidas--;
            if (!vidasPelotas.isEmpty()){
                vidasPelotas.remove(vidasPelotas.size() - 1);
            }
            detener(1);
            pelota.moverPelotaAlCentro();
        }
        
        if(vidas == 2){
            barra.setBase(125);
            pelota.setRadio(20);
        }else if(vidas == 1){
            barra.setBase(100);
            pelota.setRadio(15);
        }
    }
    
    private void cantidadVidas(){
        vidasPelotas.clear();
        int ubicacionEnX = 20;
        for (int i = 0; i<vidas; i++){
            vidasPelotas.add(new Pelota(ubicacionEnX, 45, 7, 0, 0, "/recursos/imagenes/vida/Hearts.png"));
            ubicacionEnX = ubicacionEnX + 35;
        }
        jpJuego.setVidasPelotas(vidasPelotas);
    }
    
    public void reiniciarJuego(){
        String tiempoFormateado = String.format("%02d:%02d", 0, 0);
        crearBloques();
        this.vidas = 3;
        cantidadVidas();
        pelota.moverPelotaAlCentro();
        pelota.setRadio(25);
        barra.setBase(150);
        this.nivel1Completo = false;
        jpJuego.setPerder(false);
        jpJuego.setNivel1Completo(false);  
        jpJuego.actualizarLblTiempo(tiempoFormateado);
        jpJuego.actualizarPuntos(0);
        jpJuego.actualizar();      
    }
    
    public static void cambiarPanel(JPanel newPanel) {
        if (panelActual != null) {
            frame.remove(panelActual);
        }

        panelActual = newPanel; 
        frame.add(panelActual); 
        frame.revalidate();
        frame.repaint();
    }
    
    private void temporizadorDelJuego(){
        temporizadorJuego = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tiempoRestante > 0){
                    tiempoRestante--;
                    
                    int minutos = tiempoRestante/60;
                    int segundos = tiempoRestante % 60;
                    String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
                    jpJuego.actualizarLblTiempo(tiempoFormateado);
                }else{
                    temporizador.stop();
                    jpJuego.setPerder(true);
                }
            }
        });
    }

    public void iniciar() {
        temporizador.start();
        temporizadorJuego.start();
    }

    public void detener(int eleccion) {
        if(eleccion == 1){
            temporizador.stop();
        }else{
            temporizadorJuego.stop();
            temporizador.stop();
        }
        
    }
    
    public void reiniciarTemporizador(){
        temporizador.restart();
        temporizadorJuego.restart();
    }
    
    public static JpJuego getJpJuego(){
        return jpJuego;
    }
    
    public void setNivelSeleccionado(int nivel){
        this.nivelSeleccionado = nivel;
    }
    
    public static JpNiveles getJpNiveles(){
        return jpNiveles;
    }
    
    public Sonido getSonido(){
        return sonido;
    }
    
    public static Control getControl(){
        return instancia;
    }
}