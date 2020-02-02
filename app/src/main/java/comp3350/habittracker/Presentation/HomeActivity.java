package comp3350.habittracker.Presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.R;

public class HomeActivity extends AppCompatActivity {

    private TextView txtSelectedDate;
    private CalendarView calendarView;
    private ListView habbitList;
    private FloatingActionButton btnAddHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        txtSelectedDate = findViewById(R.id.txtSelectedDate);
        habbitList = findViewById(R.id.listView);
        txtSelectedDate.setText("Today's Date: " + getCurrentDate()); //set date field to show current date
        configAddButton(); //attach listener to add button
        configCalendar(); //attach listener to calendarView
    }

    private void configAddButton(){
        btnAddHabit = findViewById(R.id.btnAddHabit);
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(HomeActivity.this, AddHabitActivity.class);
                User user = new User("userA");
                nextActivity.putExtra("user",user);
                startActivity(nextActivity);
            }
        });
    }

    private void configCalendar() {
        calendarView = findViewById(R.id.calendarView);

        //fire's when selected date on the calendarView changes.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                String sCurrentDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                txtSelectedDate.setText("Today's Date: " + sCurrentDate);
            }
        });
    }

    //current date doesn't get set on launch since, the date change event hasn't fired yet.
    //So, manually set the current date
    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
}
