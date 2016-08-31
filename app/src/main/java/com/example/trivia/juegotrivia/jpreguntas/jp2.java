package com.example.trivia.juegotrivia.jpreguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.trivia.juegotrivia.R;

public class jp2 extends AppCompatActivity {
    static int miVar=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         *
         * Consultar a BD para desplegar la informacion en el texto de los botone
         *
         **/
        String CambiarNombre = "Pregunta NO."+ ++miVar;
        getSupportActionBar().setTitle(CambiarNombre);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jp2);
    }

    public void onButtonClick(View V) {
        if (V.getId() == R.id.respuesta1) {
            boton1();
        }else if(V.getId() == R.id.respuesta2){
            boton2();
        }else if(V.getId() == R.id.pregunta){
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
}
