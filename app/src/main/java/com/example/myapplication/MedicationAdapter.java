package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class MedicationAdapter extends ArrayAdapter<Medication> {

    private Context context;
    private List<Medication> medications;

    public MedicationAdapter(Context context, List<Medication> medications) {
        super(context, R.layout.list_item_medication, medications);
        this.context = context;
        this.medications = medications;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_medication, parent, false);
        }

        Medication medication = medications.get(position);

        TextView nameTextView = convertView.findViewById(R.id.medicationName);
        TextView dosageTextView = convertView.findViewById(R.id.medicationDosage);
        TextView timeTextView = convertView.findViewById(R.id.medicationTime);

        nameTextView.setText(medication.getName());
        dosageTextView.setText(medication.getDosage());

        String time = String.format("%02d:%02d", medication.getHour(), medication.getMinute());
        timeTextView.setText(time);

        return convertView;
    }
}