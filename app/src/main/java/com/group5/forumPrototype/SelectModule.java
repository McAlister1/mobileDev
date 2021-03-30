/**
 * This activity allows the user to select the module that they would like to see
 * announcements and posts for.
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
import android.widget.Toast;


public class SelectModule extends Activity {

    Spinner studentSpinner;
    ArrayAdapter<CharSequence> studentAdapter;
    Button cs991Button;

    /**
     * This method loads the activity_select_module.xml and allows user to select the module forum
     * they wish to see.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_module);


         //Code to direct the cs991 button to the forum page
        cs991Button = findViewById(R.id.CS991Button);
        cs991Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openPostPage();
            }



        });

        studentSpinner = (Spinner) findViewById(R.id.studentSpinner);
        studentAdapter = ArrayAdapter.createFromResource(this, R.array.selectModuleSpinner, android.R.layout.simple_spinner_item);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(studentAdapter);


         //Code to make logout option work within spinner.
        logoutFromSpinner();


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
                        Intent intent = new Intent(SelectModule.this, Login.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Method that the cs991 button code uses to locate the page.
     */
    private void openPostPage() {
        Intent intent = new Intent(this, ForumDisplay.class);
        startActivity(intent);
    }


}
