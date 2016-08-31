package com.example.trivia.juegotrivia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by victor on 30/08/2016.
 */
public class ControladorRecursosExternos {

    String album = null;

    ControladorRecursosExternos(String dir)
    {
        if(isExternalStorageWritable())
            getAlbumStorageDir(dir);
        else
            System.out.println("no es leible el directorio");
    }

    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), albumName);
        if(file.exists()) {
            album = file.getAbsolutePath();
        }
        else{
            if (file.mkdirs()) {
                album = file.getAbsolutePath();
            } else {
                //Log.e(LOG_TAG, "Directory not created");
                System.out.println("Directory not created");
            }
        }
        return file;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public Bitmap readImage(String image) throws IOException {
        //Bitmap bitmap1 = BitmapFactory.decodeFile(this.album + "/image");
        //System.out.println(this.album + "/image");
        File file = new File(this.album, image);
        FileInputStream streamIn = new FileInputStream(file);
        Bitmap bitmap = BitmapFactory.decodeStream(streamIn);
        streamIn.close();
        return bitmap;
    }


    public String pathAudio(String audio) {
        //Bitmap bitmap1 = BitmapFactory.decodeFile(this.album + "/image");
        //System.out.println(this.album + "/image");
        return this.album +"/"+ audio;
    }
}
