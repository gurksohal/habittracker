package comp3350.habittracker.Logic;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class CalendarDateValidator {

    //return the date for the end of current week
    //sunday is last of a week, monday being the first
    public static String getEndOfWeek(String date)throws ParseException{
        Date selectedDate = Utils.parseString(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDate);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        if(day != Calendar.SUNDAY){
            int daysRemaining = (7 - day + 1);
            cal.add(Calendar.DATE, daysRemaining);
        }
        return Utils.formatDate(cal.getTime());
    }

    //check if selected date is within current week
    public static boolean isCurrentWeek(String date)throws ParseException{
        boolean returnValue = false;
        if(isValidDate(date)) {
            String endOfWeek = getEndOfWeek(Utils.formatDate(getCurrentDate())); //end of current week
            Date endWeekDate = Utils.parseString(endOfWeek);
            Date selectedDate = Utils.parseString(date);
            returnValue = selectedDate.before(endWeekDate) || endWeekDate.equals(selectedDate);
        }
        return returnValue;
    }

    //return if selected date is a valid date for displaying habits
    //selected date is valid if its todays after, or is an upcoming date
    public static boolean isValidDate(String date)throws ParseException {
        boolean returnValue = false;
        Date selectedDate = Utils.parseString(date);
        Date currentDate = getCurrentDate();
        if(selectedDate.equals(currentDate) || selectedDate.after(currentDate)){
            returnValue = true;
        }
        return returnValue;
    }

    public static Date getCurrentDate()throws ParseException{
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        return Utils.parseString(day + "/" + month + "/" + year);
    }
}
