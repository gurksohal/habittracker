package comp3350.habittracker.Logic;

import android.util.Log;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;

//manage a list of one users habits
public class HabitListManager {

    private ArrayList<Habit> habits;
    private User user;

    public HabitListManager(User user){
        this.user = user;
        habits = HabitManager.getHabits(user);
    }

    //return the uncompleted habits for selected week
    public ArrayList<Habit> getUncompletedHabits(){
        ArrayList<Habit> returnHabits = new ArrayList<>();
        for(Habit habit : habits){
            if(!habit.isCompleted()){
                returnHabits.add(habit);
            }
        }
        return returnHabits;
    }

    //return only names of habits
    //used for display the list
    public ArrayList<String> getHabitNames(ArrayList<Habit> habits){
        ArrayList<String> returnNames = new ArrayList<>();
        for(Habit habit : habits){
            returnNames.add(habit.getHabitName());
        }
        return returnNames;
    }

    //update habit in database
    public void completeHabit(String name){
        for(Habit habit: habits){
            if(habit.getHabitName().equals(name)){
                habit.complete();
                HabitManager.updateHabit(habit);
                break;
            }
        }
    }

    public void updateHabitList(){
        habits = HabitManager.getHabits(user);
    }

}
