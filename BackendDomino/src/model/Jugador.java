/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Jugador implements Serializable {
    
    int id;
    ArrayList<Ficha> fichasDelJugador;

    public Jugador(int id) {
        this.id = id;
        this.fichasDelJugador = new ArrayList<Ficha>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Ficha> getFichasDelJugador() {
        return fichasDelJugador;
    }

    public void setFichasDelJugador(ArrayList<Ficha> fichasDelJugador) {
        this.fichasDelJugador = fichasDelJugador;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id : " + id + ", fichasDelJugador : " + fichasDelJugador.toString() + '}';
    }
    
    
}
