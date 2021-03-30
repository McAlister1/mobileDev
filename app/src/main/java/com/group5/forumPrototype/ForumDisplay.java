package com.group5.forumPrototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ForumDisplay extends Activity {
    Spinner studentSpinner, filterSpinner;
    ArrayAdapter<CharSequence> studentAdapter;
    ArrayAdapter<CharSequence> filterAdapter;
    Button backToModuleSelBtn, addPostBtn;
    ListView forumPostsDisplay, announcementsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_display);

        studentSpinner = (Spinner) findViewById(R.id.studentSpinner);
        studentAdapter = ArrayAdapter.createFromResource(this, R.array.student_spinner, android.R.layout.simple_spinner_item);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(studentAdapter);

        filterSpinner = (Spinner) findViewById(R.id.filterSpinner);
        filterAdapter = ArrayAdapter.createFromResource(this, R.array.filter_spinner, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);


        //Fill Announcements display with dummy data for prototype visualisation
        announcementsDisplay = findViewById(R.id.announcementsDisplay);
        ArrayList<String> announcementTitles = new ArrayList<>();
        ArrayList<String> announcementContent = new ArrayList<>();
        announcementTitles.add(0,"Week 8: Coursework deadline extended");
        announcementContent.add(0, "The submission for the final coursework has been extended after discussion till 10/4/21");
        announcementTitles.add(1, "Guest lecture - Jeff Besoz from Amazon Web Services.");
        announcementContent.add(1, "I hope you will attend as Jeff is a busy man with a business empire and dreams of world domination.");
        announcementTitles.add(2, "Week 7: videos available");
        announcementContent.add(2, "Sorry for the delay the videos are now available via the MyPlace");
        announcementTitles.add(3, "Changes to assignment");
        announcementContent.add(3, "I will announce changes on Monday's lecture");
        announcementTitles.add(4,"Please submit coursework 1");
        announcementContent.add(4, "Don't be lazy and submi your coursework on time!");
        announcementTitles.add(5,"Welcome to CS991");
        announcementContent.add(5, "In this module you will build cool mobile apps ENJOY!!!!");
        ArrayAdapter adapterAnnounce = new ArrayAdapter(this, android.R.layout.simple_list_item_1, announcementTitles);
        announcementsDisplay.setAdapter(adapterAnnounce);
        announcementsDisplay.setOnItemClickListener((parent, view, position, id) -> {
            Intent displayAnnouncementIntent = new Intent(view.getContext(), AnnouncementsDisplay.class);

            for(int i = 0; i <= 12; i++){
                if(i == position){
                    displayAnnouncementIntent.putExtra("Title", announcementTitles.get(i));
                    displayAnnouncementIntent.putExtra("Content", announcementContent.get(i));
                    break;
                }
            }
            startActivity(displayAnnouncementIntent);

        });

        forumPostsDisplay = findViewById(R.id.forumPostsDisplay);

        addFakePostsOnStart();
        addPostToList();

        ArrayAdapter adapterForum = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListHolder.getInstance().forumDisplayString);
        forumPostsDisplay.setAdapter(adapterForum);


        //Select back to module
        backToModuleSelBtn = findViewById(R.id.backToModuleSelButton);

        //add post
        addPostBtn = findViewById(R.id.addPost);


        forumPostsDisplay.setOnItemClickListener((parent, view, position, id) -> {
            Intent displayForumPostIntent = new Intent(view.getContext(), DisplayForumPost.class);

            for(int i = 0; i <= 12; i++){
                if(i == position){
                    //displayForumPostIntent.putExtra("Title", forumPostTitles.get(i));
                    displayForumPostIntent.putExtra("Title", ListHolder.getInstance().forumPostsListTitle.get(i));
                    displayForumPostIntent.putExtra("Author", ListHolder.getInstance().forumPostAuthor.get(i));
                    displayForumPostIntent.putExtra("Date", ListHolder.getInstance().forumPostDate.get(i));
                    //displayForumPostIntent.putExtra("Comments", forumPostComments.get(i));
                    displayForumPostIntent.putExtra("Content", ListHolder.getInstance().forumPostsListContent.get(i));
                    break;
                }
            }
            startActivity(displayForumPostIntent);

        });


        addPostBtn.setOnClickListener(v -> addButton());

        /**
         * Code to make logout option work within spinner.
         */
        logoutFromSpinner();

    }



    public void backToModuleSelect(View view) {
        Intent openModuleSel = new Intent(this, SelectModule.class);
        startActivity(openModuleSel);
    }

    public void addButton() {
        Intent addPostIntent = new Intent(this, AddPost.class);
        startActivity(addPostIntent);
    }

    //Start application with some fake posts for visualisation purposes
    public void addFakePostsOnStart(){
        if(ListHolder.getInstance().forumDisplayString.size() == 0){
            //Fake post 1
            ListHolder.getInstance().forumPostsListTitle.add(0,  "JavaDocs in Android Studio?");
            ListHolder.getInstance().forumPostAuthor.add(0,"Joe Bloggs");
            ListHolder.getInstance().forumPostDate.add(0, "[12-03-21]");
            ListHolder.getInstance().forumPostsListContent.add(0, "How to generate JavaDocs in Android studio can't seem to figure it out.\n Thanks!");
            ListHolder.getInstance().forumDisplayString.add(0, ListHolder.getInstance().forumPostsListTitle.get(0) + "\n"+ ListHolder.getInstance().forumPostAuthor.get(0) + " " + ListHolder.getInstance().forumPostDate.get(0));

            //Fake post 2
            ListHolder.getInstance().forumPostsListTitle.add(0,  "Question 4 help!!!");
            ListHolder.getInstance().forumPostAuthor.add(0,"Sarah Brown");
            ListHolder.getInstance().forumPostDate.add(0, "[18-03-21]");
            ListHolder.getInstance().forumPostsListContent.add(0, "Anyone know how to return an object in Java?");
            ListHolder.getInstance().forumDisplayString.add(0, ListHolder.getInstance().forumPostsListTitle.get(0) + "\n"+ ListHolder.getInstance().forumPostAuthor.get(0) + " " + ListHolder.getInstance().forumPostDate.get(0));
        }
    }

    public void addPostToList() {
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();

            ListHolder.getInstance().forumPostsListTitle.add(0,  extras.getString("Title"));
            ListHolder.getInstance().forumPostAuthor.add(0, extras.getString("Author"));
            ListHolder.getInstance().forumPostDate.add(0, "[" + extras.getString("Date") + "]");
            ListHolder.getInstance().forumPostsListContent.add(0, extras.getString("Content"));
            ListHolder.getInstance().forumDisplayString.add(0, ListHolder.getInstance().forumPostsListTitle.get(0) + "\n"+ ListHolder.getInstance().forumPostAuthor.get(0) + " " + ListHolder.getInstance().forumPostDate.get(0));
        }
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
                        Intent intent = new Intent(ForumDisplay.this, Login.class);
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
