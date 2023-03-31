

public class Task{
    private final int taskId;
    private final String description ;
    private final int duration;
    private final int maxWindow;


    public Task(int taskId, String description,int duration, int maxWindow){
        this.taskId = taskId;
        this.description = description;
        this.duration = duration;
        this.maxWindow = maxWindow; 
    }
    public int getTaskID(){
        return this.taskId;
    }
    public String getDescription(){
        return this.description;
    }
    public int getDuration(){
        return this.duration;
    }
    public int maxWindow(){
        return this.maxWindow;
    }
}