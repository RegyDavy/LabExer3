package com.caampued.externalstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {


    Button btn_LoadSharedPef, btn_LoadInternalStorage, btn_LoadInternalCache, btn_LoadExternalCache, btn_LoadExternalStorage, btn_LoadExternalPublic, btn_PreviousAct;
    TextView etDisplay;
    SharedPreferences pref;
    FileInputStream fis;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etDisplay = (TextView) findViewById(R.id.etDisplay);
        btn_LoadSharedPef = (Button) findViewById(R.id.btn_LoadSharedPref);
        btn_LoadInternalStorage = (Button) findViewById(R.id.btn_LoadInternalStorage);
        btn_LoadInternalCache = (Button) findViewById(R.id.btn_LoadInternalCache);
        btn_LoadExternalCache = (Button) findViewById(R.id.btn_LoadExternalCache);
        btn_LoadExternalStorage = (Button) findViewById(R.id.btn_LoadExternalStorage);
        btn_LoadExternalPublic = (Button) findViewById(R.id.btn_LoadExternalPublic);btn_PreviousAct = (Button) findViewById(R.id.btn_PreviousAct);
        pref = getApplication().getSharedPreferences(" ", MODE_PRIVATE);

    }

    public void loadSharedPref (View view){
        String DATA = pref.getString("Data", " ");
        etDisplay.setText(DATA);
    }

    public void loadInternalStorage (View view){
        StringBuffer DaTa = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("data.txt");
            while ((read = fis.read()) != -1) {
                DaTa.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        etDisplay.setText(DaTa.toString());
    }

    public String loadIntCache (View view) {
        File file = getCacheDir();
        File file1 = new File(file, "MyText1.txt");

        FileInputStream fileinputstream = null;
        try {
            fileinputstream = new FileInputStream(file1);
            int read = -1;
            StringBuffer stringbuffer = new StringBuffer();
            while ((read = fileinputstream.read()) != -1){

                stringbuffer.append((char)read);
            }
            return stringbuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {
                fileinputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    


    public void gotoPreviousAct (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}
