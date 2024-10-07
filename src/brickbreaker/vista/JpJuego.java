package brickbreaker.vista;

import brickbreaker.controlador.Control;
import brickbreaker.modelo.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;


public class JpJuego extends javax.swing.JPanel {
    private Barra barra;
    private Pelota pelota;
    private Bloques bloque;
    private JfJuego jfJuego;
    private JFrame frameJuego;
    private Bloques[] bloques;
    private Random random;
    private boolean nivel1Completo;
    private ArrayList<Pelota> vidasPelotas;
    private boolean perder;
    private Control control;

    public JpJuego(Pelota pelota, Barra barra, JfJuego jfJuego, JFrame frame, Control control) {
        this.pelota = pelota;
        this.barra = barra;
        this.jfJuego = jfJuego;
        this.frameJuego = frame;
        this.random = new Random();
        this.nivel1Completo = false;
        this.vidasPelotas = new ArrayList<>();
        this.perder = false;
        this.control = control;
        
        initComponents();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(new Color(152, 154, 166));
        g.fillOval(pelota.getX() - pelota.getRadio(), pelota.getY() - pelota.getRadio(),
                pelota.getRadio() * 2, pelota.getRadio() * 2);

        g.setColor(new Color(192, 192, 192));
        g.fillRect(barra.getX(), barra.getY(), barra.getBase(), barra.getAltura());
        
        for(int i = 0; i < 32; i++){
            g.setColor(Color.BLUE);
            g.fillRect(bloques[i].getX(), bloques[i].getY(), bloques[i].getAncho(), bloques[i].getAlto());
        }
        
        for(Pelota vidasPintar: vidasPelotas){
            if(vidasPelotas != null){
                g.setColor(Color.red);
                g.fillOval(vidasPintar.getX(), vidasPintar.getY(), vidasPintar.getRadio() * 2, vidasPintar.getRadio() * 2);
            }
        }
        
        if(nivel1Completo){
            g.drawString("YOU WON!", 370,250);
        }else if(perder){
            g.drawString("YOU LOST!", 370, 250);
        }
        
        
        
    }
    

    public void actualizar() {
        System.out.println("Se actualiza");
        repaint(); 
    }
    
    public void setBloques(Bloques[] bloques){
        this.bloques = bloques;
    }
    
    public void setNivel1Completo(boolean estado){
        this.nivel1Completo = estado;
    }
    
    public void setPerder(boolean estado){
        this.perder = estado;
    }
    
    public void setVidasPelotas(ArrayList<Pelota> vidasPelotas){
        this.vidasPelotas = vidasPelotas;
    }
    
    public void setControl(Control control){
        this.control = control;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnJugar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnJugar.setBackground(new java.awt.Color(0, 0, 0));
        btnJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/Boton-play.png"))); // NOI18N
        btnJugar.setBorderPainted(false);
        btnJugar.setFocusPainted(false);
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });
        jPanel2.add(btnJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 150, 50));

        btnReiniciar.setBackground(new java.awt.Color(34, 34, 34));
        btnReiniciar.setText("Volver a Jugar");
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });
        jPanel2.add(btnReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 150, 50));

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/boton-close.png"))); // NOI18N
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 150, 50));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 0, 200, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
        control.iniciar();
    }//GEN-LAST:event_btnJugarActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        control.reiniciarJuego();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        jfJuego.cambiarPanel("niveles");
        frameJuego.setVisible(false);
        jfJuego.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
