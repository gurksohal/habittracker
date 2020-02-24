package comp3350.habittracker.Persistence;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;
import java.util.ArrayList;

public interface NotePersistance {
    boolean addNote(Note note);
    ArrayList<Habit> getHabitNotes(int habitID);
    boolean deleteNote(int noteID);
    boolean modifyNote(Note note);
    Note getNote(int noteId);
}
