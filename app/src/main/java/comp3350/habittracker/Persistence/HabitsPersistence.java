package comp3350.habittracker.Persistence;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;


public interface HabitsPersistence {
    ArrayList<Habit> getUserHabits(String userEmail);
    boolean deleteHabit(int habitId);
    boolean deleteByName(String habitName, String userEmail);
    boolean addHabit(Habit habit);
    boolean modify(Habit modifiedHabit);
    Habit getHabit(int habitId);
}
