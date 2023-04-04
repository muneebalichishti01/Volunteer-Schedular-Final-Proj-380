package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class data_Connector {
	private Connection dbConnect;
    private ResultSet results;
    private Animal[] animalList = null;
    private Task[] taskList = null;
    private Treatment[] treatmentList= null;

    private int[][] hourList= new int [24][10];
    private HashMap<Integer, TreeSet<Priority1>> hoursMap = new HashMap<Integer, TreeSet<Priority1>>();
    private HashMap<Integer, Priority1[]> newb = new HashMap<Integer, Priority1[]>();

    private int numberOfFOx = 0;
    private int numberOfCoyotes = 0;
    private int numberOfprocupines = 0;
    private int numberOfBeavers = 0;
    private int numberOfraccoons = 0;
	
	public data_Connector(){
	}
    public Animal[] getAnimalList(){
        return this.animalList;
    }
    public Task[] getTaskList(){
        return this.taskList;
    }
    public int getnumberOfCoyotes(){
        return this.numberOfCoyotes;
    }
    public int getnumberOfFOx(){
        return this.numberOfFOx;
    }
    public int getnumberOfprocupines(){
        return this.numberOfprocupines;
    }
    public int getnumberOfBeavers(){
        return this.numberOfBeavers;
    }
    public int getnumberOfraccoons(){
        return this.numberOfraccoons;
    }
    public Treatment[] getTreatmentList(){
        return this.treatmentList;
    }
    public int[][] getHourList(){
        return this.hourList;
    }
	
    public void createConnection(){

        try{
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "root", "0953326601");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void selectAnimals(){
        ArrayList<Animal> myAnimals = new ArrayList<>();

        try {   
		
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM animals");
            int i = 0;
			
            while (results.next()){
            	
				//this.animalList[i]= new Animal(results.getInt("AnimalID"),results.getString("AnimalNickname"),results.getString("AnimalSpecies"));
                Animal animalNew = new Animal(results.getInt("AnimalID"),results.getString("AnimalNickname"),results.getString("AnimalSpecies"));
				
                myAnimals.add(animalNew);
                if (animalNew.getAnimalSpecies().equals("fox")){
                    this.numberOfFOx+=1;
                }
                else if (animalNew.getAnimalSpecies().equals("coyote")){
                    this.numberOfCoyotes+=1;
                }
                else if (animalNew.getAnimalSpecies().equals("porcupine")){
                    this.numberOfprocupines+=1;
                }
                else if (animalNew.getAnimalSpecies().equals("beavers")){
                    this.numberOfBeavers+=1;
                }
                else if (animalNew.getAnimalSpecies().equals("raccoons")){
                    this.numberOfraccoons+=1;
                }
                
                i++;
            }
            this.animalList = myAnimals.toArray(new Animal[0]);
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  

	public void selectTasks(){
        ArrayList<Task> myTasks = new ArrayList<>();

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM tasks");
            
			int i=0;
            while (results.next()){
                
                myTasks.add(new Task(results.getInt("TaskID"),results.getString("Description"),results.getInt("Duration"),
				results.getInt("MaxWindow")));
				i++;		
                
            }
            this.taskList = myTasks.toArray(new Task[0]);
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
    }

    public void selectTreatments(){
        ArrayList<Treatment> myTreatment = new ArrayList<>();
  
        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM treatments");
			int i=0;
			
            while (results.next()){
				myTreatment.add(new Treatment(results.getInt("AnimalID"),results.getInt("TaskID"),results.getInt("StartHour")));
                i++;
            }

            this.treatmentList = myTreatment.toArray(new Treatment[0]);
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    public void setPriority(){
        for(int i = 0 ; i<taskList.length;i++){
            for(int j = 0; j<treatmentList.length ;j++){
           
                if (this.taskList[i].getTaskID()==this.treatmentList[j].getTaskID() ){
                    Priority1 myPriority = new Priority1(this.taskList[i].getTaskID(),this.treatmentList[j].getAnimalID(),this.taskList[i].getDuration(),taskList[i].getMaxWindow(),this.taskList[i].getDescription(),this.treatmentList[j].getStartHour());

                     if(this.hoursMap.containsKey(treatmentList[j].getStartHour())){
                        TreeSet<Priority1> set = this.hoursMap.get(treatmentList[j].getStartHour());
                        set.add(myPriority);
                        this.hoursMap.put(treatmentList[j].getStartHour(),set);                 
                    }
                     else{
                        TreeSet<Priority1> set1 = new TreeSet<>();
                        set1.add(myPriority);
                        this.hoursMap.put(treatmentList[j].getStartHour(),set1);
                   } 
                }
            }
        }
    }
    
    public void newa(){
        for (Entry<Integer, TreeSet<Priority1>> entry : hoursMap.entrySet()) {
            Integer key = entry.getKey();
            TreeSet<Priority1> value = entry.getValue();
                
            Priority1[] myArray = value.toArray(new Priority1[value.size()]);
                
            Arrays.sort(myArray);
                
            this.newb.put(key,myArray);
        }
    }
      
    public HashMap<Integer,TreeSet<Priority1>> getHashmap (){
        return this.hoursMap;
    }
    public HashMap<Integer,Priority1[]> getnewb (){
        return this.newb;
    }
    
    public static void  main(String [] args){
        data_Connector myJDBC = new data_Connector();

        myJDBC.createConnection();
        myJDBC.selectAnimals();
        myJDBC.selectTasks();
        myJDBC.selectTreatments();
        myJDBC.setPriority();
        myJDBC.newa();

        System.out.print( myJDBC.getnumberOfCoyotes());
        System.out.print( myJDBC.getnumberOfFOx());
        System.out.print( myJDBC.getnumberOfprocupines());

       /*  for (Entry<Integer, Priority1[]> entry : myJDBC.getnewb().entrySet()) {
            Integer key = entry.getKey();
            Priority1[] value = entry.getValue();
            for (Priority1 item : value) {
                System.out.print("hour: "+key+ "    ");
                System.out.print("task: "+item.getTaskID()+ "    ");
                System.out.print("maxwindow:"+item.getMaxWindow()+ "    ");
                System.out.print("description: "+item.getdescription()+ "    ");
                System.out.print("animalid: "+item.getanimalID()+ "    ");
                System.out.print("duration: "+item.getduration()+ "    ");
                System.out.print("\n");
                
                }
            }
        }*/
    }
}
       
    
    
    
            
    
   
        

     
    


        
        

        

    
