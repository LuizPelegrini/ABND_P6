package com.example.android.abnd_p6;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.prefs.PreferenceChangeListener;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class ArticlePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            // Find the "topic" preference and set it up
            Preference topicPreference = findPreference(getString(R.string.settings_topic_key));
            setupPreference(topicPreference, getString(R.string.settings_topic_key));

            // Find the "order by" preference and set it up
            Preference orderByPreference = findPreference(getString(R.string.settings_order_by_key));
            setupPreference(orderByPreference, getString(R.string.settings_order_by_key));
        }


        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            // As the preference is a list, we need to find the index of the new value so we can
            // set it in the summary
            ListPreference listPreference = (ListPreference) preference;
            int valueIndex = listPreference.findIndexOfValue(newValue.toString());
            String summary = listPreference.getEntries()[valueIndex].toString();

            preference.setSummary(summary);

            // Updates the dialog and save the preference
            return true;
        }

        // Set the preference up by attaching a listener to it and updating the UI
        private void setupPreference(Preference preference, String preferenceKey) {
            // Setup listener to the preference (in this case, it will be the very ArticlePreferenceFragment)
            preference.setOnPreferenceChangeListener(this);

            // Get the value of this preference
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceValue = sharedPreferences.getString(preferenceKey, "");

            // Force the onPreferenceChange calling so it updates the preference summary upon SettingsActivity creation
            onPreferenceChange(preference, preferenceValue);
        }
    }
}
