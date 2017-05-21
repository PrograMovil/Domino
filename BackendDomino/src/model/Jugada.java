/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;


public class Jugada implements Serializable {
    
    int idJugador;
    Ficha ficha; 

    public Jugada(int idJugador, Ficha ficha) {
        this.idJugador = idJugador;
        this.ficha = ficha;
    }
    
    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return "Jugada {" + "idJugador : " + idJugador + ", ficha : " + ficha + '}';
    }
    
}
