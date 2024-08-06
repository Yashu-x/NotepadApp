package com.example.notepadapp.dabamasa.entites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NoteTable {
    private String title;
    private String date;
    private  String body;

    @PrimaryKey(autoGenerate = true)
     private Integer id;

    public NoteTable(String title, String date, String body) {
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
