package com.domino;

import java.net.Socket;



public class socket {

    Socket socket;
    private static socket instancia;

    private socket() {
        try {
            socket = new Socket("192.168.2.176", 3333);
        }catch (Exception e){

        }
    }

    public static synchronized socket getInstancia(){
        if(instancia==null)
            instancia=new socket();
        return instancia;
    }

    public Socket getSocket() {
        return socket;
    }
}

