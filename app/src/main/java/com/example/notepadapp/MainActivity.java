package com.example.notepadapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadapp.adapters.ControllAdapter;
import com.example.notepadapp.dabamasa.Notedabamasa;
import com.example.notepadapp.dabamasa.entites.NoteTable;
import com.example.notepadapp.dabamasa.repo.Noterepo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Noterepo noterepo;
    private ControllAdapter controllAdapter;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        noterepo=new Noterepo(Notedabamasa.getInstance(this));
        controllAdapter=new ControllAdapter(this);
        executorService= Executors.newSingleThreadExecutor();
        RecyclerView recyclerView=findViewById(R.id.Rvfrontpage);
        recyclerView.setAdapter(controllAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        executorService.execute(()->{
            List<NoteTable> data=noterepo.getallnotes();
            runOnUiThread(()->{
                controllAdapter.setData(data);
            });
        });

        ImageView AddButton=findViewById(R.id.Addbtn);
        AddButton.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this,NewNote.class);
            startActivity(intent);
        });

    }
}