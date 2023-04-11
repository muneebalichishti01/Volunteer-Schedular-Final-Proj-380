package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;

import java.util.HashMap;


import javax.swing.*;



public abstract class Populating extends  JFrame {
  protected ArrayList < Treatment > myTreatment = new ArrayList < > ();
  protected Connection dbConnect;
  protected ResultSet results;
  protected Animal[] animalList = null;
  protected Task[] taskList = null;
  protected Treatment[] treatmentList = null;
  protected int[][] hourList = new int[24][10];
  protected HashMap < Integer, ArrayList < Priority >> hoursMap = new HashMap < > ();
  protected HashMap < Integer, ArrayList < Priority >> newb = new HashMap < Integer, ArrayList < Priority >> ();
  protected HashMap < Integer, ArrayList < Priority >> verynewb = new HashMap < Integer, ArrayList < Priority >> ();
  protected HashMap < Integer, ArrayList < Priority >> veryverynewb = new HashMap < Integer, ArrayList < Priority >> (); //mohammad
  protected ArrayList < Integer > keystorm = new ArrayList < > ();
  protected ArrayList < Priority > valuestorm = new ArrayList < > ();
  protected ArrayList < String > foxList = new ArrayList < > ();
  protected ArrayList < String > coyoteList = new ArrayList < > ();
  protected ArrayList < String > procupineList = new ArrayList < > ();
  protected ArrayList < String > beaverList = new ArrayList < > ();
  protected ArrayList < String > raccoonList = new ArrayList < > ();
  protected ArrayList < Integer > volList = new ArrayList < > ();
  protected int numberOfFOx = 0;
  protected int numberOfCoyotes = 0;
  protected int numberOfprocupines = 0;
  protected int numberOfBeavers = 0;
  protected int numberOfraccoons = 0;
  protected int timearray[] = new int[24];

  public ArrayList < String > getfoxList() {
    return this.foxList;
  }
  public ArrayList < String > getcoyoteList() {
    return this.coyoteList;
  }
  public ArrayList < String > getprocupineList() {
    return this.procupineList;
  }
  public ArrayList < String > getbeaverList() {
    return this.beaverList;
  }
  public ArrayList < String > getraccoonsList() {
    return this.raccoonList;
  }
  public ArrayList < Priority > getvaluestorm() {
    return this.valuestorm;
  }
  public ArrayList < Integer > getvolList() {
    return this.volList;
  }
  public ArrayList < Integer > getkeystorm() {
    return this.keystorm;
  }
  public int[] gettimearray() {
    return this.timearray;
  }
  public int gettimearrayatindex(int x) {
    return this.timearray[x];

  }
  public void settimearray(int x, int duration) {
    int y = this.timearray[x];
    this.timearray[x] = y - duration;
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
    return this.numberOfFOx;
  }
  public int getnumberOfprocupines() {
    return this.numberOfprocupines;
  }
  public int getnumberOfBeavers() {
    return this.numberOfBeavers;
  }
  public int getnumberOfraccoons() {
    return this.numberOfraccoons;
  }
  public Treatment[] getTreatmentList() {
    return this.treatmentList;
  }
  public int[][] getHourList() {
    return this.hourList;
  }
  public HashMap < Integer, ArrayList < Priority >> getHashmap() {
    return this.hoursMap;
  }
  public HashMap < Integer, ArrayList < Priority > > getnewb() {
    return this.newb;
  }
  public HashMap < Integer, ArrayList < Priority > > getverynewb() {
    return this.verynewb;
  }
  public HashMap < Integer, ArrayList < Priority > > getveryverynewb() {
    return this.veryverynewb;
  }
  public Populating() {
    super("Schedule builder");
  

  }


  public void createConnection() {

    try {
      dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "root", "1234");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

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

        myAnimals.add(animalNew);
        if (animalNew.getAnimalSpecies().equals("fox")) {
          for (int k = 0; k < this.myTreatment.size(); k++) {
            if (myTreatment.get(k).getAnimalID() == animalNew.getAnimalID() && myTreatment.get(k).getTaskID() == 1) {
              z.add(animalNew.getAnimalNickName());

            }

          }

          if (!z.contains(animalNew.getAnimalNickName())) {
            this.numberOfFOx += 1;
            this.foxList.add(animalNew.getAnimalNickName());
          }
        }

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
        if (animalNew.getAnimalSpecies().equals("porcupine")) {
          for (int k = 0; k < this.myTreatment.size(); k++) {
            if (myTreatment.get(k).getAnimalID() == animalNew.getAnimalID() && myTreatment.get(k).getTaskID() == 1) {
              z.add(animalNew.getAnimalNickName());

            }

          }

          if (!z.contains(animalNew.getAnimalNickName())) {
            this.numberOfprocupines += 1;
            this.procupineList.add(animalNew.getAnimalNickName());
          }
        }
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

      this.animalList = myAnimals.toArray(new Animal[0]);
      myStmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }

  public void selectTasks() {
    ArrayList < Task > myTasks = new ArrayList < > ();

    try {
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM tasks");

      int i = 0;
      while (results.next()) {

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
      int i = 0;

      while (results.next()) {
        myTreatment.add(new Treatment(results.getInt("AnimalID"), results.getInt("TaskID"), results.getInt("StartHour")));
        i++;
      }

      this.treatmentList = myTreatment.toArray(new Treatment[0]);

      myStmt.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }

  public void setPriority() {

    for (int i = 0; i < taskList.length; i++) {
      for (int j = 0; j < treatmentList.length; j++) {

        if (this.taskList[i].getTaskID() == this.treatmentList[j].getTaskID()) {
          Priority myPriority = new Priority(this.taskList[i].getTaskID(), this.treatmentList[j].getAnimalID(), this.taskList[i].getDuration(), taskList[i].getMaxWindow(), this.taskList[i].getDescription(), this.treatmentList[j].getStartHour());

          if (this.hoursMap.containsKey(treatmentList[j].getStartHour())) {
            ArrayList < Priority > set = this.hoursMap.get(treatmentList[j].getStartHour());
            set.add(myPriority);
            this.hoursMap.put(treatmentList[j].getStartHour(), set);
          } else {

            ArrayList < Priority > set1 = new ArrayList < Priority > ();
            set1.add(myPriority);
            this.hoursMap.put(treatmentList[j].getStartHour(), set1);
          }
        }
      }
    }
  }



}