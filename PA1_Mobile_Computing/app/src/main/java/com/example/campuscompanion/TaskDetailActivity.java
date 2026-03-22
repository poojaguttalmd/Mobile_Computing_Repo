package com.example.campuscompanion;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Defensive: get extras with fallback values
        String title       = getIntent().getStringExtra("TASK_TITLE");
        String description = getIntent().getStringExtra("TASK_DESCRIPTION");
        String priority    = getIntent().getStringExtra("TASK_PRIORITY");

        if (title == null)       title       = getString(R.string.error_missing_data);
        if (description == null) description = getString(R.string.error_missing_data);
        if (priority == null)    priority    = getString(R.string.error_missing_data);

        TextView tvTitle       = findViewById(R.id.tvTitle);
        TextView tvDescription = findViewById(R.id.tvDescription);
        TextView tvPriority    = findViewById(R.id.tvPriority);
        MaterialButton btnMarkComplete = findViewById(R.id.btnMarkComplete);

        tvTitle.setText(title);
        tvDescription.setText(description);
        tvPriority.setText(priority);

        // Color-code priority
        switch (priority) {
            case "High":
                tvPriority.setTextColor(getColor(R.color.priority_high)); break;
            case "Medium":
                tvPriority.setTextColor(getColor(R.color.priority_medium)); break;
            default:
                tvPriority.setTextColor(getColor(R.color.priority_low)); break;
        }

        // Mark Complete with confirmation dialog
        String finalTitle = title;
        btnMarkComplete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.confirm_title))
                    .setMessage(getString(R.string.confirm_message))
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        Toast.makeText(this, getString(R.string.task_completed),
                                Toast.LENGTH_SHORT).show();
                        btnMarkComplete.setEnabled(false);
                        btnMarkComplete.setText("Completed");
                    })
                    .setNegativeButton(getString(R.string.no), null)
                    .show();
        });
    }
}