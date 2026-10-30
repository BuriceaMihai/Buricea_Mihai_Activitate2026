package com.example.lab04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    public static ArrayList<MotorBike> bikeList = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        Button btnAddStandard = findViewById(R.id.btnAddStandard);
        Button btnAddAi = findViewById(R.id.btnAddAi);
        Button btnViewList = findViewById(R.id.btnViewList);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle bundle = result.getData().getExtras();
                        if (bundle != null) {
                            MotorBike bike;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                bike = bundle.getParcelable("new_bike", MotorBike.class);
                            } else {
                                //noinspection deprecation
                                bike = bundle.getParcelable("new_bike");
                            }

                            if (bike != null) {
                                bikeList.add(bike);
                                tvResult.setText("Last Added:\n" + bike.toString() + "\n\nTotal in list: " + bikeList.size());
                                Toast.makeText(this, "MotorBike added!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );

        btnAddStandard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            launcher.launch(intent);
        });

        btnAddAi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAiActivity.class);
            launcher.launch(intent);
        });

        btnViewList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            intent.putParcelableArrayListExtra("bike_list", bikeList);
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        if (bikeList.isEmpty()) {
            tvResult.setText("Total MotorBikes: 0");
        }
    }
}