package comp3350.habittracker.Persistence;

import java.util.ArrayList;


import comp3350.habittracker.DomainObjects.Note;

public class NoteStub implements NotePersistance {
    private ArrayList<Note> notes = new ArrayList<>();
    int uniqueId = 0; //keep track of the uniqueID for Habits


    public NoteStub(){
        notes.add(0,new Note(1,"userA","test Note From stub", 2, "20200224",uniqueId));
        uniqueId++;
        notes.add(0,new Note(1,"userA","second", 2, "20200224",uniqueId));
        uniqueId++;
        notes.add(0,new Note(1,"userA","third", 2, "20200224",uniqueId));
        uniqueId++;
        notes.add(0,new Note(1,"userA","fourth", 2, "20200224",uniqueId));
        uniqueId++;

    }

    public boolean addNote(Note note){
        boolean returnValue = false;
        //todo: habit must check for name and uID of each item not just the habit object itself

        if(!notes.contains(note)){
            note.setNoteId(uniqueId);
            uniqueId++;
            notes.add(note);
            returnValue = true;
        }
        return returnValue;
    }
    public ArrayList<Note> getHabitNotes(int habbitID){
        ArrayList<Note> habitNotes = new ArrayList<>();
        for(Note note : notes){
            if(note.getHabitId() == habbitID){
                habitNotes.add(note);
            }
        }
        return habitNotes;
    }
    public boolean deleteNote(int noteID){
        if (noteID>=0){
            for (Note note : notes) {
                if (note.getNoteId() == noteID) {
                    notes.remove(note);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean modifyNote(Note note){
        if(note.getNoteId()>=0) {
            for (Note notei : notes) {
                if (note.getNoteId() == notei.getNoteId()) {
                    notes.remove(notei);
                    notes.add(note);
                    return true;
                }
            }

        }
        return false;
    }
    public Note getNote(int noteId){
        for(Note note : notes){
            if(note.getNoteId() == noteId){
                return note;
            }
        }
        return null;
    }
}
