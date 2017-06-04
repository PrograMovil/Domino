package com.domino;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.domino.Model.Accion;
import com.domino.Model.Juego;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCantidadJugadores;
    private Button botonJugar;
    private static DataOutputStream dataOut;
    private static DataInputStream dataIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        botonJugar=(Button) findViewById(R.id.button);
        spinnerCantidadJugadores=(Spinner) findViewById(R.id.spinner);


        new verificaJuegoIniciado().execute();
        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer cantJugadores = Integer.parseInt((String) spinnerCantidadJugadores.getSelectedItem());
                new InicioJuegoTask(cantJugadores).execute();
            }
        });
    }



    public class InicioJuegoTask extends AsyncTask<Void, Void, String> {

        private Accion accion;
        private Integer cantJugadores;
        Gson gson;
        Socket socket;


        InicioJuegoTask(Integer c){
            cantJugadores=c;
            accion=new Accion();
            gson=new Gson();
        }

        @Override
        protected void onPostExecute(String result) {
                Accion dataIncoming = gson.fromJson(result, Accion.class);
                if (dataIncoming.getError() == 0) {
                    String idJ = gson.fromJson(dataIncoming.getData(), String.class);
                    Intent intent = new Intent(MainActivity.this, JuegoActivity.class);
                    intent.putExtra("idJugador", idJ);
                    MainActivity.this.startActivity(intent);
                } else {
                    new obtenerIdTask().execute();
                }

        }

        @Override
        protected String doInBackground(Void... params) {
            try {

                accion.setTipo(1);
                accion.setMensaje("Iniciar nuevo Juego o unirse?");
                accion.setData(this.gson.toJson(cantJugadores));
                accion.setError(0);
                dataOut.writeUTF(this.gson.toJson(accion));
                dataOut.flush();
                return dataIn.readUTF();
            } catch (IOException ex) {
                ex.getMessage();
            }

            return null;
        }
    }

    public class verificaJuegoIniciado extends AsyncTask<Void, Void, String> {

            private Accion accion;
            Gson gson;

            verificaJuegoIniciado(){
            accion=new Accion();
            gson=new Gson();
        }

            @Override
            protected void onPostExecute(String result) {
                    Accion dataIncoming = gson.fromJson(result, Accion.class);
                    String data=gson.fromJson(dataIncoming.getData(), String.class);

                    if(data.equals("1")){ //Hay un juego en curso por lo que lo envia al juego activity
                        new obtenerIdTask().execute();
                    }




        }

            @Override
            protected String doInBackground(Void... params) {
            try {
                socket socket= com.domino.socket.getInstancia();
                dataOut=new DataOutputStream(socket.getSocket().getOutputStream());
                dataIn = new DataInputStream(socket.getSocket().getInputStream());
                accion.setTipo(0);
                accion.setError(0);
                dataOut.writeUTF(this.gson.toJson(accion));
                dataOut.flush();
                return dataIn.readUTF();
            } catch (IOException ex) {
                ex.getMessage();
            }

            return null;
        }
    }

    public class obtenerIdTask extends AsyncTask<Void, Void, String> {

        private Accion accion;
        Gson gson;

        obtenerIdTask(){
            accion=new Accion();
            gson=new Gson();
        }

        @Override
        protected void onPostExecute(String result) {
            Accion dataIncoming = gson.fromJson(result, Accion.class);
            String data=gson.fromJson(dataIncoming.getData(), String.class);
            if(data.equals("-1")){
                Toast.makeText(MainActivity.this, "Juego lleno, intente mas tarde",Toast.LENGTH_LONG).show();
                botonJugar.setEnabled(false);
            }
            else {
                Intent intent = new Intent(MainActivity.this, JuegoActivity.class);
                intent.putExtra("idJugador", data);
                MainActivity.this.startActivity(intent);
            }



        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                accion.setTipo(5);
                accion.setError(0);
                dataOut.writeUTF(this.gson.toJson(accion));
                dataOut.flush();
                return dataIn.readUTF();
            } catch (IOException ex) {
                ex.getMessage();
            }

            return null;
        }
    }


    public static DataOutputStream getDataOut() {
        return dataOut;
    }

    public static DataInputStream getDataIn() {
        return dataIn;
    }
}
