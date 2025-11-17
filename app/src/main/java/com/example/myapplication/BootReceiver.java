package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Переустановка всех напоминаний после перезагрузки
            MedicationDatabase db = new MedicationDatabase(context);
            // Здесь нужно добавить логику восстановления всех активных напоминаний
        }
    }
}