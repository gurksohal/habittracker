package comp3350.habittracker.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.Logic.NotesManager;
import comp3350.habittracker.R;

public class EditNoteActivity extends AppCompatActivity {

    private Note currentNote;
    private RadioButton bad,avg,good;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_create_note);

        //Intent
        Intent intent = getIntent();
        currentNote = (Note)intent.getSerializableExtra("note");

        //Radio Group
        bad = (RadioButton)findViewById(R.id.radio_bad);
        avg = (RadioButton)findViewById(R.id.radio_average);
        good = (RadioButton)findViewById(R.id.radio_good);

        int userFeeling = currentNote.getFeeling();
        if(userFeeling == 0){
            bad.toggle();
        }else if(userFeeling == 1){
            avg.toggle();
        }else if(userFeeling == 2){
            good.toggle();
        }


        //set edit text to be current note
        EditText et = (EditText)findViewById(R.id.etWriteNotes);
        et.setText(currentNote.getNoteText());
        configUpdateButton();
    }

    private  void configUpdateButton(){
        FloatingActionButton btnUpdate = findViewById(R.id.btnUpdateNote);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.etWriteNotes);
                String updated = et.getText().toString();
                int feelings = getFeelings();
                if(feelings >-1) {
                    if(NotesManager.updateNote(currentNote,updated,feelings,currentNote.getNoteDate())){
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }else{
                        Toast.makeText(EditNoteActivity.this,"Note with that message already exists",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EditNoteActivity.this,"Please select a feeling",Toast.LENGTH_SHORT).show();
                }
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
        return feelings;
    }
}
