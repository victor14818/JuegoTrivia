package com.example.trivia.juegotrivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class EditRespuesta extends AppCompatActivity {

    Respuesta r_actual = null;
    Long prg_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_respuesta);
        Intent myIntent = getIntent();
        final Long tmp = myIntent.getLongExtra("respuesta",-1);
        prg_id = myIntent.getLongExtra("pregunta", -1);

        if(tmp > 0) {
            ModeloCategoria md = new ModeloCategoria(this);
            System.out.println("tratando de leer a respuesta " + tmp);
            r_actual = md.getRespuesta(tmp);
            md.destruir();
            colocarDatosRespuesta();
            Button btn = (Button) findViewById(R.id.buttonApplyR);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ApplyRespuesta(tmp);
                }
            });
        }
    }

    private void colocarDatosRespuesta()
    {
        if(r_actual != null) {
            EditText et;
            //nombre
            et = (EditText) findViewById(R.id.editText_textoR);
            et.setText("" + r_actual.getTexto());
            //correcta
            Switch sw = (Switch) findViewById(R.id.switchR);
            if (r_actual.isCorrecta() > 0)
                sw.setChecked(true);
            else
                sw.setChecked(false);
        }
    }

    private void ApplyRespuesta(Long tmp)
    {
        try {
            ModeloCategoria md = new ModeloCategoria(this);

            EditText et = (EditText) findViewById(R.id.editText_textoR);
            String t = et.getText().toString();

            Switch sw = (Switch) findViewById(R.id.switchR);
            int c;
            if (sw.isChecked())
                c = 1;
            else
                c = 0;

            if(!t.equals("")) {
                if (tmp >= 0) {
                    //modificar
                    Respuesta nv = new Respuesta(t, c, r_actual.getPregunta(), r_actual.getRowid());
                    md.updateRespuesta(nv);
                } else {
                    //ingresar
                    Respuesta nv = new Respuesta(t, c, prg_id, Long.parseLong("-1"));
                    md.addRespuesta(nv);
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
}
