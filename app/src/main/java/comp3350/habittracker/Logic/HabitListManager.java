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

    public HabitListManager(User user)throws ParseException {
        this.user = user;
        habits = HabitManager.getHabits(user);

        //if in a new week, reset completed counter
        completedAmountCheck();
        Collections.sort(habits); //sort on intitial creation
    }

    /*
     * getUnCompletedHabits
     * return list of all the habits that uncompleted for the selected date
     *
     * Input: selected date
     */
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

    /*
     * getHabitNames
     * return a list of only names of habits
     *
     * Input: list of habit
     */
    public ArrayList<String> getHabitNames(ArrayList<Habit> habits){
        ArrayList<String> returnNames = new ArrayList<>();
        for(Habit habit : habits){
            returnNames.add(habit.getHabitName());
        }
        return returnNames;
    }

    /*
     * completeHabit
     *
     * Input: name of the habit to be completed
     */
    public void completeHabit(String name){
        for(Habit habit: habits){
            //find the habit with the right name
            if(habit.getHabitName().equals(name)){
                //complete the habit and update database
                habit.complete();
                HabitManager.updateHabit(habit);
                break;
            }
        }
    }

    /*
     * getHabit
     * return a habit
     *
     * Input: name of habit
     */
    public Habit getHabit(String name){
        Habit returnHabit = null;
        for(Habit habit: habits){
            //find the habit with matching name
            if(habit.getHabitName().equals(name))
                returnHabit = habit;
        }
        return returnHabit;
    }

    /*
     * updateHabitList
     *
     */
    public void updateHabitList(){
        //update the arraylist with a new instance from the database
        habits = HabitManager.getHabits(user);
        //sort the list
        Collections.sort(habits);
    }

    /*
     * completedAmountCheck
     * check if habit was completed in the last week,
     * if so reset it's checked amount and update in the database
     *
     */
    private void completedAmountCheck()throws ParseException{
        for(Habit habit : habits){
            if(HabitDateValidator.updateCompletedAmount(habit)){
               HabitManager.updateHabit(habit);
            }
        }
    }



}
