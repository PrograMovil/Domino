
package testclient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestClient {

    TestClientConnection clientConnection;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TestClient();
    }
    
    public TestClient(){
        try {
            Socket socket = new Socket("localhost",3333);
            clientConnection = new TestClientConnection(socket);
            clientConnection.start();
            listenForData();
        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void listenForData(){
        Scanner console = new Scanner(System.in);
        
        while(true){
            
            while(!console.hasNextLine()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            String data = console.nextLine();
            
            if(data.toLowerCase().equals("exit")){
                break;
            }
            
            clientConnection.sendDataToServer(data);
            
        }
        
        clientConnection.closeAll();
    }
    
}
