package com.example.trivia.juegotrivia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

public class ShowQuest extends AppCompatActivity {

    int StreamID = 0;
    SoundPool sp;
    Hashtable<Integer, Integer> streams;
    ControladorRecursosExternos mg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quest);

/*        TextView TV = (TextView)findViewById(R.id.TV_id);   //definimos en ONCREATE donde esta por                                                                                                     //medio de un ID

        String font_path = "font/MixBrush.ttf";  //definimos un STRING con el valor PATH ( o ruta por                                                                                    //donde tiene que buscar ) de nuetra fuente

        Typeface TF = Typeface.createFromAsset(getAssets(),"VINTAGE COLLEGE DEPT_worn.otf");

        //llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde                                                    //ASSETS con la ruta STRING

        TV.setTypeface(TF);   //finalmente aplicamos TYPEFACE al TEXTVIEW*/

        mg = new ControladorRecursosExternos("TribiaImages");

        Button btn = (Button) findViewById(R.id.buttonPrueba);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //cargar imagen
                    Bitmap img = mg.readImage("images.jpg");
                    ImageView tv = (ImageView) findViewById(R.id.imageViewPrueba);
                    tv.setImageBitmap(img);

                    //reproducir audio
                    sp = new SoundPool(1, AudioManager.STREAM_MUSIC,100);

                    final int sid = sp.load(mg.pathAudio("0564.ogg"),1);
                    sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                             @Override
                             public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                                 AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                                 float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                                 float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                                 float leftVolume = curVolume/maxVolume;
                                 float rightVolume = curVolume/maxVolume;
                                 int priority = 1;
                                 int no_loop = 0;
                                 float normal_playback_rate = 1f;
                                 StreamID = sp.play(sid, leftVolume, rightVolume, priority, no_loop, normal_playback_rate);
                             }
                         }

                        );
                    }catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }


            }
        });

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if(StreamID != 0)
            sp.stop(StreamID);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        if(StreamID != 0)
            sp.resume(StreamID);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(sp != null)
            sp.release();
            sp = null;
    }

}
