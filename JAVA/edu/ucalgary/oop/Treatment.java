package edu.ucalgary.oop;

public class Treatment implements Print{
    private final int ANIMALID;
    private final int TASKID;
    private final int STARTHOUR;

    public Treatment(int animalID, int taskID, int startHour) throws IllegalArgumentException {

        if(animalID < 0 || taskID < 0 || startHour < 0)
            throw new IllegalArgumentException("Invalid input");
        if(startHour < 0 || startHour > 34)
            throw new IllegalArgumentException("Start Hour must be between 1 and 24 hours");
        if(taskID < 1 )
            throw new IllegalArgumentException("Task ID must be greater than1" );
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
    @Override
    public void print() {
        // TODO Auto-generated method stub
        System.out.println("Animal ID: " + this.ANIMALID + " Task ID: " + this.TASKID + " Start Hour: " + this.STARTHOUR);
    }
}