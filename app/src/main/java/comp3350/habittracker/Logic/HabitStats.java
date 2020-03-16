package comp3350.habittracker.Logic;

import android.util.Log;

import java.text.ParseException;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.Note;

public class HabitStats {

    private Habit habit;

    public HabitStats(Habit h){
        habit = h;
    }

    public String getCompletedThisWeek(){
        int toComplete = habit.getWeeklyAmount();
        int completed = habit.getCompletedWeeklyAmount();
        return completed + "(" + (completed/toComplete)*100 + "%)";
    }

    public String getLastCompleteDate(){
        String returnString = habit.getLastCompletedDate();
        if(returnString == null){
            returnString = "Never completed";
        }
        return returnString;
    }

    public String getFavDay(){
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int maxIndex = 0;
        String returnString = "None";
        int[] array = habit.getDaysOfWeek();
        for(int i = 1; i < array.length; i++){
            if(array[i] > array[maxIndex]){
                maxIndex = i;
            }
        }

        if(array[maxIndex] > 0){
            returnString = days[maxIndex];
        }
        return returnString;
    }

    public String getAvgNoteFeeling(){
        ArrayList<Note> notes = NotesManager.getNotes(habit);
        double sum = 0;
        for(Note note : notes){
            sum = note.getFeeling();
        }

        int avg = (int) Math.round(sum/notes.size());
        String returnString = "";
        if(avg == 0){
            returnString = "Bad";
        }else if(avg == 1){
            returnString = "Average";
        }else if(avg == 2){
            returnString = "Good";
        }else if(notes.size() == 0){
            returnString = "No notes added!";
        }
        return returnString;
    }

    public int getTimesCompleted(){
        return habit.getTotalCompletedAmt();
    }

    public boolean isCompletedThisweek(){
        return habit.getCompletedWeeklyAmount() >= habit.getWeeklyAmount();
    }
}
