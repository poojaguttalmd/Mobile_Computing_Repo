package com.example.campuscompanion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        // Get user name from Intent
        String userName = getIntent().getStringExtra("USER_NAME");
        if (userName == null) userName = "Student";

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(getString(R.string.welcome_message, userName));

        // Create task list
        taskList = new ArrayList<>();
        taskList.add(new Task("Submit Assignment 1", "Complete and submit PA1 on GitHub", "High"));
        taskList.add(new Task("Study for Midterm", "Review chapters 1-5 for mobile computing midterm", "High"));
        taskList.add(new Task("Lab Report", "Write up results from last week's lab session", "Medium"));
        taskList.add(new Task("Group Project Meeting", "Meet with team to discuss project milestones", "Medium"));
        taskList.add(new Task("Read Chapter 6", "Read Android lifecycle chapter before next class", "Low"));
        taskList.add(new Task("Office Hours", "Visit professor's office hours for assignment clarification", "Low"));
        taskList.add(new Task("Peer Review", "Review a classmate's code as per course requirement", "Medium"));
        taskList.add(new Task("Update GitHub Repo", "Push latest changes and update README", "High"));

        // Build title list for display
        List<String> titles = new ArrayList<>();
        for (Task t : taskList) {
            titles.add(t.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, titles);
        ListView lvTasks = findViewById(R.id.lvTasks);
        lvTasks.setAdapter(adapter);

        // On task click → open TaskDetailActivity
        lvTasks.setOnItemClickListener((parent, view, position, id) -> {
            Task selected = taskList.get(position);
            Intent intent = new Intent(TaskListActivity.this, TaskDetailActivity.class);
            intent.putExtra("TASK_TITLE", selected.getTitle());
            intent.putExtra("TASK_DESCRIPTION", selected.getDescription());
            intent.putExtra("TASK_PRIORITY", selected.getPriority());
            startActivity(intent);
        });
    }
}