package com.example.myapplication;

public class Medication {
    private long id;
    private String name;
    private String dosage;
    private int hour;
    private int minute;
    private boolean isActive;

    public Medication() {}

    public Medication(long id, String name, String dosage, int hour, int minute, boolean isActive) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.hour = hour;
        this.minute = minute;
        this.isActive = isActive;
    }

    // Геттеры и сеттеры
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public int getHour() { return hour; }
    public void setHour(int hour) { this.hour = hour; }

    public int getMinute() { return minute; }
    public void setMinute(int minute) { this.minute = minute; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}