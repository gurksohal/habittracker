package comp3350.habittracker.Presentation;

import android.app.Activity;
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
    //alert dialog
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_view_notes);
        //going to need alert dialog
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
                //reloadList(habitId);
            }

        });

    }

    private void configList(){
        //displays the notes for the current habit in the ListView
        ListView notesList = (ListView)findViewById(R.id.lvNotes);
        reloadList(habitId);
        //click listener
    }
    private void reloadList(int habitId){//reload all notes for that habit
        ListView notesList = (ListView)findViewById(R.id.lvNotes);
        ArrayList<Note> notes = noteManager.getHabitNotes(habitId);
            //display all note contents
            ArrayList<String> noteContents = new ArrayList<String>();
            for(Note n : notes)
                noteContents.add(n.getNote());
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,noteContents);
        notesList.setAdapter(adapter);
    }
}
