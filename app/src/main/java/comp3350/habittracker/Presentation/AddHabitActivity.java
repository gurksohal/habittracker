package comp3350.habittracker.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.Collections;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.HabitManager;
import comp3350.habittracker.R;

import static java.lang.Integer.valueOf;

public class AddHabitActivity extends AppCompatActivity {

    private String userId;
    private AlertDialog.Builder builder;
    private Habit editHabit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        builder = new AlertDialog.Builder(this);

        //get current user instance from homepage
        //also if an edit is being edited, get its name
        Intent intent = getIntent();
        userId = intent.getStringExtra("user");
        editHabit = (Habit) intent.getSerializableExtra("habit");

        //attach listener
        setSpinnerText();
        setSpinnerTime();
        configAddButton();

        //editing a habit
        if(editHabit != null){
            fillInfo(editHabit);
        }
    }

    //fill the fields, with information about that habit that is being edited
    private void fillInfo(Habit habit){
        final EditText txtHabitName = findViewById(R.id.txtHabitName);
        final Spinner dropdown = findViewById(R.id.spinner);
        final Spinner dropdownTime = findViewById(R.id.spinnerTime);
        //set the fields to the correct values
        txtHabitName.setText(habit.getHabitName());
        dropdown.setSelection(habit.getWeeklyAmount() - 1);
        dropdownTime.setSelection(habit.getSortByDay() - 1);
    }
    //set the text style for the dropdown
    private void setSpinnerText(){
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.HabitWeeklyAmount, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    //sets the text style for the second dropdown
    private void setSpinnerTime(){
        Spinner timeSpinner = findViewById(R.id.spinnerTime);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.HabitTimes,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);
    }

    //set listener to the addHabit button
    private void configAddButton(){
        Button btnAddHabit = findViewById(R.id.btnAddHabit);
        final EditText txtHabitName = findViewById(R.id.txtHabitName);
        final Spinner dropdown = findViewById(R.id.spinner);
        final Spinner dropdownTime = findViewById(R.id.spinnerTime);

        //when clicked
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from the input fields and create habit object
                String habitName = txtHabitName.getText().toString();
                char c = dropdown.getSelectedItem().toString().charAt(0);
                int timesPerWeek = Integer.parseInt(String.valueOf(c));
                String schedule = dropdownTime.getSelectedItem().toString();
                int scheduleAssoc;
                if(schedule.equals("Morning"))
                    scheduleAssoc=1;
                else if(schedule.equals("Afternoon"))
                    scheduleAssoc=2;
                else if(schedule.equals("Evening"))
                    scheduleAssoc=3;
                else
                    scheduleAssoc=4;


                if(editHabit!=null) {
                    editHabit.setHabitName(habitName);
                    System.out.println("Setting times per week too: " + timesPerWeek);
                    editHabit.setWeeklyAmount(timesPerWeek);
                    editHabit.setTimeOfDay(schedule);
                    editHabit.setUserEmail(userId);
                    editHabit.setSortByDay(scheduleAssoc);
                }


                Intent intent = new Intent();
                if(editHabit != null && HabitManager.updateHabit(editHabit)){
                    setResult(RESULT_OK,intent);
                    finish(); //close activity, returns back to the home screen
                }else if(HabitManager.saveNewHabit(habitName,timesPerWeek,userId,schedule,scheduleAssoc)){ //if habit was saved, close the page
                    setResult(RESULT_OK,intent);
                    finish(); //close activity, returns back to the home screen
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
