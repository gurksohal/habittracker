package comp3350.habittracker.Logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.Persistence.NotePersistence;
import comp3350.habittracker.Persistence.Stub.HabitsStub;
import comp3350.habittracker.Persistence.Stub.NotesStub;

public class NotesManager {

    private static NotePersistence notePersistence;

    public NotesManager(NotePersistence db){
        notePersistence = db;
    }

    public static ArrayList<Note> getNotes(Habit habit){
        return notePersistence.getHabitNotes(habit);
    }

    public static ArrayList<String> getNoteText(ArrayList<Note> notes){
        ArrayList<String> returnList = new ArrayList<>();
        for(Note note : notes){
            returnList.add(note.getNoteText());
        }

        return returnList;
    }

    public static Note getNoteByContents(Habit habit, String text){
        Note returnValue = null;
        ArrayList<Note> notes = getNotes(habit);

        for(Note note : notes){
            if(note.getNoteText().equalsIgnoreCase(text)){
                returnValue = note;
                break;
            }
        }
        return returnValue;
    }

    public static boolean saveNewNote(String text, int feel, String date, Habit habit){
        boolean returnValue = false;
        if(text.length() > 0 && getNoteByContents(habit, text) == null){
            Note note = new Note(text, feel, date, habit);
            notePersistence.addNote(note);
            returnValue = true;
        }

        return returnValue;
    }

    public static boolean updateNote(Note oldNote, String text, int feel, String date){
        boolean returnValue = false;
        if(text.length() > 0 && getNoteByContents(oldNote.getHabit(), text) == null){
            Note note = new Note(text, feel, date, oldNote.getHabit());
            notePersistence.editNote(oldNote,note);
            returnValue = true;
        }
        return returnValue;
    }

    public static void deleteNote(Note note){
        notePersistence.deleteNote(note);
    }

    public void setupTest(ArrayList<Note> testNotes){
        NotesStub stub = (NotesStub) notePersistence;
        stub.setTestList(testNotes);
    }
}
