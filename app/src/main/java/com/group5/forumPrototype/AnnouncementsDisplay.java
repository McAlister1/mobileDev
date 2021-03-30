package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AnnouncementsDisplay extends AppCompatActivity {
    TextView display;
    Button backButton;
    Spinner studentSpinner;
    ArrayAdapter<CharSequence> studentAdapter;

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
                        Intent intent = new Intent(AnnouncementsDisplay.this, Login.class);
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