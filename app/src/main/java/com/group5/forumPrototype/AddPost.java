package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;

public class AddPost extends Activity {


    String postTitle;
    String postContent;
    TextView titleTextBox;
    TextView contentTextBox;
    Button addPost2, backButton;
    String postDate;
    Spinner studentSpinner;
    ArrayAdapter<CharSequence> studentAdapter;
    int countOpenForumDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);


        //add post
        addPost2 = findViewById(R.id.addPost);

        titleTextBox=findViewById(R.id.postTitle);
        contentTextBox=findViewById(R.id.postContent);
        addPost2.setOnClickListener(v -> {
            Intent addPostIntent = new Intent(this, ForumDisplay.class);
            // variables that catch the values filled on textbox
            postTitle = titleTextBox.getText().toString();
            postContent = contentTextBox.getText().toString();
            //String postAuthor = "STUDENT NAME";
            postDate =  new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            addPostIntent.putExtra("Title", postTitle);
            addPostIntent.putExtra("Content", postContent);
            addPostIntent.putExtra("Author", "STUDENT NAME");
            addPostIntent.putExtra("Date", postDate);

            Toast.makeText(getApplicationContext(), "Adding post to CS991 forum!", LENGTH_SHORT).show();

            startActivity(addPostIntent);


        });

        /**
         * Code for the back button to return the user to the forum display page.
         */
        backButton = findViewById(R.id.backToModuleSelButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                returnToForumDisplay();
            }
        });

        studentSpinner = (Spinner) findViewById(R.id.studentSpinner);
        studentAdapter = ArrayAdapter.createFromResource(this, R.array.student_spinner, android.R.layout.simple_spinner_item);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(studentAdapter);

        /**
         * Code to make logout option work within spinner.
         */
        logoutFromSpinner();

    }

    /**
     * Method that is implemented for the on click listener in the onCreate method that directs the
     * app to the correct page.
     */
    public void returnToForumDisplay() {
        Intent intent = new Intent(this, ForumDisplay.class);
        startActivity(intent);
    }

    /**
     * Method that is implemented from within the onCreate to make the logout option work.
     */
    private void logoutFromSpinner() {
        studentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("STUDENT NAME ▼")){
                    //do nothing
                } else{
                    String item = parent.getItemAtPosition(position).toString();

                    //SHow selected spinner item
                    Toast.makeText(parent.getContext(), "Selected:"+ item, Toast.LENGTH_SHORT).show();

                    if (parent.getItemAtPosition(position).equals("Logout")){
                        Intent intent = new Intent(AddPost.this, Login.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}