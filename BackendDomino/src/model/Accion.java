/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SheshoVega
 */
public class Accion {
    int tipo;   //  1 - iniciar juego
                //  2 - poner ficha
                //  3 - comer ficha
                //  4 - terminar juego
    String mensaje;
    int error;  //  0 - no hay error
                //  1 - hay error

    public Accion(int tipo, String mensaje, int error) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.error = error;
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

    @Override
    public String toString() {
        return "Accion {" + "tipo : " + tipo + ", mensaje : " + mensaje + ", error : " + error + '}';
    }
    
    
}
