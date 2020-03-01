package comp3350.habittracker.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.NoteManager;
import comp3350.habittracker.Persistence.NoteStub;
import comp3350.habittracker.R;
public class ViewNotesActivity extends AppCompatActivity {
    private int habitId;
    private String userId;
    private Habit userHabit;
    private String noteDate;
    private NoteManager noteManager;
    private static final int CREATE_NOTE_ACTIVITY_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_view_notes);
        noteManager = new NoteManager();
        //Receive intent
        Intent intent = getIntent();
        userId = (String)intent.getSerializableExtra("user");
        userHabit = (Habit)intent.getSerializableExtra("habit");
        noteDate = (String)intent.getSerializableExtra("date");
        habitId = userHabit.getId();
        TextView txtHabitName = findViewById(R.id.tvHabitNameView);
        txtHabitName.setText(userHabit.getHabitName());
        //config buttons
       configCreateButton();
       configCancelButton();
       configList();
    }

    //when another activity finishes and sends result and data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if creating new habit
        if(requestCode == CREATE_NOTE_ACTIVITY_ID && resultCode == Activity.RESULT_OK){
            reloadList(habitId); //redisplay the list
        }
    }
    private void configCreateButton(){
        FloatingActionButton btnCreate = findViewById(R.id.btnAddNote);
        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //start createNote activity
                Intent nextActivity = new Intent(ViewNotesActivity.this,CreateNewNoteActivity.class);
                nextActivity.putExtra("user",userId);
                nextActivity.putExtra("habit",userHabit);
                nextActivity.putExtra("date",noteDate);
                //startActivity(nextActivity);
                startActivityForResult(nextActivity, CREATE_NOTE_ACTIVITY_ID);
            }

        });

    }

    private void configCancelButton(){
        FloatingActionButton btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
    private void configList(){
        //displays the notes for the current habit in the ListView
        ListView notesList = (ListView)findViewById(R.id.lvNotes);
        reloadList(habitId);
        //click listener
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String)parent.getItemAtPosition(position);
                showAlertBox(selected, habitId);
            }
        });
    }
    private void reloadList(int habitId){//reload all notes for that habit
        ListView notesList = (ListView)findViewById(R.id.lvNotes);
        //ArrayList<Note> notes = noteManager.getHabitNotes(habitId);
            //display all note contents
          //  ArrayList<String> noteContents = new ArrayList<String>();
           // for(Note n : notes) //todo: make sure this isnt a code smell
                //noteContents.add(n.getNote());
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,noteManager.listNotes(habitId));
        notesList.setAdapter(adapter);
    }

    public void showAlertBox(final String selected, final int habitId){
       //Build alerts
        final AlertDialog.Builder assurance = new AlertDialog.Builder(this);
        assurance.setMessage("Are you sure you want to delete this note?");
       final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("To edit or remove a note, click one of the buttons below");
        //set pos neg buttons
        builder.setPositiveButton("Edit note", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //switch to edit note activity
                Intent nextActivity = new Intent(ViewNotesActivity.this,EditNoteActivity.class);
                Note currNote = noteManager.getNotebyContents(selected,habitId);
                int noteId = currNote.getNoteId();
                nextActivity.putExtra("noteId",noteId);
                nextActivity.putExtra("note",selected);
                nextActivity.putExtra("habit",userHabit);
                startActivityForResult(nextActivity,CREATE_NOTE_ACTIVITY_ID);
            }
        });
        builder.setNegativeButton("Remove note", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assurance.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Note currNote = noteManager.getNotebyContents(selected,habitId);
                       int noteId = currNote.getNoteId();
                      //remove Note
                       noteManager.deleteNote(noteId);
                      reloadList(habitId);
                      Toast.makeText(ViewNotesActivity.this,"Note deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                assurance.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        assurance.setCancelable(true);
                        Toast.makeText(ViewNotesActivity.this,"Note was unchanged",Toast.LENGTH_SHORT).show();
                    }
                });
                assurance.create().show();
            }
        });
       builder.create().show();
    }
}
