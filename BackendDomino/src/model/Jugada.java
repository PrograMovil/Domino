/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;


public class Jugada implements Serializable {
    Ficha ficha;
    int accion; 

    public Jugada(Ficha ficha, int accion) {
        this.ficha = ficha;
        this.accion = accion;
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
