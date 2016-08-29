package com.example.trivia.juegotrivia;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class CategoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        inicializarCategorias();
        //listar();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutCategorias);
        linearLayout.removeAllViews();
        listar();
    }

    private void inicializarCategorias()
    {
        ModeloCategoria md = new ModeloCategoria(this);
        if(md.IsEmpty("categoria"))
        {
            String [] categorias = new String[]{"Alta Verapaz","Sololá", "Petén", "Escuintla", "Quiché",
                                                "Jutiapa", "Jalapa", "Izabal", "Chiquimula", "Quetzaltenango"};
            Categoria ct;
            for (int i = 0; i < categorias.length; i++) {
                ct = new Categoria(categorias[i],null,null,Long.parseLong("-1"));
                md.addCategoria(ct);
            }
        }
        md.destruir();
    }

    private void listar() {
        System.out.println("ahora vamos a listar Category");
        ModeloCategoria md = new ModeloCategoria(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutCategorias);
        final Categoria[] categorias = md.getAllCategorias();
        if(categorias != null) {
            Button btn;
            for (int i = 0; i < categorias.length; i++) {
                final int aux = i;
                btn = new Button(this);
                btn.setText(categorias[i].getNombre());
                btn.setTextAppearance(this, R.style.listText);
                btn.setWidth(linearLayout.getWidth());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*AlertDialog tmp = createSimpleDialog(categorias[aux].getRowid());
                        tmp.show();*/
                        Intent qyestionIntent = new Intent(getApplicationContext(), QuestionListActivity.class);
                        qyestionIntent.putExtra("categoria",categorias[aux].getRowid());
                        startActivity(qyestionIntent);
                    }
                });
                linearLayout.addView(btn);
            }
            md.destruir();
        }
    }

    /*private AlertDialog createSimpleDialog(final Long rowid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Qué deseas hacer")
                .setMessage("Eliminar la categoría o modificarla")
                .setPositiveButton("Modificar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent qyestionIntent = new Intent(getApplicationContext(), QuestionListActivity.class);
                                qyestionIntent.putExtra("categoria",rowid);
                                startActivity(qyestionIntent);
                            }
                        })
                .setNegativeButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ModeloCategoria md = new ModeloCategoria(getApplicationContext());
                                md.deleteCategoria(rowid);
                                md.destruir();
                            }
                        })
                .setCancelable(true);

        return builder.create();
    }*/
}
