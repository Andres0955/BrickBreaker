
package brickbreaker;


import brickbreaker.vista.*;
import brickbreaker.modelo.*;
import brickbreaker.controlador.Control;
import javax.swing.JFrame;

public class BrickBreaker {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JpInicio jpInicio = new JpInicio();

        Control control = new Control(frame); 
       
        control.cambiarPanel(jpInicio);
        frame.setVisible(true);
        frame.pack();
         
    }   
}
