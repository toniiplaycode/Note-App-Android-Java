package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_setting) {
            Toast.makeText(this, "You choose setting !", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.action_setting2) {
            Toast.makeText(this, "You choose setting 2 !", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.action_search) {
            Toast.makeText(this, "You searching a note...", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void saveNote() {
        // Get the text from the EditTexts
        EditText titleEditText = findViewById(R.id.note_edit_text_title);
        EditText noteEditText = findViewById(R.id.note_edit_text);

        String noteTitle = titleEditText.getText().toString();
        String noteContent = noteEditText.getText().toString();

        // Save the note using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyNotes", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the note with a unique key (you can change the key to something dynamic if needed)
        editor.putString("note_title", noteTitle);
        editor.putString("note_content", noteContent);

        // Commit the changes
        editor.apply();

        // Notify the user
        Toast.makeText(this, "Note saved successfully!", Toast.LENGTH_SHORT).show();
    }

    private void loadNote() {
        // Get the EditTexts
        EditText titleEditText = findViewById(R.id.note_edit_text_title);
        EditText noteEditText = findViewById(R.id.note_edit_text);

        // Load the note using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyNotes", MODE_PRIVATE);
        String noteTitle = sharedPreferences.getString("note_title", "");
        String noteContent = sharedPreferences.getString("note_content", "");

        // Set the text in the EditTexts
        titleEditText.setText(noteTitle);
        noteEditText.setText(noteContent);
    }

}