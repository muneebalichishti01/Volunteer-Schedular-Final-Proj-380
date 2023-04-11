/**
* This class is used to make a connection with the database and extract all
* the information needed to fill our Animals, Treatments, and Tasks Lists
* It Also creates a Hashmap with keys of hours in the day and values as 
* ArraList of Priority objects that describes the medical treatments only
* 
* @author  Mohamad Jamal Hammoud: Version 1.0
 Qazi Ali: Version 2.0
 Mirza Hassan Baig Version: 3.0,
 Muneeb Ali Version: 4.0
 Mohamad Jamal Hammoud: Version 5.0
* @version 5.0
* @since   2023-04-3 
*/

package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;



public class Populating extends  JFrame {
  protected ArrayList < Treatment > myTreatment = new ArrayList < > ();
  protected Connection dbConnect;
  protected ResultSet results;
  protected Animal[] animalList = null;
  protected Task[] taskList = null;
  protected Treatment[] treatmentList = null;
  protected int[][] hourList = new int[24][10];
  protected HashMap < Integer, ArrayList < Priority >> hoursMap = new HashMap < > ();
  protected ArrayList < String > foxList = new ArrayList < > ();
  protected ArrayList < String > coyoteList = new ArrayList < > ();
  protected ArrayList < String > procupineList = new ArrayList < > ();
  protected ArrayList < String > beaverList = new ArrayList < > ();
  protected ArrayList < String > raccoonList = new ArrayList < > ();
  protected int numberOfFox = 0;
  protected int numberOfCoyotes = 0;
  protected int numberOfProcupines = 0;
  protected int numberOfBeavers = 0;
  protected int numberOfraccoons = 0;


  //Getters
  public ArrayList < String > getFoxList() { //here
    return this.foxList;
  }
  public ArrayList < String > getCoyoteList() { //here
    return this.coyoteList;
  }
  public ArrayList < String > getProcupineList() { //here
    return this.procupineList;
  }
  public ArrayList < String > getBeaverList() { //here
    return this.beaverList;
  }
  public ArrayList < String > getRaccoonsList() { //here
    return this.raccoonList;
  }
 
  public Animal[] getAnimalList() {
    return this.animalList;
  }
  public Task[] getTaskList() {
    return this.taskList;
  }
  public int getnumberOfCoyotes() {
    return this.numberOfCoyotes;
  }
  public int getnumberOfFOx() {
    return this.numberOfFox;
  }
  public int getnumberOfProcupines() { //here
    return this.numberOfProcupines; //here
  }
  public int getnumberOfBeavers() {
    return this.numberOfBeavers;
  }
  public int getnumberOfRaccoons() { //here
    return this.numberOfraccoons;
  }
  public Treatment[] getTreatmentList() {
    return this.treatmentList;
  }
  public int[][] getHourList() {
    return this.hourList;
  }
  public HashMap < Integer, ArrayList < Priority >> getHoursMap() { //here
    return this.hoursMap;
  }

  

  //Constructor has super because it is the parent class of the Scheduling class
  public Populating() {
    super("Schedule builder");

  }

  // Connects with the sql database
  public void createConnection() {

    try {
      dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "root", "1234");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //Function to read line by line from the Animal Table in the sql database, and intialize animal objects for each line read
  public void selectAnimals () {
    ArrayList < Animal > myAnimals = new ArrayList < > ();

    try {

      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM animals");
      int i = 0;

      while (results.next()) {
        ArrayList < String > z = new ArrayList < > ();
        //this.animalList[i]= new Animal(results.getInt("AnimalID"),results.getString("AnimalNickname"),results.getString("AnimalSpecies"));
        Animal animalNew = new Animal(results.getInt("AnimalID"), results.getString("AnimalNickname"), results.getString("AnimalSpecies"));

        //Adding read object to the arraylist of animals
        myAnimals.add(animalNew);
        // Creating an arrayList of foxes which contains the fox names, and the size of the array is the number of foxes
        if (animalNew.getAnimalSpecies().equals("fox")) {
          for (int k = 0; k < this.myTreatment.size(); k++) {
            if (myTreatment.get(k).getAnimalID() == animalNew.getAnimalID() && myTreatment.get(k).getTaskID() == 1) {
              z.add(animalNew.getAnimalNickName());

            }

          }

          if (!z.contains(animalNew.getAnimalNickName())) {
            this.numberOfFox += 1;
            this.foxList.add(animalNew.getAnimalNickName());
          }
        }
        // Creating an arrayList of Coyote which contains the Coyote names, and the size of the array is the number of coyotes
        if (animalNew.getAnimalSpecies().equals("coyote")) {
          for (int k = 0; k < this.myTreatment.size(); k++) {
            if (myTreatment.get(k).getAnimalID() == animalNew.getAnimalID() && myTreatment.get(k).getTaskID() == 1) {
              z.add(animalNew.getAnimalNickName());

            }

          }

          if (!z.contains(animalNew.getAnimalNickName())) {
            this.numberOfCoyotes += 1;
            this.coyoteList.add(animalNew.getAnimalNickName());
          }
        }
        // Creating an arrayList of Porcupines which contains the porcupine names, and the size of the array is the number of porcupines
        if (animalNew.getAnimalSpecies().equals("porcupine")) {
          for (int k = 0; k < this.myTreatment.size(); k++) {
            if (myTreatment.get(k).getAnimalID() == animalNew.getAnimalID() && myTreatment.get(k).getTaskID() == 1) {
              z.add(animalNew.getAnimalNickName());

            }

          }

          if (!z.contains(animalNew.getAnimalNickName())) {
            this.numberOfProcupines += 1;
            this.procupineList.add(animalNew.getAnimalNickName());
          }
        }
        // Creating an arrayList of Beavers which contains the Beaver names, and the size of the array is the number of Beavers
        if (animalNew.getAnimalSpecies().equals("beavers")) {
          for (int k = 0; k < this.myTreatment.size(); k++) {
            if (myTreatment.get(k).getAnimalID() == animalNew.getAnimalID() && myTreatment.get(k).getTaskID() == 1) {
              z.add(animalNew.getAnimalNickName());

            }

          }

          if (!z.contains(animalNew.getAnimalNickName())) {
            this.numberOfBeavers += 1;
            this.beaverList.add(animalNew.getAnimalNickName());
          }
        }
        // Creating an arrayList of raccoons which contains the raccoon names, and the size of the array is the number of raccoons
        if (animalNew.getAnimalSpecies().equals("raccoons")) {
          for (int k = 0; k < this.myTreatment.size(); k++) {
            if (myTreatment.get(k).getAnimalID() == animalNew.getAnimalID() && myTreatment.get(k).getTaskID() == 1) {
              z.add(animalNew.getAnimalNickName());

            }

          }

          if (!z.contains(animalNew.getAnimalNickName())) {
            this.numberOfraccoons += 1;
            this.raccoonList.add(animalNew.getAnimalNickName());
          }
        }

        i++;
      }
      //converting the arrayList (AnimalList) to an array.
      this.animalList = myAnimals.toArray(new Animal[0]);
      myStmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }
  
  public void selectTasks() {
    //Creating an empty arrayList(myTasks) which task objects will be added to
    ArrayList < Task > myTasks = new ArrayList < > ();

    try {
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM tasks");

      int i = 0;
      while (results.next()) {
        //Adding task objects to our ArrayList(myTasks)
        myTasks.add(new Task(results.getInt("TaskID"), results.getString("Description"), results.getInt("Duration"),
          results.getInt("MaxWindow")));
        i++;

      }
      this.taskList = myTasks.toArray(new Task[0]);
      myStmt.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public void selectTreatments() {

    try {
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM treatments");
      // Adding treatment objects to our arraList (myTreatment)
      while (results.next()) {
        myTreatment.add(new Treatment(results.getInt("AnimalID"), results.getInt("TaskID"), results.getInt("StartHour")));
      }

      this.treatmentList = myTreatment.toArray(new Treatment[0]);

      myStmt.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }
  // This function will check if any of the animals has any medical treatment in the Treatment List, 
  //and then assigns it to a priority object. Then it adds these priority objects to the arrayList based on there StartHours
  // Then we add this ArrayList to our hasmap which has Starthour as the keys and the arrayList as the value
  public void setPriority() {

    for (int i = 0; i < taskList.length; i++) {
      for (int j = 0; j < treatmentList.length; j++) {

        // Checking if the animal has any medical treatments
        if (this.taskList[i].getTaskID() == this.treatmentList[j].getTaskID()) {
          Priority myPriority = new Priority(this.taskList[i].getTaskID(), this.treatmentList[j].getAnimalID(), this.taskList[i].getDuration(), taskList[i].getMaxWindow(), this.taskList[i].getDescription(), this.treatmentList[j].getStartHour());
          //Checking if the Starthour has any priority object or arrayList of prioritys, if it does then the object is appended to the arraList
          
          if (this.hoursMap.containsKey(treatmentList[j].getStartHour())) {
            ArrayList < Priority > set = this.hoursMap.get(treatmentList[j].getStartHour());
            set.add(myPriority);
            this.hoursMap.put(treatmentList[j].getStartHour(), set);
          } else {
            //Otherwise it creates the arrayList of the objects to add to the hashmap
            ArrayList < Priority > set1 = new ArrayList < Priority > ();
            set1.add(myPriority);
            this.hoursMap.put(treatmentList[j].getStartHour(), set1);
          }
        }
      }
    }
  }



}