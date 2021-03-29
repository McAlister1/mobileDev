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
    Button backButton, likeButton, likeButton2;
    int count1, count2 = 0;
    boolean pressed, pressed2 = false;
    //TextView editTextComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_forum_post);

        studentSpinner = (Spinner) findViewById(R.id.selectModuleStudentSpinner);
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

        /**
         * Code for the like button to add a like to the post.
         */
        likeButton = findViewById(R.id.likeButton);
        likeButton2 = findViewById(R.id.likeButton2);
        likeButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pressed == false){
                    likePost();
                } else {
                    removeLike();
                }

            }
        }));
        likeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pressed2 == false){
                    likePost2();
                } else {
                    removeLike2();
                }
            }
        });

    }

    /**
     * Method that contains code to remove one like from the post.
     */
    private void removeLike() {
        count1--;
        TextView txt = (TextView)findViewById(R.id.likeCounter2);
        txt.setText("Likes: " +count1);
        pressed = false;
    }
    private void removeLike2() {
        count2--;
        TextView txt = (TextView)findViewById(R.id.likeCounter);
        txt.setText("Likes: " +count2);
        pressed2 = false;
    }

    /**
     * Method that contains the code to like a post and add one like to it.
     */
    private void likePost() {
        count1++;
        TextView txt = (TextView)findViewById(R.id.likeCounter2);
        txt.setText("Likes: " +count1);
        pressed = true;

    }
    private void likePost2() {
        count2++;
        TextView txt = (TextView)findViewById(R.id.likeCounter);
        txt.setText("Likes: " +count2);
        pressed2 = true;
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