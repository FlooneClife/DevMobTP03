package com.example.devmobtp03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Planning extends AppCompatActivity {

    private PlanningModel planningModel;
    private TextView planning;
    private TextView task;
    private Button jour1;
    private Button jour2;
    private Button room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        planning = findViewById(R.id.planning);
        task = findViewById(R.id.testtask);
        jour1 = findViewById(R.id.jour1);
        jour2 = findViewById(R.id.jour2);
        room = findViewById(R.id.roomdb);

        Intent intent = getIntent();
        String filename1 = intent.getStringExtra("jour1");
        String filename2 = intent.getStringExtra("jour2");
        String task1 = readFromFile(filename1);
        String task2 = readFromFile(filename2);

        planningModel = new ViewModelProvider(this).get(PlanningModel.class);

        final Observer<String> planningObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                task.setText(s);
            }
        };

        planningModel.getTask().observe(this, planningObserver);

        StringBuilder sb = new StringBuilder();
        sb.append("8h-10h : " + planningModel.getH8_10() + "\n");
        sb.append("10h-12h : " + planningModel.getH10_12() + "\n");
        sb.append("14h-16h : " + planningModel.getH14_16() + "\n");
        sb.append("16h-18h : " + planningModel.getH16_18() + "\n");
        planning.setText(sb.toString());

        jour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planningModel.getTask().setValue(task1);
            }
        });

        jour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planningModel.getTask().setValue(task2);
            }
        });

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), com.example.devmobtp03.Planning2.class);
                startActivity(intent);
            }
        });

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