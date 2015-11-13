package com.example.android.epiquick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.ImageView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    int index = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayFiles(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method will update the display and text based on which radio button
     * is selected when the "learn More button is clicked.
     *
     * @param view
     */
    public void submit(View view) {
        //set index equal to radiobutton's index
        index = checkRadio();

        //fetch files
        displayFiles(index);
    }

    /**
     * reutrns to the starting info screen on click
     *
     * @param view
     */
    public void home(View view) {
        //set index to intro file path
        index = 5;

        //fetch files
        displayFiles(index);
    }

    /**
     * Ths method will get the index of the checked radio button
     *
     * @return an integer equal to the index of the radio button in radio group
     */

    private int checkRadio() {
        index = 5;
        RadioGroup userChoice = (RadioGroup) findViewById(R.id.my_group);
        // Returns an integer which represents the selected radio button's ID
        int selected = userChoice.getCheckedRadioButtonId();

        //update the view with the selected button
        View selectedButton = userChoice.findViewById(selected);

        //verify the index choice
        int idx = userChoice.indexOfChild(selectedButton);

        //return the value of the index only if it is within the parameters
        if (idx < 5 || idx >= 0) {
            return idx;
        }

        //if index equals anything else return to the ome screen
        else {
            Toast.makeText(this, "Something went wrong! Please refresh EpiQuick.", Toast.LENGTH_SHORT).show();
            return 5;
        }

    }

    /**
     * This method will fetch the text for a given illness based on the index
     */
    private void displayFiles(int path) {
        TextView text = (TextView) findViewById(R.id.text);
        ImageView display = (ImageView) findViewById(R.id.head_image);
        String line = "";
        StringBuilder finalString = new StringBuilder();
        InputStream iStream = null;

        if (path == 0) {
            iStream = getResources().openRawResource(R.raw.salm);
            display.setImageResource(R.drawable.salm);
        } else if (path == 1) {
            iStream = getResources().openRawResource(R.raw.lyme);
            display.setImageResource(R.drawable.lyme);
        } else if (path == 2) {
            iStream = getResources().openRawResource(R.raw.pox);
            display.setImageResource(R.drawable.pox);
        } else if (path == 3) {
            iStream = getResources().openRawResource(R.raw.shig);
            display.setImageResource(R.drawable.shig);
        } else if (path == 4) {
            iStream = getResources().openRawResource(R.raw.gia);
            display.setImageResource(R.drawable.gia);
        } else if (path == 5) {
            iStream = getResources().openRawResource(R.raw.intro);
            display.setImageResource(R.drawable.intro);
        } else {
            Toast.makeText(this, "Something went wrong! Please refresh EpiQuick.", Toast.LENGTH_SHORT).show();
            return;
        }

        BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
        try {
            while ((line = bReader.readLine()) != null) {
                finalString.append(line);
                finalString.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(finalString);


    }


}
