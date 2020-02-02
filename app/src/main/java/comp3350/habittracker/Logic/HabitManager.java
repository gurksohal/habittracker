package comp3350.habittracker.Logic;

import android.util.Log;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Persistence.HabitsStub;

public class HabitManager {

    private static HabitsStub db;

    public HabitManager(){
        db = new HabitsStub();
    }

    public static boolean saveNewHabit(String name, String timesPerWeek, User user){
        boolean returnValue = false;
        int perWeek = Integer.parseInt(timesPerWeek.substring(0,1));
        if(name.length() > 0){
            Habit habit = new Habit(name,perWeek,0,user);
            returnValue = db.addHabit(habit);
        }
        return returnValue;
    }

    //update habit in database
    public static void completeHabit(String name, User user){
        for(Habit habit: getHabits(user)){
            if(habit.getHabitName().equals(name)){
                habit.complete();
                Log.e("MAIN", habit.isCompleted() + "");
                break;
            }
        }
    }

    public static ArrayList<Habit> getHabits(User user){
        return db.getUserHabits(user);
    }

}
