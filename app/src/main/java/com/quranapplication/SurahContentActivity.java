package com.quranapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class SurahContentActivity extends AppCompatActivity {

    private static final String SELECTED_SURAH_INDEX_KEY = "selectedSurahIndex";
    private static final String AYAT_CONTENT_KEY = "ayatContent";
    private static final String CURRENT_AYAT_NUMBER_KEY = "currentAyatNumber";
    TextView textViewSurahContent;
    ParahSurah ps;
    QuranData qd;
    EditText editTextAyatNumber;
    Button buttonSearch;
    ScrollView scrollview;
    String[] ayatContent;
    int selectedSurahIndex;
    int startingIndex;
    int ayatCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_content);
        ps = new ParahSurah();
        qd = new QuranData();
        textViewSurahContent = findViewById(R.id.textViewSurahContent);
        editTextAyatNumber = findViewById(R.id.editTextAyatNumber);
        buttonSearch = findViewById(R.id.buttonSearch);



        Intent intent = getIntent();
        if (intent != null) {
            selectedSurahIndex = intent.getIntExtra("selectedSurahIndex", 0);
            startingIndex = ps.getSurahStart(selectedSurahIndex);
            ayatCount = ps.getSurahVerses(selectedSurahIndex);

            if (savedInstanceState != null) {
                // Restore the saved state
                ayatContent = savedInstanceState.getStringArray(AYAT_CONTENT_KEY);

            } else {
                // Load the ayat content for the selected surah
                ayatContent = qd.GetData(startingIndex - 1, startingIndex + ayatCount);

            }

            StringBuilder ayatContentBuilder = new StringBuilder();
            for (String ayat : ayatContent) {
                ayatContentBuilder.append(ayat).append(" ");
            }

            // Display the Surah content
            textViewSurahContent.setText(ayatContentBuilder.toString());

            buttonSearch.setOnClickListener(v -> {

                String ayatNumberStr = editTextAyatNumber.getText().toString();
                if (!TextUtils.isEmpty(ayatNumberStr)) {
                    int ayatNumber = Integer.parseInt(ayatNumberStr);
                    selectedSurahIndex = intent.getIntExtra("selectedSurahIndex", 0);


                    startingIndex = ps.getSurahStart(selectedSurahIndex);


                    ayatCount = ps.getSurahVerses(selectedSurahIndex);


                    int targetIndex = startingIndex + (ayatNumber - 1);


                    if (targetIndex >= startingIndex && targetIndex < (startingIndex + ayatCount)) {
                        String[] ayatContent = qd.GetData(targetIndex, targetIndex+1);

                        String surahName = ps.englishSurahNames[selectedSurahIndex];


                        String ayatContentString = ayatContent[0];

                        // Create an intent to start AyatDetailActivity
                        Intent detailIntent = new Intent(SurahContentActivity.this, DisplayAyatActivity.class);

                        detailIntent.putExtra("surahName", surahName);
                        detailIntent.putExtra("ayatNumber", ayatNumber);
                        detailIntent.putExtra("ayatContent", ayatContentString);

                        startActivity(detailIntent);


                    } else {

                        Toast.makeText(SurahContentActivity.this, "Invalid ayat number", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(SurahContentActivity.this, "Invalid ayat number", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the necessary data in the bundle
        outState.putInt(SELECTED_SURAH_INDEX_KEY, selectedSurahIndex);
        outState.putStringArray(AYAT_CONTENT_KEY, ayatContent);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the necessary data from the bundle
        selectedSurahIndex = savedInstanceState.getInt(SELECTED_SURAH_INDEX_KEY);
        startingIndex = ps.getSurahStart(selectedSurahIndex);
        ayatCount = ps.getSurahVerses(selectedSurahIndex);
        ayatContent = savedInstanceState.getStringArray(AYAT_CONTENT_KEY);

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Handle orientation change here
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_surah_content_land);

            // Find and update views in landscape layout
            EditText editTextAyatNumber = findViewById(R.id.editTextAyatNumber);
            Button buttonSearch = findViewById(R.id.buttonSearch);
            TextView textViewSurahContent = findViewById(R.id.textViewSurahContent);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Portrait mode
            setContentView(R.layout.activity_surah_content);

            // Find and update views in portrait layout
            EditText editTextAyatNumber = findViewById(R.id.editTextAyatNumber);
            Button buttonSearch = findViewById(R.id.buttonSearch);

            TextView textViewSurahContent = findViewById(R.id.textViewSurahContent);
        }
    }

}