
package testClient;

import com.google.gson.Gson;
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
import model.Accion;
import model.Ficha;


public class TestClient {

    TestClientConnection clientConnection;
    Gson gson = new Gson();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TestClient();
    }
    
    public TestClient(){
        try {
            Socket socket = new Socket("localhost",3333);
            clientConnection = new TestClientConnection(socket,this);
            clientConnection.start();
//            Accion accion = new Accion(1,"Iniciar Juego",0);
//            clientConnection.sendDataToServer(accion);
            listenForData();
        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void listenForData(){
        Scanner console = new Scanner(System.in);
        
//        outerLoop:
        while(true){
            
            while(!console.hasNextLine()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            String data = console.nextLine();
            
//            if(data.toLowerCase().equals("exit")){
//                break;
//            }
            
            Accion accion = new Accion();
            switch(data){
                case "init" : {
                    accion.setTipo(1);
                    accion.setMensaje("Juego iniciado!");
                    accion.setError(0);
                    Integer cantJugadores = console.nextInt();
                    accion.setData(this.gson.toJson(cantJugadores));
                    clientConnection.sendDataToServer(this.gson.toJson(accion));                
                }
                break;
                case "ponerFicha" : {
                    accion.setTipo(2);
                    accion.setMensaje("Poner ficha!");
                    accion.setError(0);
                    Ficha ficha = new Ficha(6,6);
                    accion.setData(this.gson.toJson(ficha));
                    clientConnection.sendDataToServer(this.gson.toJson(accion));
                }
                break;
                case "comerFicha" : {
                    accion.setTipo(3);
                    accion.setMensaje("Comer ficha!");
                    accion.setError(0);
                    clientConnection.sendDataToServer(this.gson.toJson(accion));
                }
                break;
                case "end" : {
                    accion.setTipo(4);
                    accion.setMensaje("Juego Terminado!");
                    accion.setError(0);
                    clientConnection.sendDataToServer(this.gson.toJson(accion));
//                    break outerLoop;
                }
            }       
        }
        
//        clientConnection.closeAll();
    }
    
}
