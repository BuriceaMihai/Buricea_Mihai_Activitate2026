package com.example.buriceamihai.lab2_3;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.buriceamihai.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LifecycleDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "onCreate called (Verbose)");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called (Debug)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called (Info)");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "onPause called (Warning)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop called (Error)");
    }
}