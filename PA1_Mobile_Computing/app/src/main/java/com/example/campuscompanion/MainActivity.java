package com.example.campuscompanion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_USER_NAME = "user_name";

    private TextInputLayout tilUserName;
    private TextInputEditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");

        tilUserName = findViewById(R.id.tilUserName);
        etUserName = findViewById(R.id.etUserName);
        MaterialButton btnGoToTasks = findViewById(R.id.btnGoToTasks);

        if (savedInstanceState != null) {
            etUserName.setText(savedInstanceState.getString(KEY_USER_NAME));
        }

        btnGoToTasks.setOnClickListener(v -> {
        String name = etUserName.getText() != null ?
        etUserName.getText().toString().trim() : "";

        if (name.isEmpty()) {
            tilUserName.setError(getString(R.string.error_empty_name));
        } else {
            tilUserName.setError(null);
            Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
        }
    });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (etUserName.getText() != null) {
            outState.putString(KEY_USER_NAME, etUserName.getText().toString());
        }
        Log.d(TAG, "onSaveInstanceState called");
    }

    @Override protected void onStart()   { super.onStart();   Log.d(TAG, "onStart"); }
    @Override protected void onResume()  { super.onResume();  Log.d(TAG, "onResume"); }
    @Override protected void onPause()   { super.onPause();   Log.d(TAG, "onPause"); }
    @Override protected void onStop()    { super.onStop();    Log.d(TAG, "onStop"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy"); }
}