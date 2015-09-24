package com.example.farid.myapplication;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements ImageDialog.DialogAnswerInterface {

    public static int m_theme = 1;

    public final static String EXTRA_MESSAGE = "com.example.farid.myapplication.MESSAGE";

    public final static String EXTRA_ARRAY = "com.example.farid.myapplication.ARRAY_IMAGES";

//    public Bundle chosen_values;

    public ArrayList chosen_values;


    public TextView textView_test_prg;


    boolean restart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String pref_appearance = sharedPreferences.getString("pref_general_appearance", "");

        assert (pref_appearance != null);
        if (pref_appearance.equals(getString(R.string.white))) {
            setTheme(R.style.HoloNormal);
        } else if (pref_appearance.equals(getString(R.string.black))) {
            setTheme(R.style.HoloDark);
        }else if (pref_appearance.equals(getString(R.string.custom))) {
            setTheme(R.style.TotalCustom);
        }

        super.onCreate(savedInstanceState);


        ActionBar mainActionBar = getSupportActionBar();
        assert (mainActionBar!= null);
        mainActionBar.setTitle(R.string.Main_action_bar_title);

/*
        // We first need to use a FragmentManager to get the instance of the Frag class
        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final Frag_disp_message fragment = new Frag_disp_message();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        // commit the transaction
        fragmentTransaction.commit();
*/


        // change the content of textView_test_prg with the value entered in the preferences


        setContentView(R.layout.activity_main);


        // We try to add an other TextView programmatically
        LinearLayout testLinear = (LinearLayout) findViewById(R.id.lin_lay_test);
        textView_test_prg = new TextView(this);
        textView_test_prg.setTag("text_test");
        textView_test_prg.setText(pref_appearance);
        // try to change the parameters of the TextView to have half of the size

        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,0,1);
        // now we can set the parameters to the view
        textView_test_prg.setLayoutParams(params);
        // and finally we can add the view (not necessary after the other methods)
        testLinear.addView(textView_test_prg);



        textView_test_prg.setBackgroundColor(Color.RED);

        // this part is about to add a listener to the textEdit field

        EditText edit_message = (EditText) findViewById(R.id.edit_message);
        edit_message.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        // this to methods are not really correct in their content
                        /*
                        if (count > 5) {
                            // We do something here if the user has entered a text with a size > 5 characters

                            EditText edit_message = (EditText) findViewById(R.id.edit_message);
                            edit_message.setBackgroundColor(Color.rgb(255, 0, 0));


                            //TextView message_disp = (TextView) findViewById(R.id.message_displayer);
                            //message_disp.setText(s);
                        } else if (count - before <= 5) {
                            EditText edit_message = (EditText) findViewById(R.id.edit_message);
                            edit_message.setBackgroundColor(Color.parseColor("#5A5A5A"));
                        }
                        */


                        // A test to see if the content of an other TextView directly in the activity is updated or not

                        TextView textView_test = (TextView) findViewById(R.id.disp_test);
                        textView_test.setText(s);

//                        textView_test_prg.setText(s);



                        // try by using an other method
                        Frag_disp_message frag = (Frag_disp_message) getFragmentManager().findFragmentById(R.id.frag_disp_message);
                        frag.disp_message.setText(s);


                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        );





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // populate the Application's action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle the clickable button of the action Bar
    @Override
    public boolean onOptionsItemSelected (MenuItem menu)
    {
        // depending of the item selected
        switch (menu.getItemId())
        {
            case R.id.action_deleteMessage:
                // in this case an other function is called
                deleteMessage();
                return true;

            case R.id.action_showActionBar:
                //
                showActionBar();
                return true;

            case R.id.action_hideActionBar:
                hideActionBar();
                return true;

            case R.id.action_settings:
                openSettings();
                return true;

            // this two methods will not work because of the fact that the application must restart if the theme have to be changed
            /*
            case R.id.theme_light:
                setThemeLight();
                return true;
            case R.id.theme_dark:
                setThemeDark();
                return true;
            */
            default:
                return super.onOptionsItemSelected(menu);
        }
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle savedStateInstance)
    {
        Bundle savedState = new Bundle();
        savedState.putInt("theme", m_theme);
        super.onSaveInstanceState(savedState);
    }
    */

    // this method is obsolet because we can't change the theme of the application programmitacaly without restart it
    // we can try to implements this method to change the activity appearance when the settings are modified



    @Override
    public void onResume()
    {
        super.onResume();

        // the method with the restart variable seems not to be correct
        if (restart) {
            //here we need to get the SharedPreference file
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String pref_appearance = sharedPreferences.getString("pref_general_appearance", getString(R.string.custom));

            textView_test_prg.setText(pref_appearance);
            Intent intent=getIntent();
            finish();
            startActivity(intent);
//            recreate();
        }
    }

    @Override
    public void onPause()
    {
        restart = true;
        super.onPause();
    }




    public void deleteMessage()
    {
        // suppress the content of the Text field
        EditText textField = (EditText) findViewById(R.id.edit_message);
        textField.getText().clear();
    }

    public void showActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        assert(actionBar != null);
        actionBar.show();
    }

    public void hideActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        assert(actionBar != null);
        actionBar.hide();
    }
    /*
    public void setThemeLight()
    {
        m_theme = 1;
        Intent recreateIntent =  new Intent(this,MainActivity.class);
        startActivity(recreateIntent);

    }

    public void setThemeDark()
    {
        m_theme = 2;
        Intent recreateIntent =  new Intent(this,MainActivity.class);
        startActivity(recreateIntent);
    }
    */

    // méthode appelée dès que l'utilisateur appuie sur le bouton d'envoi
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        // pour récupérer le contenu par exemple du champ textuel
        EditText edit = (EditText) findViewById(R.id.edit_message);
        String message = edit.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);

        startActivity(intent);

    }


    // method called when the user want to hide the red frag

    public void hideFrag (View view)
    {
        FragmentManager fragmentManager = getFragmentManager();

        // get an instance of the fragment we want to hide
        Frag_disp_message frag = (Frag_disp_message) fragmentManager.findFragmentById(R.id.frag_disp_message);
        // transaction we want to do

        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).hide(frag).commit();


    }


    public void showFrag (View view)
    {


        FragmentManager fragmentManager = getFragmentManager();

        // get an instance of the fragment we want to hide
        Frag_disp_message frag = (Frag_disp_message) fragmentManager.findFragmentById(R.id.frag_disp_message);
        // transaction we want to do


        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).show(frag).commit();
    }

    public void showImagesDialog (View view)
    {
        DialogFragment imageDialog = new ImageDialog();
        imageDialog.show(getFragmentManager(), "images_dialog");
    }

    /*
    This method could be not necessary
    public Bundle getChosen_values (DialogFragment dialogFragment)
    {
        return chosen_values;
    }
    */

    // we need to implement the dialogInterface methods
    public void onDialogOk(DialogFragment dialogFragment)
    {
        // here we will open a new activity
        Intent startImages = new Intent(this,ShowImagesActivity.class);
        startImages.putExtra(EXTRA_ARRAY,chosen_values);
        startActivity(startImages);
    }

    public void sentArray(DialogFragment dialogFragment,ArrayList arrayList)
    {
        chosen_values = new ArrayList(arrayList);

    }

    public void onDialogCancel(DialogFragment dialogFragment)
    {
        // this method will do nothing
    }

    public void openSettings()
    {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

}

