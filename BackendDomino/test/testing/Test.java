/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.util.Collections;
import java.util.Random;
import model.Juego;
import model.Jugada;
import model.Jugador;
import server.Control;

/**
 *
 * @author SheshoVega
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Control ctrl = new Control(3);
        Juego game = ctrl.getJuego();
        Jugador j1 = game.getJugadores().get(0);
        Jugador j2 = game.getJugadores().get(1);
        Jugador j3 = game.getJugadores().get(2);
        
        Jugada ju1 = new Jugada(1,j1.getFichasDelJugador().get(0),1);
        Jugada ju2 = new Jugada(2,game.getFichasDelPozo().get(0),2);
        
        ctrl.aplicarJugada(ju1);
        ctrl.aplicarJugada(ju2);
        System.out.println(game.toString());
        System.out.println(j1.getFichasDelJugador().size());
        System.out.println(j2.getFichasDelJugador().size());
//        System.out.println(ctrl.getFichas().toString()); 
//        System.out.println(ctrl.getFichas().size());
        
//        Juego juego = new Juego(3);
//        System.out.println(juego.toString());
        
        
        
        
    }
    
}
