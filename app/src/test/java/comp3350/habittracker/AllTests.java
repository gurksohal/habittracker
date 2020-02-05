package comp3350.habittracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.habittracker.Logic.CalendarDateValidatorTests;
import comp3350.habittracker.Logic.HabitDateValidatorTests;
import comp3350.habittracker.Logic.HabitListManagerTests;
import comp3350.habittracker.Logic.HabitManagerTests;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalendarDateValidatorTests.class,
        HabitDateValidatorTests.class,
        HabitListManagerTests.class,
        HabitManagerTests.class
})
public class AllTests {
}
