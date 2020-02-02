package comp3350.habittracker.Persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;

public interface HabitsPersistence {
    ArrayList<Habit> getUserHabits(User user);
    void deleteHabit(Habit habit);
    boolean addHabit(Habit habit);
}
