package com.example.trivia.juegotrivia;

/**
 * Created by Joaquin on 01/09/2016.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by Joaquin on 01/09/2016.
 */
public class AudioPlayer2 extends Thread {
    private Context c;
    private Thread blinker;
    //private File file;
    private int file;
    public boolean corriendo;

    public AudioPlayer2 (Context c, int file)
    {
        this.c = c;
        this.file = file;
        this.corriendo=false;
    }

    public void go ()
    {
        if(corriendo)
            return;

        blinker = this;
        if(!blinker.isAlive())
        {
            blinker.start();
            corriendo=true;
        }
    }

    public void end ()
    {
        if(!corriendo)
            return;

        Thread waiter = blinker;
        blinker = null;
        corriendo=false;

        if (waiter != null)
            waiter.interrupt ();
    }

    public void run () {
        //MediaPlayer ap = MediaPlayer.create(c, Uri.fromFile(file));
        MediaPlayer ap = MediaPlayer.create(c, file);
        int duration = ap.getDuration();
        long startTime = System.currentTimeMillis();
        ap.start();

        try
        {
            Thread thisThread = Thread.currentThread();
            while (this.blinker == thisThread && System.currentTimeMillis() - startTime < duration) {
                Thread.sleep (500);  // interval between checks (in ms)
            }
            ap.stop ();
            ap.release ();
            ap = null;

        }
        catch (InterruptedException e)
        {
            Log.d("AUDIO-PLAYER", "INTERRUPTED EXCEPTION");
            ap.stop ();
            ap.release();
            //a
            ap = null;
        }
    }
}

