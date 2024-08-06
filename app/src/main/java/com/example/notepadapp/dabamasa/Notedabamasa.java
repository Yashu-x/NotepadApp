package com.example.notepadapp.dabamasa;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notepadapp.dabamasa.doas.Notedoas;
import com.example.notepadapp.dabamasa.entites.NoteTable;

@Database(entities = {NoteTable.class},version = 1)
public abstract class Notedabamasa extends RoomDatabase {
    public abstract Notedoas getNoteDao();

    private static volatile Notedabamasa Instance;

    public static  Notedabamasa getInstance(Context context){
        if (Instance ==null){
            synchronized (Notedabamasa.class){
                Instance= Room.databaseBuilder(
                        context.getApplicationContext(),
                        Notedabamasa.class,
                        "ikddabamasa"
                ).build();
            }
        }
        return Instance;
    }


}
