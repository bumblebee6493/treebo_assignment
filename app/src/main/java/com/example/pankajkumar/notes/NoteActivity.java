package com.example.pankajkumar.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

/**
 * Created by pankajkumar on 27/5/16.
 */
public class NoteActivity extends AppCompatActivity {
    EditText editText;
    ToggleButton toggleButton;
    String title;
    int id;
    String from;
    DataBaseHandler db = new DataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        title = getIntent().getStringExtra("title");
        from = getIntent().getStringExtra("from");
        id = getIntent().getIntExtra("id",-1);

        editText = (EditText)findViewById(R.id.edit_content);
        if(from.equals("update"))
            editText.setText(db.getNote(id).content);
        toggleButton = (ToggleButton)findViewById(R.id.toggle);

        toggleButton.setTextOn("EDIT");
        toggleButton.setTextOff("DONE");
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ToggleButton) v).isChecked()) {
                    // handle toggle on
                    Note note = new Note();
                    note.title = title;
                    note.content = editText.getText().toString();

                    if(from.equals("update")){
                        note.id = id;
                        db.updateNote(note);
                    }
                    else{
                        note.id = db.getNotesCount()+1;
                        db.addNote(note);
                    }
                    editText.setEnabled(false);
                } else {
                    // handle toggle off
                    editText.setEnabled(true);
                }
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
        setTitle(title);
    }
}
