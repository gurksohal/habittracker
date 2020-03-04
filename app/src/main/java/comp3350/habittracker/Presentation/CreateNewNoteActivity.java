package comp3350.habittracker.Presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.RadioButton;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.Logic.NotesManager;

import comp3350.habittracker.R;


public class CreateNewNoteActivity extends AppCompatActivity {

    private Habit userHabit;
    private String noteDate;
    private NotesManager noteManager;
    private AlertDialog.Builder builder;
    private RadioButton bad,avg,good;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_create_note);
        builder = new AlertDialog.Builder(this);

        //Radio Group
        bad = (RadioButton)findViewById(R.id.radio_bad);
        avg = (RadioButton)findViewById(R.id.radio_average);
        good = (RadioButton)findViewById(R.id.radio_good);
        bad.setChecked(false);
        avg.setChecked(false);
        good.setChecked(false);
        //Intent
        Intent intent = getIntent();
        userHabit = (Habit) intent.getSerializableExtra("habit");
        noteDate = intent.getStringExtra("date");
        TextView txtHabitName = findViewById(R.id.tvHabitName);
        txtHabitName.setText(userHabit.getHabitName());
        configSaveButton();
        configCancelButton();
    }

    //listener for btnNotes  //btnSaveNote -- creates a new note
    private void configSaveButton(){
        FloatingActionButton btnSave = findViewById(R.id.btnUpdateNote);
        //when clicked
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.etWriteNotes);
                String note = et.getText().toString();
                int feeling = getFeelings();
                if(feeling >-1) {
                    if(noteManager.saveNewNote(note, feeling, noteDate, userHabit)){
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }else {
                        Toast.makeText(CreateNewNoteActivity.this,"Note with that message already exists",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CreateNewNoteActivity.this,"Please select a feeling",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //btnCancelNote -- returns user to home activity
    private void configCancelButton(){
        FloatingActionButton btnCancel = findViewById(R.id.btnCancelNote);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private int getFeelings(){
        int feelings = -1;

        if(bad.isChecked())
            feelings = 0;
        else if(avg.isChecked())
            feelings = 1;
        else if(good.isChecked())
            feelings = 2;
        return feelings;
    }
}

