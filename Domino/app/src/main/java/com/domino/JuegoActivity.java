package com.domino;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domino.Model.Accion;
import com.domino.Model.Ficha;
import com.domino.Model.Juego;
import com.domino.Model.Jugador;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class JuegoActivity extends AppCompatActivity {
    TextView texto;
    String idJugador;
    Juego juego;
    private static DataOutputStream dataOut;
    private static DataInputStream dataIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        dataIn=MainActivity.getDataIn();
        dataOut=MainActivity.getDataOut();
        texto=(TextView) findViewById(R.id.textView2);
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("idJugador")!= null)
        {
            idJugador=bundle.getString("idJugador");
            texto.setText("iD del jugador: "+idJugador);
        }

        new pedirJuegoTask().execute();


    }


    public ImageView ponerImagenFicha(Ficha f) {
        int numIzq=f.getNumIzq();
        int numDer=f.getNumDer();
        int doble=f.getEsDoble();
        if (doble==0) {
            switch (numDer) {
                case 0:{
                    switch (numIzq){
                        case 1: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d01);
                            return image;
                        }

                        case 2: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d02);
                            return image;
                        }

                        case 3: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d03);
                            return image;
                        }

                        case 4: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d04);
                            return image;
                        }

                        case 5: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d05);
                            return image;
                        }

                        case 6: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d06);
                            return image;
                        }
                    }
                }


                case 1:{
                    switch (numIzq){
                        case 2: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d12);
                            return image;
                        }

                        case 3: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d13);
                            return image;
                        }

                        case 4: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d14);
                            return image;
                        }

                        case 5: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d15);
                            return image;
                        }

                        case 6: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d16);
                            return image;
                        }
                    }
                }

                case 2:{
                    switch (numIzq){
                        case 3: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d23);
                            return image;
                        }

                        case 4: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d24);
                            return image;
                        }

                        case 5: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d25);
                            return image;
                        }

                        case 6: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d26);
                            return image;
                        }
                    }
                }
                case 3:{
                    switch (numIzq){
                        case 4: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d34);
                            return image;
                        }

                        case 5: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d35);
                            return image;
                        }

                        case 6: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d36);
                            return image;
                        }
                    }
                }
                case 4:{
                    switch (numIzq){
                        case 5: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d45);
                            return image;
                        }

                        case 6: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d46);
                            return image;
                        }
                    }
                }
                case 5:{
                    switch (numIzq){
                        case 6: {
                            ImageView image = new ImageView(this);
                            image.setImageResource(R.drawable.d56);
                            return image;
                        }
                    }
                }
            }
        }
        else {
            switch (numIzq) {
                case 0: {
                    ImageView image = new ImageView(this);
                    image.setImageResource(R.drawable.d00);
                    return image;
                }
                case 1: {
                    ImageView image = new ImageView(this);
                    image.setImageResource(R.drawable.d11);
                    return image;
                }

                case 2: {
                    ImageView image = new ImageView(this);
                    image.setImageResource(R.drawable.d22);
                    return image;
                }

                case 3: {
                    ImageView image = new ImageView(this);
                    image.setImageResource(R.drawable.d33);
                    return image;
                }

                case 4: {
                    ImageView image = new ImageView(this);
                    image.setImageResource(R.drawable.d44);
                    return image;
                }

                case 5: {
                    ImageView image = new ImageView(this);
                    image.setImageResource(R.drawable.d55);
                    return image;
                }

                case 6: {
                    ImageView image = new ImageView(this);
                    image.setImageResource(R.drawable.d66);
                    return image;
                }
            }
        }


        return null;
    }


    public class pedirJuegoTask extends AsyncTask<Void, Void, String> {

        private Accion accion;
        Gson gson;
        pedirJuegoTask(){
            accion=new Accion();
            gson=new Gson();
        }

        @Override
        protected void onPostExecute(String result) {
            Accion dataIncoming = gson.fromJson(result, Accion.class);
            Juego j=gson.fromJson(dataIncoming.getData(), Juego.class);
            juego=j;
            Jugador jug=juego.getJugadores().get(Integer.parseInt(idJugador)-1);

            LinearLayout layout = (LinearLayout)findViewById(R.id.Layout_fichas_jugador);

            for(int i=0;i<jug.getFichasDelJugador().size();i++) {
                ImageView image = ponerImagenFicha(jug.getFichasDelJugador().get(i));
                image.setOnTouchListener(new MyTouchListener());
                layout.addView(image);
            }

        }

        private final class MyTouchListener implements View.OnTouchListener {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                            view);
                    view.startDrag(data, shadowBuilder, view, 0);
                    view.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                accion.setTipo(4);
                accion.setMensaje("Estado del Juego");
                accion.setError(0);
                accion.setData(idJugador);
                dataOut.writeUTF(this.gson.toJson(accion));
                dataOut.flush();
                return dataIn.readUTF();
            } catch (IOException ex) {
                ex.getMessage();
            }

            return null;
        }
    }

}
