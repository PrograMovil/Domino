package com.domino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JuegoActivity extends AppCompatActivity {
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        texto=(TextView) findViewById(R.id.textView2);
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("idJugador")!= null)
        {
            texto.setText("iD del jugador: "+bundle.getString("idJugador"));
        }
    }
}
