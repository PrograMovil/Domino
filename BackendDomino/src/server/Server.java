
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    
    public static final int PORT = 3333;
    ServerSocket serverSocket;
    ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
    boolean serverRunning = true;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Servidor corriendo!!!. Esperando peticiones...");
        new Server();
    }
    
    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            while(serverRunning){
                Socket socket = serverSocket.accept();   
                ServerConnection serverConnection = new ServerConnection(socket,this);
                serverConnection.start();
                connections.add(serverConnection);
            }            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
