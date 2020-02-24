package comp3350.habittracker.DomainObjects;

public class Note {
    private int habitId;
    private String notes;
    public Note(int id, String notes){
        habitId = id; //notes are associated with a habit
        this.notes = notes; //save the contents of the note
    }
    public void setHabitId(int id){habitId=id;}
    public void setNotes(String n){notes=n;}
}
