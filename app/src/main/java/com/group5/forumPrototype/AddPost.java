package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddPost extends Activity {


    public String postTitle;
    public String postContent;
    public TextView titleTextBox;
    public TextView contentTextBox;
    Button addPost2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        //add post
        addPost2 = findViewById(R.id.addPost);

        titleTextBox=findViewById(R.id.postTitle);
        contentTextBox=findViewById(R.id.postContent);
        addPost2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // variables that catch the values filled on textbox
                postTitle=(String)titleTextBox.getText();
                postContent=(String)contentTextBox.getText();
                addButton2();
            }
        });
    }

    public void addButton2() {

        // must be changed , redirect to a new page including the new post on ForumDisplay
        Intent addPostIntent = new Intent(this, ForumDisplay.class);
        startActivity(addPostIntent);
    }
}