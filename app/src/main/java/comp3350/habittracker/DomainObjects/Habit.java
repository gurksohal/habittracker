package comp3350.habittracker.DomainObjects;

import androidx.annotation.Nullable;

public class Habit {

    private String habitName;
    private int weeklyAmount;
    private int completedWeeklyAmount;
    private User user;

    public Habit(String name, int weeklyAmt, int completedWeekAmt, User u){
        habitName = name;
        weeklyAmount = weeklyAmt;
        completedWeeklyAmount = completedWeekAmt;
        user = u;
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

    public User getUser(){
        return user;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean returnValue = false;
        if(obj instanceof Habit){
            Habit otherHabit = (Habit) obj;
            if(otherHabit.habitName.equalsIgnoreCase(habitName) && otherHabit.user.equals(user)){
                returnValue = true;
            }
        }
        return returnValue;
    }
}
