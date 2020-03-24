package comp3350.habittracker;


import android.content.Intent;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.habittracker.Application.Services;
import comp3350.habittracker.DomainObjects.Habit;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.HabitManager;
import comp3350.habittracker.Persistence.HabitsPersistence;
import comp3350.habittracker.Presentation.HomeActivity;
import comp3350.habittracker.Presentation.LoginActivity;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
//system tests for adding, deleting and editing habits
public class ManageHabitTests {
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp(){
        //remove stored login info
        SystemTestUtils.cleanUp();
        //remove instance of habit from db
        HabitsPersistence habitsPersistence = Services.getHabitsPersistence();
        for(Habit habit : habitsPersistence.getUserHabits(new User("userA"))){
            if(habit.getHabitName().equals("test habit1") || habit.getHabitName().equals("test habit2")){
                habitsPersistence.deleteHabit(habit);
            }
        }
        //cache login, so it can auto login
        SystemTestUtils.setAccount("userA", "pass");
        Intents.init();
    }

    @After
    public void tearDown(){
        Intents.release();
    }

    @Test
    public void testAddHabit(){
        //click add habit button
        onView(withId(R.id.btnAddHabit)).perform(click());
        //type habit name
        onView(withId(R.id.txtHabitName)).perform(typeText("test habit1"));
        //click add habit button
        onView(withId(R.id.btnAddHabit)).perform(click());
        //check activity to make sure it contains new habit text
        onView(withText("test habit1")).check(matches(isDisplayed()));
    }

    @Test
    public void testEditHabit(){
        //add habit to be edited
        testAddHabit();

        //open top right menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //press habits
        onView(withText("Habits")).perform(click());
        //press the created habit
        onView(withText("test habit1")).perform(click());

        //without this delay the next actionbar menu doesn't open for some reason
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //open top right menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //press Edit Habit
        onView(withText("Edit Habit")).perform(click());
        //enter text
        onView(withId(R.id.txtHabitName)).perform(clearText()).perform(typeText("test habit2"));
        //click add habit
        onView(withId(R.id.btnAddHabit)).perform(click());
        //check activity to make sure it contains new habit text
        onView(withText("test habit2")).check(matches(isDisplayed()));
    }

    @Test
    public void testHabitDelete(){
        //add habit to be edited
        testAddHabit();

        //open top right menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //press habits
        onView(withText("Habits")).perform(click());
        //press the created habit
        onView(withText("test habit1")).perform(click());

        //without this delay the next actionbar menu doesn't open for some reason
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //open top right menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //press Delete Habit
        onView(withText("Delete Habit")).perform(click());
        //press yes
        onView(withText("YES")).perform(click());
        //make sure habit doesn't exist
        onView(withText("test habit1")).check(doesNotExist());
    }


}
