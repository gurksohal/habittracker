package comp3350.habittracker.Logic;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Persistence.HabitsStub;

public class HabitManager {

    private HabitsStub db;

    public HabitManager(){
        db = new HabitsStub();
    }

    public boolean saveNewHabit(String name, String timesPerWeek, User user){
        boolean returnValue = false;
        int perWeek = Integer.parseInt(timesPerWeek.substring(0,1));
        if(name.length() > 0){
            Habit habit = new Habit(name,perWeek,0,user);
            returnValue = db.addHabit(habit);
        }
        return returnValue;
    }
}
