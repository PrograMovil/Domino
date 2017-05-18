/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Juego implements Serializable {
    
    int cantJugadores;
    int opDeJuegoALaIzq;
    int opDeJuegoALaDer;
    ArrayList<Ficha> fichasJugadas;
    ArrayList<Ficha> fichasDelPozo;
    ArrayList<Jugador> jugadores;
    ArrayList<Integer> jugadoresOk;
    

    public Juego(int cantJugadores) {
        this.cantJugadores = cantJugadores;
        this.fichasJugadas = new ArrayList<Ficha>();
        this.fichasDelPozo = new ArrayList<Ficha>();
        this.jugadores = new ArrayList<Jugador>();
        this.jugadoresOk = new ArrayList<Integer>();
        this.initJugadoresDelJuego(cantJugadores);
    }
    
    private void initJugadoresDelJuego(int cantJugadores){
        for(int i=1; i <= cantJugadores; i++){
            Jugador nuevo = new Jugador(i);
            this.jugadores.add(nuevo);
        }
    }

    public int getCantJugadores() {
        return cantJugadores;
    }

    public void setCantJugadores(int cantJugadores) {
        this.cantJugadores = cantJugadores;
    }

    public ArrayList<Ficha> getFichasJugadas() {
        return fichasJugadas;
    }

    public void setFichasJugadas(ArrayList<Ficha> fichasJugadas) {
        this.fichasJugadas = fichasJugadas;
    }

    public ArrayList<Ficha> getFichasDelPozo() {
        return fichasDelPozo;
    }

    public void setFichasDelPozo(ArrayList<Ficha> fichasDelPozo) {
        this.fichasDelPozo = fichasDelPozo;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    public void setOpcionesDeJuego(int izq, int der){
        this.opDeJuegoALaIzq = izq;
        this.opDeJuegoALaDer = der;
    }
    
    public void setOpcionDeJuegoIzq(int izq){
        this.opDeJuegoALaIzq = izq;
    }
    
    public void setOpcionDeJuegoDer(int der){
        this.opDeJuegoALaDer = der;
    }

    public int getOpDeJuegoALaIzq() {
        return opDeJuegoALaIzq;
    }

    public int getOpDeJuegoALaDer() {
        return opDeJuegoALaDer;
    }

    public ArrayList<Integer> getJugadoresOk() {
        return jugadoresOk;
    }
    
    public void setJugadoresOK(){
        this.jugadoresOk = this.jugadoresQuePuedenJugar();
    }
    
    public ArrayList<Integer> jugadoresQuePuedenJugar(){
        ArrayList<Integer> idsJugadores = new ArrayList<Integer>();
        for(int i=0; i < this.getJugadores().size(); i++){
            if(this.puedeJugar(this.getJugadores().get(i).getFichasDelJugador())){
                idsJugadores.add(i+1);
            }
        }
        return idsJugadores;        
    }
    
    private boolean puedeJugar(ArrayList<Ficha> fichasJugador){
        boolean puede = false;
        for(Ficha ficha : fichasJugador){
            if( (ficha.getNumDer() == this.opDeJuegoALaDer) || (ficha.getNumDer() == this.opDeJuegoALaIzq) ||
                    (ficha.getNumIzq() == this.opDeJuegoALaDer) || (ficha.getNumIzq() == this.opDeJuegoALaIzq)){
                puede = true;                
            }
        }
        return puede;
    }
    
    
        
    
    @Override
    public String toString() {
        return "Juego {" + "cantJugadores : " + cantJugadores + ", opDeJuegoALaIzq : " + opDeJuegoALaIzq + ", opDeJuegoALaDer : " + opDeJuegoALaDer + ", fichasJugadas : " + fichasJugadas.toString() + ", jugadoresOk : " + jugadoresOk.toString() + ", fichasDelPozo : " + fichasDelPozo.toString() + ", jugadores : " + jugadores.toString() + '}';
    }
    
    
    
}
