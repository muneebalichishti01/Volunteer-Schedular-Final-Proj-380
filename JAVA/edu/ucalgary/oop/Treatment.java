/**
* 
* This Treatment Class is used to create Treatment objects which will have 3 data members: AnimalId, TaskID, and StartHour
* @author  Mohamad Jamal Hammoud, Qazi Ali, Mirza Hassan Baig, Muneeb Ali
* @version 3.0
* @since   2023-03-31
*/

package edu.ucalgary.oop;

public class Treatment implements Print{
    private final int ANIMALID;
    private final int TASKID;
    private final int STARTHOUR;

    // A coonstructor that throws illegal argument exception when bad data is given
    public Treatment(int animalID, int taskID, int startHour) throws IllegalArgumentException {

        if(animalID < 1 || taskID < 1 || startHour < 0)
            throw new IllegalArgumentException("Invalid input");
        if(startHour < 0 || startHour > 23)
            throw new IllegalArgumentException("Start Hour must be between 1 and 24 hours");
        if(taskID < 1 )
            throw new IllegalArgumentException("Task ID must be greater than1" );
        this.TASKID = taskID;
        this.ANIMALID = animalID;
        this.STARTHOUR = startHour;
    }
    //Getters
    public int getAnimalID() {
        return this.ANIMALID;
    }
    public int getTaskID() {
        return this.TASKID;
    }
    public int getStartHour() {
        return this.STARTHOUR;
    }
    //Implementation of abstract method included in the interface that the class implements
    @Override
    public void print() {
        System.out.println("Animal ID: " + this.ANIMALID + " Task ID: " + this.TASKID + " Start Hour: " + this.STARTHOUR);
    }
}