package com.example.pankajkumar.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    NotesListAdapter notesListAdapter;
    RecyclerView recyclerView;
    Button createNote;
    Context context;
    DataBaseHandler db = new DataBaseHandler(this);
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        //count = db.getNotesCount();

        createNote = (Button)findViewById(R.id.create);
        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                final EditText edittext = new EditText(context);
                alert.setMessage("Title");

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        Intent intent = new Intent(context, NoteActivity.class);
                        String title = edittext.getText().toString();
                        intent.putExtra("title", title);
                        intent.putExtra("from", "create");
                        if(!title.equals(""))
                            startActivity(intent);
                    }
                });

                alert.setView(edittext);
                alert.show();
            }
        });

        notesListAdapter = new NotesListAdapter(this, db);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(notesListAdapter);
    }

}
