package comp3350.habittracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.habittracker.Logic.CalendarDateValidatorTest;
import comp3350.habittracker.Logic.HabitDateValidatorTest;
import comp3350.habittracker.Logic.HabitListManagerTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalendarDateValidatorTest.class,
        HabitDateValidatorTest.class,
        HabitListManagerTest.class
})
public class AllTests {
}
