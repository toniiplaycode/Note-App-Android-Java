package com.example.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

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

        LinearLayout containerNotes = binding.containerNotes; // Access LinearLayout from binding

        for (int i = 1; i <= 6; i++) {
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
}
