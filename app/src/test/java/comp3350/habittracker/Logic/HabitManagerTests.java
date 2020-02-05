package comp3350.habittracker.Logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.TestUtils;

public class HabitManagerTests {
    private HabitManager habitManager;
    private User user;
    private ArrayList<Habit> habits;
    private Habit newHabit;
    @Before
    public void setUp() {
        user = new User("userA");
        habitManager = new HabitManager();
        //create fake database for testing
        habits = TestUtils.createDB(user);
        habitManager.setupTest(habits);
        //create new Habit
        newHabit = new Habit("newHabit",1,0,user,"Morning",1);
    }

    @Test
    public void testSaveNewHabit(){
        habitManager.saveNewHabit("newHabit","1",user,"Morning",1);
        //the database should contain the new habit
        assertTrue("database should contain the new habit",habits.contains(newHabit));
    }

    @Test
    public void testDelete(){
        habits.add(newHabit); //manually add new habit to db
        //save db state
        ArrayList<Habit> dbState = new ArrayList<>(habits);
        HabitManager.delete(newHabit);
        //the state of two db instances should not be the same
        assertNotEquals("two db states shouldn't be equal, one is missing newHabit", habits,dbState);
    }

    @Test
    public void testGetHabits(){
        assertEquals("db should be equal", HabitManager.getHabits(user),habits);
    }
}
