package com.example.trivia.juegotrivia.jpreguntas;
import com.example.trivia.juegotrivia.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class jp1 extends AppCompatActivity {

    static int miVar=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String CambiarNombre = "Pregunta NO."+ ++miVar;
        getSupportActionBar().setTitle(CambiarNombre);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jp1);
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
}
