package edu.ucalgary.oop;

import java.util.ArrayList;

public class Priority1  implements Comparable<Priority1>{
    private int taskID;
    private int animalID;
    private int duration;
    private int maxWindow;
    private String description;
    private int startHour;
    private int id;
    private  ArrayList<String> animalNames;

    public Priority1(int task, int animal, int Duration, int MaxWindow, String Desc , int start){
        this.taskID = task;
        this.animalID = animal;
        this.duration = Duration;
        this.maxWindow= MaxWindow;
        this.description = Desc;
        this.startHour= start;
        this.id = id;
    }
    public Priority1(ArrayList<String> animallist , int Duration, int MaxWindow, String Desc ){
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
    public int compareTo(Priority1 o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.maxWindow, o.maxWindow);
    }
}
/*Arrays.sort(myArray); */