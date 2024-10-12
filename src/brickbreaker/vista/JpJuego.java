package brickbreaker.vista;

import brickbreaker.controlador.Control;
import brickbreaker.modelo.*;
import brickbreaker.utils.Sonido;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class JpJuego extends javax.swing.JPanel {
    private Barra barra;
    private Pelota pelota;
    private Control control;
    private Bloques[] bloques;
    private boolean nivelCompleto;
    private ArrayList<Pelota> vidasPelotas;
    private boolean perder;
    private static int nivelSeleccionado;
    private Image fondo;
    private Sonido sonido;

    public JpJuego(Pelota pelota, Barra barra, Control control, Sonido sonido) {
        this.pelota = pelota;
        this.barra = barra;
        this.control = control;
        this.sonido = sonido;
        this.nivelCompleto = false;
        this.vidasPelotas = new ArrayList<>();
        this.perder = false;
        this.bloques = new Bloques[32];
        
        initComponents();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(fondo, 0, 0, 700, 600, this);
        g.drawImage(pelota.getImagen(), pelota.getX() - pelota.getRadio(), pelota.getY() - pelota.getRadio(), pelota.getRadio() * 2, pelota.getRadio() * 2, null);
        g.drawImage(barra.getImagen(), barra.getX(), barra.getY(), barra.getBase(), barra.getAltura(), null);
        
        for(int i = 0; i < 32; i++){
            if(bloques[i] != null){
                g.drawImage(bloques[i].getImagen(), bloques[i].getX(), bloques[i].getY(), bloques[i].getAncho(), bloques[i].getAlto(), null);
            }
            
        }
         
        for(Pelota vidasPintar: vidasPelotas){
            if(vidasPelotas != null){
                g.drawImage(vidasPintar.getImagen(), vidasPintar.getX(), vidasPintar.getY(), vidasPintar.getRadio() * 6, vidasPintar.getRadio() * 6, null);
            }
        }
        
        if(nivelCompleto){
            Image victoria = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondoVictory.png")).getImage();
            g.drawImage(victoria, 100, 150, 480, 270, null);
        }else if(perder){
            Image derrota = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondoLose.png")).getImage();
            g.drawImage(derrota, 20, 100, 640, 360, null);
        }   
    }
    
    public void cambiarImagenFondo(Image fondo){
        this.fondo = fondo;
        repaint();
    }
    
    public void moverBloquesHorizontales() {
        if(nivelSeleccionado == 3){
            for (int i = 0; i < 32; i++) {
                if (bloques[i] != null && i < 8 || i >= 16 && i < 24){
                    bloques[i].moverDerecha();
                }else if(bloques[i] != null && i >= 8 && i < 16 || i >= 24 ){
                    bloques[i].moverIzquierda();
                }
            }
        }
    }
    
    public void actualizarPuntos(int puntos){
        lblPuntos.setText("Score: " + puntos);
    }
    
    public void actualizarLblTiempo(String tiempoActualizado){
        lblTiempo.setText(tiempoActualizado);
    }

    public void actualizar() {
        repaint(); 
    }
    
    public void setBloques(Bloques[] bloques){
        this.bloques = bloques;
    }
    
    public void setNivel1Completo(boolean estado){
        this.nivelCompleto = estado;
    }
    
    public void setPerder(boolean estado){
        this.perder = estado;
    }
    
    public void setVidasPelotas(ArrayList<Pelota> vidasPelotas){
        this.vidasPelotas = vidasPelotas;
    }
    
    public static void setNivelSeleccionado(int nivel){
        JpJuego.nivelSeleccionado = nivel;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblPuntos = new javax.swing.JLabel();
        btnPausa = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblTiempo = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPuntos.setFont(new java.awt.Font("Ravie", 1, 18)); // NOI18N
        lblPuntos.setForeground(new java.awt.Color(255, 255, 255));
        lblPuntos.setText("Score: 0");
        jPanel2.add(lblPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 180, 30));

        btnPausa.setBackground(new java.awt.Color(34, 34, 34));
        btnPausa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/BotonPausa.png"))); // NOI18N
        btnPausa.setBorderPainted(false);
        btnPausa.setContentAreaFilled(false);
        btnPausa.setFocusPainted(false);
        btnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausaActionPerformed(evt);
            }
        });
        jPanel2.add(btnPausa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 50, 50));

        btnReiniciar.setBackground(new java.awt.Color(34, 34, 34));
        btnReiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/botonRestart.png"))); // NOI18N
        btnReiniciar.setActionCommand("");
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setContentAreaFilled(false);
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });
        jPanel2.add(btnReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 50, 50));

        btnRegresar.setBackground(new java.awt.Color(34, 34, 34));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/botonBack.png"))); // NOI18N
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 170, 70));

        lblTiempo.setFont(new java.awt.Font("Ravie", 1, 18)); // NOI18N
        lblTiempo.setForeground(new java.awt.Color(255, 255, 255));
        lblTiempo.setText("00:00");
        jPanel2.add(lblTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 80, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 0, 210, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Control.cambiarPanel(Control.getJpNiveles());
        control.reiniciarJuego();
        control.reiniciarTemporizador();
        pelota.setVelocidades(5, 5);
        control.detener(0);
        sonido.detener();
        sonido.reproducir("menuPrincipal");   
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        control.detener(0);
        control.reiniciarJuego();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausaActionPerformed
        control.detener(0);
    }//GEN-LAST:event_btnPausaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPausa;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblPuntos;
    private javax.swing.JLabel lblTiempo;
    // End of variables declaration//GEN-END:variables
}
