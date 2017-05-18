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
}
