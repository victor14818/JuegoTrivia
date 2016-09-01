package com.example.trivia.juegotrivia.jpreguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivia.juegotrivia.R;

public class jp2 extends AppCompatActivity {
    static public int miVar=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         *
         * Consultar a BD para desplegar la informacion en el texto de los botone
         *
         **/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jp2);

        //Setear al string que contiene los datos de punteo y tiempo
        String notyet = getString(R.string.datosPT, "100", "00:00");
        TextView textView1 = (TextView) findViewById(R.id.textView1_B);
        textView1.setText(notyet);

        Button textView = (Button) findViewById(R.id.pregunta);
        textView.setText(++miVar+") "+"Ingresar pregunta");

        textView = (Button) findViewById(R.id.respuesta1);
        textView.setText("A) "+"Ingresar respuesta1");
        textView = (Button) findViewById(R.id.respuesta2);
        textView.setText("B) "+"Ingresar respuesta2");
        textView = (Button) findViewById(R.id.respuesta3);
        textView.setText("C) "+"Ingresar respuesta3");
    }

    public void onButtonClick(View V) {
        if (V.getId() == R.id.respuesta1) {
            boton1();
        }else if(V.getId() == R.id.respuesta2){
            boton2();
        }else{//respuesta3_A
            boton3();
        }
    }

    private void boton1() {
        /**
         *
         * Comprobar respuesta1
         *
         **/
        Intent i = new Intent(jp2.this, jp1.class);
        jp1.miVar=this.miVar;
        startActivity(i);
        overridePendingTransition(R.anim.animacion2,R.anim.animacion1);
        finish();
    }
    private void boton2() {
        /**
         *
         * Comprobar respuesta2
         *
         **/
        Intent i = new Intent(jp2.this, jp1.class);
        jp1.miVar=this.miVar;
        startActivity(i);
        overridePendingTransition(R.anim.animacion2,R.anim.animacion1);
        finish();
    }
    private void boton3() {
        /**
         *
         * Comprobar respuesta3
         *
         **/
        Intent i = new Intent(jp2.this, jp1.class);
        jp1.miVar=this.miVar;
        startActivity(i);
        overridePendingTransition(R.anim.animacion2,R.anim.animacion1);
        finish();
    }
    @Override
    public void onBackPressed() {
        this.miVar=0;
        jp1.miVar=0;
        this.finish();
    }
}
