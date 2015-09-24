package com.example.farid.myapplication;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


// the class ActionBArActivity has been deprecated and we should use AppCompatActivity instead
public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent received_intent = getIntent();
        String message = received_intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // modify the action bar of this activity
        ActionBar displayMessageActionBar = getSupportActionBar();
        if (displayMessageActionBar != null) {
            // modification of the title that appears on the action Bar
            displayMessageActionBar.setTitle("Message : ");
        }



        // don't forget to always pass the Context as a parameter when creating a View
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        setContentView(textView);


        // Here we have to enable the Up button on the action bar (by clicking on the Icon we will be able to go up in the application
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    // cette méthode est là pour répondre à un clic de l'utilisateur sur la barre d'activité

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
}
