/**
 * This class allows data to be added to ArrayLists throughout the use of the app. The data
 * in the ArrayLists can be called from anywhere in the application allowing us to pass data and
 * update data dynamically.
 *
 * Version 30/03/2021
 */
package com.group5.forumPrototype;

import java.util.ArrayList;

public class ListHolder {
    final ArrayList<String> forumPostsListTitle = new ArrayList<String>();
    final ArrayList<String> forumPostsListContent = new ArrayList<String>();
    final ArrayList<String> forumDisplayString = new ArrayList<>();
    final ArrayList<String> forumPostAuthor = new ArrayList<>();
    final ArrayList<String> forumPostDate = new ArrayList<>();



    private ListHolder() {}

    static ListHolder getInstance() {
        if( instance == null ) {
            instance = new ListHolder();
        }
        return instance;
    }

    private static ListHolder instance;
}
