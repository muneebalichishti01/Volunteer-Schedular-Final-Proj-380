// package edu.ucalgary.oop;
// package ENSF380_Final_Project;

public class Treatment {
    private final int ANIMALID;
    private final int TASKID;
    private final int STARTHOUR;

    public Treatment(int animalID, int taskID, int startHour) {
        this.TASKID = taskID;
        this.ANIMALID = animalID;
        this.STARTHOUR = startHour;
    }

    public int getAnimalID() {
        return ANIMALID;
    }

    public int getTaskID() {
        return TASKID;
    }

    public int getStartHour() {
        return STARTHOUR;
    }
}