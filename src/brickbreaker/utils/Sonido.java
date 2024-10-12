package brickbreaker.utils;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sonido {
    private Clip clip;  
    private Clip clipRebote;  
    private String sonidoMenuPrincipal;
    private String sonidoNivel1;
    private String sonidoNivel2;
    private String sonidoNivel3;
    private String sonidoRebote;
    
    public Sonido(){
        sonidoMenuPrincipal = "/recursos/sonidos/sonidoMenuPrincipal.wav";
        sonidoNivel1 = "/recursos/sonidos/sonidoNivel1.wav";
        sonidoNivel2 = "/recursos/sonidos/sonidoNivel2.wav";
        sonidoNivel3 = "/recursos/sonidos/sonidoNivel3.wav";
        sonidoRebote = "/recursos/sonidos/sonidoRebote.wav";
    }
    
    
    public void reproducir(String nombreSonido) {
        String rutaSonido = "";
        
        switch (nombreSonido) {
        case "menuPrincipal":
            rutaSonido = sonidoMenuPrincipal;
            break;
        case "nivel1":
            rutaSonido = sonidoNivel1;
            break;
        case "nivel2":
            rutaSonido = sonidoNivel2;
            break;
        case "nivel3":
            rutaSonido = sonidoNivel3;
            break;
        default:
            System.out.println("Sonido no reconocido: " + nombreSonido);
            return;
        }
       
        if (clip != null && clip.isRunning()) {
            clip.stop();  
            clip.flush();
            clip.close();
        }

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(rutaSonido));
            
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void reproducirSonidoRebote(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(sonidoRebote));
            
            clipRebote = AudioSystem.getClip();
            clipRebote.open(ais);
            clipRebote.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.flush();
            clip.close();
        }
    }

}
