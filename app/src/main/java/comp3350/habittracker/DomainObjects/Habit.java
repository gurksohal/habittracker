package comp3350.habittracker.DomainObjects;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

//represents a habit
public class Habit {
    private String habitName;
    private int weeklyAmount; //how many times a week user wants to do the task
    private int completedWeeklyAmount; //how many times in the current week has the task been completed
    private String lastCompletedDate;
    private User user; //user who the habit belongs too

    public Habit(String name, int weeklyAmt, int completedWeekAmt, User user) {
        habitName = name;
        weeklyAmount = weeklyAmt;
        completedWeeklyAmount = completedWeekAmt;
        this.user = user;
    }

    public String getHabitName() {
        return habitName;
    }

    public int getWeeklyAmount() {
        return weeklyAmount;
    }

    public int getCompletedWeeklyAmount() {
        return completedWeeklyAmount;
    }

    public boolean isCompleted() {
        return completedWeeklyAmount >= weeklyAmount;
    }

    public String getLastCompletedDate() {
        return lastCompletedDate;
    }

    public void clearCompletedAmout(){
        completedWeeklyAmount = 0;
    }

    public void complete(){
        //set last completed date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        lastCompletedDate = formatter.format(date);

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
}
