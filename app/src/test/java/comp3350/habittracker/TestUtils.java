package comp3350.habittracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;

public class TestUtils {

    //add days to current date
    public static Date addDaysToDate(int days){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,days); //set calendar to a future date
        return cal.getTime();
    }

    public static ArrayList<Habit> createDB(User user){
        ArrayList<Habit> returnList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            Habit h = new Habit("h"+i,1,0,user,"Afternoon",2);
            returnList.add(h);
        }
        Habit h = new Habit("h"+4,1,0,user,"Morning",1);
        returnList.add(h);
        return returnList;
    }
}
