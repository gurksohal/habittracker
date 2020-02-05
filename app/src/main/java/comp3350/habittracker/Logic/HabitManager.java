package comp3350.habittracker.Logic;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Persistence.HabitsStub;

//manage habits
//add, remove, edit
public class HabitManager {

    private static HabitsStub db;

    public HabitManager(){
        db = new HabitsStub();
    }

    public static boolean saveNewHabit(String name, String timesPerWeek, User user, String timeOfDay, int timeAssoc){
        boolean returnValue = false;
        int perWeek = Integer.parseInt(timesPerWeek.substring(0,1));
        if(name.length() > 0){
            Habit habit = new Habit(name,perWeek,0,user, timeOfDay, timeAssoc);
            returnValue = db.addHabit(habit);
        }
        return returnValue;
    }

    public static boolean updateHabit(Habit habit){
        return db.update(habit);
    }
    public static void delete(Habit habit){db.deleteHabit(habit);}

    //return all habits created by a user
    public static ArrayList<Habit> getHabits(User user){
        return db.getUserHabits(user);
    }

    //temp database just for tests
    public void setupTest(ArrayList<Habit> testHabits){
        db.setTestList(testHabits);
    }

}
