package com.example.pankajkumar.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pankajkumar on 27/5/16.
 */
public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesListVH> {
    Context context;
    DataBaseHandler db;

    public NotesListAdapter(Context context, DataBaseHandler db){
        this.context = context;
        this.db = db;
    }

    @Override
    public void onBindViewHolder(NotesListAdapter.NotesListVH holder, int position) {
        //Log.i("count", position+"");
        final Note note = db.getNote(position+1);
        holder.title.setText(note.title);
        holder.content.setText(note.content);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra("id", note.id);
                intent.putExtra("from", "update");
                intent.putExtra("title", note.title);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage("Are you sure you want to delete this note?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        db.deleteNote(note);
                        notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return db.getNotesCount();
    }

    @Override
    public NotesListAdapter.NotesListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row, parent, false);
        return new NotesListVH(itemView);
    }

    public class NotesListVH extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView title;
        TextView content;
        ImageView delete;

        public NotesListVH(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            content = (TextView)view.findViewById(R.id.content);
            linearLayout = (LinearLayout)view.findViewById(R.id.note_row);
            delete = (ImageView)view.findViewById(R.id.delete);
        }
    }

}
