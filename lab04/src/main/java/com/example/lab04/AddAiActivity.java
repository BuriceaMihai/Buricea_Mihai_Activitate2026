package com.example.lab04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAiActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ai);

        EditText etBrand = findViewById(R.id.etBrandAi);
        EditText etWeightKg = findViewById(R.id.etWeightKg);
        Spinner spCategory = findViewById(R.id.spCategoryAi);
        TextView tvCapacityLabel = findViewById(R.id.tvCapacityLabel);
        SeekBar sbCapacityCc = findViewById(R.id.sbCapacityCc);
        RadioGroup rgEngineType = findViewById(R.id.rgEngineTypeAi);
        Switch swElectric = findViewById(R.id.swElectricAi);
        CheckBox cbABS = findViewById(R.id.cbABSAi);
        RatingBar rbRating = findViewById(R.id.rbRatingAi);

        Button btnReset = findViewById(R.id.btnResetAi);
        Button btnCancel = findViewById(R.id.btnCancelAi);
        Button btnSave = findViewById(R.id.btnSaveAi);

        String[] categories = {"Supersport", "Adventure Touring", "Naked", "Cruiser", "Enduro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

        // Actualizează textul în cm³ pe măsură ce miști SeekBar-ul
        sbCapacityCc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int cc = Math.max(progress, 50); // Minim 50 cm³
                tvCapacityLabel.setText("Engine Displacement: " + cc + " cm³");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        swElectric.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sbCapacityCc.setEnabled(!isChecked);
            if (isChecked) {
                tvCapacityLabel.setText("Engine Displacement: 0 cm³ (Electric)");
            } else {
                tvCapacityLabel.setText("Engine Displacement: " + sbCapacityCc.getProgress() + " cm³");
            }
        });

        btnReset.setOnClickListener(v -> {
            etBrand.setText("");
            etWeightKg.setText("");
            spCategory.setSelection(0);
            sbCapacityCc.setProgress(600);
            swElectric.setChecked(false);
            cbABS.setChecked(true);
            rbRating.setRating(4.5f);
            Toast.makeText(this, "Form reset to defaults", Toast.LENGTH_SHORT).show();
        });

        btnCancel.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            String brand = etBrand.getText().toString().trim();
            String weightStr = etWeightKg.getText().toString().trim();

            if (brand.isEmpty()) {
                etBrand.setError("Brand is required!");
                return;
            }
            if (weightStr.isEmpty()) {
                etWeightKg.setError("Weight in kg is required!");
                return;
            }

            double weightKg = Double.parseDouble(weightStr);
            String category = spCategory.getSelectedItem().toString();
            boolean isElectric = swElectric.isChecked();
            boolean hasABS = cbABS.isChecked();
            int capacityCc = isElectric ? 0 : Math.max(sbCapacityCc.getProgress(), 50);

            int selectedId = rgEngineType.getCheckedRadioButtonId();
            RadioButton selectedRb = findViewById(selectedId);
            String engineType = isElectric ? "Electric Motor" : selectedRb.getText().toString();
            float rating = rbRating.getRating();

            MotorBike newBike = new MotorBike(brand, category, isElectric, hasABS, engineType, rating, capacityCc, weightKg);

            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("new_bike", newBike);
            resultIntent.putExtras(bundle);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}