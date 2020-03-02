package comp3350.habittracker.Logic;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.Persistence.HabitsPersistence;
import comp3350.habittracker.Persistence.stub.HabitsStub;

//manage habits
//add, remove, edit
public class HabitManager {

    private static HabitsPersistence db;

    public HabitManager(){
        db = new HabitsStub();
    }

    /*
     * saveNewHabit
     * return true if the habit was saved
     *
     * Input: habit info
     */
    public static boolean saveNewHabit(String name, int perWeek, String userID, String timeOfDay, int timeAssoc){
        boolean returnValue = false;
        //timesPerWeek = "# times per week"
        //parse the first char into an int
        //if a habit name exists
        if(name.length() > 0){
            Habit habit = new Habit(name,perWeek,0,userID, timeOfDay, timeAssoc);
            returnValue = db.addHabit(habit);
        }
        return returnValue;
    }



    public static boolean updateHabit(Habit habit){
        return db.modify(habit);
    }

    public static void delete(int habitId){
        db.deleteHabit(habitId);
    }

    public static void deleteByName(String habitName, String userEmail){
        db.deleteByName(habitName,userEmail);
    }

    //return all habits created by a user
    public static ArrayList<Habit> getHabits(String UserId){
        return db.getUserHabits(UserId);
    }

    //temp database just for tests
    public void setupTest(ArrayList<Habit> testHabits){
        HabitsStub stub = (HabitsStub)db;
        stub.setTestList(testHabits);
    }

}
