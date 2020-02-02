package comp3350.habittracker.Persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;

public class HabitsStub implements HabitsPersistence {

    private ArrayList<Habit> habits;
    public HabitsStub(){
        habits = new ArrayList<>();
        habits.add(new Habit("workout",5,3, new User("userA")));
        habits.add(new Habit("piano",7,1, new User("userA")));
        habits.add(new Habit("read",7,0, new User("userA")));
        habits.add(new Habit("run",2,0, new User("userA")));
    }

    @Override
    public ArrayList<Habit> getUserHabits(User user) {
        ArrayList<Habit> userHabits = new ArrayList<>();
        for(Habit habit : habits){
            if(habit.getUser().equals(user)){
                userHabits.add(habit);
            }
        }

        return userHabits;
    }

    @Override
    public void deleteHabit(Habit habit) {
        habits.remove(habit);
    }

    @Override
    public boolean addHabit(Habit habit) {
        //only add to list, if the habit doesn't already exist
        //two habits are the same if they have the same user, and name
        boolean returnValue = false;
        if(!habits.contains(habit)){
            habits.add(habit);
            returnValue = true;
        }
        return returnValue;
    }

    @Override
    public boolean update(Habit habit) {
        deleteHabit(habit);
        return addHabit(habit);
    }
}
