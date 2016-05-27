package com.example.pankajkumar.notes;

/**
 * Created by pankajkumar on 27/5/16.
 */
public class Note {
    public int id;
    public String title;
    public String content;

    public  Note(){
    }

    public Note(String title, String content, int id){
        this.title = title;
        this.content = content;
        this.id = id;
    }
}
