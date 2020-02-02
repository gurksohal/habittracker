package comp3350.habittracker.Presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.HabitManager;
import comp3350.habittracker.R;

public class HomeActivity extends AppCompatActivity {

    private TextView txtSelectedDate;
    private CalendarView calendarView;
    private ListView habbitList;
    private FloatingActionButton btnAddHabit;

    User user; //fake user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        user = new User("userA");
        new HabitManager(user); //create stub database

        txtSelectedDate = findViewById(R.id.txtSelectedDate);
        habbitList = findViewById(R.id.listView);

        String todayDate = getCurrentDate();
        txtSelectedDate.setText("Today's Date: " + todayDate); //set date field to show current date
        populateList(todayDate); //get today habits

        configAddButton(); //attach listener to add button
        configCalendar(); //attach listener to calendarView
    }

    private void configAddButton(){
        btnAddHabit = findViewById(R.id.btnAddHabit);
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(HomeActivity.this, AddHabitActivity.class);
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

    private void populateList(String date){
        ListView list = findViewById(R.id.listView);
        ArrayList<Habit> habits = HabitManager.getDailyHabits(date);
        ArrayList<String> habitNames = HabitManager.getHabitNames(habits);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, habitNames);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomeActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                Toast.makeText(HomeActivity.this, "Long Click", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
