package comp3350.habittracker.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.HabitManager;
import comp3350.habittracker.R;

public class AddHabitActivity extends AppCompatActivity {

    private HabitManager habitManager;
    private User user;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        builder = new AlertDialog.Builder(this);

        //get current user
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("user");

        habitManager = new HabitManager();
        setSpinnerText();
        configAddButton(); //attach listener
    }

    //set the text style for the dropdown
    private void setSpinnerText(){
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.HabitWeeklyAmount, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void configAddButton(){
        Button btnAddHabit = findViewById(R.id.btnAddHabit);
        final EditText txtHabitName = findViewById(R.id.txtHabitName);
        final Spinner dropdown = findViewById(R.id.spinner);

        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String habitName = txtHabitName.getText().toString();
                String timesPerWeek = dropdown.getSelectedItem().toString();
                if(habitManager.saveNewHabit(habitName,timesPerWeek,user)){
                    finish();
                }else{
                    builder.setMessage("Unable to save habit!").setTitle("Error!");
                    AlertDialog alert = builder.create();
                    alert.setTitle("Error!");
                    alert.show();
                }

            }
        });
    }
}
