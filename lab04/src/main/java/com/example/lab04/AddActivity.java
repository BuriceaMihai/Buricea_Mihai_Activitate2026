package com.example.lab04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText etBrand = findViewById(R.id.etBrand);
        Spinner spCategory = findViewById(R.id.spCategory);
        Switch swElectric = findViewById(R.id.swElectric);
        CheckBox cbABS = findViewById(R.id.cbABS);
        RadioGroup rgEngineType = findViewById(R.id.rgEngineType);
        RatingBar rbRating = findViewById(R.id.rbRating);
        Button btnSave = findViewById(R.id.btnSave);

        String[] categories = {"Sport", "Cruiser", "Off-Road", "Scooter"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String brand = etBrand.getText().toString().trim();
            if (brand.isEmpty()) brand = "Unknown";

            String category = spCategory.getSelectedItem().toString();
            boolean isElectric = swElectric.isChecked();
            boolean hasABS = cbABS.isChecked();

            int selectedId = rgEngineType.getCheckedRadioButtonId();
            RadioButton selectedRb = findViewById(selectedId);
            String engineType = selectedRb.getText().toString();
            float rating = rbRating.getRating();

            MotorBike newBike = new MotorBike(brand, category, isElectric, hasABS, engineType, rating, 500, 180.0);

            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("new_bike", newBike);
            resultIntent.putExtras(bundle);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}