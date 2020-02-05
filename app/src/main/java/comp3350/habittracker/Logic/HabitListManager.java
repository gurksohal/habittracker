package comp3350.habittracker.Logic;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;

//manage a list of one users habits
public class HabitListManager {

    private ArrayList<Habit> habits;
    private User user;

    public HabitListManager(User user)throws ParseException{
        this.user = user;
        habits = HabitManager.getHabits(user);

        //if in a new week, reset completed counter
        completedAmountCheck();
        Collections.sort(habits); //sort on intitial creation
    }

    //return the uncompleted habits for selected week
    public ArrayList<Habit> getUncompletedHabits(String date)throws ParseException {
        ArrayList<Habit> returnHabits = new ArrayList<>();
        for(Habit habit : habits){
            //habit is uncompleted if selected date is valid and isn't in current week, and habit completed amount hasn't reached the desired amount
            if(!HabitDateValidator.isCompleted(habit,date) && CalendarDateValidator.isValidDate(date)){
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
    //returns a habit from the habitList that matches the given String
    public Habit getHabit(String name){
        Habit hRetrieved = null;
        for(Habit habit: habits){
            if(habit.getHabitName().equals(name))
                hRetrieved = habit;
        }
        return hRetrieved;
    }

    public void updateHabitList(){
        habits = HabitManager.getHabits(user);
        Collections.sort(habits); //sort reloaded list
    }

    //check if habit was completed in the last week,
    //if so reset it's checked amount and update in the database
    private void completedAmountCheck()throws ParseException{
        for(Habit habit : habits){
            if(HabitDateValidator.updateCompletedAmount(habit)){
               HabitManager.updateHabit(habit);
            }
        }
    }



}
