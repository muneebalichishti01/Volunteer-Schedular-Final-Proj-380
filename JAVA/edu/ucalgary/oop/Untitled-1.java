/*
 package edu.ucalgary.oop;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.print.attribute.PrintRequestAttribute;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;




public class data_Connector extends JFrame implements ActionListener {
    private ArrayList<Treatment> myTreatment = new ArrayList<>();
	private Connection dbConnect;
    private ResultSet results;
    private Animal[] animalList = null;
    private Task[] taskList = null;
    private Treatment[] treatmentList= null;
    private int[][] hourList= new int [24][10];
    private HashMap<Integer, ArrayList<Priority1>> hoursMap = new HashMap<>();
    private HashMap<Integer, ArrayList<Priority1>> newb = new HashMap<Integer, ArrayList<Priority1>>(); 
    private HashMap<Integer, ArrayList<Priority1>> verynewb = new HashMap<Integer, ArrayList<Priority1>>();
    private HashMap<Integer, ArrayList<Priority1>> veryverynewb = new HashMap<Integer, ArrayList<Priority1>>();
    private ArrayList<ArrayList<Priority1>> twoDimArrayList = new ArrayList<ArrayList<Priority1>>();
    private ArrayList<Integer>  keystorm =new ArrayList<>();
    private ArrayList<Priority1> valuestorm= new ArrayList<>();
    private ArrayList<String> foxList = new ArrayList<>();
    private ArrayList<String> coyoteList = new ArrayList<>();
    private ArrayList<String> procupineList = new ArrayList<>();
    private ArrayList<String> beaverList = new ArrayList<>();
    private ArrayList<String> raccoonList = new ArrayList<>();
    private ArrayList<Integer> volList = new ArrayList<>();


    private int numberOfFOx = 0;
    private int numberOfCoyotes = 0;
    private int numberOfprocupines = 0;
    private int numberOfBeavers = 0;
    private int numberOfraccoons = 0;
    private int timearray [] = new int [24];
   
  public ArrayList<String> getfoxList(){return this.foxList;}
  public ArrayList<String> getcoyoteList(){return this.coyoteList;}
  public ArrayList<String> getprocupineList(){return this.procupineList;}
  public ArrayList<String> getbeaverList(){return this.beaverList;}
  public ArrayList<String> getraccoonsList(){return this.raccoonList;}
    public ArrayList<Priority1> getvaluestorm(){
        return this.valuestorm;
    }
    public ArrayList<Integer> getvolList(){
        return this.volList;
    }
    public ArrayList<Integer> getkeystorm(){
        return this.keystorm;
    }
    public int [] gettimearray(){
        return this.timearray;
    }
    public int gettimearrayatindex(int x){
        return this.timearray[x];

    }
    public void settimearray(int x,int duration ){
         int y=  this.timearray[x] ;
         this.timearray[x] = y- duration;
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
    public HashMap<Integer,ArrayList<Priority1>> getHashmap (){
        return this.hoursMap;
    }
    public HashMap<Integer,ArrayList<Priority1> > getnewb (){
        return this.newb;
    }
    public HashMap<Integer,ArrayList<Priority1> > getverynewb (){
        return this.verynewb;
    }
    public HashMap<Integer,ArrayList<Priority1> > getveryverynewb (){
        return this.veryverynewb;
    }
    public data_Connector(){
        super("Schedule builder");
        createConnection();
        selectTreatments();
         selectAnimals();
         selectTasks();
         
         setPriority();
         newa();
         copying();
         // fun1();
      
         setupGUI();

         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
      
         
          
       }


    public void setupGUI(){
        JFrame frame = new JFrame("Schedule builder"); // MAIN FRAME
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel buttonsPanel = new JPanel(); //THIS WILL BE ADDED INTO MAIN FRAME : ADD BUTTON INTO THIS PANNEL
        JButton myButton = new JButton("Click to test your schedule"); //CREATES A BUTTON

        //CREATES A FUNCTION OF BUTTON
        myButton.addActionListener(this); //ADD FUNCTIONALITY TO BUTTON
        buttonsPanel.add(myButton); // ADD BUTTON TO PANEL
        frame.getContentPane().add(BorderLayout.CENTER, buttonsPanel); //ADD THIS PANEL TO BUTTON
        frame.setVisible(true);

    }
	public void actionPerformed(ActionEvent event){

        fun1();
        feeding();
        cleaning();
       
        

        JOptionPane.showMessageDialog(this,"You are all good");
        printSchedule();

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
                ArrayList<String> z = new ArrayList<>();
				//this.animalList[i]= new Animal(results.getInt("AnimalID"),results.getString("AnimalNickname"),results.getString("AnimalSpecies"));
                Animal animalNew = new Animal(results.getInt("AnimalID"),results.getString("AnimalNickname"),results.getString("AnimalSpecies"));
				
                myAnimals.add(animalNew);
                if (animalNew.getAnimalSpecies().equals("fox")){
                   for (int k=0; k<this.myTreatment.size();k++){
                    if (myTreatment.get(k).getAnimalID()==animalNew.getAnimalID() &&  myTreatment.get(k).getTaskID() == 1  ){
                           z.add(animalNew.getAnimalNickName());
                           
                    }

                }
                   
                   if (!z.contains(animalNew.getAnimalNickName())){
                    this.numberOfFOx+=1;
                    this.foxList.add(animalNew.getAnimalNickName());
                   }
                }
                
                
                 if (animalNew.getAnimalSpecies().equals("coyote")){
                    for (int k=0; k<this.myTreatment.size();k++){
                        if (myTreatment.get(k).getAnimalID()==animalNew.getAnimalID() &&  myTreatment.get(k).getTaskID() == 1  ){
                               z.add(animalNew.getAnimalNickName());
                               
                        }
    
                    }
                       
                       if (!z.contains(animalNew.getAnimalNickName())){
                        this.numberOfCoyotes+=1;
                        this.coyoteList.add(animalNew.getAnimalNickName());
                       }
                }
                 if (animalNew.getAnimalSpecies().equals("porcupine")){
                    for (int k=0; k<this.myTreatment.size();k++){
                        if (myTreatment.get(k).getAnimalID()==animalNew.getAnimalID() &&  myTreatment.get(k).getTaskID() == 1  ){
                               z.add(animalNew.getAnimalNickName());
                               
                        }
    
                    }
                       
                       if (!z.contains(animalNew.getAnimalNickName())){
                        this.numberOfprocupines+=1;
                        this.procupineList.add(animalNew.getAnimalNickName());
                       }
                }
                 if (animalNew.getAnimalSpecies().equals("beavers")){
                    for (int k=0; k<this.myTreatment.size();k++){
                        if (myTreatment.get(k).getAnimalID()==animalNew.getAnimalID() &&  myTreatment.get(k).getTaskID() == 1  ){
                               z.add(animalNew.getAnimalNickName());
                               
                        }
    
                    }
                       
                       if (!z.contains(animalNew.getAnimalNickName())){
                        this.numberOfBeavers+=1;
                        this.beaverList.add(animalNew.getAnimalNickName());
                       }
                }
                 if (animalNew.getAnimalSpecies().equals("raccoons")){
                    for (int k=0; k<this.myTreatment.size();k++){
                        if (myTreatment.get(k).getAnimalID()==animalNew.getAnimalID() &&  myTreatment.get(k).getTaskID() == 1  ){
                               z.add(animalNew.getAnimalNickName());
                               
                        }
    
                    }
                       
                       if (!z.contains(animalNew.getAnimalNickName())){
                        this.numberOfraccoons+=1;
                        this.raccoonList.add(animalNew.getAnimalNickName());
                       }
                }
                
                i++;
            }
        
           
        

        
        this.animalList = myAnimals.toArray(new Animal[0]);
        myStmt.close();
    }
      catch (SQLException ex) {
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
                        ArrayList<Priority1> set = this.hoursMap.get(treatmentList[j].getStartHour());
                        set.add(myPriority);
                        this.hoursMap.put(treatmentList[j].getStartHour(),set); 
                    }
                     else{
                        
                        ArrayList<Priority1> set1 = new ArrayList<Priority1>();
                        set1.add(myPriority);
                        this.hoursMap.put(treatmentList[j].getStartHour(),set1);}                        
                   }
                } 
            }
}
                
    public void copying(){
       for (int i = 0; i < 24; i++) {
        verynewb.put(i,new ArrayList<Priority1>());
    }
       for (int key : verynewb.keySet()) {
           if (newb.containsKey(key)) {
               verynewb.put(key, newb.get(key));
           }
       }
}
    public void fun1(){
        boolean adjustSchedule = false;
        this.verynewb =new HashMap<>(newb);
        
        for (int i = 0; i<24; i++){
            
            this.timearray[i] = 60;
           
        }
            for (Entry<Integer, ArrayList<Priority1>> entry :this.newb.entrySet()) {
                Integer key = entry.getKey();
                ArrayList<Priority1> value = entry.getValue();
                for (Priority1 item : value){
                    if (item.getduration()<= timearray[key]){
                    int y=  this.timearray[key] ;
                    this.timearray[key] = y- item.getduration();}
                    
                    else if (item.getMaxWindow()==1){
                        int result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?", "Title", JOptionPane.YES_NO_OPTION);


                        if(result == JOptionPane.YES_OPTION){
                            JOptionPane.showMessageDialog(this, "Calling back up volunteer");
                            
                            
                            
        
                            timearray[key ]+=60;
                       this.volList.add(key);
                       if (item.getduration()<= timearray[key]){
                        int y=  this.timearray[key] ;
                        this.timearray[key] = y- item.getduration();}
                       }
                       
                             
                            
        
                        
                        else{
                            JOptionPane.showMessageDialog(this, "conflic in hour"+key+ "please ");
        
                        }}
                        else{
                            keystorm.add(key);
                            valuestorm.add(item);
                            adjustSchedule = true;
                        }
                       }}

        if(adjustSchedule == true){
           JOptionPane.showMessageDialog(this, "Making adjustments to your schedule");
            fun2();
        
                }
        else{
            JOptionPane.showMessageDialog(this, "No need to make changes to schedule");
                }
               }

    public void fun2(){
   
        for (int i=0; i<this.keystorm.size(); i++){
            ArrayList<Priority1> valueForKeyB = this.newb.get(keystorm.get(i));
            valueForKeyB.remove(valuestorm.get(i));
            this.verynewb.put(keystorm.get(i),valueForKeyB);
        //value is not added yet
    }
    int max=0;
    Boolean added = false;
    int key1=0;
    Priority1 item= new Priority1();
        for (int i=0; i<this.valuestorm.size(); i++){
            
            item = this.valuestorm.get(i);
           
             if (item.getMaxWindow() >1){
                max  =item.getMaxWindow();
                int j=1;
                 key1 = keystorm.get(i);
            
              
            
                while(j<max){
                    if(key1+j>23){
                          System.out.println("Day finished cant add");
               
                        break;
                    }
                    
                if (item.getduration()<=this.timearray[key1+j]){
                    
                    int y=  this.timearray[key1+j] ;
                    this.timearray[key1+j] = y- item.getduration();
                    ArrayList<Priority1> valueForKeyB = this.verynewb.get(key1+j);
                    if(valueForKeyB == null){
                        valueForKeyB = new ArrayList<Priority1>();
                    }
                valueForKeyB.add(item);
                this.verynewb.put(key1+j, valueForKeyB);
                added = true; //value added succesfully
               //we should add break here to prevent further adding into other hours after this hour
                break;
            }
            

            j++;
           }
        }}
           int l=0;
           while(l<max-1){
            if(added == false){
                this.timearray[key1+l]+=60;
                this.volList.add(key1+l);
                if (item.getduration()<=this.timearray[key1+l]){
                    this.timearray[key1+l]-=item.getduration();
                    ArrayList<Priority1> valueForKeyB = this.verynewb.get(key1+l);
                    if(valueForKeyB == null){
                        valueForKeyB = new ArrayList<Priority1>();
                    }
                valueForKeyB.add(item);
                this.verynewb.put(key1+l, valueForKeyB);
                added = true;
                l=max-1;
                }
                
                
            }
            l++;
        }
            
        
        
 


   
        sort(); //this will sort veynewb
    }
    public void feeding(){
        System.out.print("size"+this.foxList.size());
        if (this.foxList.size()!= 0){
            boolean done = false;
        int feedingtime = 5+(this.foxList.size() * 5);
        String desc= "feeding";
        for (int i=0; i<this.foxList.size(); i++){
            desc = desc+foxList.get(i)+" , ";
            
    
        }
        Priority1 newpro = new Priority1(this.foxList, feedingtime, 3,desc );
       
        for( int i=0; i<3;i++){
            ArrayList<Priority1> u = verynewb.get(i);
            if(u==null){
                u = new ArrayList<Priority1>();
            }
        if(feedingtime<=this.timearray[i]){
            u.add(newpro);
            this.verynewb.put(i,u);
            this.timearray[i]= this.timearray[i]-feedingtime;
            done = true;
            break;
        }
    }
    if (done == false){
        
        int alreadyfed=0;
        int needtofeed =0;
        int s=0;
        for( int i=0; i<3;i++){
            
            
            String desc1= "feeding";
            ArrayList<String> first = new ArrayList<String>();
            if (this.timearray[i]>=10){
        
      int  timeleft = this.timearray[i];
      timeleft=timeleft -5;
      int z= timeleft/5;
       s +=z;
      
       if (z>foxList.size()-alreadyfed){
        z= foxList.size()-alreadyfed;
       }
    
    if (alreadyfed == foxList.size()){break;}
    
       System.out.print("this is "+z);
     
      for (int l=alreadyfed; l<alreadyfed+z;l++){
        first.add(foxList.get(l));
      }
        alreadyfed +=z;
        needtofeed = foxList.size()-alreadyfed;
    

      
    
      System.out.print("first "+first.size());
      for (int k=0; k<first.size(); k++){
        desc1 = desc1+foxList.get(k)+" , ";
        

    }
    Priority1 newpro1 = new Priority1(first, (z*5)+5, 3,desc1 );
    ArrayList<Priority1> u = verynewb.get(i);
    if(u==null){
        u = new ArrayList<Priority1>();
    }
    u.add(newpro1);
    this.verynewb.put(i,u);
    this.timearray[i]= this.timearray[i]-(z*5)-5;
    if (s==foxList.size()){
        System.out.print("wow");
        break;}


        }
      



}  
    }
}
if (this.raccoonList.size()!= 0){
    boolean done = false;
    int feedingtime = (this.raccoonList.size() * 5);
    String desc= "feeding";
    for (int i=0; i<this.raccoonList.size(); i++){
        desc = desc+raccoonList.get(i)+" , ";
        

    }
    Priority1 newpro = new Priority1(this.raccoonList, feedingtime, 3,desc );
    
    for( int i=0; i<3;i++){
        ArrayList<Priority1> u = verynewb.get(i);
        if(u==null){
            u = new ArrayList<Priority1>();
        }
    if(feedingtime<this.timearray[i]){
        u.add(newpro);
        this.verynewb.put(i,u);
        this.timearray[i]= this.timearray[i]-feedingtime;
        done = true;
        break;
    }
}
if (done == false){
        
    int alreadyfed=0;
    int needtofeed =0;
    int s=0;
    for( int i=0; i<3;i++){
        
        
        String desc1= "feeding";
        ArrayList<String> first = new ArrayList<String>();
        if (this.timearray[i]>=5){
    
  int  timeleft = this.timearray[i];
  
  int z= timeleft/5;
   s +=z;
  
   if (z>raccoonList.size()-alreadyfed){
    z= raccoonList.size()-alreadyfed;
   }

if (alreadyfed == raccoonList.size()){break;}

   System.out.print("this is "+z);
 
  for (int l=alreadyfed; l<alreadyfed+z;l++){
    first.add(raccoonList.get(l));
  }
    alreadyfed +=z;
    needtofeed = raccoonList.size()-alreadyfed;


  

  System.out.print("first "+first.size());
  for (int k=0; k<first.size(); k++){
    desc1 = desc1+raccoonList.get(k)+" , ";
    

}
Priority1 newpro1 = new Priority1(first, (z*5), 3,desc1 );
ArrayList<Priority1> u = verynewb.get(i);
if(u==null){
    u = new ArrayList<Priority1>();
}
u.add(newpro1);
this.verynewb.put(i,u);
this.timearray[i]= this.timearray[i]-(z*5);
if (s==raccoonList.size()){
    System.out.print("wow");
    break;}


    }
  



}  
}
}
if (this.beaverList.size()!= 0){
    boolean done = false;
    int feedingtime = (this.beaverList.size() * 5);
    String desc= "feeding";
    for (int i=0; i<this.beaverList.size(); i++){
        desc = desc+beaverList.get(i)+" , ";
        

    }
    Priority1 newpro = new Priority1(this.beaverList, feedingtime, 3,desc );
    
    for( int i=7; i<10;i++){
        ArrayList<Priority1> u = verynewb.get(i);
        if(u==null){
            u = new ArrayList<Priority1>();
        }
    if(feedingtime<this.timearray[i]){
        u.add(newpro);
        this.verynewb.put(i,u);
        this.timearray[i]= this.timearray[i]-feedingtime;
        done =true;
        break;
    }
}
if (done == false){
        
    int alreadyfed=0;
    int needtofeed =0;
    int s=0;
    for( int i=7; i<10;i++){
        
        
        String desc1= "feeding";
        ArrayList<String> first = new ArrayList<String>();
        if (this.timearray[i]>=5){
    
  int  timeleft = this.timearray[i];
  
  int z= timeleft/5;
   s +=z;
  
   if (z>beaverList.size()-alreadyfed){
    z= beaverList.size()-alreadyfed;
   }

if (alreadyfed == beaverList.size()){break;}

   System.out.print("this is "+z);
 
  for (int l=alreadyfed; l<alreadyfed+z;l++){
    first.add(beaverList.get(l));
  }
    alreadyfed +=z;
    needtofeed = beaverList.size()-alreadyfed;


  

  System.out.print("first "+first.size());
  for (int k=0; k<first.size(); k++){
    desc1 = desc1+beaverList.get(k)+" , ";
    

}
Priority1 newpro1 = new Priority1(first, (z*5), 3,desc1 );
ArrayList<Priority1> u = verynewb.get(i);
if(u==null){
    u = new ArrayList<Priority1>();
}
u.add(newpro1);
this.verynewb.put(i,u);
this.timearray[i]= this.timearray[i]-(z*5);
if (s==beaverList.size()){
    System.out.print("wow");
    break;}


    }
  



}  
}

}  






if (this.coyoteList.size()!= 0){
    boolean done = false;
    int feedingtime = 10+(this.coyoteList.size() * 5);
    String desc= "feeding";
    for (int i=0; i<this.coyoteList.size(); i++){
        desc = desc+" , "+coyoteList.get(i);
        

    }
    Priority1 newpro = new Priority1(this.coyoteList, feedingtime, 3,desc );
    
    for( int i=7; i<10;i++){
        ArrayList<Priority1> u = verynewb.get(i);
        if(u==null){
            u = new ArrayList<Priority1>();
        }
    if(feedingtime<this.timearray[i]){
        u.add(newpro);
        this.verynewb.put(i,u);
        this.timearray[i]= this.timearray[i]-feedingtime;
        done =true;
        break;
    }
}
if (done == false){
        
    int alreadyfed=0;
    int needtofeed =0;
    int s=0;
    for( int i=7; i<10;i++){
        
        
        String desc1= "feeding";
        ArrayList<String> first = new ArrayList<String>();
        if (this.timearray[i]>=10){
    
  int  timeleft = this.timearray[i];
  timeleft=timeleft -10;
  int z= timeleft/5;
   s +=z;
  
   if (z>coyoteList.size()-alreadyfed){
    z= coyoteList.size()-alreadyfed;
   }

if (alreadyfed == coyoteList.size()){break;}

   System.out.print("this is "+z);
 
  for (int l=alreadyfed; l<alreadyfed+z;l++){
    first.add(coyoteList.get(l));
  }
    alreadyfed +=z;
    needtofeed = coyoteList.size()-alreadyfed;


  

  System.out.print("first "+first.size());
  for (int k=0; k<first.size(); k++){
    desc1 = desc1+coyoteList.get(k)+" , ";
    

}
Priority1 newpro1 = new Priority1(first, (z*5)+10, 3,desc1 );
ArrayList<Priority1> u = verynewb.get(i);
if(u==null){
    u = new ArrayList<Priority1>();
}
u.add(newpro1);
this.verynewb.put(i,u);
this.timearray[i]= this.timearray[i]-(z*5)-10;
if (s==coyoteList.size()){
    System.out.print("wow");
    break;}


    }
  



}  
}
}
if (this.procupineList.size()!= 0){
    boolean done = false;
    int feedingtime = 10+(this.procupineList.size() * 5);
    String desc= "feeding";
    for (int i=0; i<this.procupineList.size(); i++){
        desc = desc+" , "+procupineList.get(i);
        

    }
    Priority1 newpro = new Priority1(this.procupineList, feedingtime, 3,desc );
    
    for( int i=7; i<10;i++){
        ArrayList<Priority1> u = verynewb.get(i);
        if(u==null){
            u = new ArrayList<Priority1>();
        }
    if(feedingtime<this.timearray[i]){
        u.add(newpro);
        this.verynewb.put(i,u);
        this.timearray[i]= this.timearray[i]-feedingtime;
        done = true;
        break;
    }
}
if (done == false){
        
    int alreadyfed=0;
    int needtofeed =0;
    int s=0;
    for( int i=7; i<10;i++){
        
        
        String desc1= "feeding";
        ArrayList<String> first = new ArrayList<String>();
        if (this.timearray[i]>=5){
    
  int  timeleft = this.timearray[i];
  
  int z= timeleft/5;
   s +=z;
  
   if (z>coyoteList.size()-alreadyfed){
    z= procupineList.size()-alreadyfed;
   }

if (alreadyfed == procupineList.size()){break;}

   System.out.print("this is "+z);
 
  for (int l=alreadyfed; l<alreadyfed+z;l++){
    first.add(procupineList.get(l));
  }
    alreadyfed +=z;
    needtofeed = procupineList.size()-alreadyfed;


  

  System.out.print("first "+first.size());
  for (int k=0; k<first.size(); k++){
    desc1 = desc1+procupineList.get(k)+" , ";
    

}
Priority1 newpro1 = new Priority1(first, (z*5), 3,desc1 );
ArrayList<Priority1> u = verynewb.get(i);
if(u==null){
    u = new ArrayList<Priority1>();
}
u.add(newpro1);
this.verynewb.put(i,u);
this.timearray[i]= this.timearray[i]-(z*5);
if (s==procupineList.size()){
    System.out.print("wow");
    break;}


    }
  



}  
}
}
    
    }    




    public void newa(){
        for (Entry<Integer, ArrayList<Priority1>> entry : hoursMap.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Priority1> value = entry.getValue();
                
            Priority1[] myArray = value.toArray(new Priority1[value.size()]);
                
            Arrays.sort(myArray);
            ArrayList<Priority1> list = new ArrayList<>(Arrays.asList(myArray));
                
            this.newb.put(key,list);
    }
}

    public void sort(){ //sorts verynewB after adding values
            for (Entry<Integer, ArrayList<Priority1>> entry : verynewb.entrySet()) {
                Integer key = entry.getKey();
                ArrayList<Priority1> value = entry.getValue();
                Priority1[] myArray = value.toArray(new Priority1[value.size()]);
                Arrays.sort(myArray);
                ArrayList<Priority1> list = new ArrayList<>(Arrays.asList(myArray));
                this.verynewb.put(key,list);
            }
        
    }
    public void cleaning(){
        ArrayList<Animal> animaltoclean = new ArrayList<>();
       
        ArrayList<Animal> procupinetoclean = new ArrayList<>();


        ArrayList<String> k = new ArrayList<>();
        for (int i = 0; i<this.animalList.length;i++){
          
            if (animalList[i].getAnimalSpecies().equals("porcupine")){
                procupinetoclean.add(animalList[i]);
                
            }
            else{
                animaltoclean.add(animalList[i]);
                System.out.println("panchod"+animalList[i].getAnimalSpecies());
            }
            
        }
       
        boolean all = false;
        int num = 0;
        int alreadyclean=0;
        for (int i =0 ; i<24; i++){
            String desc= "cleaning ";
            
            if (this.timearray[i]>0){
                int x= this.timearray[i]/5;
                if (x>animaltoclean.size()-alreadyclean){
                    x= animaltoclean.size()-alreadyclean;
                    all =true;

                }
                for (int j=alreadyclean; j<x+alreadyclean;j++){
                   k.add(animaltoclean.get(j).getAnimalNickName());
                   desc =desc +" ,"+ animaltoclean.get(j).getAnimalNickName();
                }

                Priority1 kk = new Priority1(k, x*5, 24, desc);
                ArrayList<Priority1> h=  this.verynewb.get(i);
                if(h==null){h= new ArrayList<Priority1>();}
               alreadyclean +=x;
                h.add(kk);
                this.verynewb.put(i,h);
                timearray[i]-=(x*5);
                if (alreadyclean==animaltoclean.size()){
                    break;
                } 
                if (i==23 && alreadyclean!=x){
                    System.out.print("please call for help please add qazi");
                    for (int l=0; l<24;l++){
                        if (!volList.contains(l)){
                            this.timearray[l]+=60;
                            volList.add(l);
                        }
                             k = new ArrayList<>();
                            desc= "cleaning";

                            for (int a=alreadyclean;a<animaltoclean.size(); a++ ){
                                k.add(animaltoclean.get(a).getAnimalNickName());
                                desc=desc +" ,"+ animaltoclean.get(a).getAnimalNickName();
                            }
                             kk = new Priority1(k, x*5, 24, desc);
                             h=  this.verynewb.get(l);
                            if(h==null){h= new ArrayList<Priority1>();}
                           
                            h.add(kk);
                            this.verynewb.put(l,h);
                            timearray[l]-=(animaltoclean.size()-alreadyclean)*5;
                            all =true;

                            
                        }
                    }
                }
                if (all){break;}




                


            }
            boolean all1 = false;
            
            int alreadyclean1=0;
            for (int i =0 ; i<24; i++){
                String desc= "cleaning ";
                
                if (this.timearray[i]>0){
                    int x= this.timearray[i]/10;
                    if (x>procupinetoclean.size()-alreadyclean1){
                        x= procupinetoclean.size()-alreadyclean1;
                        all1 =true;
    
                    }
                    for (int j=alreadyclean1; j<x+alreadyclean1;j++){
                       k.add(procupinetoclean.get(j).getAnimalNickName());
                       desc =desc +" ,"+ procupinetoclean.get(j).getAnimalNickName();
                    }
    
                    Priority1 kk = new Priority1(k, x*10, 24, desc);
                    ArrayList<Priority1> h=  this.verynewb.get(i);
                    if(h==null){h= new ArrayList<Priority1>();}
                   alreadyclean1 +=x;
                    h.add(kk);
                    this.verynewb.put(i,h);
                    timearray[i]-=(x*10);
                    if (alreadyclean1==procupinetoclean.size()){
                        break;
                    } 
                    if (i==23 && alreadyclean1!=x){
                        System.out.print("please call for help please add qazi");
                        for (int l=0; l<24;l++){
                            if (!volList.contains(l)){
                                this.timearray[l]+=60;
                                volList.add(l);
                            }
                                 k = new ArrayList<>();
                                desc= "cleaning";

                                for (int a=alreadyclean1;a<procupinetoclean.size(); a++ ){
                                    k.add(procupinetoclean.get(a).getAnimalNickName());
                                    desc=desc +" ,"+ procupinetoclean.get(a).getAnimalNickName();
                                }
                                 kk = new Priority1(k, x*10, 24, desc);
                                 h=  this.verynewb.get(l);
                                if(h==null){h= new ArrayList<Priority1>();}
                               
                                h.add(kk);
                                this.verynewb.put(l,h);
                                timearray[l]-=(procupinetoclean.size()-alreadyclean1)*10;
                                all1 =true;
    
                                
                            }
                        }
                    }
                    if (all1){break;}
    
    
    
    
                    
    
    
                }
            
        }
    
      public void printSchedule(){
        for (Entry<Integer, ArrayList<Priority1>> entry : getverynewb().entrySet()){
            Integer key1 = entry.getKey();
            ArrayList<Priority1> value = entry.getValue();
            if(value!=null){
            for (Priority1 item : value) {
               
                System.out.print("hour: "+key1+ "    ");
                
                System.out.print("maxwindow:"+item.getMaxWindow()+ "    ");
                System.out.print("description: "+item.getdescription()+ "    ");
                
                System.out.print("duration: "+item.getduration()+ "    ");
                System.out.print("\n");
               
                }
            }
       }
       for (int val :this.volList){
        System.out.print(val);
       }
       for(int val : this.timearray){
        System.out.println(val);
       }

      }

    
    public static void  main(String [] args){
      
        EventQueue.invokeLater(()->{
             new data_Connector().setVisible(true);

        });
        
      
    }
    
}


 
        
 */