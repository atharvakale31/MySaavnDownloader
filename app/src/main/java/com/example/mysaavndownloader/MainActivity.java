package com.example.mysaavndownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity.java";
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE =1 ;
    EditText songName;

    public void clr(View view) {
        songName.setText("");
    }

    public void save(View view) {
        String sdCard = Environment.getExternalStorageDirectory().toString();
        String name = songName.getText().toString();

        // the file to be moved or copied
        File sourceLocation = new File(sdCard + "/Android/data/com.jio.media.jiobeats/files/songs/mqwerty.mp4");

        // make sure your target location folder exists!
        File targetLocation = new File(sdCard +"/"+ name+ ".mp3");

        // just to take note of the location sources
        //Log.i(TAG, "sourceLocation: " + sourceLocation);
        //Log.i(TAG, "targetLocation: " + targetLocation);

        try {


            if (sourceLocation.exists()) {

                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();

                //Log.i(TAG, "Copy file successful.");

            } else {
                Toast.makeText(this, "Please Play Downloaded Song", Toast.LENGTH_SHORT).show();
                //Log.i(TAG, "Copy file failed. Source file missing.");
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songName = findViewById(R.id.editText);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted

            // No explanation needed; request the permission

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);


        }
        else {


        }

    }
}