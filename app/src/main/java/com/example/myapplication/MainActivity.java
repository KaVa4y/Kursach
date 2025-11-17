package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView medicationListView;
    private TextView emptyText;
    private Button addButton;
    private MedicationAdapter adapter;
    private List<Medication> medicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicationListView = findViewById(R.id.medicationListView);
        emptyText = findViewById(R.id.emptyText);
        addButton = findViewById(R.id.addButton);

        medicationList = new ArrayList<>();
        adapter = new MedicationAdapter(this, medicationList);
        medicationListView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMedicationActivity.class);
                startActivity(intent);
            }
        });

        // Для теста добавим несколько примеров
        medicationList.add(new Medication(1, "Аспирин", "1 таблетка", 8, 0, true));
        medicationList.add(new Medication(2, "Витамины", "2 капсулы", 12, 30, true));
        adapter.notifyDataSetChanged();

        if (!medicationList.isEmpty()) {
            emptyText.setVisibility(View.GONE);
            medicationListView.setVisibility(View.VISIBLE);
        }
    }
}