package brickbreaker.vista;

import brickbreaker.controlador.Control;
import brickbreaker.modelo.Barra;
import brickbreaker.modelo.Pelota;
import java.awt.CardLayout;
import javax.swing.JFrame;

public class JfJuego extends javax.swing.JFrame {
    private CardLayout cardLayout;
    private JFrame frame;
    
    
    public JfJuego(JFrame frame) {
        this.frame = frame;
        
        initComponents();
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.add(new JpInicio(this), "inicio");
        this.add(new JpInstrucciones(this), "instrucciones");
        this.add(new JpNiveles(this), "niveles");
        //this.add(new JpJuego(pelota, barra), "juego");
        
        cardLayout.show(this.getContentPane(), "inicio");        
    }
    
    public void cambiarPanel(String nombrePanel){
        if(nombrePanel.equals("juego")){
            this.setVisible(false);
            frame.setVisible(true);
        }
        
        cardLayout.show(this.getContentPane(), nombrePanel);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
