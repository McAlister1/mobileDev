package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ForumDisplay extends Activity {
    Spinner studentSpinner, filterSpinner;
    ArrayAdapter<CharSequence> studentAdapter;
    ArrayAdapter<CharSequence> filterAdapter;
    Button backToModuleSelBtn, addPostBtn;
    ListView forumPostsDisplay;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_display);
        studentSpinner = (Spinner) findViewById(R.id.studentSpinner);
        filterSpinner = (Spinner) findViewById(R.id.filterSpinner);
        studentAdapter = ArrayAdapter.createFromResource(this, R.array.student_spinner, android.R.layout.simple_spinner_item);
        filterAdapter = ArrayAdapter.createFromResource(this, R.array.filter_spinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        studentSpinner.setAdapter(studentAdapter);
        filterSpinner.setAdapter(filterAdapter);

        ListView announcementsDisplay = findViewById(R.id.announcementsDisplay);
        ArrayList<String> announcements = new ArrayList<>();
        announcements.add("Announcement 1");
        announcements.add("Announcement 2");
        announcements.add("Announcement 3");
        announcements.add("Announcement 4");
        announcements.add("Announcement 5");
        announcements.add("Announcement 6");
        ArrayAdapter adapterAnnounce = new ArrayAdapter(this, android.R.layout.simple_list_item_1, announcements);
        announcementsDisplay.setAdapter(adapterAnnounce);

        forumPostsDisplay = findViewById(R.id.forumPostsDisplay);
        ArrayList<String> forumPosts = new ArrayList<>();
        forumPosts.add("Forum post 1");
        forumPosts.add("Forum post 2");
        forumPosts.add("Forum post 3");
        forumPosts.add("Forum post 4");
        forumPosts.add("Forum post 5");
        forumPosts.add("Forum post 6");

        ArrayAdapter adapterForum = new ArrayAdapter(this, android.R.layout.simple_list_item_1, forumPosts);
        forumPostsDisplay.setAdapter(adapterForum);


        //Select back to module
        backToModuleSelBtn = findViewById(R.id.backToModuleSelButton);

        //add post
        addPostBtn = findViewById(R.id.addPost);

        forumPostsDisplay.setOnItemClickListener((parent, view, position, id) -> {
            if(position < 10){
                Intent displayForumPostIntent = new Intent(view.getContext(), DisplayForumPost.class);
                startActivity(displayForumPostIntent);
            }
        });

    }



    public void backToModuleSelect(View view) {
        Intent openModuleSel = new Intent(this, SelectModule.class);
        startActivity(openModuleSel);
    }

    public void addButton(View view) {
        Intent addPostIntent = new Intent(this, AddPost.class);
        startActivity(addPostIntent);
    }




}
