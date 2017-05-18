package com.domino;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.domino.Model.Juego;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCantidadJugadores;
    private Button botonJugar;
    private Juego juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonJugar=(Button) findViewById(R.id.button);
        spinnerCantidadJugadores=(Spinner) findViewById(R.id.spinner);

        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                juego=new Juego(Integer.parseInt((String) spinnerCantidadJugadores.getSelectedItem()));
                new InicioJuegoTask(juego).execute();
            }
        });
    }



    public class InicioJuegoTask extends AsyncTask<Void, Void, String> {

        private Juego j;
        InicioJuegoTask(Juego j){
            this.j=j;
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected String doInBackground(Void... params) {


            return null;
        }
    }

}
