package com.example.farid.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

/**
 * Created by farid on 19/08/15.
 */
public class SettingsActivity extends PreferenceActivity {

    public final static String KEY_PREF_APPEARANCE = "pref_general_appearance";


    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }


    public static class SettingsFragment extends PreferenceFragment
    {


        @Override
        public void onCreate(Bundle savedStateInstance)
        {
            super.onCreate(savedStateInstance);
            addPreferencesFromResource(R.xml.preferences);
        }
        //    SettingsFragment settingsFragment;

        @Override
        public void onResume()
        {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
        }

        @Override
        public void onPause()
        {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
            super.onPause();
        }



        SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                // We will use this interface to listen to all changes done in the settings by the user
                if (key.equals(KEY_PREF_APPEARANCE))
                {
                    Preference pref_appearance = findPreference(key);
                    pref_appearance.setSummary(sharedPreferences.getString(key, ""));
                }
            }
        };



    }


}

