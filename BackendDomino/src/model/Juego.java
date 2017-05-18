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
    ArrayList<Ficha> fichasJugadas;
    ArrayList<Ficha> fichasDelPozo;
    ArrayList<Jugador> jugadores;

    public Juego(int cantJugadores) {
        this.cantJugadores = cantJugadores;
        this.fichasJugadas = new ArrayList<Ficha>();
        this.fichasDelPozo = new ArrayList<Ficha>();
        this.jugadores = new ArrayList<Jugador>();
        this.initJugadoresDelJuego(cantJugadores);
    }

    public Juego(int cantJugadores, ArrayList<Ficha> fichasDelPozo) {
        this.cantJugadores = cantJugadores;
        this.fichasDelPozo = fichasDelPozo;
        this.fichasJugadas = new ArrayList<Ficha>();
        this.jugadores = new ArrayList<Jugador>();
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

    @Override
    public String toString() {
        return "Juego {" + "cantJugadores : " + cantJugadores + ", fichasJugadas : " + fichasJugadas.toString() + ", fichasDelPozo : " + fichasDelPozo.toString() + ", jugadores : " + jugadores.toString() + '}';
    }

    
    
    
}
