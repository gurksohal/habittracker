package comp3350.habittracker.Logic;

import android.util.Log;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;

public class HabitListManager {

    private static ArrayList<Habit> habits;

    public HabitListManager(User user){
        habits = HabitManager.getHabits(user);
    }

    public ArrayList<Habit> getDailyHabits(){
        ArrayList<Habit> returnHabits = new ArrayList<>();
        for(Habit habit : habits){
            if(!habit.isCompleted()){
                returnHabits.add(habit);
            }
        }
        return returnHabits;
    }

    public ArrayList<String> getHabitNames(ArrayList<Habit> habits){
        ArrayList<String> returnNames = new ArrayList<>();
        for(Habit habit : habits){
            returnNames.add(habit.getHabitName());
        }
        return returnNames;
    }

}
