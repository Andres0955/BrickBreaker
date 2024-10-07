
package brickbreaker;


import brickbreaker.vista.*;
import brickbreaker.modelo.*;
import brickbreaker.controlador.Control;
import javax.swing.JFrame;

public class BrickBreaker {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JfJuego jfJuego = new JfJuego(frame);
        jfJuego.setVisible(true);
       
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pelota pelota = new Pelota(350, 300, 25, 10, 10);
        Barra barra = new Barra();
        JpJuego jpJuego = new JpJuego(pelota, barra, jfJuego, frame, null);
        Control control = new Control(pelota, jpJuego, barra, frame); 
        jpJuego.setControl(control);
        
        
        frame.add(jpJuego);
        frame.pack();
         
    }   
}
