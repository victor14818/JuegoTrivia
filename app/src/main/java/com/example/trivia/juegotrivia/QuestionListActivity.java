package com.example.trivia.juegotrivia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class QuestionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        listar("sololá");
    }

    private void listar(String categoria)
    {
        //ModeloPregunta md = new ModeloPregunta(this);
        final Pregunta preguntas [] = new Pregunta[16];
        preguntas[0] = new Pregunta(0,"cómo estas","sololá");
        preguntas[1] = new Pregunta(1,"cómo te llamas","sololá");
        preguntas[2] = new Pregunta(2,"segura??","sololá");
        preguntas[3] = new Pregunta(3,"bueno","sololá");
        preguntas[4] = new Pregunta(0,"cómo estas","sololá");
        preguntas[5] = new Pregunta(1,"cómo te llamas","sololá");
        preguntas[6] = new Pregunta(2,"segura??","sololá");
        preguntas[7] = new Pregunta(3,"bueno","sololá");
        preguntas[8] = new Pregunta(0,"cómo estas","sololá");
        preguntas[9] = new Pregunta(1,"cómo te llamas","sololá");
        preguntas[10] = new Pregunta(2,"segura??","sololá");
        preguntas[11] = new Pregunta(3,"bueno","sololá");
        preguntas[12] = new Pregunta(0,"cómo estas","sololá");
        preguntas[13] = new Pregunta(1,"cómo te llamas","sololá");
        preguntas[14] = new Pregunta(2,"segura??","sololá");
        preguntas[15] = new Pregunta(3,"bueno","sololá");

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layoutPreguntas);
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
                    Intent answerIntent = new Intent(getApplicationContext(), AnswerActivity.class);
                    answerIntent.putExtra("pregunta",preguntas[aux].getRowid());
                    startActivity(answerIntent);
                }
            });
            linearLayout.addView(btn);
        }
    }
}
