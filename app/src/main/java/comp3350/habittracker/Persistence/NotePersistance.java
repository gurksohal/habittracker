package comp3350.habittracker.Persistence;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;
import java.util.ArrayList;

public interface NotePersistance {
    boolean addNote(Note note);
    ArrayList<Habit> getHabitNotes(Habit habit);
    void deleteNote(Note note);
    boolean modifyNote(Habit habit, Habit newHabit);
    boolean update(Habit habit);
}
