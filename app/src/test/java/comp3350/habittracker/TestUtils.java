package comp3350.habittracker;

import java.util.Calendar;
import java.util.Date;

public class TestUtils {

    //add days to current date
    public static Date addDaysToDate(int days){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,days); //set calendar to a future date
        return cal.getTime();
    }
}
