package com.example.trivia.juegotrivia;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionListActivity extends AppCompatActivity {

    Categoria cat_actual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        Intent myIntent = getIntent();
        Long tmp = myIntent.getLongExtra("categoria",0);

        ModeloCategoria md = new ModeloCategoria(this);
        System.out.println("tratando de leer a " + tmp);
        cat_actual =  md.getCategoria(tmp);
        md.destruir();

        colocarDatosCategoria();
        //listar();
        Button btn = (Button) findViewById(R.id.buttonApplyCat);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyCategoria();
            }
        });


    }

    @Override
    protected void onStart()
    {
        super.onStart();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutPreguntas);
        linearLayout.removeAllViews();
        listar();
    }

    private void colocarDatosCategoria()
    {
        EditText et;
        //nombre
        TextView tv = (TextView) findViewById(R.id.TextViewTituloCategoria);
        tv.setText(""+cat_actual.getNombre());
        tv.setTextSize(40);

        et = (EditText) findViewById(R.id.editText_nombreCat);
        et.setText(""+cat_actual.getNombre());
        //texto
        et = (EditText) findViewById(R.id.editText_textoCat);
        et.setText(""+cat_actual.getTexto());

        //imagen Menu
        et = (EditText) findViewById(R.id.editText_audioCat);
        et.setText(""+cat_actual.getImgp());

        //imagen Segunda
        et = (EditText) findViewById(R.id.editText_imgpCat);
        et.setText(""+cat_actual.getImgs());

        //audio
        et = (EditText) findViewById(R.id.editText_imgsCat);
        et.setText(""+cat_actual.getAudio());
    }

    private void listar()
    {

        ModeloCategoria md = new ModeloCategoria(this);
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layoutPreguntas);
        final Pregunta [] preguntas = md.getAllPreguntas(cat_actual);
        Button btn;
        for (int i = 0; i < preguntas.length; i++) {
            final int aux = i;
            btn = new Button(this);
            btn.setText(preguntas[i].getTexto());
            btn.setTextAppearance(this,R.style.listText);
            btn.setWidth(linearLayout.getWidth());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*                    Intent answerIntent = new Intent(getApplicationContext(), AnswerActivity.class);
                    answerIntent.putExtra("pregunta",preguntas[aux].getRowid());
                    startActivity(answerIntent);*/
                    AlertDialog tmp = createSimpleDialog(preguntas[aux].getRowid(),linearLayout);
                    tmp.show();
                }
            });
            linearLayout.addView(btn);
        }

    }

    private void ApplyCategoria()
    {
        try {
            EditText et;
            String n, t, a, ip, ims;
            ModeloCategoria md = new ModeloCategoria(this);

            et = (EditText) findViewById(R.id.editText_nombreCat);
            n = et.getText().toString();
            et = (EditText) findViewById(R.id.editText_textoCat);
            t = et.getText().toString();
            et = (EditText) findViewById(R.id.editText_audioCat);
            a = et.getText().toString();
            et = (EditText) findViewById(R.id.editText_imgpCat);
            ip = et.getText().toString();
            et = (EditText) findViewById(R.id.editText_imgsCat);
            ims = et.getText().toString();

            if(!n.equals("") && !t.equals("")) {
                Categoria nv = new Categoria(n, t, a, ip, ims, cat_actual.getRowid());
                md.updateCategoria(nv);
                Toast.makeText(this, "Las modificaciones han sido correctas", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Debe llenar los campos con *", Toast.LENGTH_SHORT).show();
            }
            md.destruir();
        }catch(Exception e)
        {
            Toast.makeText(this, "Las modificaciones no han sido correctas", Toast.LENGTH_SHORT).show();
        }
    }

    public void addQuestion(View v)
    {
        Intent AnswerIntent = new Intent(this, AnswerActivity.class);
        AnswerIntent.putExtra("pregunta", -1);
        AnswerIntent.putExtra("categoria", cat_actual.getRowid());
        startActivity(AnswerIntent);
    }

    private AlertDialog createSimpleDialog(final Long rowid, final LinearLayout l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Qué deseas hacer")
                .setMessage("Eliminar la pregunta o modificarla")
                .setPositiveButton("Modificar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent answerIntent = new Intent(getApplicationContext(), AnswerActivity.class);
                                answerIntent.putExtra("pregunta",rowid);
                                startActivity(answerIntent);
                            }
                        })
                .setNegativeButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ModeloCategoria md = new ModeloCategoria(getApplicationContext());
                                md.deletePregunta(rowid);
                                l.removeAllViews();
                                listar();
                                md.destruir();
                            }
                        })
                .setCancelable(true);

        return builder.create();
    }
}
