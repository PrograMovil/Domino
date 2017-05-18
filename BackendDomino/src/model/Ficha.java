/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;


public class Ficha implements Serializable{
    int numIzq;
    int numDer;
    int esDoble; // 1 si es doble o 0 si no lo es.

    public Ficha(int numIzq, int numDer) {
        this.numIzq = numIzq;
        this.numDer = numDer;
        if(numIzq == numDer){
            this.esDoble = 1;
        }else{
            this.esDoble = 0;
        }
    }

    public int getNumIzq() {
        return numIzq;
    }

    public void setNumIzq(int numIzq) {
        this.numIzq = numIzq;
    }

    public int getNumDer() {
        return numDer;
    }

    public void setNumDer(int numDer) {
        this.numDer = numDer;
    }

    public int getEsDoble() {
        return esDoble;
    }

    public void setEsDoble(int esDoble) {
        this.esDoble = esDoble;
    }

    @Override
    public String toString() {
        return "Ficha {" + "numIzq : " + numIzq + ", numDer : " + numDer + ", esDoble : " + esDoble + '}';
    }
    
    
}
