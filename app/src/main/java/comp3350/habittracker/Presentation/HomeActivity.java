package comp3350.habittracker.Presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import comp3350.habittracker.R;

public class HomeActivity extends AppCompatActivity {

    private TextView txtSelectedDate;
    private CalendarView calendarView;
    private ListView habbitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        txtSelectedDate = findViewById(R.id.txtSelectedDate);
        calendarView = findViewById(R.id.calendarView);
        habbitList = findViewById(R.id.listView);

        txtSelectedDate.setText("Today's Date: " + getCurrentDate());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                String sCurrentDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                txtSelectedDate.setText("Today's Date: " + sCurrentDate);
            }
        });
    }

    private String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
}
