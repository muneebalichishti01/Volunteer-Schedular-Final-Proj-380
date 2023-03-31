package edu.ucalgary.oop;

import java.sql.*;
// import java.util.ArrayList;

public class data_Connector{
	private Connection dbConnect;
    private ResultSet results;
	private Animal[] animalList = new Animal[50];
	private Task[] taskList = new Task[50];
	private Treatment[] treatmentList = new Treatment[50];
	
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
}	
