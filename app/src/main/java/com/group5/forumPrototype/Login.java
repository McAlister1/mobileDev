package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    //Defining the text boxes and button for login
    EditText username, password;
    Button login;
    boolean isUsernameValid, isPasswordValid;

    /**
     * OnCreate Method that links the text boxes to the xml and login button to the xml
     * Then it implements the method for logging in
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.editTextTextPersonName);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        login = (Button)findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckCredentials();
            }
        });
    }

    /**
     * Method to check the credentials entered into the username and password fields
     */
    private void CheckCredentials() {
        //Check username is correct
        if (username.getText().toString().equals("admin")) {
            isUsernameValid = true;
        } else {
            isUsernameValid = false;
        }

        //Check password is correct
        if (password.getText().toString().equals("admin")) {
            isPasswordValid = true;
        } else {
            isPasswordValid = false;
        }

        //if both are valid, move to next screen
        if(isUsernameValid && isPasswordValid) {
            startActivity(new Intent(Login.this, SelectModule.class));
        }
    }
}