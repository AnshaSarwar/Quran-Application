package com.quranapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.ViewGroup;
import android.widget.TextView;
import com.quranapplication.QuranData;
import com.quranapplication.ParahSurah;
public class MainActivity extends AppCompatActivity {

    ListView ListViewSurahs;
    ParahSurah ps = new ParahSurah();;
    QuranData qd = new QuranData();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListViewSurahs = findViewById(R.id.listViewSurahs);

        ArrayAdapter<String> surahAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                ps.englishSurahNames
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                String surahEnglish = ps.englishSurahNames[position];
                String surahText = surahEnglish;
                textView.setText(surahText);
                return textView;
            }
        };
        ListViewSurahs.setAdapter(surahAdapter);
        ListViewSurahs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, Surahcontent.class);
                intent.putExtra("selectedSurahIndex", position);
                startActivity(intent);

            }
        });
    }
}