package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddMedicationActivity extends AppCompatActivity {

    private EditText nameEditText, dosageEditText;
    private TimePicker timePicker;
    private Button saveButton;
    private MedicationDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        nameEditText = findViewById(R.id.nameEditText);
        dosageEditText = findViewById(R.id.dosageEditText);
        timePicker = findViewById(R.id.timePicker);
        saveButton = findViewById(R.id.saveButton);

        db = new MedicationDatabase(this);
        timePicker.setIs24HourView(true);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMedication();
            }
        });
    }

    private void saveMedication() {
        String name = nameEditText.getText().toString().trim();
        String dosage = dosageEditText.getText().toString().trim();

        if (name.isEmpty() || dosage.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Medication medication = new Medication();
        medication.setName(name);
        medication.setDosage(dosage);
        medication.setHour(hour);
        medication.setMinute(minute);
        medication.setActive(true);

        long id = db.addMedication(medication);

        if (id != -1) {
            setAlarm(medication);
            Toast.makeText(this, "Лекарство добавлено", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Ошибка сохранения", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAlarm(Medication medication) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("medication_name", medication.getName());
        intent.putExtra("medication_dosage", medication.getDosage());
        intent.putExtra("medication_id", medication.getId());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                (int) medication.getId(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, medication.getHour());
        calendar.set(Calendar.MINUTE, medication.getMinute());
        calendar.set(Calendar.SECOND, 0);

        // Если время уже прошло сегодня, установить на завтра
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );
    }
}