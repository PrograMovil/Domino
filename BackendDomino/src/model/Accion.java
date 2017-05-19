/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author SheshoVega
 */
public class Accion implements Serializable {
    int tipo;   //  1 - iniciar juego
                //  2 - poner ficha
                //  3 - comer ficha
                //  4 - terminar juego
                //  5 - response iniciar juego
                //  6 - response terminar juego
                //  7 - response juego
    String mensaje;
    int error;  //  0 - no hay error
                //  1 - hay error
    String data; // un objeto json para luego parsearlo

    public Accion() {
        this.tipo = 0;
        this.mensaje = "";
        this.error = -1;
        this.data = "";
    }
    
    
    public Accion(int tipo, String mensaje, int error) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.error = error;
        this.data = "";
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Accion {" + "tipo : " + tipo + ", mensaje : " + mensaje + ", error : " + error + "data : " + data.toString() +'}';
    }
    
    
}
