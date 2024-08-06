package com.example.notepadapp.dabamasa.doas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notepadapp.dabamasa.entites.NoteTable;

import java.util.List;

@Dao
public interface Notedoas {
    @Insert
    void InsertNote (NoteTable noteTable);

    @Delete
    void DeleteNotes (NoteTable noteTable);

    @Query("SELECT * FROM NOTETABLE")
    List<NoteTable> getAllNotes();
}
