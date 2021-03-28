package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPost extends Activity {


    String postTitle;
    String postContent;
    TextView titleTextBox;
    TextView contentTextBox;
    Button addPost2;
    String postDate;
    int countOpenForumDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);


        //add post
        addPost2 = findViewById(R.id.addPost);

        titleTextBox=findViewById(R.id.postTitle);
        contentTextBox=findViewById(R.id.postContent);
        addPost2.setOnClickListener(v -> {
            Intent addPostIntent = new Intent(this, ForumDisplay.class);
            // variables that catch the values filled on textbox
            postTitle = titleTextBox.getText().toString();
            postContent = contentTextBox.getText().toString();
            //String postAuthor = "STUDENT NAME";
            postDate =  new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            addPostIntent.putExtra("Title", postTitle);
            addPostIntent.putExtra("Content", postContent);
            addPostIntent.putExtra("Author", "STUDENT NAME");
            addPostIntent.putExtra("Date", postDate);


            startActivity(addPostIntent);
        });
    }


}