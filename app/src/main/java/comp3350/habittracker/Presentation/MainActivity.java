package comp3350.habittracker.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import comp3350.habittracker.R;

public class MainActivity extends AppCompatActivity {

    private TextView txtSelectedDate;
    private CalendarView oCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSelectedDate = findViewById(R.id.txtSelectedDate);
        oCalendar = findViewById(R.id.calendarView);
    }
}
