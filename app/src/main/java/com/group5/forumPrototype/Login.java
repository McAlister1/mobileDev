package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    //Defining the text boxes and button for login
    EditText username, password;
    Button login;
    boolean isUsernameValid, isPasswordValid;
    Button loginBtn;
    Button forgotEmailBtn;
    Button forgotPassnBtn;
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

        /**
         * This section uses the method to check the credentials before allowing the user to login
         */
        login = (Button)findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckCredentials();
            }
        });


        loginBtn = findViewById(R.id.buttonLogin);
        forgotEmailBtn = findViewById(R.id.buttonForgottenUsername);
        forgotPassnBtn = findViewById(R.id.buttonForgottenPassword);

        //event is generated when the user clicks login button
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //back to homepage with elements initialized
                Intent intent= new Intent(Login.this,SelectModule.class);
                startActivity(intent);

            }


        });

        forgotEmailBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //redirect the user to recover password page
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.strath.ac.uk/professionalservices/it/use/password/"));
                startActivity(intent);

            }


        });

        forgotPassnBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //redirect the user to recover password page
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.strath.ac.uk/professionalservices/it/use/password/"));
                startActivity(intent);


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