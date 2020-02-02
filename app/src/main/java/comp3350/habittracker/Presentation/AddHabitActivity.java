package comp3350.habittracker.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import comp3350.habittracker.R;

public class AddHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
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
                //TODO: start from here: create new habit, validate name
                finish();
            }
        });
    }
}
