package comp3350.habittracker.Logic.IntegrationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import comp3350.habittracker.Logic.UserManager;
import comp3350.habittracker.Persistence.HSQLDB.UserHSQLDB;
import comp3350.habittracker.Persistence.UserPersistence;
import comp3350.habittracker.Utils.TestUtils;


public class UserManagerIT {
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        tempDB = TestUtils.copyDB();
        UserPersistence userPersistence = new UserHSQLDB(tempDB.getAbsolutePath().replace(".script",""));
        new UserManager(userPersistence);
    }

    @Test
    public void testLogin(){
        //able to fetch the user from db
        assertTrue(UserManager.login("userA", "pass"));
    }

    @Test
    public void testRegister(){
        UserManager.register("fake", "123");
        //
        //should be able to fetch the newly registered user
        assertTrue(UserManager.login("fake","123"));
    }

    @Test
    public void testChangePassword(){
        UserManager.changePassword("userA", "update");

        //old info shouldn't work anymore
        assertFalse(UserManager.login("userA", "pass"));
        //should be able to login with the updated password
        assertTrue(UserManager.login("userA", "update"));
    }

    @After
    public void tearDown() {
        // reset DB
        tempDB.delete();
    }
}
