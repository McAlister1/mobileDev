package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnnouncementsDisplay extends AppCompatActivity {
    TextView display;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_display);

        display = findViewById(R.id.announcementsDisplayText);

        //Grab intent from list view and put into single string to be displayed
        Bundle extras = getIntent().getExtras();
        String announcement = extras.getString("Title") + "\n \n" + extras.getString("Content");
        display.setText(announcement);

        /**
         * Code for the back button to return the user to the forum display page.
         * Calls method from DisplayForumPost class.
         */
        backButton = findViewById(R.id.backToForumDisplayPageButton);
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