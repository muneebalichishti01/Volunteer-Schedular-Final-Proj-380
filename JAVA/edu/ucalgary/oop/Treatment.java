package edu.ucalgary.oop;

public class Treatment {
    private  int ANIMALID;
    private  int TASKID;
    private  int STARTHOUR;

    public Treatment(int animalID, int taskID, int startHour) {
        this.TASKID = taskID;
        this.ANIMALID = animalID;
        this.STARTHOUR = startHour;
    }

    public int getAnimalID() {
        return this.ANIMALID;
    }

    public int getTaskID() {
        return this.TASKID;
    }

    public int getStartHour() {
        return this.STARTHOUR;
    }
}