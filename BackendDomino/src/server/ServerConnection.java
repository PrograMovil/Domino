
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


public class ServerConnection extends Thread{
    
    Socket socket;
    Server server;
    DataInputStream dataIn;
    DataOutputStream dataOut;
    boolean serverRunning = true;
    Control ctrl;
    
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
                    case 1 : {
                        Integer cantJugadores = this.server.gson.fromJson(dataIncoming.getData(), Integer.class) ;
                        ctrl = new Control(cantJugadores);
                        response.setTipo(5);
                        response.setMensaje("Juego iniciado, esperando jugadores...");
                        response.setError(0);
                        response.setData(this.server.gson.toJson(ctrl.juego.getJugadores().get(0).getId()));
                        this.sendDataToClient(this.server.gson.toJson(response));
                    }
                    break;
                    case 2 : {
                        Ficha ficha = this.server.gson.fromJson(dataIncoming.getData(), Ficha.class);
                        System.out.println("Poniendo ficha: " + ficha.toString());
                        response.setTipo(2);
                        response.setMensaje("Ficha puesta!");
                        response.setError(0);                        
                        response.setData(this.server.gson.toJson(ficha));
                        this.sendDataToClient(this.server.gson.toJson(response));
                        
                    }
                    break; 
                    case 3 : {
                        
                    }
                    break;
                    case 100 : { //Caso de pruebas!
                        this.ctrl = new Control(4);
                        response.setTipo(100);
                        response.setData(this.server.gson.toJson(ctrl.getJuego()));
                        this.sendDataToClient(this.server.gson.toJson(response));
                    }
                    break;
                    case 4 : {
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
}
