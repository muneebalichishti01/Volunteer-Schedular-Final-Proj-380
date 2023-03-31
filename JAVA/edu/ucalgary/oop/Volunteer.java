package edu.ucalgary.oop;

public class Volunteer {
    private String ID;
    private Task task;
    private String volunteerName;
    private String backupVolName;

    public Volunteer(String volID, String volunteerName, String backupVolName) {
        this.ID = volID;
        this.volunteerName = volunteerName;
        this.backupVolName = backupVolName;
    }

    public void setVolID(String volID) {
        this.ID = volID;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public void setBackupVol(String backupVolName) {
        this.backupVolName = backupVolName;
    }

    public String getID() {
        return ID;
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
