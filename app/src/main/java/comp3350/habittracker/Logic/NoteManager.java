package comp3350.habittracker.Logic;
import java.text.ParseException;
import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Persistence.NotePersistance;
import comp3350.habittracker.Persistence.NoteStub;
public class NoteManager {
    private static NotePersistance db;

    public NoteManager(){
        db = new NoteStub();
    }
    /*
     * saveNewNote
     * return true if note was saved successfully
     */
    public static boolean saveNewNote(int habitId, String userEmail, String note, int feeling, String noteDate){
        boolean bReturn = false;
        if(note.length()>0) {
            Note nNote = new Note(habitId, userEmail, note, feeling, noteDate);
            bReturn = db.addNote(nNote);
        }
        return bReturn;
    }
    /*
     * updateNote
     */
    public static boolean updateNote(Note note){
        return db.modifyNote(note);
    }

    public static void deleteNote(int noteID){
        db.deleteNote(noteID);
    }
    /*
     * returns all notes associated with a given habitID
     */
    public static ArrayList<Note> getHabitNotes(int habitID){
        return db.getHabitNotes(habitID);
    }

}
