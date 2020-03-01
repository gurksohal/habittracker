package comp3350.habittracker.Logic;
import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Note;
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
    /*
        gets a Note based on the String contained inside the Note object
     */
    public static Note getNotebyContents(String sNote, int habitId){
        ArrayList<Note> notes = getHabitNotes(habitId);
        for(Note note : notes){
            if(note.getNote().equals(sNote)){
                return note;
            }
        }
       return null;
    }
    /*
        returns an arraylist of all the Strings contained inside all Notes associated with the habitId
     */
    public static ArrayList<String>listNotes(int habitId) {
        ArrayList<Note> notes = getHabitNotes(habitId);
        //display all note contents
        ArrayList<String> noteContents = new ArrayList<String>();
            for(Note n : notes)
                noteContents.add(n.getNote());
            return noteContents;
    }
    /*
        Gets a Note based on the unique id
     */
    public static Note getNote(int noteId){return db.getNote(noteId);}

}
