package comp3350.habittracker;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

import comp3350.habittracker.Persistence.DBUtils;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class SystemTestUtils {

    public static void cleanUp(){
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    public static void setAccount(String username, String pass){
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.putString("password", pass);
        editor.commit();
    }
}
