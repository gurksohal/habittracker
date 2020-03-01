package comp3350.habittracker.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.Logic.NoteManager;
import comp3350.habittracker.R;

public class EditNoteActivity extends AppCompatActivity {
    private String userId;
    private Habit userHabit;
    private String noteDate;
    private String currentText;
    private int noteId;
    private RadioButton bad,avg,good;
    private RadioGroup feels;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_edit_note);
        //Intent
        Intent intent = getIntent();
        userId = (String)intent.getSerializableExtra("user");
        userHabit = (Habit)intent.getSerializableExtra("habit");
        noteDate = (String)intent.getSerializableExtra("date");
        currentText = (String)intent.getSerializableExtra("note");
        noteId = (int)intent.getSerializableExtra("noteId");
        TextView txtHabitName = findViewById(R.id.tvHabitName);
        txtHabitName.setText(userHabit.getHabitName());
        //Radio Group
        bad = (RadioButton)findViewById(R.id.radio_bad);
        avg = (RadioButton)findViewById(R.id.radio_average);
        good = (RadioButton)findViewById(R.id.radio_good);
        feels = (RadioGroup)findViewById(R.id.radio_feelings);
        feels.clearCheck(); //clear the checked buttons so user can change if they want
        //set edit text to be current note
        EditText et = (EditText)findViewById(R.id.etWriteNotes);
        et.setText(currentText);
        configUpdateButton();
        configCancelButton();

    }

    private  void configUpdateButton(){
        FloatingActionButton btnUpdate = findViewById(R.id.btnUpdateNote);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note currentNote = NoteManager.getNote(noteId);
                EditText et = (EditText)findViewById(R.id.etWriteNotes);
                String updated = et.getText().toString();
                int feelings = getFeelings();
                NoteManager.updateNote(new Note(userHabit.getId(),userId,updated,feelings,noteDate,currentNote.getNoteId()));
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
    /*
        when cancelbtn is clicked, exit back to ViewNotesActivity
     */
    private void configCancelButton(){
        FloatingActionButton btnCancel = findViewById(R.id.btnCancelNote);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
    /*
        assigns a numerical value to the radiobtn clicked
     */
    private int getFeelings(){
        int feelings = -1;
        if(bad.isChecked())
            feelings = 0;
        else if(avg.isChecked())
            feelings = 1;
        else if(good.isChecked())
            feelings = 2;
        feels.clearCheck();
        return feelings;
    }
}
