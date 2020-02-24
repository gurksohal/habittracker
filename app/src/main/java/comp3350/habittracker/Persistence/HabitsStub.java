package comp3350.habittracker.Persistence;

import java.util.*;

import comp3350.habittracker.DomainObjects.Habit;


public class HabitsStub implements HabitsPersistence {

    private ArrayList<Habit> habits = new ArrayList<>();
    int uniqueId = 0; //keep track of the uniqueID for Habits
    public HabitsStub(){
        habits.add(0,new Habit("workout",5,3, "userA","Morning", 1,0));
        uniqueId++;
        habits.add(1,new Habit("piano",7,1, "userA","Afternoon",2,1));
        uniqueId++;
        habits.add(2,new Habit("read",7,0, "userA","Morning",1,2));
        uniqueId++;
        habits.add(3,new Habit("run",2,0, "userA","Evening",3,3));
        uniqueId++;
    }


    public ArrayList<Habit> getUserHabits(String userEmail) {
        ArrayList<Habit> userHabits = new ArrayList<>();

        for(Habit habit : habits){
            if(habit.getUserEmail().equals(userEmail)){
                userHabits.add(habit);
            }
        }

        return userHabits;
    }


    public boolean deleteHabit(int habitId) {
        if (habitId>=0){
            for (Habit habit : habits) {
                if (habit.getId() == habitId) {
                    habits.remove(habit);
                    return true;
                }
            }
        }
        return false;
    }



    public boolean addHabit(Habit habit) {
        //only add to list, if the habit doesn't already exist
        //two habits are the same if they have the same user, and name
        boolean returnValue = false;
        //todo: habit must check for name and uID of each item not just the habit object itself
        if(!habits.contains(habit)){
            habit.setId(uniqueId);
            uniqueId++;
            habits.add(habit);
            returnValue = true;
        }
        return returnValue;
    }


    public boolean modify(Habit modifiedHabit) {
        if(modifiedHabit.getId()>=0) {
            for (Habit habit : habits) {
                if (habit.getId() == modifiedHabit.getId()) {
                    habits.remove(habit);
                    habits.add(modifiedHabit);
                    return true;
                }
            }

        }
            return false;
    }

    public boolean deleteByName(String habitName, String userEmail){
        for (Habit habit : habits) {
            if (habit.getHabitName().equalsIgnoreCase(habitName)&&habit.getUserEmail().equalsIgnoreCase(userEmail)) {
                habits.remove(habit);
                return true;
            }
        }
        return false;
    }



    //get a habit based on it's id
    public Habit getHabit(int habitId){
        for(Habit habit : habits){
            if(habit.getId() == habitId){
                return habit;
            }
        }
        return null;
    }

    //test up temp database just for running tests
    public void setTestList(ArrayList<Habit> testList){
        habits = testList;
    }
}
