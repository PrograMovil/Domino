/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import model.Accion;
import model.Ficha;
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
        Gson gson = new Gson();
        Control ctrl = new Control(2);
        Juego game = ctrl.getJuego();
        Jugador j1 = game.getJugadores().get(0);
        Jugador j2 = game.getJugadores().get(1);
        j1.getFichasDelJugador().clear();
        j2.getFichasDelJugador().clear();
        System.out.println("Cant fichas J1:"+j1.getFichasDelJugador().size());
        System.out.println("Cant fichas J2:"+j2.getFichasDelJugador().size());
        
        j1.getFichasDelJugador().add(new Ficha(2,0));
        j1.getFichasDelJugador().add(new Ficha(2,2));        
        j2.getFichasDelJugador().add(new Ficha(2,3));
        j2.getFichasDelJugador().add(new Ficha(3,3));
        
        game.setOpcionDeJuegoIzq(0);     
        game.setOpcionDeJuegoDer(0);
        
        game.setJugadoresOK();
        
        System.out.println("Opciones de juego: izq = "+game.getOpDeJuegoALaIzq()+" der = "+ game.getOpDeJuegoALaDer());
        System.out.println("Jugadores OK: " + game.getJugadoresOk().toString());
        
        j1.getFichasDelJugador().get(0).setLadoJuego('D');
        Jugada ju1 = new Jugada(1,j1.getFichasDelJugador().get(0));
        Accion acc1 = new Accion();
        acc1.setTipo(2);
        acc1.setData(gson.toJson(ju1));
        ctrl.aplicarJugada(acc1);
        System.out.println("Cant fichas J1:"+j1.getFichasDelJugador().size());
        System.out.println("Cant fichas J2:"+j2.getFichasDelJugador().size());
        System.out.println("Opciones de juego: izq = "+game.getOpDeJuegoALaIzq()+" der = "+ game.getOpDeJuegoALaDer());
        System.out.println("Jugadores OK: " + game.getJugadoresOk().toString());
        System.out.println("Fichas jugadas: " + game.getFichasJugadas().toString());
        

        j2.getFichasDelJugador().get(0).setLadoJuego('D');
        Jugada ju2 = new Jugada(2,j2.getFichasDelJugador().get(0));
        Accion acc2 = new Accion();
        acc2.setTipo(2);
        acc2.setData(gson.toJson(ju2));
        ctrl.aplicarJugada(acc2);
        System.out.println("Cant fichas J1:"+j1.getFichasDelJugador().size());
        System.out.println("Cant fichas J2:"+j2.getFichasDelJugador().size());
        System.out.println("Opciones de juego: izq = "+game.getOpDeJuegoALaIzq()+" der = "+ game.getOpDeJuegoALaDer());
        System.out.println("Jugadores OK: " + game.getJugadoresOk().toString());
        System.out.println("Fichas jugadas: " + game.getFichasJugadas().toString());
    }
    
}
