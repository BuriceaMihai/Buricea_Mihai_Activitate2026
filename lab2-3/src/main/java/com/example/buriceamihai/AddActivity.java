package com.example.buriceamihai;

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

import com.example.buriceamihai.lab4.Automobil;

public class AddActivity extends AppCompatActivity {

    private EditText etMarca;
    private CheckBox cbInmatriculat;
    private RadioGroup rgTransmisie;
    private Spinner spCombustibil;
    private RatingBar rbSiguranta;
    private Switch swCasco;
    private Button btnSalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etMarca = findViewById(R.id.etMarca);
        cbInmatriculat = findViewById(R.id.cbInmatriculat);
        rgTransmisie = findViewById(R.id.rgTransmisie);
        spCombustibil = findViewById(R.id.spCombustibil);
        rbSiguranta = findViewById(R.id.rbSiguranta);
        swCasco = findViewById(R.id.swCasco);
        btnSalveaza = findViewById(R.id.btnSalveaza);

        String[] optiuniCombustibil = {"Benzină", "Motorină", "Electric", "Hibrid"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, optiuniCombustibil);
        spCombustibil.setAdapter(adapter);

        btnSalveaza.setOnClickListener(v -> {
            String marca = etMarca.getText().toString().trim();
            if (marca.isEmpty()) {
                marca = "Necunoscut";
            }

            boolean esteInmatriculat = cbInmatriculat.isChecked();

            int selectedRadioId = rgTransmisie.getCheckedRadioButtonId();
            RadioButton rbSelectat = findViewById(selectedRadioId);
            String transmisie = rbSelectat.getText().toString();

            String combustibil = spCombustibil.getSelectedItem().toString();
            float siguranta = rbSiguranta.getRating();
            boolean areCasco = swCasco.isChecked();

            Automobil auto = new Automobil(marca, esteInmatriculat, transmisie, combustibil, siguranta, areCasco);

            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("cheie_automobil", auto);
            resultIntent.putExtras(bundle);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}