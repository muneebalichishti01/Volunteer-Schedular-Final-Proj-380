package edu.ucalgary.oop;

import java.util.ArrayList;

public class Priority  implements Comparable<Priority>,Print{
    private int taskID;
    private int animalID;
    private int duration;
    private int maxWindow;
    private String description;
    private int startHour;
    private int id;
    private  ArrayList<String> animalNames;
    
    public Priority(){}

    public Priority(int task, int animal, int Duration, int MaxWindow, String Desc , int start)throws IllegalArgumentException{
        if(task < 0 || animal < 0 || Duration < 0 || MaxWindow < 0 || Desc == null || start < 0)
            throw new IllegalArgumentException("Invalid input");
       
        if(start < 0 || start > 23)
            throw new IllegalArgumentException("Start Hour must be between 1 and 24 hours");
        if(task < 1 )
            throw new IllegalArgumentException("Task ID must be greater than1" );
        if(animal < 1 )
            throw new IllegalArgumentException("Animal ID must be greater than1" );
        
        this.taskID = task;
        this.animalID = animal;
        this.duration = Duration;
        this.maxWindow= MaxWindow;
        this.description = Desc;
        this.startHour= start;
        this.id = id;
    }
    public Priority(ArrayList<String> animallist , int Duration, int MaxWindow, String Desc ){
       this.animalNames = animallist;
        this.duration = Duration;
        this.maxWindow = MaxWindow;
        this.description = Desc;

        
    }

    public int getTaskID(){
        return this.taskID;
    }
    public int getanimalID(){
        return this.animalID;
    }
    public int getduration(){
        return this.duration;
    
    }
    public int getMaxWindow(){
        return this.maxWindow;
    }
    public String getdescription(){
        return this.description;
    }
    public int getStartHour(){
        return this.startHour;
    }
    
    @Override
    public int compareTo(Priority o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.maxWindow, o.maxWindow);
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub
        System.out.println("Task ID: " + this.taskID + " Animal ID: " + this.animalID + " Duration: " + this.duration + " Max Window: " + this.maxWindow + " Description: " + this.description + " Start Hour: " + this.startHour);
    }
}
/*Arrays.sort(myArray); */
