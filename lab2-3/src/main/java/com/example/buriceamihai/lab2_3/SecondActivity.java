package com.example.buriceamihai.lab2_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buriceamihai.R;

public class SecondActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String responseMessage = result.getData().getStringExtra("responseMessage");
                        int sum = result.getData().getIntExtra("sumResult", 0);

                        String toastText = responseMessage + "\nResult: " + sum;
                        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
                    }
                });

        Button btnOpen = findViewById(R.id.btnOpenThird);
        btnOpen.setOnClickListener(view -> {
            Intent intent = new Intent(this, ThirdActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("message", "Hello from Activity 2!");
            bundle.putInt("value1", 10);
            bundle.putInt("value2", 20);

            intent.putExtras(bundle);
            launcher.launch(intent);
        });
    }
}