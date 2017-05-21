/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domino.Model;

import java.io.Serializable;


public class Jugada implements Serializable {
    
    int idJugador;
    Ficha ficha;
    Accion accion; 

    public Jugada(int idJugador, Ficha ficha) {
        this.idJugador = idJugador;
        this.ficha = ficha;
    }

    public Jugada(int idJugador, Ficha ficha, Accion accion) {
        this.idJugador = idJugador;
        this.ficha = ficha;
        this.accion = accion;
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

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "Jugada {" + "ficha : " + ficha.toString() + ", accion : " + accion.toString() + '}';
    }
    
    
    
}
