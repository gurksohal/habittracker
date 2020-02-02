package comp3350.habittracker.Logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import comp3350.habittracker.DomainObjects.Habit;

public class CalendarDateValidator {

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

    //check if selected date is within current week
    public static boolean isCurrentWeek(String date)throws ParseException{
        boolean returnValue = false;
        if(isValidDate(date)) {
            String endOfWeek = getEndOfCurrentWeek(date);
            Date endWeekDate = parseString(endOfWeek);
            Date selectedDate = parseString(date);
            returnValue = selectedDate.before(endWeekDate);
        }
        return returnValue;
    }

    //return if selected date is a valid date for displaying habits
    //selected date is valid if its todays after, or is an upcoming date
    public static boolean isValidDate(String date)throws ParseException {
        boolean returnValue = false;
        Date selectedDate = parseString(date);
        if(selectedDate.equals(new Date()) || selectedDate.after(new Date())){
            returnValue = true;
        }
        return returnValue;
    }

    public static Date parseString(String date)throws ParseException{
        return new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }
}
