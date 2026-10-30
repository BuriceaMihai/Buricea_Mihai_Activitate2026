package com.example.lab04;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<MotorBike> bikeList;
    private ArrayAdapter<MotorBike> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView lvBikes = findViewById(R.id.lvBikes);

        bikeList = getIntent().getParcelableArrayListExtra("bike_list");
        if (bikeList == null) {
            bikeList = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bikeList);
        lvBikes.setAdapter(adapter);

        lvBikes.setOnItemClickListener((parent, view, position, id) -> {
            MotorBike selectedBike = bikeList.get(position);
            Toast.makeText(this, selectedBike.toString(), Toast.LENGTH_SHORT).show();
        });

        lvBikes.setOnItemLongClickListener((parent, view, position, id) -> {
            bikeList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "MotorBike deleted!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}