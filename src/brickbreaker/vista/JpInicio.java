package brickbreaker.vista;

import brickbreaker.controlador.Control;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class JpInicio extends javax.swing.JPanel {
    private ImageIcon fondo;
    
    public JpInicio() {
       this.fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondoInicio.gif"));
        initComponents();
        
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fondo != null) {
            g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIniciar.setBackground(new java.awt.Color(0, 0, 0));
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/botonPlay.png"))); // NOI18N
        btnIniciar.setBorderPainted(false);
        btnIniciar.setFocusPainted(false);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel3.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 170, 70));

        btnSalir.setBackground(new java.awt.Color(34, 34, 34));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/botonExit.png"))); // NOI18N
        btnSalir.setBorderPainted(false);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 170, 70));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 200, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        Control.cambiarPanel(Control.getJpNiveles());
                
    }//GEN-LAST:event_btnIniciarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
