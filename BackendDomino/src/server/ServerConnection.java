
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Accion;
import model.Ficha;
import model.Juego;
import model.Jugada;


public class ServerConnection extends Thread{
    
    Socket socket;
    Server server;
    DataInputStream dataIn;
    DataOutputStream dataOut;
    boolean serverRunning = true;
    
    public ServerConnection(Socket socket, Server server){
        super("HiloDeLaConexionDelServer");
        this.socket = socket;
        this.server = server;
    }
    
    public void sendDataToClient(String data){
        try {
            dataOut.writeUTF(data);
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendDataToAllClients(String data){
        for(int i = 0; i < this.server.connections.size(); i++){
            ServerConnection serverConnection = this.server.connections.get(i);
            serverConnection.sendDataToClient(data);
        }
    }
    
    public void run(){
        try {
            System.out.println(this.server.connections.toString());
            dataOut = new DataOutputStream(this.socket.getOutputStream());
            dataIn = new DataInputStream(this.socket.getInputStream());
            
            while(serverRunning){
                System.out.println("Recibiendo...");                
                while(dataIn.available() == 0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Leyendo...");
                Accion dataIncoming = this.server.gson.fromJson(dataIn.readUTF(), Accion.class);
                System.out.println("Data recibida: " + dataIncoming.getMensaje());
                Accion response = new Accion();
                switch(dataIncoming.getTipo()){
                    case 100 : { //Caso de pruebas!
                        this.server.ctrl = new Control(4);
                        response.setTipo(100);
                        response.setData(this.server.gson.toJson(this.server.ctrl.getJuego()));
                        this.sendDataToClient(this.server.gson.toJson(response));
                    }
                    break;
                    case 0 : {
                        System.out.println("CONSULTA HAY JUEGO INICIADO");
                        if(this.server.ctrl == null){
                            response.setTipo(0);
                            response.setMensaje("NO hay un juego iniciado");
                            response.setError(0);
                            response.setData("0");
                            this.sendDataToClient(this.server.gson.toJson(response));
                        }else{
                            response.setTipo(0);
                            response.setMensaje("Hay un juego iniciado");
                            response.setError(0);
                            response.setData("1");
                            this.sendDataToClient(this.server.gson.toJson(response));
                        }
                    }
                    break;
                    case 1 : {
                        System.out.println("CONSULTA INICIALIZAR JUEGO");
                        response.setTipo(1);
                        if(this.server.ctrl == null){
                            System.out.println("Iniciando juego nuevo");
                            Integer cantJugadores = this.server.gson.fromJson(dataIncoming.getData(), Integer.class);
                            this.server.ctrl = new Control(cantJugadores);
                            
                            response.setMensaje("Juego iniciado, esperando jugadores...");
                            response.setError(0);
                            this.server.jugadoresConectados.add(this.server.ctrl.juego.getJugadores().get(0).getId());
                            response.setData(this.server.gson.toJson(this.server.ctrl.juego.getJugadores().get(0).getId()));
                            
                        }else{
                            System.out.println("Error al iniciar juego");
                            response.setMensaje("ERROR: ya existe un juego iniciado!");
                            response.setError(1);
                        }
                        this.sendDataToClient(this.server.gson.toJson(response));                       
                        
                    }
                    break;
                    case 2 : {
                        System.out.println("CONSULTA PONER FICHA");
                        Jugada jugada = this.server.gson.fromJson(dataIncoming.getData(), Jugada.class);
                        System.out.println("Poniendo ficha de: " + jugada.toString());
                        response.setTipo(2);
                        if(this.server.ctrl.validarJugadaPoniendo(jugada)){
                            if(this.server.ctrl.aplicarJugada(dataIncoming)){
                                response.setMensaje("Ficha puesta!");
                                response.setError(0);
                            }else{
                                response.setMensaje("ERROR: No se pudo poner la ficha!");
                                response.setError(1);
                            }
                        }else{
                            response.setMensaje("ERROR: jugada no valida!");
                            response.setError(1);
                        }
                        System.out.println("Nuevas opciones de Juego: izp = " 
                                + this.server.ctrl.juego.getOpDeJuegoALaIzq() 
                                +  " der = " + this.server.ctrl.juego.getOpDeJuegoALaDer());
                        System.out.println("Jugadores ok: "+ this.server.ctrl.juego.getJugadoresOk().toString());
                        System.out.println("Siguiente turno: "+this.server.ctrl.juego.getTurno());
                        response.setData(this.server.gson.toJson(this.server.ctrl.juego));
                        this.sendDataToAllClients(this.server.gson.toJson(response));                        
                    }
                    break; 
                    case 3 : {
                        System.out.println("CONSULTA COMER FICHA");
                        Jugada jugada = this.server.gson.fromJson(dataIncoming.getData(), Jugada.class);
                        System.out.println("Comiendo ficha: " + dataIncoming.getData());
                        response.setTipo(3);
                        if(this.server.ctrl.validarJugadaPoniendo(jugada)){
                            if(this.server.ctrl.aplicarJugada(dataIncoming)){
                                response.setMensaje("Ficha comida!");
                                response.setError(0);
                            }else{
                                response.setMensaje("ERROR: No se pudo comer la ficha!");
                                response.setError(1);
                            }                       
                        }else{
                            response.setMensaje("ERROR: jugada no valida!");
                            response.setError(1);
                        }
                        System.out.println("Nuevas opciones de Juego: izp = " 
                                + this.server.ctrl.juego.getOpDeJuegoALaIzq() 
                                +  " der = " + this.server.ctrl.juego.getOpDeJuegoALaDer());
                        System.out.println("Jugadores ok: "+ this.server.ctrl.juego.getJugadoresOk().toString());
                        System.out.println("Siguiente turno: "+this.server.ctrl.juego.getTurno());
                        response.setData(this.server.gson.toJson(this.server.ctrl.juego));
                        this.sendDataToClient(this.server.gson.toJson(response));
                    }
                    break;
                    case 4 : {
                        System.out.println("CONSULTA ESTADO DEL JUEGO");
                        response.setTipo(4);
                        response.setMensaje("Estado del Juego");
                        response.setError(0);
                        response.setData(this.server.gson.toJson(this.server.ctrl.juego));
                        this.sendDataToClient(this.server.gson.toJson(response));
                    }
                    break;
                    case 5 : {
                        System.out.println("CONSULTA ID DEL JUGADOR");
                        response.setTipo(5);
                        response.setMensaje("ID del jugador");
                        response.setError(0);
                        int cantJugadores = this.server.ctrl.juego.getCantJugadores();
                        if(this.server.jugadoresConectados.size() == cantJugadores){ // el juego ya tiene todos lo jugadores
                            response.setData("-1"); //no puede unirse al juego
                        }else{
                            this.server.jugadoresConectados.add(this.server.jugadoresConectados.size()+1);
                            response.setData(String.valueOf(this.server.jugadoresConectados.size()));
                            System.out.println("");
                            System.out.println("Jugadores conectados: " + this.server.jugadoresConectados.toString());
                        }                        
                        this.sendDataToClient(this.server.gson.toJson(response));
                    }
                    break;
                    case 6 : {
                        System.out.println("Terminando juego!...");
                        response.setTipo(6);
                        response.setMensaje("Terminado el juego.");
                        response.setError(0);
                        this.sendDataToAllClients(this.server.gson.toJson(response));
                    }
                    break;
                }
//                this.sendDataToAllClients(dataIncoming);
            }
            
            this.closeAll();
            
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void closeAll() throws IOException{
        dataOut.close();
        dataIn.close();            
        socket.close();
    }

    @Override
    public String toString() {
        return "ServerConnection {" + "serverRunning : " + serverRunning + '}';
    }
    
    
}
