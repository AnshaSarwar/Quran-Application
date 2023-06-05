package com.quranapplication;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quranapplication.QuranData;
import com.quranapplication.ParahSurah;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class SurahContentActivity extends AppCompatActivity {
    EditText editTextAyatNumber;
    Button buttonSearch;
    TextView textViewSurahContent;
    int selectedSurahIndex;
    int startingIndex;
    int ayatCount;

    String[] ayats;
    ParahSurah ps = new ParahSurah();
    QuranData qd = new QuranData();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_content);

        editTextAyatNumber = findViewById(R.id.editTextAyatNumber);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewSurahContent = findViewById(R.id.textViewSurahContent);

        Intent intent = getIntent();
        if (intent != null) {
            int count = 0;
            selectedSurahIndex = intent.getIntExtra("selectedSurahIndex", 0);
            startingIndex = ps.getSurahStart(selectedSurahIndex);
            ayatCount = ps.getSurahVerses(selectedSurahIndex);
            if (ayatCount > 0) {
                count = ayatCount + startingIndex;
            }

            ayats = new String[count];
            for (int i = 0; i < count; i++) {
                ayats[i] = Arrays.toString(qd.GetData(selectedSurahIndex, i));
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String ayat : ayats) {
                stringBuilder.append(ayat).append("\n");
            }
            textViewSurahContent.setText(stringBuilder.toString());
        }

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ayatNumberStr = editTextAyatNumber.getText().toString();
                if (!TextUtils.isEmpty(ayatNumberStr)) {
                    int ayatNumber = Integer.parseInt(ayatNumberStr);
                    selectedSurahIndex = intent.getIntExtra("selectedSurahIndex", 0);


                    startingIndex = ps.getSurahStart(selectedSurahIndex);


                    ayatCount = ps.getSurahVerses(selectedSurahIndex);


                    int targetIndex = startingIndex + (ayatNumber - 1);


                    if (targetIndex >= startingIndex && targetIndex < (startingIndex + ayatCount)) {

                        String[] ayatContent = qd.GetData(targetIndex, targetIndex + 1);
                        if (ayatContent != null) {
                            Intent intent = new Intent(SurahContentActivity.this, DiaplayAyatActivity.class);
                            intent.putExtra("ayat", ayatContent);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SurahContentActivity.this, "Invalid ayat number", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            }
        });




    }
}



