/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domino.Model;

import java.io.Serializable;
import java.util.ArrayList;


public class Juego implements Serializable {
    
    int cantJugadores;
    int opDeJuegoALaIzq;
    int opDeJuegoALaDer;
    ArrayList<Integer> jugadoresOk;
    int mano; //jugador que inicia la ronda.
    int ganador; //jugador que gana el juego
    ArrayList<Ficha> fichasJugadas;
    ArrayList<Ficha> fichasDelPozo;
    ArrayList<Jugador> jugadores;
    
    
    public Juego(int cantJugadores) {
        this.cantJugadores = cantJugadores;
        this.fichasJugadas = new ArrayList<Ficha>();
        this.fichasDelPozo = new ArrayList<Ficha>();
        this.jugadores = new ArrayList<Jugador>();
        this.jugadoresOk = new ArrayList<Integer>();
        this.initJugadoresDelJuego(cantJugadores);
        this.mano = -1;
        this.ganador = 0;
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

    public int getMano() {
        return mano;
    }

    public int getGanador() {
        return ganador;
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
    
    public void setJugadorMano(){
        int mano = 6;
        ArrayList<Jugador> jugadores = this.jugadores;
        outerLoop :
        while(mano >= 0){
            for(int i=0; i < jugadores.size(); i++){
                ArrayList<Ficha> fichas = jugadores.get(i).getFichasDelJugador();
                for(Ficha ficha : fichas){
                    if(ficha.getEsDoble() == 1){
                        if(ficha.getNumDer() == mano){
                            this.mano = i+1;
                            break outerLoop;
                        }
                    }
                }
            }
            mano--;
        }        
        if(this.mano == -1){
            this.manoAlternativa();
        }
    }
    
    private void manoAlternativa(){
        int izq = 6;
        int der = 6;
        outerLoop:
        while(izq >= 0){
            while(der >= 0){
                for(int i=0; i < jugadores.size(); i++){
                    ArrayList<Ficha> fichas = jugadores.get(i).getFichasDelJugador();
                    for(Ficha ficha : fichas){
                        if(ficha.getEsDoble() == 0){
                            if( (ficha.getNumIzq() == izq) && (ficha.getNumDer() == der) ){
                                this.mano = i+1;
                                break outerLoop;
                            }
                        }
                    }
                }
                der--;
            }
            izq--;
        }
    }
    
    public void verificarGane(){
        for(int i=0; i < this.jugadores.size(); i++){
            if(this.jugadores.get(i).getFichasDelJugador().size() == 0){
                this.ganador = i+1;
            }
        }
    }

    @Override
    public String toString() {
        return "Juego {" + "cantJugadores : " + cantJugadores + ", opDeJuegoALaIzq : " + opDeJuegoALaIzq + ", opDeJuegoALaDer : " + opDeJuegoALaDer + ", jugadoresOk : " + jugadoresOk.toString() + ", mano : " + mano + ", ganador : " + ganador + ", fichasJugadas : " + fichasJugadas.toString() + ", fichasDelPozo : " + fichasDelPozo.toString() + ", jugadores : " + jugadores.toString() + '}';
    }
   
    
}
