package edu.ucalgary.oop;

import java.lang.annotation.Retention;
import java.sql.*;
import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class data_Connector {
    
	private Connection dbConnect;
    private ResultSet results;
	//private Animal[] animalList = new Animal[15];
    private Animal[] animalList = null;
	//private Task[] taskList = new Task[10];
    private Task[] taskList = null;
	//private Treatment[] treatmentList = new Treatment[30];
    private Treatment[] treatmentList= null;
    private int[][] hourList= new int [24][10];
    private HashMap<Integer, String> scheduleMap = new HashMap<>(); 
    private HashMap<Integer, TreeSet<Priority1>> hoursMap = new HashMap<Integer, TreeSet<Priority1>>();
    

	
	public data_Connector(){
	}
    public Animal[] getAnimalList(){
        return this.animalList;
    }
    public Task[] getTaskList(){
        return this.taskList;
    }
    public HashMap<Integer,String> getScheduMap(){
        return this.scheduleMap;
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
				myAnimals.add(new Animal(results.getInt("AnimalID"),results.getString("AnimalNickname"),results.getString("AnimalSpecies")));

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

        for(int i = 0 ; i<10;i++){

            for(int j = 0; j<30 ;j++){
          
                
                
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

        public HashMap<Integer,TreeSet<Priority1>> getHashmap (){
            return this.hoursMap;
        }
        public void scheduling(){
            
            for (Entry<Integer, TreeSet<Priority1>> entry : hoursMap.entrySet()) {
                Integer key = entry.getKey();
                TreeSet<Priority1> value = entry.getValue();
                int i = 0;
                for (Priority1 item : value) {
                    for (int j=0; j<24; j++){
                    
                        if (j == item.getStartHour() ){
                            this.hourList[j][i] = item.getTaskID(); 

                            i++;
                        }
                        if (this.hourList[j][i] != 0){ 
                            if(this.scheduleMap.containsKey(item.getStartHour())){ //0: lol + lol3
                                String myStr = this.scheduleMap.get(item.getStartHour());
                                myStr = myStr + "\n" + item.getdescription();
                                
                                this.scheduleMap.put(item.getStartHour(),myStr); 
                            }
                             else{
                                String myStr2 = item.getdescription(); //0 = empty
                                
                                this.scheduleMap.put(item.getStartHour(),myStr2);
                           }
                            
                        }
                        
                    }
                
            }
           
        }
    }
        


        public static void  main(String [] args){
        data_Connector myJDBC = new data_Connector();


 
        myJDBC.createConnection();
        
        myJDBC.selectAnimals();
        
        myJDBC.selectTasks();

        myJDBC.selectTreatments();
        myJDBC.setPriority();
        myJDBC.scheduling();
        for (int i = 0; i<myJDBC.getHourList().length;i++){
            for (int j= 0; j<myJDBC.getHourList()[i].length; j++){
                System.out.println( i+ " "+j+"     "+myJDBC.getHourList()[i][j]);
            }
        }
        System.out.println(myJDBC.getHourList());
        System.out.println(myJDBC.getAnimalList().length);
        System.out.println(myJDBC.getTaskList().length);
        System.out.println(myJDBC.getTreatmentList().length);
       /* HashMap<Integer, TreeSet<Priority1>> hoursMap = myJDBC.getHashmap();
        for (Entry<Integer, TreeSet<Priority1>> entry : hoursMap.entrySet()) {
            Integer key = entry.getKey();
            TreeSet<Priority1> value = entry.getValue();
            for (Priority1 item : value) {
                System.out.println(key + " " + item.getanimalID());
                }
            }
                
        } */  

     HashMap<Integer, String> schedule = myJDBC.getScheduMap();
     for (Entry<Integer, String> entry : schedule.entrySet()) {
        Integer key = entry.getKey();
        String value = entry.getValue();
        System.out.println(key + " " + value);
      
        
    }
        

        }
    }


        
        

        

    
