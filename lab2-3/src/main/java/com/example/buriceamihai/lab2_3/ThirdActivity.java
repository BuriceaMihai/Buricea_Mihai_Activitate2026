package com.example.buriceamihai.lab2_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buriceamihai.R;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String message = bundle.getString("message");
            int value1 = bundle.getInt("value1", 0);
            int value2 = bundle.getInt("value2", 0);

            String receivedText = "Message: " + message + "\nValue 1: " + value1 + "\nValue 2: " + value2;
            Toast.makeText(this, receivedText, Toast.LENGTH_LONG).show();

            Button btnSendBack = findViewById(R.id.btnSendBack);

            btnSendBack.setOnClickListener(view -> {
                int sum = value1 + value2;

                Intent response = new Intent();
                response.putExtra("responseMessage", "Calculation completed successfully!");
                response.putExtra("sumResult", sum);

                setResult(RESULT_OK, response);
                finish();
            });
        }
    }
}