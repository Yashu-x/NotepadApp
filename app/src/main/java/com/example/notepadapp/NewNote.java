package com.example.notepadapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.notepadapp.dabamasa.Notedabamasa;
import com.example.notepadapp.dabamasa.entites.NoteTable;
import com.example.notepadapp.dabamasa.repo.Noterepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewNote extends AppCompatActivity {
    private Noterepo noterepo;
    private ExecutorService executorService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        executorService = Executors.newSingleThreadExecutor();
        EditText etTitle = findViewById(R.id.EditTitle);
        EditText etContent = findViewById(R.id.newtext);

        Button SaveButton = findViewById(R.id.Savebtn);
        SaveButton.setOnClickListener(v -> {
            String title=etTitle.getText().toString();
            String Body=etContent.getText().toString();
            String date=DateandTime();

            noterepo = new Noterepo(Notedabamasa.getInstance(this));
            executorService.execute(()->{
                noterepo.insert(new NoteTable(title,Body,date));
            });

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
    private String DateandTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateandtime = formatter.format(new Date());
        return dateandtime;
    }
}