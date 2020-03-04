package comp3350.habittracker.Presentation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.habittracker.Application.Main;
import comp3350.habittracker.Application.Services;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.HabitManager;
import comp3350.habittracker.Logic.NotesManager;
import comp3350.habittracker.Logic.UserManager;
import comp3350.habittracker.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        copyDatabaseToDevice();
        loadManagers();

        SharedPreferences sharedPreferences = getSharedPreferences("account", 0);
        String username = sharedPreferences.getString("username", ""); //get username if its stored, otherwise default to empty string
        String password = sharedPreferences.getString("password", "");

        //if username and pass are both stored, and info hasn't changed
        if(username.length() > 0 && password.length() > 0 && UserManager.login(username, password)){
            launchHomePage(new User(username));
        }

        configLoginButton();
        configRegisterButton();
    }

    private void loadManagers(){
        new UserManager(Services.getUserPersistence());
        new HabitManager(Services.getHabitsPersistence());
        new NotesManager(Services.getNotePersistence());
    }

    private void launchRegisterView(){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    private void launchHomePage(User user){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish(); //remove from back stack
    }

    private void configLoginButton(){
        final Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loginUserName = findViewById(R.id.userNameLogin);
                String username = loginUserName.getText().toString();
                if(TextUtils.isEmpty(username)) {
                    loginUserName.setError("Please enter your username");
                    return;
                }

                EditText loginPassword = findViewById(R.id.passwordLogin);
                String password = loginPassword.getText().toString();
                if(TextUtils.isEmpty(password)) {
                    loginPassword.setError("Please enter your password");
                    return;
                }

                if(username.length() <= 20 && password.length() <= 20 && UserManager.login(username,password)){
                    //store username and pass
                    SharedPreferences sharedPreferences = getSharedPreferences("account", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.commit();

                    launchHomePage(new User(username));
                }else{
                    login.setError("Incorrect username/password");
                }

            }
        });
    }

    private void configRegisterButton(){
        final Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                launchRegisterView();
            }
        });
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());
        } catch (final IOException ioe) {
            UserMessage.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
