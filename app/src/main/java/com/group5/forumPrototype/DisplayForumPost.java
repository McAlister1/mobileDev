package com.group5.forumPrototype;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class DisplayForumPost extends Activity {

    Spinner studentSpinner;
    ArrayAdapter<CharSequence> studentAdapter;
    TextView postTitleAndContent;
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

    }


}