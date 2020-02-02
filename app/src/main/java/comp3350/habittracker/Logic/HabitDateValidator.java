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

    public static void updateCompletedAmount(Habit habit)throws ParseException{
        Date today = new Date();

        //find the last day of the week in which this habit was last completeted
        //ex) lastCompletedDate = Wednesday of last week
        //endOfWeek = The following sunday
        String endWeek = CalendarDateValidator.getEndOfCurrentWeek(habit.getLastCompletedDate());
        Date endOfWeek = CalendarDateValidator.parseString(endWeek);
        
        //if a new week has started, reset completed amount
        if(endOfWeek.before(today)){
            habit.clearCompletedAmout();
        }
    }
}
