package com.domino;


import com.domino.Model.Accion;
import com.domino.Model.Ficha;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;


public class Conexion  extends Thread {

    Socket socket;
    DataInputStream dataIn;
    DataOutputStream dataOut;
    boolean connectionRunning = true;

    public Conexion(Socket socket){
        this.socket = socket;


    }

    public void sendDataToServer(String data){
        try {
            dataOut.writeUTF(data);
            dataOut.flush();
            System.out.println("Enviando!");
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            this.closeAll();
        }
    }

    public void run(){
        try {
            System.out.println("Recibiendo...");
            this.dataOut = new DataOutputStream(this.socket.getOutputStream());
            this.dataIn = new DataInputStream(this.socket.getInputStream());

            outerLoop:
            while(this.connectionRunning){

                try {
                    System.out.println("Leyendo...");
                    while(dataIn.available() == 0){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Accion dataIncoming = new Gson().fromJson(dataIn.readUTF(), Accion.class);

                    switch(dataIncoming.getTipo()){

                        case 2 : {
                            Ficha ficha = new Gson().fromJson(dataIncoming.getData(), Ficha.class);
                            System.out.println(dataIncoming.getMensaje() + " | Data : " + ficha.toString());
                        }
                        break;
                        case 6 : {
                            System.out.println(dataIncoming.getMensaje());
                            break outerLoop;
                        }
                    }


                } catch (IOException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                    this.closeAll();
                }
            }
//            this.closeAll();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            this.closeAll();
        }
        System.out.println("Terminar Programa");
        this.closeAll();
        System.exit(0);
    }

    public void closeAll(){
        try {
            dataOut.close();
            dataIn.close();
            socket.close();
            System.out.println("Cerrando...");
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
