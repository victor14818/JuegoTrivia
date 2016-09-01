package com.example.trivia.juegotrivia.jpreguntas;
import com.example.trivia.juegotrivia.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class jp1 extends AppCompatActivity {

    static int miVar=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jp1);

        //Setear el string que contiene los datos de punteo y tiempo
        String notyet = getString(R.string.datosPT, "100", "00:00");
        TextView textView1 = (TextView) findViewById(R.id.textView1_A);
        textView1.setText(notyet);

        Button textView = (Button) findViewById(R.id.pregunta_A);
        assert textView != null;
        textView.setText(++miVar+") "+"Ingresar pregunta");

        textView = (Button) findViewById(R.id.respuesta1_A);
        textView.setText("A) "+"Ingresar Respuesta1");
        textView = (Button) findViewById(R.id.respuesta2_A);
        textView.setText("B) "+"Ingresar Respuesta2");
        textView = (Button) findViewById(R.id.respuesta3_A);
        textView.setText("C) "+"Ingresar Respuesta3");
    }

    public void onButtonClick(View V) {
        if (V.getId() == R.id.respuesta1_A) {
            boton1();
        }else if(V.getId() == R.id.respuesta2_A){
            boton2();
        }else{//respuesta3
            boton3();
        }
    }

    private void boton1() {
        Intent i = new Intent(jp1.this, jp2.class);
        jp2.miVar=this.miVar;
        startActivity(i);
        overridePendingTransition(R.anim.animacion2,R.anim.animacion1);
        finish();
    }
    private void boton2() {
        Intent i = new Intent(jp1.this, jp2.class);
        jp2.miVar=this.miVar;
        startActivity(i);
        overridePendingTransition(R.anim.animacion2,R.anim.animacion1);
        finish();
    }
    private void boton3() {
        Intent i = new Intent(jp1.this, jp2.class);
        jp2.miVar=this.miVar;
        startActivity(i);
        overridePendingTransition(R.anim.animacion2,R.anim.animacion1);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.miVar=0;
        jp2.miVar=0;
        this.finish();
    }
}
