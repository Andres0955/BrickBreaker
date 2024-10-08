
package brickbreaker.vista;

import brickbreaker.BrickBreaker;
import brickbreaker.controlador.Control;
import brickbreaker.modelo.Barra;
import brickbreaker.modelo.Pelota;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class JpInicio extends javax.swing.JPanel {
    private Image fondo;
    
    public JpInicio() {
        URL imageUrl = getClass().getResource("/recursos/imagenes/fondo.png");
        if (imageUrl != null) {
            fondo = new ImageIcon(imageUrl).getImage();
        } else {
            System.out.println("Imagen no encontrada");
        }
       
        //this.jfJuego = jfInicio;
        initComponents();
        
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen de fondo si está cargada
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnInstrucciones = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIniciar.setBackground(new java.awt.Color(0, 0, 0));
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/Boton-play.png"))); // NOI18N
        btnIniciar.setBorderPainted(false);
        btnIniciar.setFocusPainted(false);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel3.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 50));

        btnSalir.setBackground(new java.awt.Color(0, 0, 0));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/Boton-Exit.png"))); // NOI18N
        btnSalir.setBorderPainted(false);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 150, 50));

        btnInstrucciones.setBackground(new java.awt.Color(0, 0, 0));
        btnInstrucciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/boton-instrucciones.png"))); // NOI18N
        btnInstrucciones.setBorderPainted(false);
        btnInstrucciones.setFocusPainted(false);
        btnInstrucciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstruccionesActionPerformed(evt);
            }
        });
        jPanel3.add(btnInstrucciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 150, 55));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 200, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void btnInstruccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstruccionesActionPerformed
        JpInstrucciones jpInstrucciones = new JpInstrucciones();
        Control.cambiarPanel(jpInstrucciones);
    }//GEN-LAST:event_btnInstruccionesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        JpNiveles jpNiveles = new JpNiveles();
        Control.cambiarPanel(jpNiveles);
        
        //control.iniciar();
        
    }//GEN-LAST:event_btnIniciarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnInstrucciones;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
