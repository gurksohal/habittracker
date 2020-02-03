package comp3350.habittracker.Logic;

import java.text.ParseException;
import java.util.Date;

import comp3350.habittracker.DomainObjects.Habit;

//validate if habits should be shown of given date
public class HabitDateValidator {

    //return if habit has been completed for current week
    public static boolean isCompleted(Habit habit, String date)throws ParseException {
        boolean returnValue = false;
        if(CalendarDateValidator.isCurrentWeek(date)){
            returnValue = habit.isCompleted();
        }
        return returnValue;
    }

    public static boolean updateCompletedAmount(Habit habit)throws ParseException{
        Date today = CalendarDateValidator.getCurrentDate();
        boolean returnValue = false;
        //find the last day of the week in which this habit was last completeted
        //ex) lastCompletedDate = Wednesday of last week
        //endOfWeek = The following sunday


        if(habit.getLastCompletedDate() != null) {
            String endWeek = CalendarDateValidator.getEndOfWeek(habit.getLastCompletedDate());
            Date endOfWeek = CalendarDateValidator.parseString(endWeek);

            //if a new week has started, reset completed amount
            if (endOfWeek.before(today) && habit.getCompletedWeeklyAmount() != 0) {
                habit.clearCompletedAmout();
                returnValue = true;
            }
        }
        return returnValue;
    }
}
