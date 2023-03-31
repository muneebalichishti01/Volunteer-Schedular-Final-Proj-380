// package edu.ucalgary.oop;
// package ENSF380_Final_Project;

public class Treatment {
    private final int animalID;
    private final int taskID;
    private final int startHour;

    public Treatment(int animalID, int taskID, int startHour) {
        this.taskID = taskID;
        this.animalID = animalID;
        this.startHour = startHour;
    }

    public int getAnimalID() {
        return animalID;
    }

    public int getTaskID() {
        return taskID;
    }

    public int getStartHour() {
        return startHour;
    }
}