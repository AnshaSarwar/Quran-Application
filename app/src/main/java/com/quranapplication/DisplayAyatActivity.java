package com.quranapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.quranapplication.R;

public class DisplayAyatActivity extends AppCompatActivity {

    private static final String SURAH_NAME_KEY = "surahName";
    private static final String AYAT_NUMBER_KEY = "ayatNumber";
    private static final String AYAT_CONTENT_KEY = "ayatContent";

    TextView surahNameTextView;
    TextView ayatNumberTextView;
    TextView ayatContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ayat);

        surahNameTextView = findViewById(R.id.surahNameTextView);
        ayatNumberTextView = findViewById(R.id.ayatNumberTextView);
        ayatContentTextView = findViewById(R.id.ayatContentTextView);

        // Get the data passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String surahName = extras.getString(SURAH_NAME_KEY);
            int ayatNumber = extras.getInt(AYAT_NUMBER_KEY);
            String ayatContent = extras.getString(AYAT_CONTENT_KEY);

            // Set the values to the corresponding TextViews
            surahNameTextView.setText(surahName);
            ayatNumberTextView.setText(String.valueOf(ayatNumber));
            ayatContentTextView.setText(ayatContent);
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Handle orientation change here
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_display_ayat_land);

            // Find and update views in landscape layout
            surahNameTextView = findViewById(R.id.surahNameTextView);
            ayatNumberTextView = findViewById(R.id.ayatNumberTextView);
            ayatContentTextView = findViewById(R.id.ayatContentTextView);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Portrait mode
            setContentView(R.layout.activity_display_ayat);

            // Find and update views in portrait layout
            surahNameTextView = findViewById(R.id.surahNameTextView);
            ayatNumberTextView = findViewById(R.id.ayatNumberTextView);
            ayatContentTextView = findViewById(R.id.ayatContentTextView);
        }
    }
}
