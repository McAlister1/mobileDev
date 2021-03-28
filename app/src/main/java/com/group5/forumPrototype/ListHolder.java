package com.group5.forumPrototype;

import java.util.ArrayList;

public class ListHolder {
    final ArrayList<String> forumPostsListTitle = new ArrayList<String>();
    final ArrayList<String> forumPostsListContent = new ArrayList<String>();
    final ArrayList<String> forumDisplayString = new ArrayList<>();
    final ArrayList<String> forumPostAuthor = new ArrayList<>();
    final ArrayList<String> forumPostDate = new ArrayList<>();
    //final ArrayList<String> forumPostComments = new ArrayList<>();



    private ListHolder() {}

    static ListHolder getInstance() {
        if( instance == null ) {
            instance = new ListHolder();
        }
        return instance;
    }

    private static ListHolder instance;
}
