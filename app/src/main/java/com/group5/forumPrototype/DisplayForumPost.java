/**
 * Class to display the individual forum posts and comments in a module .
 *
 * Version 30/03/2021
 */

package com.group5.forumPrototype;


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


public class DisplayForumPost extends Activity {

    Spinner studentSpinner;
    ArrayAdapter<CharSequence> studentAdapter;
    TextView postTitleAndContent;
    Button backButton, likeButton, likeButton2, replyButton, replyButton2, editButton, deleteButton;
    int count1, count2 = 0;
    boolean pressed, pressed2 = false;
    TextView comment1, comment2;

    /**
     * This method loads the activity_forum_post.xml. It displays the selected post and allows users
     * to like, reply or comment on posts. User can edit or delete their own posts.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_forum_post);

        studentSpinner = (Spinner) findViewById(R.id.studentSpinner);
        studentAdapter = ArrayAdapter.createFromResource(this, R.array.student_spinner, android.R.layout.simple_spinner_item);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(studentAdapter);
        likeButton = findViewById(R.id.likeButton);
        likeButton2 = findViewById(R.id.likeButton2);
        editButton = findViewById(R.id.editPostButton);
        deleteButton = findViewById(R.id.deleteButton);
        comment1 = findViewById(R.id.editTextComment1);
        comment2 = findViewById(R.id.editTextComment2);
        replyButton = findViewById(R.id.replyButton);
        replyButton2 = findViewById(R.id.replyButton2);
        //Grab intent from list view and put into single string to be displayed
        Bundle extras = getIntent().getExtras();


         /*The activity will display 'fake posts' from other users for prototype visualisation purposes
         * the logged in user wouldn't be able to edit or delete another users posts. The edit and delete button
         * have been hidden for these posts. Functionality, to make this  work dynamically will be added in the future.*/
        if(extras.getString("Title").equals("JavaDocs in Android Studio?") || extras.getString("Title").equals("Question 4 help!!!")){
            deleteButton.setVisibility(View.INVISIBLE);
            editButton.setVisibility(View.INVISIBLE);
        }


        /*Adding a new post wouldn't have any comments to begin with. These have been hidden for
         * prototype visualisation. Again functionality to do this dynamically would be added in the future.*/
        if(extras.getString("Author").equals("STUDENT NAME")){
            comment1.setText(R.string.no_comments);
            likeButton.setVisibility(View.INVISIBLE);
            replyButton.setVisibility(View.INVISIBLE);
            comment2.setVisibility(View.INVISIBLE);
            replyButton2.setVisibility(View.INVISIBLE);
            likeButton2.setVisibility(View.INVISIBLE);
        }

        //Make new post into single display string
        String post = extras.getString("Title").toUpperCase() + "\n" + extras.getString("Author") + " " + extras.getString("Date") + "\n \n" + extras.getString("Content");

        postTitleAndContent = findViewById(R.id.editTextInitialQuestion);
        postTitleAndContent.setText(post);




        //Code for the back button to return the user to the forum display page.
        backButton = findViewById(R.id.buttonBackToForumDisplay);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                returnToForumDisplay();
            }
        });


         //Code for the like button to add a like to the post.
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


         // Code to make logout option work within spinner.
        logoutFromSpinner();
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
                        Intent intent = new Intent(DisplayForumPost.this, Login.class);
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