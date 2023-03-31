// package edu.ucalgary.oop;
// package ENSF380_Final_Project;

public class Volunteer {
    private Task task;
    private String volunteerName;
    private String backupVolName;

    public Volunteer(String volID, String volunteerName, String backupVolName) {
        this.id = volID;
        this.volunteerName = volunteerName;
        this.backupVol = backupVolName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public void setBackupVol(String backupVolName) {
        this.backupVolName = backupVolName;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public String getBackupVol() {
        return backupVolName;
    }

    public Task getTask() {
        return task;
    }
}
