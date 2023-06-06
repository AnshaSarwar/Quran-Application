package com.quranapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    ListView ListViewSurahs;
    ParahSurah ps = new ParahSurah();

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
                String surahText = ps.englishSurahNames[position];
                textView.setText(surahText);
                return textView;
            }
        };
        ListViewSurahs.setAdapter(surahAdapter);
        ListViewSurahs.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(MainActivity.this, SurahContentActivity.class);
            intent.putExtra("selectedSurahIndex", position);
            startActivity(intent);

        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Landscape mode
            setContentView(R.layout.activity_main_land);

            ListView ListViewSurahs = findViewById(R.id.listViewSurahs);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Portrait mode
            setContentView(R.layout.activity_main);

            ListView ListViewSurahs = findViewById(R.id.listViewSurahs);
        }
    }
}