package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

import java.util.Map;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout containerNotes = binding.containerNotes; // Access LinearLayout from binding, id bên file xml được đặt theo snack_case, nhưng bên đây thì truy cập theo kiểu camalCase

        for (int i = 1; i <= 2; i++) {
            // Create the outer LinearLayout
            LinearLayout itemLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams itemLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            itemLayoutParams.setMargins(0, 0, 0, dpToPx(20)); // Bottom margin
            itemLayout.setLayoutParams(itemLayoutParams);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(dpToPx(20), dpToPx(20), dpToPx(20), dpToPx(20));
            itemLayout.setBackground(getContext().getDrawable(R.drawable.round_item_note));

            // Create the TextViews
            TextView titleTextView = new TextView(getContext());
            titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            titleTextView.setText("The Note " + i);
            titleTextView.setTextSize(20);
            titleTextView.setTypeface(null, Typeface.BOLD);

            TextView taskA = new TextView(getContext());
            taskA.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            taskA.setText("Do something...");

            // Add the TextViews to the outer LinearLayout
            itemLayout.addView(titleTextView);
            itemLayout.addView(taskA);

            // Add the item layout to the container
            containerNotes.addView(itemLayout);
        }

        showAllData();

        Button saveButton = view.findViewById(R.id.button_add);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click here
                saveNote(view);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Utility method to convert dp to pixels
    private int dpToPx(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }

    private void saveNote(View view) {
        // Get the text from the EditTexts
        EditText titleEditText = view.findViewById(R.id.note_edit_text_title);
        EditText noteEditText = view.findViewById(R.id.note_edit_text);

        String noteTitle = titleEditText.getText().toString();
        String noteContent = noteEditText.getText().toString();

        // Save the note using SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyNotes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the note with a unique key (you can change the key to something dynamic if needed)
        editor.putString("note_title", noteTitle);
        editor.putString("note_content", noteContent);

        // Commit the changes
        editor.apply();

        // Notify the user
        Toast.makeText(getContext(), "Note saved!", Toast.LENGTH_SHORT).show();
        loadNote(view);
    }

    private void loadNote(View view) {
        // Get the EditTexts
//        EditText titleEditText = view.findViewById(R.id.note_edit_text_title);
//        EditText noteEditText = view.findViewById(R.id.note_edit_text);

        // Load the note using SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyNotes", Context.MODE_PRIVATE);
        String noteTitle = sharedPreferences.getString("note_title", "");
        String noteContent = sharedPreferences.getString("note_content", "");

        // Set the text in the EditTexts
//        titleEditText.setText(noteTitle);
//        noteEditText.setText(noteContent);
    }

    private void showAllData() {
        // Retrieve the SharedPreferences object
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyNotes", Context.MODE_PRIVATE);

        // Get all key-value pairs
        Map<String, ?> allEntries = sharedPreferences.getAll();

        // Iterate through all entries and display them
        StringBuilder dataBuilder = new StringBuilder();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            dataBuilder.append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
        }

        // Display the data using a Toast or in a TextView
        if (dataBuilder.length() > 0) {
            Toast.makeText(getContext(), dataBuilder.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "No data found in SharedPreferences", Toast.LENGTH_SHORT).show();
        }
    }



}
