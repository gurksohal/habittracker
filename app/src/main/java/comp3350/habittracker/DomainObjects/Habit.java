package comp3350.habittracker.DomainObjects;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.Date;

import comp3350.habittracker.Logic.Utils;

//represents a habit
public class Habit implements Comparable<Habit> {
    private String habitName;
    private int weeklyAmount; //how many times a week user wants to do the task
    private int completedWeeklyAmount; //how many times in the current week has the task been completed
    private String lastCompletedDate;
    private User user; //user who the habit belongs too
    private String timeOfDay;
    private int sortByDay; //1==Morning, 2==Afternoon, 3==Evening, 4==Night

    public Habit(String name, int weeklyAmt, int completedWeekAmt, User user, String time,int num) {
        habitName = name;
        weeklyAmount = weeklyAmt;
        completedWeeklyAmount = completedWeekAmt;
        this.user = user;
        timeOfDay = time;
        sortByDay = num;
    }

    public String getHabitName() {
        return habitName;
    }

    public int getWeeklyAmount() {
        return weeklyAmount;
    }

    public String getTimeOfDay(){
        return timeOfDay;
    }

    public int getSortByDay(){
        return sortByDay;
    }

    public int getCompletedWeeklyAmount() {
        return completedWeeklyAmount;
    }

    public boolean isCompleted(String selectedDate) throws ParseException{
        return completedWeeklyAmount >= weeklyAmount || isSameDay(selectedDate);
    }

    public String getLastCompletedDate() {
        return lastCompletedDate;
    }

    public void clearCompletedAmount(){
        completedWeeklyAmount = 0;
    }

    public void complete(){
        //set last completed date
        lastCompletedDate = Utils.formatDate(new Date());
        //increase completed amount
        completedWeeklyAmount++;
    }

    public User getUser() {
        return user;
    }

    //two habits are equals if they have the same name and user
    @Override
    public boolean equals(@Nullable Object obj) {
        boolean returnValue = false;
        if (obj instanceof Habit) {
            Habit otherHabit = (Habit) obj;
            if (otherHabit.habitName.equalsIgnoreCase(habitName) && otherHabit.user.equals(user)) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    @Override
    public int compareTo(Habit o) {
        int iCompare = o.sortByDay;
        return this.getSortByDay()-iCompare;
    }

    private boolean isSameDay(String date)throws ParseException {
        boolean returnValue = false;
        if(lastCompletedDate != null) {
            Date selectedDate = Utils.parseString(date);
            Date lastDoneDate = Utils.parseString(lastCompletedDate);
            returnValue = selectedDate.equals(lastDoneDate);
        }
        return returnValue;
    }
}
