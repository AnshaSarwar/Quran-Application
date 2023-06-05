package com.quranapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class SurahContentActivity extends AppCompatActivity {
    EditText editTextAyatNumber;
    Button buttonSearch;
    TextView textViewSurahContent;
    int surahIndex;
    String[] ayats;
    ParahSurah ps = new ParahSurah();
    QuranData qd = new QuranData();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_content);

        editTextAyatNumber = findViewById(R.id.editTextAyatNumber);
        Intent intent = getIntent();
        if (intent != null) {
            surahIndex = intent.getIntExtra("surahIndex", 0);
            int ayatCount = ps.getAyatCount(surahIndex);

            ayats = new String[ayatCount];
            for (int i = 0; i < ayatCount; i++) {
                ayats[i] = Arrays.toString(qd.GetData(surahIndex, i));
            }
        }
    }

    private String getSurahContent(String surahName) {
        // Retrieve the surah content based on the surahName
        // Replace this with your logic to fetch the surah content
        return "Surah Content for " + surahName;
    }
}



