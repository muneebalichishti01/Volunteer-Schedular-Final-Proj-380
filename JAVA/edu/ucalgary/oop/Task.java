package edu.ucalgary.oop;

public class Task implements Print{
    private final int TASKID;
    private final String DESCRIPTION;
    private final int DURATION;
    private final int MAXWINDOW;


    public Task(int taskId, String description,int duration, int maxWindow)throws IllegalArgumentException{
        if(taskId < 1 || description == null || duration < 0 || maxWindow < 0)
            throw new IllegalArgumentException("Invalid input");
        this.TASKID = taskId;
        this.DESCRIPTION = description;
        this.DURATION= duration;
        this.MAXWINDOW = maxWindow; 
    }
    
    public int getTaskID(){
        return this.TASKID;
    }
    public String getDescription(){
        return this.DESCRIPTION;
    }
    public int getDuration(){
        return this.DURATION;
    }
    public int getMaxWindow(){
        return this.MAXWINDOW;
    }
    @Override
    public void print() {
            // TODO Auto-generated method stub
            System.out.println("Task ID: " + this.TASKID + " Description: " + this.DESCRIPTION +" Duration: " + this.DURATION + " Max Window: " + this.MAXWINDOW);
    }
}