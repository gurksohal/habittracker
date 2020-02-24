package comp3350.habittracker.DomainObjects;

public class Note {
    private int noteId;
    private int habitId; //referencing habit
    private String note; // the actual note
    private int feeling; //feeling after doing habit 1 worse 5 best,  -1 is no feeling attached
    private String noteDate; //date the note was taken
    private String userEmail; //the email of the user taking the note
    public Note(int habitId, String userEmail, String note, int feeling, String noteDate, int noteId ){
        this.habitId = habitId;
        this.userEmail = userEmail;
        this.feeling = feeling;
        this.note = note;
        this.noteDate = noteDate;
        this.noteId = noteId;

    }

    public Note(int habitId, String userEmail, String note, int feeling, String noteDate ){
        this.habitId = habitId;
        this.userEmail = userEmail;
        this.feeling = feeling;
        this.note = note;
        this.noteDate = noteDate;
    }


}
