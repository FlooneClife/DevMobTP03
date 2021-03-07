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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Planning2 extends AppCompatActivity {

    private TextView planning;

    private PlanningDatabase planningDB;
    private List<PlanningRoom> plannings;
    private List<Integer> hoursBegin;
    private List<Integer> hoursEnd;
    private List<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning2);

        planning = findViewById(R.id.planning);

        planningDB = Room.databaseBuilder(getApplicationContext(), PlanningDatabase.class, "database-planning").build();
        PlanningRoom task1 = new PlanningRoom(0, 8, 10, "Dossier vente");
        PlanningRoom task2 = new PlanningRoom(1, 14, 16, "Réunion équipe");
        planningDB.planningDAO().insertAll(task1, task2);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                plannings = planningDB.planningDAO().getAll();
                hoursBegin = planningDB.planningDAO().getAllHoursBegin();
                hoursEnd = planningDB.planningDAO().getAllHoursEnd();
                tasks = planningDB.planningDAO().getAllTasks();
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < plannings.size(); i++) {
            sb.append(hoursBegin.get(i) + "h-" + hoursEnd.get(i) + "h : " + tasks.get(i) + "\n");
        }
        planning.setText(sb.toString());

    }

}