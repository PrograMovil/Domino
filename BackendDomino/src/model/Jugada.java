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
    int accion; //  1 - poner ficha
                //  2 - comer ficha

    public Jugada(int idJugador, int accion) {
        this.ficha = ficha;
        this.accion = accion;
    }

    public Jugada(int idJugador, Ficha ficha, int accion) {
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

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "Jugada {" + "ficha : " + ficha.toString() + ", accion : " + accion + '}';
    }
    
    
    
}
