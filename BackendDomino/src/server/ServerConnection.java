
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            dataIn = new DataInputStream(this.socket.getInputStream());
            dataOut = new DataOutputStream(this.socket.getOutputStream());
            
            while(serverRunning){
                
                while(dataIn.available() == 0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                String dataIncoming = dataIn.readUTF();
                System.out.println("Data recibida: " + dataIncoming);
                this.sendDataToAllClients(dataIncoming);
            }
            
            this.closeAll();
            
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeAll() throws IOException{
        dataIn.close();
        dataOut.close();
        socket.close();
    }
}
