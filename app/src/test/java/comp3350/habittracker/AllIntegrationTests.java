package comp3350.habittracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.habittracker.Logic.IntegrationTests.HabitManagerIT;
import comp3350.habittracker.Logic.IntegrationTests.NotesManagerIT;
import comp3350.habittracker.Logic.UserManager;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        HabitManagerIT.class,
        NotesManagerIT.class,
        UserManager.class
})
public class AllIntegrationTests {
}
