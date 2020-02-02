package comp3350.habittracker.Logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Persistence.HabitsStub;

public class HabitManager {

    private static HabitsStub db;
    private static ArrayList<Habit> habits;

    public HabitManager(User user){
        db = new HabitsStub();
        habits = getHabits(user);
    }

    public static boolean saveNewHabit(String name, String timesPerWeek, User user){
        boolean returnValue = false;
        int perWeek = Integer.parseInt(timesPerWeek.substring(0,1));
        if(name.length() > 0){
            Habit habit = new Habit(name,perWeek,0,user);
            returnValue = db.addHabit(habit);
            if(returnValue){
                habits.add(habit); //update the list with the new habit
            }
        }
        return returnValue;
    }

    public static ArrayList<Habit> getHabits(User user){
        return db.getUserHabits(user);
    }

    public static ArrayList<Habit> getDailyHabits(){
        ArrayList<Habit> returnHabits = new ArrayList<>();
        for(Habit habit : habits){
            if(!habit.isCompleted()){
                returnHabits.add(habit);
            }
        }
        return returnHabits;
    }

    public static ArrayList<String> getHabitNames(ArrayList<Habit> habits){
        ArrayList<String> returnNames = new ArrayList<>();
        for(Habit habit : habits){
            returnNames.add(habit.getHabitName());
        }
        return returnNames;
    }

    public static void completeHabit(String name){
        for(Habit habit: habits){
            if(habit.getHabitName().equals(name)){
                habit.complete();
                Log.e("MAIN", habit.isCompleted() + "");
                break;
            }
        }
    }

}
