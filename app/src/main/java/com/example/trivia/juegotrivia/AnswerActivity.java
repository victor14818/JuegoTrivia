package com.example.trivia.juegotrivia;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.print.PrintAttributes;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerActivity extends AppCompatActivity {

    Pregunta pregunta_actual = null;
    Long cateogira_id;
    Long tmp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent myIntent = getIntent();
        tmp1 = myIntent.getLongExtra("pregunta",-1);
        cateogira_id = myIntent.getLongExtra("categoria", -1);

/*        if(tmp >= 0) {
            ModeloCategoria md = new ModeloCategoria(this);
            System.out.println("tratando de leer a pregunta " + tmp);
            pregunta_actual = md.getPrgunta(tmp);
            md.destruir();
            colocarDatosPregunta();
            listar();
        }
        Button btn = (Button) findViewById(R.id.buttonApplyPrg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyPregunta(tmp);
            }
        });*/
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutRespuestas);
        linearLayout.removeAllViews();
        final Long tmp = tmp1;
        ModeloCategoria md = new ModeloCategoria(this);
        pregunta_actual = md.getPrgunta(tmp);
        md.destruir();
        colocarDatosPregunta();
        listar();
        Button btn = (Button) findViewById(R.id.buttonApplyPrg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyPregunta(tmp);
            }
        });
    }

    private void colocarDatosPregunta()
    {
        if(pregunta_actual != null) {
            EditText et;
            //nombre
            et = (EditText) findViewById(R.id.editText_textoPrg);
            et.setText("" + pregunta_actual.getTexto());
            //texto
            et = (EditText) findViewById(R.id.editText_ptsPrg);
            et.setText("" + pregunta_actual.getPuntos());
            //audio
            et = (EditText) findViewById(R.id.editText_audioPrg);
            et.setText("" + pregunta_actual.getAudio());
        }
    }

    private void listar()
    {
        if(pregunta_actual != null) {
            ModeloCategoria md = new ModeloCategoria(this);
            final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutRespuestas);
            final Respuesta[] respuestas = md.getAllRespuestas(pregunta_actual);
            Button btn;
            for (int i = 0; i < respuestas.length; i++) {
                final int aux = i;
                btn = new Button(this);
                btn.setText(respuestas[i].getTexto());
                btn.setTextAppearance(this, R.style.listText);
                btn.setWidth(linearLayout.getWidth());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog tmp = createSimpleDialog(respuestas[aux].getRowid(), linearLayout);
                        tmp.show();
                    }
                });
                if (respuestas[aux].isCorrecta() > 0) {
                    btn.setBackgroundColor(Color.rgb(11, 108, 4));
                }
                linearLayout.addView(btn);
            }
        }
    }

    private void ApplyPregunta(Long tmp)
    {
        try {
            EditText et;
            String t, p, a;
            ModeloCategoria md = new ModeloCategoria(this);

            et = (EditText) findViewById(R.id.editText_textoPrg);
            t = et.getText().toString();
            et = (EditText) findViewById(R.id.editText_ptsPrg);
            p = et.getText().toString();
            et = (EditText) findViewById(R.id.editText_audioPrg);
            a = et.getText().toString();

            if(!t.equals("") && !p.equals("")) {
                if (tmp >= 0) {
                    //modificar
                    Pregunta nv = new Pregunta(t, pregunta_actual.getCategoria(), Integer.parseInt(p), a, pregunta_actual.getRowid());
                    md.updatePregunta(nv);
                } else {
                    //ingresar
                    Pregunta nv = new Pregunta(t, cateogira_id, Integer.parseInt(p), a, Long.parseLong("-1"));
                    md.addPregutna(nv);
                }
                Toast.makeText(this, "Las modificaciones han sido correctas", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Debe llenar los campos con *", Toast.LENGTH_SHORT).show();
            }
            md.destruir();
        }catch(Exception e)
        {
            Toast.makeText(this, "Las modificaciones no han sido correctas", Toast.LENGTH_SHORT).show();
        }
    }

    public void addAnswer(View v)
    {
        if(pregunta_actual != null) {
            Intent AnswerIntent = new Intent(this, EditRespuesta.class);
            AnswerIntent.putExtra("respuesta", -1);
            AnswerIntent.putExtra("pregunta", pregunta_actual.getRowid());
            startActivity(AnswerIntent);
        }
    }

    private AlertDialog createSimpleDialog(final Long rowid, final LinearLayout l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Qué deseas hacer")
                .setMessage("Eliminar la respuesta o modificarla")
                .setPositiveButton("Modificar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent finIntent = new Intent(getApplicationContext(), EditRespuesta.class);
                                finIntent.putExtra("respuesta",rowid);
                                startActivity(finIntent);
                            }
                        })
                .setNegativeButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ModeloCategoria md = new ModeloCategoria(getApplicationContext());
                                md.deleteRespuesta(rowid);
                                l.removeAllViews();
                                listar();
                                md.destruir();
                            }
                        })
                .setCancelable(true);

        return builder.create();
    }
}
