package comp3350.habittracker.Presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.CalendarDateValidator;
import comp3350.habittracker.Logic.HabitListManager;
import comp3350.habittracker.Logic.HabitManager;
import comp3350.habittracker.Logic.Utils;
import comp3350.habittracker.R;

public class HomeActivity extends AppCompatActivity {

    private static final int ADD_ACTIVITY_ID = 0;
    private static final int EDIT_HABIT_ID = 1;
    private TextView txtSelectedDate;
    private CalendarView calendarView;
    private FloatingActionButton btnAddHabit;
    private String selectedDate;

    private User user; //fake user
    private HabitListManager habitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        user = new User("userA");
        new HabitManager(); //create stub database

        try {
            habitList = new HabitListManager(user);
        }catch(ParseException e){
            e.printStackTrace();
        }

        txtSelectedDate = findViewById(R.id.txtSelectedDate);
        selectedDate = getCurrentDate(); //get current date formatted
        txtSelectedDate.setText("Today's Date: " + selectedDate); //set date field to show current date

        configList(); //get today habits and attach listener
        configAddButton(); //attach listener to add button
        configCalendar(); //attach listener to calendarView
    }

    //when another activity finishes and sends result and data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if creating new habit
        if(requestCode == ADD_ACTIVITY_ID && resultCode == Activity.RESULT_OK){
            habitList.updateHabitList(); //reload the list with the new data
            reloadList(selectedDate); //redisplay the list
        }else if(requestCode == EDIT_HABIT_ID && resultCode == Activity.RESULT_OK){ //if editing habit
            String habitName = data.getStringExtra("deleteHabit");
            Habit removeHabit = habitList.getHabit(habitName); //get habit that was edited
            HabitManager.delete(removeHabit); //delete old verison
            //reload list and display list
            habitList.updateHabitList();
            reloadList(selectedDate);
        }
    }

    //launch addHabit activity, and pass the user object to it
    private void configAddButton(){
        btnAddHabit = findViewById(R.id.btnAddHabit);
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(HomeActivity.this, AddHabitActivity.class);
                nextActivity.putExtra("user",user);
                startActivityForResult(nextActivity, ADD_ACTIVITY_ID);
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
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                txtSelectedDate.setText("Today's Date: " + selectedDate);
                reloadList(selectedDate); //update the list with the correct habits
            }
        });
    }

    //current date doesn't get set on launch since the date change event hasn't fired yet.
    //So, manually set the current date
    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    private void configList(){
        ListView list = findViewById(R.id.listView);
        reloadList(selectedDate); //load list for the first time

        //set a listener on the loaded list
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                Date selectDate = null;
                Date currentDate = null;

                try {
                    //get selected date and current date as date Objects
                    selectDate = Utils.parseString(selectedDate);
                    currentDate = CalendarDateValidator.getCurrentDate();
                }catch(ParseException e){
                    e.printStackTrace();
                }

                //only process the click if selected and current dates are equal
                if(selectDate.equals(currentDate)) {
                    habitList.completeHabit(selected);
                    Toast toast = Toast.makeText(getApplicationContext(), "Completed " + selected, Toast.LENGTH_SHORT);
                    toast.show();

                    //reload the habit list
                    reloadList(selectedDate);
                }
            }
        });

        //long click for edit/remove habit
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                String selectedHabit = (String)arg0.getItemAtPosition(pos);
                showAlertDialog(selectedHabit); //show alert box
                return true;
            }
        });
    }

    //load the uncompleted habits for given day
    public void reloadList(String date){
        ListView list = findViewById(R.id.listView);
        ArrayList<Habit> habits;
        try {
            habits = habitList.getUncompletedHabits(date);
        }catch(ParseException e){
            habits = new ArrayList<>();
            e.printStackTrace();
        }

        //get only habit names
        ArrayList<String> habitNames = habitList.getHabitNames(habits);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, habitNames);
        list.setAdapter(adapter);
    }

    //Creates alertDialogs for edit and remove habit options
    public void showAlertDialog(String s){
        //Habit clicked:
        final Habit hClickedHabit = habitList.getHabit(s);
        //Build alert dialogs
        final AlertDialog.Builder assurance = new AlertDialog.Builder(this);
        assurance.setMessage("Are you sure you want to delete this habit?");
        final AlertDialog.Builder habitEditBuilder = new AlertDialog.Builder(this);
        habitEditBuilder.setMessage("To edit or remove a habit, click one of the buttons below");
        //Set pos/neg buttons; pos = edit, neg = remove
        habitEditBuilder.setPositiveButton("Edit habit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //switch activity to add habit activity
                Intent nextActivity = new Intent(HomeActivity.this, AddHabitActivity.class);
                nextActivity.putExtra("user",user);
                nextActivity.putExtra("habitName",hClickedHabit.getHabitName());
                startActivityForResult(nextActivity, EDIT_HABIT_ID);
            }
        });
        habitEditBuilder.setNegativeButton("Remove habit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assurance.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //remove habit from list
                        HabitManager.delete(hClickedHabit); //is being deleted from database
                        habitList.updateHabitList();
                        reloadList(selectedDate);
                        Toast.makeText(HomeActivity.this, "Habit removed",Toast.LENGTH_LONG).show();
                    }
                });
                assurance.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        assurance.setCancelable(true);
                        Toast.makeText(HomeActivity.this, "Habit was unchanged", Toast.LENGTH_SHORT).show();
                    }
                });
                assurance.create().show();
            }
        });
       habitEditBuilder.create().show();
    }//end of showAlertDialog

}
