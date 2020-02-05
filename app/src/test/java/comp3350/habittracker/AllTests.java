package comp3350.habittracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.habittracker.Logic.CalendarDateValidatorTest;
import comp3350.habittracker.Logic.HabitDateValidatorTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalendarDateValidatorTest.class,
        HabitDateValidatorTest.class
})
public class AllTests {
}
