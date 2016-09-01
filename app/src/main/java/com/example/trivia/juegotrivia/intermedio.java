package com.example.trivia.juegotrivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class intermedio extends AppCompatActivity {

    TextView textview_descripcion;
    TabHost tabhost;
    //MediaPlayer mediaPlayer;
    AudioPlayer2 audioplayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio);

        cargarTabs();
        cargarDescripcion();
        correrMusica();
    }

    private  void cargarTabs()
    {
        tabhost = (TabHost)findViewById(R.id.tabHost);
        tabhost.setup();
        TabHost.TabSpec ts1 = tabhost.newTabSpec("tab1");
        ts1.setIndicator("Categoria");
        ts1.setContent(R.id.tab1);
        tabhost.addTab(ts1);

        TabHost.TabSpec ts2 = tabhost.newTabSpec("tab2");
        ts2.setIndicator("Descripcion");
        ts2.setContent(R.id.tab2);
        tabhost.addTab(ts2);

        TabHost.TabSpec ts3 = tabhost.newTabSpec("tab3");
        ts3.setIndicator("Galeria");
        ts3.setContent(R.id.tab3);
        tabhost.addTab(ts3);
    }

    private void cargarDescripcion()
    {
        textview_descripcion = (TextView)findViewById(R.id.TextView_descripcion);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i<150;i++)
            sb.append("Linea: "+String.valueOf(i)+"\n");
        textview_descripcion.setMovementMethod(new ScrollingMovementMethod());
        textview_descripcion.setText(sb.toString());
    }

//    private void cargarMusica()
//    {
//        audioplayer2 = new AudioPlayer2(this, R.raw.nova);
////        mediaPlayer = MediaPlayer.create(this,R.raw.nova);
////        mediaPlayer.setLooping(true);
////        mediaPlayer.setVolume(100,100);
////        mediaPlayer.start();
//    }

    private void correrMusica()
    {
        if(audioplayer2!=null)
            return;
//        else if(audioplayer2.corriendo==true)
//            return;
        audioplayer2 = new AudioPlayer2(this, R.raw.nova);
        audioplayer2.go();
    }

    private void pararMusica()
    {
        if(audioplayer2==null)
            return;
        if(audioplayer2.corriendo==false)
            return;
        audioplayer2.end();
        audioplayer2=null;
    }

    public void bcontinuar(View v)
    {
        System.out.println("Paso por el boton de continuar");
        //onBackPressed();
        this.correrMusica();
    }

    public void batras(View V)
    {
        System.out.println("Paso por el boton de atras");
        this.pararMusica();
    }

//    @Override
//    public void onBackPressed()
//    {
//        moveTaskToBack(true);
//    }

}
