package com.example.devmobtp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AfficherInfos extends AppCompatActivity {

    private TextView infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_infos);

        infos = findViewById(R.id.infos);

        Intent intent = getIntent();
        String filename = intent.getStringExtra("filename");

        String informations = readFromFile(filename);
        infos.setText(informations);

        System.out.println("MON FICHIER : " + getFileStreamPath(filename));
    }

    private String readFromFile(String filename) {
        String ret = "";
        try {
            InputStream inputStream = openFileInput(filename);
            if(inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "Fichier inexistant: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Impossible de lire le fichier: " + e.toString());
        }

        return ret;
    }

}