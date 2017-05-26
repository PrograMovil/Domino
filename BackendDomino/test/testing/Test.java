/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import com.google.gson.Gson;
import java.util.Collections;
import java.util.Random;
import model.Accion;
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
//        Jugador j2 = game.getJugadores().get(1);
//        Jugador j3 = game.getJugadores().get(2);
        
        Jugada ju1 = new Jugada(1,game.getFichasDelPozo().get(0));
        Accion ac1 = new Accion();
        ac1.setTipo(3);
        ac1.setData(gson.toJson(ju1));
        
        
//        Jugada ju2 = new Jugada(2,game.getFichasDelPozo().get(0),2);
        
        ctrl.aplicarJugada(ac1);
//        ctrl.aplicarJugada(ju2);
        System.out.println(game.toString());
//        System.out.println(j1.getId());
//        System.out.println(j1.getFichasDelJugador().toString());
//        j1.getFichasDelJugador().clear();
//        game.verificarGane();
//        System.out.println(j1.getId());
//        System.out.println(j1.getFichasDelJugador().toString());
//        game.setOpcionDeJuegoDer(1);
//        game.setOpcionDeJuegoIzq(1);
//        game.jugadoresQuePuedenJugar();
//        game.setJugadoresOK();
//        boolean isOK = ctrl.validarJugadaPoniendo(ju1);
//        if(isOK){
//            System.out.println("todo bien");
//        }else {
//            System.out.println("no se puede");
//        }
//        ctrl.setSiguienteTurno();
//        System.out.println(game.toString());
//        ctrl.setSiguienteTurno();
//        System.out.println(game.toString());
//        
//        boolean gane = ctrl.hayGanador();
//        
//        if(gane){
//            System.out.println("Ya gano el jugador: " + game.getGanador());
//        }else{
//            System.out.println("aun no hay ganador!");
//        }
        
        
        
        
    }
    
}
