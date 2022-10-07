package com.smartspiritnotes.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button btn_EditNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_EditNote = findViewById(R.id.btn_editNote);

        btn_EditNote.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,EditNoteActivity.class);
            startActivity(intent);

        });
    }
}