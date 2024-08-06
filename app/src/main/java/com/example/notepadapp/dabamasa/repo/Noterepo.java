package com.example.notepadapp.dabamasa.repo;

import com.example.notepadapp.dabamasa.Notedabamasa;
import com.example.notepadapp.dabamasa.entites.NoteTable;

import java.util.List;

public class Noterepo {
    private final Notedabamasa db;

    public Noterepo(Notedabamasa db) {
        this.db = db;
    }
    public void insert (final NoteTable noteTable){
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.getNoteDao().InsertNote(noteTable);

            }
        }
        ).start();

    }

public void delete (final NoteTable noteTable){
    new Thread(new Runnable() {
        @Override
        public void run() {
            db.getNoteDao().DeleteNotes(noteTable);
        }
    }
    ).start();
    }

    public List<NoteTable> getallnotes(){
        return db.getNoteDao().getAllNotes();
    }

    }