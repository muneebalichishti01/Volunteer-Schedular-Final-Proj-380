package edu.ucalgary.oop;

import java.sql.*;
// import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class data_Connector{
	private Connection dbConnect;
    private ResultSet results;
	private Animal[] animalList = new Animal[50];
	private Task[] taskList = new Task[50];
	private Treatment[] treatmentList = new Treatment[50];
    private int[][] hourList= new int [24][10];
    private HashMap<Integer, TreeSet<Priority>> hoursMap = new HashMap<>();

	
	public data_Connector(){
	}
	
    public void createConnection(){
                
        try{
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "root", "0953326601");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void selectAnimals(){
	
        try {   
		
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM animals");
            int i = 0;
			
            while (results.next()){
				
				this.animalList[i]= new Animal(results.getInt("AnimalID"),results.getString("AnimaNickname"),results.getString("AnimalSpecies"));
				i++;
          
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }  

	 public void selectTasks(){

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM tasks");
            
			int i=0;
            while (results.next()){
                this.taskList[i]= new Task(results.getInt("TaskID"),results.getString("Description"),results.getInt("Duration"),
				results.getInt("MaxWindow"));
				i++;		
                
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    public void selectTreatments(){
  
        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM treatments");
			int i=0;
			
            while (results.next()){
				this.treatmentList[i]= new Treatment(results.getInt("AnimalID"),results.getInt("TaskID"),results.getInt("StartHour"));
				i++;
      
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    public void setPriority(){

        for(int i = 0 ; i<taskList.length;i++){

            for(int j = 0; i< treatmentList.length;j++){
                
                if( (taskList[i].getTaskID()==treatmentList[j].getTaskID())){
                    
                    Priority myPriority = new Priority(taskList[i].getTaskID(),treatmentList[j].getAnimalID(),taskList[i].getDuration(),taskList[i].getMaxWindow(),taskList[i].getDescription(),treatmentList[j].getStartHour());

                    if(this.hoursMap.containsKey(treatmentList[i].getStartHour())){
                        TreeSet<Priority> set = this.hoursMap.get(treatmentList[i].getStartHour());
                        set.add(myPriority);
                        this.hoursMap.put(treatmentList[i].getStartHour(),set); 
                    }
                    else{
                        TreeSet<Priority> set = new TreeSet<>();
                        set.add(myPriority);
                        this.hoursMap.put(treatmentList[i].getStartHour(),set);
                   }
                   
                    
                }
            }
        }

      

        }


        public static void  main(String [] args){

        }
        

        }

    
