package com.domino;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

import com.domino.Model.Accion;
import com.domino.Model.Juego;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCantidadJugadores;
    private Button botonJugar;
    private Accion accion;

    Conexion clientConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        botonJugar=(Button) findViewById(R.id.button);
        spinnerCantidadJugadores=(Spinner) findViewById(R.id.spinner);

        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accion=new Accion();
                Integer cantJugadores = Integer.parseInt((String) spinnerCantidadJugadores.getSelectedItem());
                new InicioJuegoTask(accion, cantJugadores).execute();
            }
        });
    }



    public class InicioJuegoTask extends AsyncTask<Void, Void, Accion> {

        private Accion a;
        private Integer cantJugadores;
        Gson gson;
        InicioJuegoTask(Accion a, Integer c){
            cantJugadores=c;
            this.a=a;
            gson=new Gson();
        }

        @Override
        protected void onPostExecute(Accion result) {




        }

        @Override
        protected Accion doInBackground(Void... params) {
            try {
                Socket socket = new Socket("localhost",3333);
                clientConnection = new Conexion(socket);
                clientConnection.start();
                accion.setTipo(1);
                accion.setMensaje("Juego iniciado!");
                accion.setData(this.gson.toJson(cantJugadores));
                accion.setError(0);
                clientConnection.sendDataToServer(this.gson.toJson(accion));
                return accion;
            } catch (IOException ex) {

            }

            return null;
        }
    }

}
