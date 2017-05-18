/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import model.Ficha;
import model.Juego;
import model.Jugada;

/**
 *
 * @author SheshoVega
 */
public class Control {
    
    ArrayList<Ficha> fichas;
    Juego juego;
    
    public Control(int cantJugadores) {
        this.initFichas();
        this.juego = new Juego(cantJugadores);
        this.repartirFichas(cantJugadores);
        System.out.println(this.juego.toString());
    }
    
    private void initFichas(){
        this.fichas = new ArrayList<Ficha>();
        for(int i=0; i < 7; i++){
            for(int j=0; j <= i; j++){
                Ficha nueva = new Ficha(i,j);
                this.fichas.add(nueva);
            }
        }
    }
    
    public void repartirFichas(int cantJugadores){
        ArrayList<ArrayList<Ficha>> setsDeFichas = new ArrayList<ArrayList<Ficha>>();
        
        //mezclar fichas
        long seed = System.nanoTime();
        Collections.shuffle(this.fichas, new Random(seed));
//        System.out.println(this.fichas.toString());
//        System.out.println(this.fichas.size());
        
        //hacer grupos de fichas
        Queue cola = new LinkedList<Ficha>(this.fichas);
//        System.out.println(cola.toString());
        for(int i=0; i < cantJugadores; i++){
            ArrayList<Ficha> set = new ArrayList<Ficha>();
            for(int j=0; j < 7; j++){
                set.add((Ficha) cola.remove());
            }
            setsDeFichas.add(set);
        }
        
        //repartir los grupos de las fichas
        for(int k=0; k < this.juego.getJugadores().size(); k++){
//            System.out.println("Jugador " + (k+1) + ":");
            this.juego.getJugadores().get(k).setFichasDelJugador(setsDeFichas.get(k));
//            System.out.println(this.juego.getJugadores().get(k).getFichasDelJugador().toString());
//            System.out.println(this.juego.getJugadores().get(k).getFichasDelJugador().size());
        }
        
        //meter en el pozo las fichas que sobran
        if(cola.size() > 0){
//            System.out.println("Pozo :");            
            this.juego.setFichasDelPozo(this.parseToListaDeFichas(cola));
//            System.out.println(this.juego.getFichasDelPozo().toString());
//            System.out.println(this.juego.getFichasDelPozo().size());
        }
        
        
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }
    
    private ArrayList<Ficha> parseToListaDeFichas(Queue cola){
        ArrayList<Ficha> listaDeFichas = new ArrayList<Ficha>();
        Iterator<Ficha> ite = cola.iterator();
        while(ite.hasNext()){
            Ficha ficha = (Ficha) cola.remove();
            listaDeFichas.add(ficha);
        }
        return listaDeFichas;
        
    }
    
    public void aplicarJugada(Jugada jugada){
        int idJugador = jugada.getIdJugador();
        int accion = jugada.getAccion();
        Ficha ficha = jugada.getFicha();
        switch(accion){
            case 1 : {
                System.out.println("Poniendo ficha : " + jugada.getFicha().toString());
                this.juego.getFichasJugadas().add(ficha);
                this.juego.getJugadores().get(idJugador - 1).getFichasDelJugador().remove(ficha);
            }
            break;
            case 2 : {
                System.out.println("Comiendo ficha : " + jugada.getFicha().toString());
                this.juego.getFichasDelPozo().remove(ficha);
                this.juego.getJugadores().get(idJugador - 1).getFichasDelJugador().add(ficha);                
            }
            break;
        }
    }
    
    public void setOpcionesDeJuego(int izq, int der){
        this.juego.setOpcionesDeJuego(izq, der);
    }
    
    public void setOpcionDeJuegoIzq(int izq){
        this.juego.setOpcionDeJuegoIzq(izq);
    }
    
    public void setOpcionDeJuegoDer(int der){
        this.juego.setOpcionDeJuegoDer(der);
    }
    
    public boolean validarJugadaPoniendo(Jugada jugada){
        int opIzq = this.juego.getOpDeJuegoALaIzq();
        int opDer = this.juego.getOpDeJuegoALaDer();
        Ficha ficha = jugada.getFicha();
        if( (ficha.getNumIzq() == opIzq) || (ficha.getNumIzq() == opDer) || (ficha.getNumDer() == opIzq) || (ficha.getNumDer() == opDer)){
            return true;
        }else{
            return false;
        }
    }
    
    public void jugadoresHabilitados(){
        this.juego.setJugadoresOK();
    }
    
    
    public Juego getJuego() {
        return juego;
    }
    
    
}