package comp3350.habittracker.Presentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Logic.UserManager;
import comp3350.habittracker.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        configRegisterButton();
    }

    private void launchHomePage(User user){
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish(); //remove from back stack
    }

    private void configRegisterButton(){
        Button registerDone = findViewById(R.id.registerDone);

        registerDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userNameEdit = findViewById(R.id.userNameRegister);
                String username = userNameEdit.getText().toString();
                if(TextUtils.isEmpty(username)) {
                    userNameEdit.setError("Please enter your username");
                    return;
                }else if(username.length() > 20){
                    userNameEdit.setError("Max length of username is 20! Current name is over that limit");
                    return;
                }

                EditText passwordEdit = findViewById(R.id.passwordRegister);
                String password = passwordEdit.getText().toString();
                if(TextUtils.isEmpty(password)) {
                    passwordEdit.setError("Please enter your password");
                    return;
                }else if(password.length() > 20){
                    passwordEdit.setError("Max length of password is 20! Current password is over that limit");
                    return;
                }

                if(UserManager.register(username,password)){
                    //store info for next time
                    SharedPreferences sharedPreferences = getSharedPreferences("account", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);

                    launchHomePage(new User(username));
                }else{
                    UserMessage.fatalError(RegisterActivity.this, "Unable to register user.");
                }
            }
        });
    }

}
