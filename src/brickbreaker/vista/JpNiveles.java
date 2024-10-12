package brickbreaker.vista;

import brickbreaker.controlador.Control;
import brickbreaker.utils.Sonido;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class JpNiveles extends javax.swing.JPanel {
     private static Control control;
     private int nivelSeleccionado;
     private JpJuego jpJuego;
     private ImageIcon fondo;
     private Sonido sonido;
    
    public JpNiveles(Control control, JpJuego jpJuego) {
        this.control = control;
        this.jpJuego = jpJuego;
        this.fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondoNiveles.gif"));
        this.sonido = control.getSonido();
        
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, 700, 600, this);
    }

    public Image seleccionFondo(){
        Image fondo = null;
        switch(nivelSeleccionado){
            case 1:
                fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondoNivel1.gif")).getImage();
                break;
            case 2:
                fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondoNivel2.gif")).getImage();
                break;
            case 3:
                fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondoNivel3.gif")).getImage();
                break;
            default:
        }
        return fondo;
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnRegresar1 = new javax.swing.JButton();
        btnNivel3 = new javax.swing.JButton();
        btnNivel2 = new javax.swing.JButton();
        btnNivel1 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar1.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/botonBack.png"))); // NOI18N
        btnRegresar1.setBorderPainted(false);
        btnRegresar1.setFocusPainted(false);
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegresar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 170, 70));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 200, 600));

        btnNivel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/BotonLevel3.png"))); // NOI18N
        btnNivel3.setBorderPainted(false);
        btnNivel3.setContentAreaFilled(false);
        btnNivel3.setFocusPainted(false);
        btnNivel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNivel3ActionPerformed(evt);
            }
        });
        add(btnNivel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 170, 70));

        btnNivel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/BotonLevel2.png"))); // NOI18N
        btnNivel2.setBorderPainted(false);
        btnNivel2.setContentAreaFilled(false);
        btnNivel2.setFocusPainted(false);
        btnNivel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNivel2ActionPerformed(evt);
            }
        });
        add(btnNivel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 170, 70));

        btnNivel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/BotonNivel1.png"))); // NOI18N
        btnNivel1.setBorderPainted(false);
        btnNivel1.setContentAreaFilled(false);
        btnNivel1.setFocusPainted(false);
        btnNivel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNivel1ActionPerformed(evt);
            }
        });
        add(btnNivel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 170, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
        JpInicio jpInicio = new JpInicio();
        Control.cambiarPanel(jpInicio);
    }//GEN-LAST:event_btnRegresar1ActionPerformed

    private void btnNivel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNivel3ActionPerformed
        nivelSeleccionado = 3; 
        sonido.reproducir("nivel3");
        jpJuego.cambiarImagenFondo(seleccionFondo());
        jpJuego.setNivelSeleccionado(nivelSeleccionado);
        control.setNivelSeleccionado(nivelSeleccionado);
        control.reiniciarJuego();
        Control.cambiarPanel(jpJuego);
    }//GEN-LAST:event_btnNivel3ActionPerformed

    private void btnNivel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNivel1ActionPerformed
        nivelSeleccionado = 1;
        sonido.reproducir("nivel1");
        jpJuego.cambiarImagenFondo(seleccionFondo());
        control.setNivelSeleccionado(nivelSeleccionado);
        control.reiniciarJuego();
        Control.cambiarPanel(jpJuego);
    }//GEN-LAST:event_btnNivel1ActionPerformed

    private void btnNivel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNivel2ActionPerformed
        nivelSeleccionado = 2; 
        sonido.reproducir("nivel2");
        jpJuego.cambiarImagenFondo(seleccionFondo());
        control.setNivelSeleccionado(nivelSeleccionado);  
        control.reiniciarJuego(); 
        Control.cambiarPanel(jpJuego);

    }//GEN-LAST:event_btnNivel2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNivel1;
    private javax.swing.JButton btnNivel2;
    private javax.swing.JButton btnNivel3;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
