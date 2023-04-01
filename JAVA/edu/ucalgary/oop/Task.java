package edu.ucalgary.oop;

public class Task{
    private final int TASKID;
    private final String DESCRIPTION;
    private final int DURATION;
    private final int MAXWINDOW;


    public Task(int taskId, String description,int duration, int maxWindow){
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
}