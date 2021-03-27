package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AnnouncementsDisplay extends AppCompatActivity {
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_display);

        display = findViewById(R.id.announcementsDisplayText);

        //Grab intent from list view and put into single string to be displayed
        Bundle extras = getIntent().getExtras();
        String announcement = extras.getString("Title") + "\n \n" + extras.getString("Content");
        display.setText(announcement);

    }
}