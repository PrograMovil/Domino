
package testclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestClientConnection extends Thread {
    
    Socket socket;
//    TestClient client;
    DataInputStream dataIn;
    DataOutputStream dataOut;
    boolean connectionRunning = true;
    
    public TestClientConnection(Socket socket){
        this.socket = socket;
//        this.client = client;
        
    }
    
    public void sendDataToServer(String data){
        try {
            dataOut.writeUTF(data);
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(TestClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            this.closeAll();
        }
    }
    
    public void run(){
        try {
            this.dataIn = new DataInputStream(this.socket.getInputStream());
            this.dataOut = new DataOutputStream(this.socket.getOutputStream());
            
            while(this.connectionRunning){

                try {
                    while(dataIn.available() == 0){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TestClientConnection.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    String dataIncoming = dataIn.readUTF();
                    System.out.println(dataIncoming);

                } catch (IOException ex) {
                    Logger.getLogger(TestClientConnection.class.getName()).log(Level.SEVERE, null, ex);
                    this.closeAll();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TestClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            this.closeAll();
        }
        
        
    }
    
    public void closeAll(){
        try {
            dataIn.close();
            dataOut.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(TestClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
