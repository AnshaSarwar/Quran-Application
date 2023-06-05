package com.quranapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.quranapplication.QuranData;
import com.quranapplication.ParahSurah;

public class DiaplayAyatActivity extends AppCompatActivity {
    TextView ayatTextView;
    int selectedAyat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ayat);

        Intent intent = getIntent();
        if (intent != null) {
            String ayat = intent.getStringExtra("ayat");
            ayatTextView.setText(ayat);
        }
    }
}