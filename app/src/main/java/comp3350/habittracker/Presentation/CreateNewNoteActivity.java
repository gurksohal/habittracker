package comp3350.habittracker.Presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.NoteManager;
import comp3350.habittracker.Persistence.NoteStub;
import comp3350.habittracker.R;

import static java.lang.Integer.valueOf;


public class CreateNewNoteActivity extends AppCompatActivity {
    private int habitId;
    private String userId;
    private Habit userHabit;
    private String noteDate;
    private NoteManager noteManager;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_create_note);
        builder = new AlertDialog.Builder(this);
        noteManager = new NoteManager();
        //Intent
        Intent intent = getIntent();
        userId = (String)intent.getSerializableExtra("user");
        userHabit = (Habit) intent.getSerializableExtra("habit");
        noteDate = (String)intent.getSerializableExtra("date");
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
                int feeling = 0; //todo: implement feelings
                noteManager.saveNewNote(userHabit.getId(),userId,note,feeling,noteDate);
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
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

}

