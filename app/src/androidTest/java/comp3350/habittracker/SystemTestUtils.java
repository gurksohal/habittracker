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

    //set db to default, so its same across all instances
    public static void setTestDB(){
        File dataDirectory = getApplicationContext().getDir("db", Context.MODE_PRIVATE);
        File[] files = dataDirectory.listFiles();
        if(files != null){
            for(File file : files){
                file.delete();
            }
        }
        DBUtils.copyDatabaseToDevice(getApplicationContext());
    }
}
