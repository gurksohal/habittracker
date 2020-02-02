package comp3350.habittracker.Persistence;

import java.util.List;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;

public interface HabitsPersistence {
    List<Habit> getUserHabits(User user);
    void deleteHabit(Habit habit);
    boolean addHabit(Habit habit);
}
