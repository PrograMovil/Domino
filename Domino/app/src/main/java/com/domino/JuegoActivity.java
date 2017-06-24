package com.domino;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.domino.Model.Accion;
import com.domino.Model.Ficha;
import com.domino.Model.Juego;
import com.domino.Model.Jugada;
import com.domino.Model.Jugador;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class JuegoActivity extends AppCompatActivity {
    TextView idTurno;
    ImageButton tomarDePozo;
    int idJugador;
    Juego juego;
    boolean primerTurno;
    private static DataOutputStream dataOut;
    private static DataInputStream dataIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        primerTurno=false;

        dataIn=MainActivity.getDataIn();
        dataOut=MainActivity.getDataOut();
        idTurno=(TextView) findViewById(R.id.turno);
        tomarDePozo=(ImageButton) findViewById(R.id.agregarFicha);
        tomarDePozo.setVisibility(View.INVISIBLE);
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("idJugador")!= null)
        {
            idJugador=Integer.parseInt(bundle.getString("idJugador"));
        }
        setTitle("Jugador #"+idJugador);

        tomarDePozo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(juego.getTurno()==idJugador){ //verificar si le toca jugar
                    if(juego.getFichasDelPozo().size()>0) {
                        Ficha fichaPozo = juego.getFichasDelPozo().remove(0);
                        new tomarPozoTask(getApplicationContext(),fichaPozo).execute();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No quedan fichas en el pozo, game Over",Toast.LENGTH_LONG).show();
                        new esperaDataIn(getApplicationContext()).execute();
                        tomarDePozo.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Espere su turno",Toast.LENGTH_LONG).show();
                }

            }
        });


        new pedirJuegoInicialTask(getApplicationContext()).execute();


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


    //Al mover ficha
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if(juego.getTurno()==idJugador){ //verificar si le toca jugar

                        if(juego.getJugadoresOk().contains(idJugador)){ //verificar si tiene fichas para jugar
                            ClipData data = ClipData.newPlainText("", "");
                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                    view);
                            view.startDrag(data, shadowBuilder, view, 0);
                            //view.setVisibility(View.INVISIBLE);
                            return true;
                        }
                    Toast.makeText(getApplicationContext(), "No tiene fichas para jugar, tome del pozo",Toast.LENGTH_LONG).show();
                    return false;
                }

                Toast.makeText(getApplicationContext(), "Espere su turno",Toast.LENGTH_LONG).show();
                return false;

            } else {
                return false;
            }
        }

    }


    //Al dropear Ficha
    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.dropzone);
        //Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    ImageView container = (ImageView) v;  //elemento a donde se va a mover la ficha
                    View view = (View) event.getLocalState(); //ficha que se esta moviendo
                    boolean rotar90=false, rotar270=false;
                    customTag t1=(customTag) view.getTag();

                    Ficha f1=new Ficha(t1.numIzq,t1.numDer);

                    if(container.getTag().equals("izquierda")){
                        f1.setLadoJuego('I');
                        if(primerTurno==false && juego.getMano()==idJugador){
                            if(t1.numIzq==juego.getOpDeJuegoALaIzq() && t1.numDer==juego.getOpDeJuegoALaIzq()){
                                primerTurno=true;
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Debe mover la ficha doble mayor que tenga",Toast.LENGTH_LONG).show();
                                break;
                            }
                        }else if(t1.numIzq==juego.getOpDeJuegoALaIzq()){
                                if(t1.iguales!=1)
                                    rotar270=true;
                            } else if(t1.numDer==juego.getOpDeJuegoALaIzq()){
                                if(t1.iguales!=1)
                                    rotar90=true;
                            }else{
                                Toast.makeText(getApplicationContext(), "No puede mover esta ficha aqui",Toast.LENGTH_LONG).show();
                                break;
                            }
                    }else if(container.getTag().equals("derecha")){
                        f1.setLadoJuego('D');
                        if(t1.numIzq==juego.getOpDeJuegoALaDer()){
                            if(t1.iguales!=1)
                                rotar90=true;
                        } else if(t1.numDer==juego.getOpDeJuegoALaDer()){
                            if(t1.iguales!=1)
                                rotar270=true;
                        }else{
                            Toast.makeText(getApplicationContext(), "No puede mover esta ficha aqui",Toast.LENGTH_LONG).show();
                            break;
                        }
                    }

                    ViewGroup owner = (ViewGroup) view.getParent(); //pila de fichas jugador
                    owner.removeView(view); //borrando la ficha que se mueve


                    ImageView img=(ImageView)view;
                    Drawable clone = img.getDrawable().getConstantState().newDrawable(); //obtener una copia del drawable que se va a poner
                    container.setImageDrawable(clone); //moviendo la ficha
                    container.setPadding(0,0,20,0);


                    container.setOnDragListener(null);
                    if(rotar90){
                        container.setRotation(90);
                        f1.setOrientacion(90);
                    }
                    else if(rotar270){
                        container.setRotation(270);
                        f1.setOrientacion(270);
                    }




                    new ponerFichaTask(getApplicationContext(), f1).execute();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }



    public class pedirJuegoInicialTask extends AsyncTask<Void, Void, String> {

        private Accion accion;
        Gson gson;
        private Context context;
        pedirJuegoInicialTask(Context context){
            this.context=context;
            accion=new Accion();
            gson=new Gson();
        }

        @Override
        protected void onPostExecute(String result) {
            Accion dataIncoming = gson.fromJson(result, Accion.class);
            Juego j=gson.fromJson(dataIncoming.getData(), Juego.class);
            juego=j;
            Jugador jug=juego.getJugadores().get(idJugador-1);

            idTurno.setText(Integer.toString(juego.getTurno()));

            LinearLayout layoutFichasJugador = (LinearLayout)findViewById(R.id.Layout_fichas_jugador);
            LinearLayout layoutDropZone = (LinearLayout) findViewById(R.id.LayoutDropZone);

            if(juego.getTurno()==idJugador){ //verificar si le toca jugar
                if(juego.getJugadoresOk().contains(idJugador)) //verificar si tiene fichas para jugar
                    tomarDePozo.setVisibility(View.INVISIBLE);
                else tomarDePozo.setVisibility(View.VISIBLE); //habilitar boton para tomar del pozo
            }

            for(int i=0;i<jug.getFichasDelJugador().size();i++) {
                ImageView image = ponerImagenFicha(jug.getFichasDelJugador().get(i));
                image.setTag(new customTag(jug.getFichasDelJugador().get(i).getNumIzq(),jug.getFichasDelJugador().get(i).getNumDer(),jug.getFichasDelJugador().get(i).getEsDoble()));
                image.setOnTouchListener(new MyTouchListener());

                layoutFichasJugador.addView(image);
            }

            ImageView imageDropZone = new ImageView(context);
            imageDropZone.setImageResource(R.drawable.dropzone);
            imageDropZone.setTag("izquierda");
            imageDropZone.setOnDragListener(new MyDragListener());
            layoutDropZone.addView(imageDropZone);

            if(j.getFichasJugadas().size()>0){
                for(Ficha f : j.getFichasJugadas()){
                    ImageView fichaEnJuego = ponerImagenFicha(f);
                    //fichaEnJuego.setBackgroundResource(R.drawable.dropped);
                    fichaEnJuego.setPadding(0,0,20,0);
                    fichaEnJuego.setRotation(f.getOrientacion());
                    layoutDropZone.addView(fichaEnJuego);
                }
                ImageView imageDropZone2 = new ImageView(context);
                imageDropZone2.setImageResource(R.drawable.dropzone);
                imageDropZone2.setTag("derecha");
                imageDropZone2.setOnDragListener(new MyDragListener());
                layoutDropZone.addView(imageDropZone2);
            }

            if(j.getTurno()!=idJugador){
                new esperaDataIn(context).execute();
            }


        }



        @Override
        protected String doInBackground(Void... params) {
            try {
                accion.setTipo(4);
                accion.setMensaje("Estado del Juego");
                accion.setError(0);
                accion.setData(Integer.toString(idJugador));
                dataOut.writeUTF(this.gson.toJson(accion));
                dataOut.flush();
                return dataIn.readUTF();
            } catch (IOException ex) {
                ex.getMessage();
            }

            return null;
        }
    }


    public class esperaDataIn extends AsyncTask<Void, Void, String> {

        private Accion accion;
        Gson gson;
        private Context context;
        esperaDataIn(Context context){
            this.context=context;
            accion=new Accion();
            gson=new Gson();
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("Error")){ //se perdio la conexion con el servidor
                Toast.makeText(getApplicationContext(), "Error: se perdio la conexion con el servidor de Domino",Toast.LENGTH_LONG).show();
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                finish();
            }
            else{
                Accion dataIncoming = gson.fromJson(result, Accion.class);
                if(dataIncoming.getError()==0 && dataIncoming.getTipo()==2){
                    Juego j=gson.fromJson(dataIncoming.getData(), Juego.class);
                    juego=j;
                    idTurno.setText(Integer.toString(juego.getTurno()));
                    Jugador jug=juego.getJugadores().get(idJugador-1);

                    if(j.getGanador()!=0){
                        if(j.getGanador()==idJugador){
                            Intent intent = new Intent(JuegoActivity.this, Ganador.class);
                            JuegoActivity.this.startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(JuegoActivity.this, perdedor.class);
                            JuegoActivity.this.startActivity(intent);
                        }
                    }

                    if(juego.getTurno()==idJugador){ //verificar si le toca jugar
                        if(juego.getJugadoresOk().contains(idJugador)) //verificar si tiene fichas para jugar
                            tomarDePozo.setVisibility(View.INVISIBLE);
                        else tomarDePozo.setVisibility(View.VISIBLE); //habilitar boton para tomar del pozo
                    }

                    LinearLayout layoutDropZone = (LinearLayout) findViewById(R.id.LayoutDropZone);
                    if(layoutDropZone.getChildCount() > 0)
                        layoutDropZone.removeAllViews();

                    ImageView imageDropZone = new ImageView(context);
                    imageDropZone.setImageResource(R.drawable.dropzone);
                    imageDropZone.setTag("izquierda");
                    imageDropZone.setOnDragListener(new MyDragListener());
                    layoutDropZone.addView(imageDropZone);

                    if(j.getFichasJugadas().size()>0){
                        for(Ficha f : j.getFichasJugadas()){
                            ImageView fichaEnJuego = ponerImagenFicha(f);
                            //fichaEnJuego.setBackgroundResource(R.drawable.dropped);
                            fichaEnJuego.setPadding(0,0,20,0);
                            fichaEnJuego.setRotation(f.getOrientacion());
                            layoutDropZone.addView(fichaEnJuego);
                        }
                        ImageView imageDropZone2 = new ImageView(context);
                        imageDropZone2.setImageResource(R.drawable.dropzone);
                        imageDropZone2.setTag("derecha");
                        imageDropZone2.setOnDragListener(new MyDragListener());
                        layoutDropZone.addView(imageDropZone2);
                    }
                    if(j.getTurno()!=idJugador){
                        new esperaDataIn(context).execute();
                    }

                }
                else if(dataIncoming.getMensaje().equals("ERROR: No se pudo comer la ficha!")){
                    Juego j=gson.fromJson(dataIncoming.getData(), Juego.class);
                    juego=j;
                    idTurno.setText(Integer.toString(juego.getTurno()));
                    Jugador jug=juego.getJugadores().get(idJugador-1);

                    if(j.getGanador()!=0){
                        if(j.getGanador()==idJugador){
                            Intent intent = new Intent(JuegoActivity.this, Ganador.class);
                            JuegoActivity.this.startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(JuegoActivity.this, perdedor.class);
                            JuegoActivity.this.startActivity(intent);
                        }
                    }

                    if(juego.getTurno()==idJugador){ //verificar si le toca jugar
                        if(juego.getJugadoresOk().contains(idJugador)) //verificar si tiene fichas para jugar
                            tomarDePozo.setVisibility(View.INVISIBLE);
                        else tomarDePozo.setVisibility(View.VISIBLE); //habilitar boton para tomar del pozo
                    }

                    LinearLayout layoutDropZone = (LinearLayout) findViewById(R.id.LayoutDropZone);
                    if(layoutDropZone.getChildCount() > 0)
                        layoutDropZone.removeAllViews();

                    ImageView imageDropZone = new ImageView(context);
                    imageDropZone.setImageResource(R.drawable.dropzone);
                    imageDropZone.setTag("izquierda");
                    imageDropZone.setOnDragListener(new MyDragListener());
                    layoutDropZone.addView(imageDropZone);

                    if(j.getFichasJugadas().size()>0){
                        for(Ficha f : j.getFichasJugadas()){
                            ImageView fichaEnJuego = ponerImagenFicha(f);
                            //fichaEnJuego.setBackgroundResource(R.drawable.dropped);
                            fichaEnJuego.setPadding(0,0,20,0);
                            fichaEnJuego.setRotation(f.getOrientacion());
                            layoutDropZone.addView(fichaEnJuego);
                        }
                        ImageView imageDropZone2 = new ImageView(context);
                        imageDropZone2.setImageResource(R.drawable.dropzone);
                        imageDropZone2.setTag("derecha");
                        imageDropZone2.setOnDragListener(new MyDragListener());
                        layoutDropZone.addView(imageDropZone2);
                    }
                    if(j.getTurno()!=idJugador){
                        new esperaDataIn(context).execute();
                    }
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                return dataIn.readUTF();
            } catch (IOException ex) {
                return "Error";

            }
        }
    }


    public class ponerFichaTask extends AsyncTask<Void, Void, String> {

        private Accion accion;
        Gson gson;
        private Ficha fichaMovida;
        private Context context;

        ponerFichaTask(Context context, Ficha fMov){
            this.context=context;
            accion=new Accion();
            gson=new Gson();
            fichaMovida=fMov;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result==null){ //se perdio la conexion con el servidor
                Toast.makeText(getApplicationContext(), "Error: se perdio la conexion con el servidor de Domino",Toast.LENGTH_LONG).show();
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                finish();
            }
            else {
                Accion dataIncoming = gson.fromJson(result, Accion.class);
                if (dataIncoming.getError() == 0 && dataIncoming.getTipo() == 2) {
                    Juego j = gson.fromJson(dataIncoming.getData(), Juego.class);
                    juego = j;
                    idTurno.setText(Integer.toString(juego.getTurno()));
                    Jugador jug = juego.getJugadores().get(idJugador - 1);

                    if(j.getGanador()!=0){
                        if(j.getGanador()==idJugador){
                            Intent intent = new Intent(JuegoActivity.this, Ganador.class);
                            JuegoActivity.this.startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(JuegoActivity.this, perdedor.class);
                            JuegoActivity.this.startActivity(intent);
                        }
                    }

                    if (juego.getTurno() == idJugador) { //verificar si le toca jugar
                        if (juego.getJugadoresOk().contains(idJugador)) //verificar si tiene fichas para jugar
                            tomarDePozo.setVisibility(View.INVISIBLE);
                        else
                            tomarDePozo.setVisibility(View.VISIBLE); //habilitar boton para tomar del pozo
                    }

                    LinearLayout layoutDropZone = (LinearLayout) findViewById(R.id.LayoutDropZone);
                    if (layoutDropZone.getChildCount() > 0)
                        layoutDropZone.removeAllViews();
                    ImageView imageDropZone = new ImageView(context);
                    imageDropZone.setImageResource(R.drawable.dropzone);
                    imageDropZone.setTag("izquierda");
                    imageDropZone.setOnDragListener(new MyDragListener());
                    layoutDropZone.addView(imageDropZone);

                    if (j.getFichasJugadas().size() > 0) {
                        for (Ficha f : j.getFichasJugadas()) {
                            ImageView fichaEnJuego = ponerImagenFicha(f);
                            //fichaEnJuego.setBackgroundResource(R.drawable.dropped);
                            fichaEnJuego.setRotation(f.getOrientacion());
                            fichaEnJuego.setPadding(0,0,20,0);
                            layoutDropZone.addView(fichaEnJuego);
                        }
                        ImageView imageDropZone2 = new ImageView(context);
                        imageDropZone2.setImageResource(R.drawable.dropzone);
                        imageDropZone2.setTag("derecha");
                        imageDropZone2.setOnDragListener(new MyDragListener());
                        layoutDropZone.addView(imageDropZone2);
                    }
                    if (j.getTurno() != idJugador) {
                        new esperaDataIn(context).execute();
                    }

                }
            }
        }



        @Override
        protected String doInBackground(Void... params) {
            try {
                accion.setTipo(2);
                accion.setMensaje("Ficha puesta");
                accion.setError(0);
                Jugada jugada=new Jugada(idJugador,fichaMovida);
                accion.setData(this.gson.toJson(jugada));
                dataOut.writeUTF(this.gson.toJson(accion));
                dataOut.flush();
                return dataIn.readUTF();
            } catch (IOException ex) {
                ex.getMessage();
            }

            return null;
        }
    }


    public class tomarPozoTask extends AsyncTask<Void, Void, String> {

        private Accion accion;
        Gson gson;
        private Context context;
        private Ficha fichaPozo;
        tomarPozoTask(Context context, Ficha fPozo){
            this.context=context;
            accion=new Accion();
            gson=new Gson();
            fichaPozo=fPozo;
        }

        @Override
        protected void onPostExecute(String result) {
            Accion dataIncoming = gson.fromJson(result, Accion.class);
            if(dataIncoming.getError()==0){
                Juego j=gson.fromJson(dataIncoming.getData(), Juego.class);
                juego=j;
                Jugador jug=juego.getJugadores().get(idJugador-1);

                if(j.getGanador()!=0){
                    if(j.getGanador()==idJugador){
                        Intent intent = new Intent(JuegoActivity.this, Ganador.class);
                        JuegoActivity.this.startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(JuegoActivity.this, perdedor.class);
                        JuegoActivity.this.startActivity(intent);
                    }
                }

                ImageView image = ponerImagenFicha(fichaPozo);
                image.setTag(new customTag(fichaPozo.getNumIzq(),fichaPozo.getNumDer(),fichaPozo.getEsDoble()));
                image.setOnTouchListener(new MyTouchListener());
                LinearLayout layoutFichasJugador = (LinearLayout)findViewById(R.id.Layout_fichas_jugador);
                layoutFichasJugador.addView(image);

                if(juego.getTurno()==idJugador){ //verificar si le toca jugar
                    if(juego.getJugadoresOk().contains(idJugador)) //verificar si tiene fichas para jugar
                        tomarDePozo.setVisibility(View.INVISIBLE); //deshabilitar boton tomar de pozo
                    else tomarDePozo.setVisibility(View.VISIBLE); //habilitar boton para tomar del pozo
                }


                if(j.getTurno()!=idJugador){
                    new esperaDataIn(context).execute();
                }

            }
            else{ //se acabaron las fichas del pozo
                new esperaDataIn(context).execute();
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                accion.setTipo(3);
                accion.setMensaje("Tomando del pozo");
                accion.setError(0);
                Jugada jugada=new Jugada(idJugador,fichaPozo);
                accion.setData(this.gson.toJson(jugada));
                dataOut.writeUTF(this.gson.toJson(accion));
                dataOut.flush();
                return dataIn.readUTF();
            } catch (IOException ex) {
                ex.getMessage();
            }
            return null;
        }
    }


    //clase para enviar varios tags en los elementos
    public class customTag{
        int numIzq, numDer, iguales;
        public customTag(int izq, int der, int igu){
            numIzq=izq;
            numDer=der;
            iguales=igu;
        }
    }


}




















