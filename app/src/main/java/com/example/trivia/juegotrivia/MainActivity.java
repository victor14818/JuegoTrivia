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

                //Listener para Actividad
            }
        }
    }

    //inicio cuestionario
    public void btnpreguntas(View v) {
        Intent confIntent = new Intent(this, jp1.class);
        startActivity(confIntent);
    }


    //acción btn configuración
    public void btnCfg(View v) {
//        Intent confIntent = new Intent(this, CategoryListActivity.class);
//        startActivity(confIntent);

        Intent confIntent = new Intent(this, CategoryListActivity.class);
        startActivity(confIntent);
    }
}
