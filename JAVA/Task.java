// package ENSF380_Final_Project;

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
    public int maxWindow(){
        return this.MAXWINDOW;
    }
}