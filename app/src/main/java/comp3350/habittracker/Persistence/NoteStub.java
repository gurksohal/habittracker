package comp3350.habittracker.Persistence;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;

public class NoteStub implements NotePersistance {
    public boolean addNote(Note note){
        return false;
    }
    public ArrayList<Habit> getHabitNotes(int habitID){
        return null;
    }
    public boolean deleteNote(int noteID){
        return false;
    }
    public boolean modifyNote(Note note){
        return false;
    }
    public Note getNote(int noteId){
        return null;
    }
}
