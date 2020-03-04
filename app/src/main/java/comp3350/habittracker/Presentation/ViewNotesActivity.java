package comp3350.habittracker.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;

import comp3350.habittracker.Logic.NotesManager;
import comp3350.habittracker.R;
public class ViewNotesActivity extends AppCompatActivity {

    private Habit userHabit;
    private String noteDate;
    private static final int CREATE_NOTE_ACTIVITY_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_view_notes);

        //Receive intent
        Intent intent = getIntent();

        userHabit = (Habit)intent.getSerializableExtra("habit");
        noteDate = intent.getStringExtra("date");

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
        //if creating new Note, or updating a Note
        if(requestCode == CREATE_NOTE_ACTIVITY_ID && resultCode == Activity.RESULT_OK){
            reloadList(); //redisplay the list
        }
    }
    private void configCreateButton(){
        FloatingActionButton btnCreate = findViewById(R.id.btnAddNote);
        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //start createNote activity
                Intent nextActivity = new Intent(ViewNotesActivity.this,CreateNewNoteActivity.class);
                nextActivity.putExtra("habit",userHabit);
                nextActivity.putExtra("date",noteDate);
                startActivityForResult(nextActivity, CREATE_NOTE_ACTIVITY_ID);
            }
        });

    }
    /*
        when cancelbtn clicked, exit to HomeActivity
     */
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
        reloadList();
        //click listener
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String)parent.getItemAtPosition(position);
                showAlertBox(selected);
            }
        });
    }
    private void reloadList(){//reload all notes for that habit
        ListView notesList = (ListView)findViewById(R.id.lvNotes);
        ArrayList<Note> notes =  NotesManager.getNotes(userHabit);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, NotesManager.getNoteText(notes));
        notesList.setAdapter(adapter);
    }

    /*
        Builds the alert box for editing/removing Notes
     */
    public void showAlertBox(final String selected){
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
                Note currNote = NotesManager.getNoteByContents(userHabit, selected);
                nextActivity.putExtra("note",currNote);
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
                        Note currNote = NotesManager.getNoteByContents(userHabit, selected);
                        //remove Note
                        NotesManager.deleteNote(currNote);
                        reloadList();
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
