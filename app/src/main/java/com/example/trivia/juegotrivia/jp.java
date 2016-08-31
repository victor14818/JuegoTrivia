package com.example.trivia.juegotrivia;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class jp extends AppCompatActivity {

    //static int miVar=0;
    int miVar = -1;
    Pregunta pregunta = null;
    Respuesta respuestas [] = new Respuesta[3];
    Long p1,p2,p3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jp);

        //obteniendo pregunta
        ModeloCategoria md = new ModeloCategoria(this);
        Intent myIntent = getIntent();

        miVar = myIntent.getIntExtra("miVar",-1);
        //String CambiarNombre = "Pregunta NO."+ miVar;
        //getSupportActionBar().setTitle(CambiarNombre);

        p1 = myIntent.getLongExtra("p0",-1);
        p2 = myIntent.getLongExtra("p1",-1);
        p3 = myIntent.getLongExtra("p2",-1);

        switch (miVar)
        {
            case 0:
                pregunta = md.getPrgunta(p1);
                break;
            case 1:
                pregunta = md.getPrgunta(p2);
                break;
            case 2:
                pregunta = md.getPrgunta(p3);
                break;
        }

        //obtener Tres Respuestas
        if(pregunta != null) {
            Respuesta[] rptTrue = md.getAllRespuestasFilter(pregunta, 1);
            Respuesta[] rptFalse = md.getAllRespuestasFilter(pregunta, 0);

            Respuesta correcta;
            Respuesta incorrecta1;
            Respuesta incorrecta2;

            //almenos tres preguntas 1 correcta y 2 Falsas
            if (rptTrue != null && rptFalse != null) {
                if (rptTrue.length > 0 && rptFalse.length > 1) {
                    Random rm = new Random();
                    //obtener correcta
                    correcta = rptTrue[rm.nextInt(rptTrue.length)];
                    //obtener incorrectas
                    incorrecta1 = rptFalse[rm.nextInt(rptFalse.length)];
                    incorrecta2 = incorrecta1;
                    //comprobar que no sean la misma
                    while (incorrecta1.equals(incorrecta2)) {
                        incorrecta2 = rptFalse[rm.nextInt(rptFalse.length)];
                    }


                    int posTmp = rm.nextInt(3);
                    int aux = 0;
                    while (aux < 3) {
                        switch (aux) {
                            case 0:
                                respuestas[posTmp] = incorrecta1;
                                break;
                            case 1:
                                respuestas[posTmp] = correcta;
                                break;
                            case 2:
                                respuestas[posTmp] = incorrecta2;
                                break;
                        }
                        posTmp++;
                        if (posTmp >= 3) posTmp = 0;
                        aux++;
                    }
                    Button textView;
                    textView = (Button) findViewById(R.id.respuesta1_P);
                    textView.setText("A) "+ respuestas[0].getTexto());
                    textView = (Button) findViewById(R.id.respuesta2_P);
                    textView.setText("B) "+ respuestas[1].getTexto());
                    textView = (Button) findViewById(R.id.respuesta3_P);
                    textView.setText("C) "+ respuestas[2].getTexto());
                }
            }
            //Colocar Texto Pregunta
            Button textView = (Button) findViewById(R.id.pregunta_P);
            assert textView != null;
            textView.setText(miVar+") "+ pregunta.getTexto().toUpperCase());
        }
        md.destruir();
    }

    public void onButtonClick(View V) {
        if (V.getId() == R.id.respuesta1_P) {
            boton1();
        }else if(V.getId() == R.id.respuesta2_P){
            boton2();
        }else{//respuesta3
            boton3();
        }
    }

    private void boton1() {
        if(respuestas[0]!=null) {
            if(respuestas[0].isCorrecta() == 1)
            {
                System.out.println("es correcta sumar " + pregunta.getPuntos());
            }
            else
            {
                System.out.println("No es correcta ");
            }
            miVar = miVar + 1;
            if(miVar < 3){
                Intent i = new Intent(this, jp.class);
                i.putExtra("miVar",miVar);
                i.putExtra("p0",p1);
                i.putExtra("p1",p2);
                i.putExtra("p2",p3);
                startActivity(i);
                overridePendingTransition(R.anim.animacion2, R.anim.animacion1);
                finish();
            }
            else
            {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animacion2, R.anim.animacion1);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        }
    }
    private void boton2() {
        if(respuestas[1]!=null) {
            if(respuestas[1].isCorrecta() == 1)
            {
                System.out.println("es correcta sumar " + pregunta.getPuntos());
            }
            else
            {
                System.out.println("No es correcta ");
            }
            miVar = miVar + 1;
            if(miVar < 3){
                Intent i = new Intent(this, jp.class);
                i.putExtra("miVar",miVar);
                i.putExtra("p0",p1);
                i.putExtra("p1",p2);
                i.putExtra("p2",p3);
                startActivity(i);
                overridePendingTransition(R.anim.animacion2, R.anim.animacion1);
                finish();
            }
            else
            {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animacion2, R.anim.animacion1);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        }
    }
    private void boton3() {
        if(respuestas[2]!=null) {
            if(respuestas[2].isCorrecta() == 1)
            {
                System.out.println("es correcta sumar " + pregunta.getPuntos());
            }
            else
            {
                System.out.println("No es correcta ");
            }
            miVar = miVar + 1;
            if(miVar < 3){
                Intent i = new Intent(this, jp.class);
                i.putExtra("miVar",miVar);
                i.putExtra("p0",p1);
                i.putExtra("p1",p2);
                i.putExtra("p2",p3);
                startActivity(i);
                overridePendingTransition(R.anim.animacion2, R.anim.animacion1);
                finish();
            }
            else
            {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animacion2, R.anim.animacion1);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
    }
}
