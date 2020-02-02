package comp3350.habittracker.Logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateValidator {

    //return the date for the end of current week
    //sunday is last of a week, monday being the first
    public static String getEndOfCurrentWeek(String date)throws ParseException{
        Date selectedDate = parseString(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDate);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        if(day != Calendar.SUNDAY){
            int daysRemaining = (7 - day + 1);
            cal.add(Calendar.DAY_OF_YEAR, daysRemaining);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(cal.getTime());
    }

    //return if selected date is a valid date for displaying habits
    //selected date is valid if its todays after, or is an upcoming date
    public boolean isValidDate(String date)throws ParseException{
        boolean returnValue = false;
        Date selectedDate = parseString(date);
        if(selectedDate.equals(new Date()) || selectedDate.after(new Date())){
            returnValue = true;
        }
        return returnValue;
    }

    private static Date parseString(String date)throws ParseException{
        return new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }
}
