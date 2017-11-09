package com.caampued.externalstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    EditText etData, etFilename;
    Button btn_SharedPef, btn_InternalStorage, btn_InternalCache, btn_ExternalCache, btn_ExternalStorage, btn_ExternalPublic, btn_NextAct;
    SharedPreferences.Editor editor;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etData = (EditText) findViewById(R.id.etData);
        etFilename = (EditText) findViewById(R.id.etFilename);
        btn_SharedPef = (Button) findViewById(R.id.btn_SharedPref);
        btn_InternalStorage = (Button) findViewById(R.id.btn_InternalStorage);
        btn_InternalCache = (Button) findViewById(R.id.btn_InternalCache);
        btn_ExternalCache = (Button) findViewById(R.id.btn_ExternalCache);
        btn_ExternalStorage = (Button) findViewById(R.id.btn_ExternalStorage);
        btn_ExternalPublic = (Button) findViewById(R.id.btn_ExternalPublic);
        btn_NextAct = (Button) findViewById(R.id.btn_NextAct);
        SharedPreferences pref = getApplication().getSharedPreferences(" ", MODE_PRIVATE);
        editor = pref.edit();

    }

    public void saveSharedPref (View view){
        editor.putString("Data", etData.getText().toString());
        editor.commit();
        Toast.makeText(this, "Successfully written to Shared Preferences", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalStorage (View view){
        String DATA = etData.getText().toString();
        try {
            fos = openFileOutput("data.txt", Context.MODE_PRIVATE);
            fos.write(DATA.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully written to Internal Storage!", Toast.LENGTH_SHORT).show();
    }


    public void saveInternalCache (View view){
        File folder = getCacheDir();
        File file = new File(folder, "MyText1.txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to Internal Cache!", Toast.LENGTH_SHORT).show();

    }

    public void saveExternalCache (View view){
        File folder = getExternalCacheDir();
        File file = new File(folder, "MyText2.txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Cache!", Toast.LENGTH_SHORT).show();
    }

    public void saveExternalStorage (View view){
        File folder = getExternalFilesDir("Regi");
        File file = new File(folder, "MyText3.txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Storage!", Toast.LENGTH_SHORT).show();
    }

    public void saveExternalPublic (View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "MyText4.txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Public!", Toast.LENGTH_SHORT).show();
    }


    private void writeData (File file, String message){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void gotoNextAct (View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }
}


