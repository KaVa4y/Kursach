package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class MedicationDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medications.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MEDICATIONS = "medications";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DOSAGE = "dosage";
    private static final String COLUMN_HOUR = "hour";
    private static final String COLUMN_MINUTE = "minute";
    private static final String COLUMN_ACTIVE = "active";

    public MedicationDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MEDICATIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DOSAGE + " TEXT,"
                + COLUMN_HOUR + " INTEGER,"
                + COLUMN_MINUTE + " INTEGER,"
                + COLUMN_ACTIVE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATIONS);
        onCreate(db);
    }

    public long addMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, medication.getName());
        values.put(COLUMN_DOSAGE, medication.getDosage());
        values.put(COLUMN_HOUR, medication.getHour());
        values.put(COLUMN_MINUTE, medication.getMinute());
        values.put(COLUMN_ACTIVE, medication.isActive() ? 1 : 0);

        long id = db.insert(TABLE_MEDICATIONS, null, values);
        db.close();
        return id;
    }

    public List<Medication> getAllMedications() {
        List<Medication> medications = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEDICATIONS,
                null, null, null, null, null, COLUMN_HOUR + ", " + COLUMN_MINUTE);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Medication medication = new Medication();
                medication.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                medication.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                medication.setDosage(cursor.getString(cursor.getColumnIndex(COLUMN_DOSAGE)));
                medication.setHour(cursor.getInt(cursor.getColumnIndex(COLUMN_HOUR)));
                medication.setMinute(cursor.getInt(cursor.getColumnIndex(COLUMN_MINUTE)));
                medication.setActive(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)) == 1);

                medications.add(medication);
            } while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return medications;
    }

    public void deleteMedication(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICATIONS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}