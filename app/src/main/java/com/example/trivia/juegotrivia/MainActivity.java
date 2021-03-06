package com.example.trivia.juegotrivia;
import com.example.trivia.juegotrivia.jpreguntas.jp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    Categoria[] categorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializar las categorías
        ModeloCategoria md = new ModeloCategoria(this);
        if(md.IsEmpty("categoria"))
        {
            String [] categorias = new String[]{"Alta Verapaz","Sololá", "Petén", "Escuintla", "Quiché",
                    "Jutiapa", "Jalapa", "Izabal", "Chiquimula", "Quetzaltenango"};
            Categoria ct;
            for (int i = 0; i < categorias.length; i++) {
                ct = new Categoria(categorias[i],"","","","",Long.parseLong("-1"));
                md.addCategoria(ct);
            }
        }
        md.destruir();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //leer las categorías
        ModeloCategoria md = new ModeloCategoria(this);
        categorias = md.getAllCategorias();
        md.destruir();

        if(categorias != null) {
            ControladorRecursosExternos cre = new ControladorRecursosExternos("Trivia");
            //imprimir todas las imagenes de las categorías

            ImageView ibtmp;
            Bitmap bmtmp;
            for (int i = 0; i < categorias.length || i < 10; i++) {

                int idtmp = getResources().getIdentifier("imageViewTmp" + i, "id", getPackageName());
                ibtmp = (ImageView) findViewById(idtmp);
                try {
                    bmtmp = cre.readImage(categorias[i].getImgp());
                } catch (Exception e) {
                    //imprimir foto default
                    bmtmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.error);
                    System.out.println(e.getMessage());
                }
                ibtmp.setImageBitmap(bmtmp);

                //elegir las tres preguntas a usar
                Pregunta[] prgTmp = md.getAllPreguntas(categorias[i]);
                int limite = 3;

                if(prgTmp.length >= limite) {
                    final ArrayList<Long> prgSeleccionadas = new ArrayList();
                    Random rm = new Random();
                    for (int k = 0; k < limite; k++) {
                        Long auxa = prgTmp[rm.nextInt(prgTmp.length)].getRowid();
                        while (prgSeleccionadas.contains(auxa)) {
                            auxa = prgTmp[rm.nextInt(prgTmp.length)].getRowid();
                        }
                        prgSeleccionadas.add(auxa);
                    }

                    //Listener para Actividad
                    ibtmp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent confIntent = new Intent(getApplicationContext(), jp.class);
                            confIntent.putExtra("miVar", 0);
                            for (int y = 0; y < prgSeleccionadas.size(); y++) {
                                Long parametro = (Long) prgSeleccionadas.get(y);
                                confIntent.putExtra("p" + y, parametro);
                            }
                            startActivity(confIntent);
                        }
                    });
                }
            }
        }
    }


    //acción btn configuración
    public void btnCfg(View v) {
//        Intent confIntent = new Intent(this, CategoryListActivity.class);
//        startActivity(confIntent);

//        Intent confIntent = new Intent(this, CategoryListActivity.class);
//        startActivity(confIntent);

        Intent iintermedio = new Intent(this, intermedio.class);
        startActivity(iintermedio);
    }

    public void btninicio(View v) {
        Intent confIntent = new Intent(this, jp1.class);
        startActivity(confIntent);
    }

}
