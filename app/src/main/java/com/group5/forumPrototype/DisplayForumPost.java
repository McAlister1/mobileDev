package com.group5.forumPrototype;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Class to display the individual forum posts and comments.
 */
public class DisplayForumPost extends Activity {

    Spinner studentSpinner;
    ArrayAdapter<CharSequence> studentAdapter;
    TextView postTitleAndContent;
    Button backButton;
    //TextView editTextComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_forum_post);

        studentSpinner = (Spinner) findViewById(R.id.studentSpinner);
        studentAdapter = ArrayAdapter.createFromResource(this, R.array.student_spinner, android.R.layout.simple_spinner_item);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(studentAdapter);

        //Grab intent from list view and put into single string to be displayed
        Bundle extras = getIntent().getExtras();
        String post = extras.getString("Title") + "\n" + extras.getString("Author") + " " + extras.getString("Date") + "\n \t" + extras.getString("Content");

        postTitleAndContent = findViewById(R.id.editTextInitialQuestion);
       //editTextComment = findViewById(R.id.editTextComment1);
        //String comment = extras.getString("Comments");

        //Display new added post
        postTitleAndContent.setText(post);
        //editTextComment.setText(comment);

        /**
         * Code for the back button to return the user to the forum display page.
         */
        backButton = findViewById(R.id.buttonBackToForumDisplay);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                returnToForumDisplay();
            }
        });

    }

    /**
     * Method that is implemented for the on click listener in the onCreate method that directs the
     * app to the correct page.
     */
    public void returnToForumDisplay() {
        Intent intent = new Intent(this, ForumDisplay.class);
        startActivity(intent);
    }


}