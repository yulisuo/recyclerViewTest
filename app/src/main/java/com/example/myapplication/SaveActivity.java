package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SaveActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        EditText et = findViewById(R.id.et);
        Button button = findViewById(R.id.save);
        button.setOnClickListener(v -> {
            String text = et.getText().toString().trim();
            Intent i = getIntent();
            i.putExtra("result", text);
            setResult(1, i);
            finish();
        });
    }
}