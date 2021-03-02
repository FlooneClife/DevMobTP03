package com.example.devmobtp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText firstname;
    private EditText age;
    private EditText phone;
    private EditText password;
    private TextView ID;
    private Button validate;

    private static final int MAX_LENGTH = 6;

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        char tempChar;
        for (int i = 0; i < MAX_LENGTH; i++){
            tempChar = (char) (generator.nextInt(90 - 65 + 1) + 65);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    String rand = random();

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("myName", String.valueOf(name.getText()));
        savedInstanceState.putString("myFirstName", String.valueOf(firstname.getText()));
        savedInstanceState.putString("myAge", String.valueOf(age.getText()));
        savedInstanceState.putString("myPhone", String.valueOf(phone.getText()));
        savedInstanceState.putString("myID", String.valueOf(ID.getText()));
//        Toast.makeText(this, "Etat de l'activité sauvegardé", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String myName = savedInstanceState.getString("myName");
        String myFirstName = savedInstanceState.getString("myFirstName");
        String myAge = savedInstanceState.getString("myAge");
        String myPhone = savedInstanceState.getString("myPhone");
        String myID = savedInstanceState.getString("myID");
        name.setText(myName);
        firstname.setText(myFirstName);
        age.setText(myAge);
        phone.setText(myPhone);
        password.setText("");
        ID.setText(myID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        firstname = findViewById(R.id.firstname);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        validate = findViewById(R.id.validate);
        ID = findViewById(R.id.id);

        ID.setText("ID : " + rand);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = makeFileName();
                writeToFile(filename);
                Intent intent = new Intent(v.getContext(), com.example.devmobtp03.AfficherInfos.class);
                intent.putExtra("filename", filename);
                startActivity(intent);
            }
        });

    }

    private void writeToFile(String filename) {
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            StringBuilder bs = new StringBuilder();
            bs.append(ID.getText() + "\n");
            bs.append("Nom : " + name.getText() + "\n");
            bs.append("Prenom : " + firstname.getText() + "\n");
            bs.append("Age : " + age.getText() + "\n");
            bs.append("Telephone : " + phone.getText() + "\n");
            bs.append("Mot de passe : " + password.getText() + "\n");
            fos.write(bs.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            Log.e("Exception", "Echec lors de l'écriture du fichier: " + e.toString());
        }
    }

    private String makeFileName() {
        StringBuilder sb = new StringBuilder();
        sb.append(name.getText());
        sb.append(rand);
        sb.append(".txt");
        return sb.toString();
    }

}