/**
*This class inherits the populating class. It has different methods.
* The purpose of this class is to sort our data and add the feeding and cleaning
*parts and print our final schedule.

* @author  Mohamad Jamal Hammoud: Version 1.0, 8.0, 9.0, 11.0
 * Qazi Ali: Version 2.0, 3.0, 10.0
 * Mirza Hassan Baig Version: 4.0,5.0,
 * Muneeb Ali Version: 6.0. 7.0
* @version 11.0
* @since   2023-04-10 
*/

package edu.ucalgary.oop;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;

public class  Scheduling extends Populating implements Print , ActionListener {
  
  private HashMap < Integer, ArrayList < Priority >> sortedTreatments = new HashMap < Integer, ArrayList < Priority >> (); //here
  private HashMap < Integer, ArrayList < Priority >> sortedTreatmentsFeedingCleaning = new HashMap < Integer, ArrayList < Priority >> (); //here
   //here
  private int timeArray[] = new int[24]; //here
  private ArrayList < Integer > volList = new ArrayList < > ();
  private ArrayList < Integer > keysToRm = new ArrayList < > ();
  private ArrayList < Priority > valuesToRm = new ArrayList < > ();

  public ArrayList < Priority > getValuesToRm() {
    return this.valuesToRm;
  }
  public ArrayList < Integer > getKeysToRm() { //here
    return this.keysToRm;
  }
  public HashMap < Integer, ArrayList < Priority > > getsortedTreatments() {
    return this.sortedTreatments;
  }
  public HashMap < Integer, ArrayList < Priority > > getsortedTreatmentsFeedingCleaning() {
    return this.sortedTreatmentsFeedingCleaning;
  }
  // Setters
  public ArrayList < Integer > getvolList() {
    return this.volList;
  }
  public int[] gettimearray() {
    return this.timeArray;
  }
  public int gettimearrayatindex(int x) {
    return this.timeArray[x];

  }
  public void settimearray(int x, int duration) {
    int y = this.timeArray[x];
    this.timeArray[x] = y - duration;
  }
    //Constructor which makes a call to the parent class (populating) using super
    public Scheduling (){
        super();
        createConnection();
        selectTreatments();
        selectAnimals();
        selectTasks();
        setPriority();
        sortHoursMap();
        copying();
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // This method is used to invoke the gui in our program
    public void setupGUI() {
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

      // This method makes calls to our other methods needed to implement feeding, cleaning etc.
      public void actionPerformed(ActionEvent event) {
        SchedulingTreatment(); 
        feeding();
        cleaning();

        JOptionPane.showMessageDialog(this, "You are all good");
        print();
        System.exit(0);
      }

      // Creating a new hashmap which has an empty arrayList as the value for each key
      // that has a null value 
      public void copying() {
        for (int i = 0; i < 24; i++) {
          sortedTreatmentsFeedingCleaning.put(i, new ArrayList < Priority > ());
        }

        for (int key: sortedTreatmentsFeedingCleaning.keySet()) {
          if (sortedTreatments.containsKey(key)) {
            sortedTreatmentsFeedingCleaning.put(key, sortedTreatments.get(key));
          }
        }
      }

      //This method orders the medical treatments for each hour depending on maxWindow 
      //It also checks if there is enough time to do the existing tasks,
      //if not then it tries to shift them, if there is still conflict then a backup volunteer is called
      public void SchedulingTreatment() {
        boolean adjustSchedule = false;
        this.sortedTreatmentsFeedingCleaning = new HashMap < > (sortedTreatments);
    
        for (int i = 0; i < 24; i++) {
          this.timeArray[i] = 60;
        }

        for (Entry < Integer, ArrayList < Priority >> entry: this.sortedTreatments.entrySet()) {
          // looping through our hashmap 
          Integer key = entry.getKey();
          ArrayList < Priority > value = entry.getValue();

          for (Priority item: value) { 
            if (item.getDuration() <= timeArray[key]) { //checking if there is enough time left 
              int y = this.timeArray[key]; 
              this.timeArray[key] = y - item.getDuration();// Adjusting the remaining time
            } else if (item.getMaxWindow() == 1) {
                int result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?",
                "Title", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                  JOptionPane.showMessageDialog(this, "Calling back up volunteer");
      
                } else {
                  while (result == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "Please select yes or come up with a new schedule");
                    result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?", 
                    "Title", JOptionPane.YES_NO_OPTION);
                  }
                } 
                //Adding extra time to the hour that backup volunteer was called for
                timeArray[key] += 60;
                this.volList.add(key);
            } else {
              keysToRm.add(key);
              valuesToRm.add(item);
              adjustSchedule = true;
            }
          }
        }
    
        if (adjustSchedule == true) {
          JOptionPane.showMessageDialog(this, "Making adjustments to your schedule");
          scheduleAdjust();
    
        } else {
          JOptionPane.showMessageDialog(this, "No need to make changes to schedule");
        }
      }

      // This method will adjust the hashmap so that it reflects the changed made to the schedule
      // It also checks for maxWindows that are bigger than 1 and tries to shift tasks depending on
      //MaxWindow time. It also prints some messages through our gui to tell the user
      // if any change was made to the schedule
      public void scheduleAdjust() { 
    
        for (int i = 0; i < this.keysToRm.size(); i++) {
          ArrayList < Priority > valueForKeyB = this.sortedTreatments.get(keysToRm.get(i));
          valueForKeyB.remove(valuesToRm.get(i));
          this.sortedTreatmentsFeedingCleaning.put(keysToRm.get(i), valueForKeyB);
          //value is not added yet
        }

        int max = 0;
        int key1 = 0;
        Priority item = new Priority();

        for (int i = 0; i < this.valuesToRm.size(); i++) {
          item = this.valuesToRm.get(i);
          if (item.getMaxWindow() > 1) {
            max = item.getMaxWindow();
            int j = 1;
            key1 = keysToRm.get(i);
    
            while (j < max) {
              if (key1 + j > 23) {
                System.out.println("Day finished cant add");
                break;
              }
    
              if (item.getDuration() <= this.timeArray[key1 + j]) {
                int y = this.timeArray[key1 + j];
                this.timeArray[key1 + j] = y - item.getDuration();
                ArrayList < Priority > valueForKeyB = this.sortedTreatmentsFeedingCleaning.get(key1 + j);

                if (valueForKeyB == null) {
                  valueForKeyB = new ArrayList < Priority > ();
                }

                valueForKeyB.add(item);
                this.sortedTreatmentsFeedingCleaning.put(key1 + j, valueForKeyB);
                //value added succesfully
                //we should add break here to prevent further adding into other hours after this hour
                break;
              } else if (item.getDuration() >= this.timeArray[key1 + j] && j == max - 1) {
                int z = key1 + j;
                int result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?",
               "Title", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                  JOptionPane.showMessageDialog(this, "Calling back up volunteer");
      
                } else {
                  while (result == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "Please select yes or come up with a new schedule");
                    result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?", 
                    "Title", JOptionPane.YES_NO_OPTION);
                  }
                } 
              
                this.timeArray[key1 + j] += 60;
                this.volList.add(key1 + j);
                this.timeArray[key1 + j] -= item.getDuration();

                if (item.getDuration() <= this.timeArray[key1 + j]) {
                  ArrayList < Priority > valueForKeyB = this.sortedTreatmentsFeedingCleaning.get(key1 + j);
                  if (valueForKeyB == null) {
                    valueForKeyB = new ArrayList < Priority > ();
                  }

                  valueForKeyB.add(item);
                  this.sortedTreatmentsFeedingCleaning.put(key1 + j, valueForKeyB);
                }
              } else {
                JOptionPane.showMessageDialog(this,"Please change schedule");
              }
              j++;
            }
          }
        }
        sortTreatments(); //this will sort veysortedTreatments
      }

      // This method will add the feeding task to our schedule
      // This method also shifts the feeding tasks or calls the backup volunteer if needed
      public void feeding() {
        if (this.foxList.size() != 0) {
          boolean done = false;
          //calculating food prep plus feeding time for foxes
          int feedingtime = 5 + (this.foxList.size() * 5);  
          //appending name of foxes to our decription
          String desc = "feeding";

          for (int i = 0; i < this.foxList.size(); i++) {
            desc = desc + foxList.get(i) + " , ";
          }

          Priority newpro = new Priority(this.foxList, feedingtime, 3, desc);
          
          for (int i = 0; i < 3; i++) {
            ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            //checking if there is remaining time, then adding the remaining task and
            //adjusting the remaining time again
            if (feedingtime <= this.timeArray[i]) {
              u.add(newpro);
              this.sortedTreatmentsFeedingCleaning.put(i, u);
              this.timeArray[i] = this.timeArray[i] - feedingtime;
              done = true;
              break;
            }
          }
          // if we are not able to feed all foxes at once,
          // then it splits them into groups depending on remaining time in each hour
          if (done == false) {
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;

            for (int i = 0; i < 3; i++) {
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();

              if (this.timeArray[i] >= 10) {
                int timeleft = this.timeArray[i];
                timeleft = timeleft - 5;
                int z = timeleft / 5;
                s += z;
    
                if (z > foxList.size() - alreadyfed) {
                  z = foxList.size() - alreadyfed;
                }
                //checking if all foxes were fed
                if (alreadyfed == foxList.size()) {
                  break;
                }

                for (int l = alreadyfed; l < alreadyfed + z; l++) {
                  first.add(foxList.get(l));
                }

                alreadyfed += z;
                needtofeed = foxList.size() - alreadyfed;
    
                for (int k = 0; k < first.size(); k++) {
                  desc1 = desc1 + foxList.get(k) + " , ";
                }

                Priority newpro1 = new Priority(first, (z * 5) + 5, 3, desc1);
                ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);

                if (u == null) {
                  u = new ArrayList < Priority > ();
                }

                u.add(newpro1);
                this.sortedTreatmentsFeedingCleaning.put(i, u);
                this.timeArray[i] = this.timeArray[i] - (z * 5) - 5;

                if (alreadyfed <= foxList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra foxes");
                  break;
                }
              }
            }
          }
        }

        //checking if we have any raccoons in the database to feed
        if (this.raccoonList.size() != 0) {
          boolean done = false;
          //calculating the feeding time for raccoons 
          int feedingtime = (this.raccoonList.size() * 5);
          String desc = "feeding";

          for (int i = 0; i < this.raccoonList.size(); i++) {
            desc = desc + raccoonList.get(i) + " , ";
          }

          Priority newpro = new Priority(this.raccoonList, feedingtime, 3, desc);
    
          for (int i = 0; i < 3; i++) {
            ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            //checking the remaining time and adjusting the new remaining time
            if (feedingtime < this.timeArray[i]) {
              u.add(newpro);
              this.sortedTreatmentsFeedingCleaning.put(i, u);
              this.timeArray[i] = this.timeArray[i] - feedingtime;
              done = true;
              break;
            }
          }
          // if we are not able to feed all raccoons at once,
          // then it splits them into groups depending on remaining time in each hour
          if (done == false) {
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;

            for (int i = 0; i < 3; i++) {
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();

              if (this.timeArray[i] >= 5) {
                int timeleft = this.timeArray[i];
                //calculating how many raccoons we can feed in that hour
                int z = timeleft / 5;
                s += z;
    
                if (z > raccoonList.size() - alreadyfed) {
                  z = raccoonList.size() - alreadyfed;
                }
    
                if (alreadyfed == raccoonList.size()) {
                  break;
                }

                for (int l = alreadyfed; l < alreadyfed + z; l++) {
                  first.add(raccoonList.get(l));
                }

                alreadyfed += z;
                needtofeed = raccoonList.size() - alreadyfed;

                for (int k = 0; k < first.size(); k++) {
                  desc1 = desc1 + raccoonList.get(k) + " , ";
    
                }

                Priority newpro1 = new Priority(first, (z * 5), 3, desc1);
                ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);

                if (u == null) {
                  u = new ArrayList < Priority > ();
                }

                u.add(newpro1);
                this.sortedTreatmentsFeedingCleaning.put(i, u);
                this.timeArray[i] = this.timeArray[i] - (z * 5);

                if (alreadyfed <= raccoonList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra raccons");
                  break;
                }
              }
            }
          }
        }
        //checking if we have any beavers in the database to feed
        if (this.beaverList.size() != 0) {
          //calulating the feeding time for beavers
          boolean done = false;
          int feedingtime = (this.beaverList.size() * 5);
          String desc = "feeding";

          for (int i = 0; i < this.beaverList.size(); i++) {
            desc = desc + beaverList.get(i) + " , ";
          }
          // making a new feeding task
          Priority newpro = new Priority(this.beaverList, feedingtime, 3, desc);
    
          for (int i = 7; i < 10; i++) {
            ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);

            if (u == null) {
              u = new ArrayList < Priority > ();
            }

            if (feedingtime < this.timeArray[i]) {
              u.add(newpro);
              this.sortedTreatmentsFeedingCleaning.put(i, u);
              this.timeArray[i] = this.timeArray[i] - feedingtime;
              done = true;
              break;
            }
          }
          // if we are not able to feed all raccoons at once,
          // then it splits them into groups depending on remaining time in each hour
          if (done == false) {
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;

            for (int i = 7; i < 10; i++) {
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();
              if (this.timeArray[i] >= 5) {
    
                int timeleft = this.timeArray[i];
                //calculating how many animals can be fed in that hour 
                //and splitting task based on the result
                int z = timeleft / 5;
                s += z;
    
                if (z > beaverList.size() - alreadyfed) {
                  z = beaverList.size() - alreadyfed;
                }
    
                if (alreadyfed == beaverList.size()) {
                  break;
                }
    
            
    
                for (int l = alreadyfed; l < alreadyfed + z; l++) {
                  first.add(beaverList.get(l));
                }
                alreadyfed += z;
                needtofeed = beaverList.size() - alreadyfed;
    

                for (int k = 0; k < first.size(); k++) {
                  desc1 = desc1 + beaverList.get(k) + " , ";
    
                }
                Priority newpro1 = new Priority(first, (z * 5), 3, desc1);
                ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);
                if (u == null) {
                  u = new ArrayList < Priority > ();
                }
                u.add(newpro1);
                this.sortedTreatmentsFeedingCleaning.put(i, u);
                //subtracting the time used for the task from our time list
                this.timeArray[i] = this.timeArray[i] - (z * 5);
                if (alreadyfed <= raccoonList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra beavers");
    
                  break;
                }
    
              }
    
            }
          }
    
        }
        //checking if we have any Coyote in the database to feed
        if (this.coyoteList.size() != 0) {
          boolean done = false;
          int feedingtime = 10 + (this.coyoteList.size() * 5);
          String desc = "feeding";
          for (int i = 0; i < this.coyoteList.size(); i++) {
            desc = desc + " , " + coyoteList.get(i);
    
          }
          Priority newpro = new Priority(this.coyoteList, feedingtime, 3, desc);
    
          for (int i = 7; i < 10; i++) {
            ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            if (feedingtime < this.timeArray[i]) {
              u.add(newpro);
              this.sortedTreatmentsFeedingCleaning.put(i, u);
              this.timeArray[i] = this.timeArray[i] - feedingtime;
              done = true;
              break;
            }
          }
          // if we are not able to feed all Coyote at once,
          // then it splits them into groups depending on remaining time in each hour
          if (done == false) {
    
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;
            for (int i = 7; i < 10; i++) {
    
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();
              if (this.timeArray[i] >= 10) {
    
                int timeleft = this.timeArray[i];
                timeleft = timeleft - 10;
                int z = timeleft / 5;
                s += z;
    
                if (z > coyoteList.size() - alreadyfed) {
                  z = coyoteList.size() - alreadyfed;
                }
    
                if (alreadyfed == coyoteList.size()) {
                  break;
                }
    
             
    
                for (int l = alreadyfed; l < alreadyfed + z; l++) {
                  first.add(coyoteList.get(l));
                }
                alreadyfed += z;
                needtofeed = coyoteList.size() - alreadyfed;
    
         
                for (int k = 0; k < first.size(); k++) {
                  desc1 = desc1 + coyoteList.get(k) + " , ";
    
                }
                Priority newpro1 = new Priority(first, (z * 5) + 10, 3, desc1);
                ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);
                if (u == null) {
                  u = new ArrayList < Priority > ();
                }
                u.add(newpro1);
                this.sortedTreatmentsFeedingCleaning.put(i, u);
                this.timeArray[i] = this.timeArray[i] - (z * 5) - 10;
                if (alreadyfed <= coyoteList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra coyotes");
    
                  break;
                }
    
              }
    
            }
          }
        }
        //checking if we have any Porcupines in the database to feed
        if (this.procupineList.size() != 0) {
          boolean done = false;
          int feedingtime = 10 + (this.procupineList.size() * 5);
          String desc = "feeding";
          for (int i = 0; i < this.procupineList.size(); i++) {
            desc = desc + " , " + procupineList.get(i);
    
          }
          Priority newpro = new Priority(this.procupineList, feedingtime, 3, desc);
    
          for (int i = 7; i < 10; i++) {
            ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            if (feedingtime < this.timeArray[i]) {
              u.add(newpro);
              this.sortedTreatmentsFeedingCleaning.put(i, u);
              this.timeArray[i] = this.timeArray[i] - feedingtime;
              done = true;
              break;
            }
          }
          // if we are not able to feed all porcupine at once,
          // then it splits them into groups depending on remaining time in each hour
          if (done == false) {
    
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;
            for (int i = 7; i < 10; i++) {
    
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();
              if (this.timeArray[i] >= 5) {
    
                int timeleft = this.timeArray[i];
    
                int z = timeleft / 5;
                s += z;
    
                if (z > coyoteList.size() - alreadyfed) {
                  z = procupineList.size() - alreadyfed;
                }
    
                if (alreadyfed == procupineList.size()) {
                  break;
                }
    
    
                for (int l = alreadyfed; l < alreadyfed + z; l++) {
                  first.add(procupineList.get(l));
                }
                alreadyfed += z;
                needtofeed = procupineList.size() - alreadyfed;
    
            
                for (int k = 0; k < first.size(); k++) {
                  desc1 = desc1 + procupineList.get(k) + " , ";
    
                }
                Priority newpro1 = new Priority(first, (z * 5), 3, desc1);
                ArrayList < Priority > u = sortedTreatmentsFeedingCleaning.get(i);
                if (u == null) {
                  u = new ArrayList < Priority > ();
                }
                u.add(newpro1);
                this.sortedTreatmentsFeedingCleaning.put(i, u);
                this.timeArray[i] = this.timeArray[i] - (z * 5);
                if (alreadyfed <= procupineList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra procipines");
    
                  break;
                }
    
              }
    
            }
          }
        }
    
      }
    
      public void sortHoursMap() {
        // This method is used to sort our hashmap based on maxWindow and put it in SortedTreatmentsHashmap
        for (Entry < Integer, ArrayList < Priority >> entry: hoursMap.entrySet()) {
          Integer key = entry.getKey();
          ArrayList < Priority > value = entry.getValue();
    
          Priority[] myArray = value.toArray(new Priority[value.size()]);
    
          Arrays.sort(myArray);
          ArrayList < Priority > list = new ArrayList < > (Arrays.asList(myArray));
    
          this.sortedTreatments.put(key, list);
        }
      }
    
      public void sortTreatments() { //sorts sortedTreatmentsFeedingCleaning after adding values and put it in sortedTreatmentsFeedingCleaning hashmap
        for (Entry < Integer, ArrayList < Priority >> entry: sortedTreatmentsFeedingCleaning.entrySet()) {
          Integer key = entry.getKey();
          ArrayList < Priority > value = entry.getValue();
          Priority[] myArray = value.toArray(new Priority[value.size()]);
          Arrays.sort(myArray);
          ArrayList < Priority > list = new ArrayList < > (Arrays.asList(myArray));
          this.sortedTreatmentsFeedingCleaning.put(key, list);
        }
    
      }
      public void cleaning() {
        ArrayList < Animal > animaltoclean = new ArrayList < > ();
    
        ArrayList < Animal > procupinetoclean = new ArrayList < > ();
    
        ArrayList < String > k = new ArrayList < > ();
        for (int i = 0; i < this.animalList.length; i++) {
          //making two arrayList one for porcupines, and one for other animals
          //because cleaning time for porcupines is different
          if (animalList[i].getAnimalSpecies().equals("porcupine")) {
            procupinetoclean.add(animalList[i]);
    
          } else {
            animaltoclean.add(animalList[i]);
       
          }
    
        }
    
        boolean all = false;
        int num = 0;
        int alreadyclean = 0;
        for (int i = 0; i < 24; i++) {
          String desc = "cleaning ";
          //checking if there is time left in any hour
          if (this.timeArray[i] > 0) {
            int x = this.timeArray[i] / 5;
            if (x > animaltoclean.size() - alreadyclean) {
              x = animaltoclean.size() - alreadyclean;
              all = true;
    
            }
            //Adding names of animals that got their cage cleaned to task description
            for (int j = alreadyclean; j < x + alreadyclean; j++) {
              k.add(animaltoclean.get(j).getAnimalNickName());
              desc = desc + " ," + animaltoclean.get(j).getAnimalNickName();
            }
            
            Priority kk = new Priority(k, x * 5, 24, desc);
            ArrayList < Priority > h = this.sortedTreatmentsFeedingCleaning.get(i);
            if (h == null) {
              h = new ArrayList < Priority > ();
            }
            alreadyclean += x;
            h.add(kk);
            this.sortedTreatmentsFeedingCleaning.put(i, h);
            timeArray[i] -= (x * 5);
            //checking if we cleaned all our animal cages
            if (alreadyclean == animaltoclean.size()) {
              break;
            }
            //checking if there is need for backup volunteering in cleaning
            if (i == 23 && alreadyclean != x) {
    
              for (int l = 0; l < 24; l++) {
                if (!volList.contains(l)) {
                  int result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?",
               "Title", JOptionPane.YES_NO_OPTION);
              if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Calling back up volunteer");
    
              } else {
                while (result == JOptionPane.NO_OPTION) {
                  JOptionPane.showMessageDialog(this, "Please select yes or come up with a new schedule");
                  result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?", 
                  "Title", JOptionPane.YES_NO_OPTION);
                }
              } 
              //Adding extra time to the hour that backup volunteer was called for
                  this.timeArray[l] += 60;
                  volList.add(l);
                  
                  JOptionPane.showMessageDialog(this, "volunteer added to hour" + l);
                  break;
                }
                k = new ArrayList < > ();
                desc = "cleaning";
    
                for (int a = alreadyclean; a < animaltoclean.size(); a++) {
                  k.add(animaltoclean.get(a).getAnimalNickName());
                  desc = desc + " ," + animaltoclean.get(a).getAnimalNickName();
                }
                kk = new Priority(k, x * 5, 24, desc);
                h = this.sortedTreatmentsFeedingCleaning.get(l);
                if (h == null) {
                  h = new ArrayList < Priority > ();
                }
    
                h.add(kk);
                this.sortedTreatmentsFeedingCleaning.put(l, h);
                // subtracting the time spent on cleaning from the hour
                timeArray[l] -= (animaltoclean.size() - alreadyclean) * 5;
                all = true;
    
              }
            }
          }
          if (all) {
            break;
          }
    
        }
        boolean all1 = false;
    
        int alreadyclean1 = 0;
        for (int i = 0; i < 24; i++) {
          String desc = "cleaning ";
    
          if (this.timeArray[i] > 0) {
            //calculating how many porcupines we can feed in that hour.
            int x = this.timeArray[i] / 10;
            if (x > procupinetoclean.size() - alreadyclean1) {
              x = procupinetoclean.size() - alreadyclean1;
              all1 = true;
    
            }
            for (int j = alreadyclean1; j < x + alreadyclean1; j++) {
              k.add(procupinetoclean.get(j).getAnimalNickName());
              desc = desc + " ," + procupinetoclean.get(j).getAnimalNickName();
            }
    
            Priority kk = new Priority(k, x * 10, 24, desc);
            ArrayList < Priority > h = this.sortedTreatmentsFeedingCleaning.get(i);
            if (h == null) {
              h = new ArrayList < Priority > ();
            }
            //keeping track of number of cleaned cages for procupines.
            alreadyclean1 += x;
            h.add(kk);
            this.sortedTreatmentsFeedingCleaning.put(i, h);
            timeArray[i] -= (x * 10);
            if (alreadyclean1 == procupinetoclean.size()) {
              break;
            }
            //checking if we need a backup volunteer for cleaning task. 
            //adding volunteer and doing time calculations if needed.
            if (i == 23 && alreadyclean1 != x) {
             
              for (int l = 0; l < 24; l++) {
                if (!volList.contains(l)) {
                  this.timeArray[l] += 60;
                  volList.add(l);
                }
                k = new ArrayList < > ();
                desc = "cleaning";
    
                for (int a = alreadyclean1; a < procupinetoclean.size(); a++) {
                  k.add(procupinetoclean.get(a).getAnimalNickName());
                  desc = desc + " ," + procupinetoclean.get(a).getAnimalNickName();
                }
                kk = new Priority(k, x * 10, 24, desc);
                h = this.sortedTreatmentsFeedingCleaning.get(l);
                if (h == null) {
                  h = new ArrayList < Priority > ();
                }
    
                h.add(kk);
                //reasigning that hour to its tasks with cleaning tasks added.
                this.sortedTreatmentsFeedingCleaning.put(l, h);
                timeArray[l] -= (procupinetoclean.size() - alreadyclean1) * 10;
                all1 = true;
    
              }
            }
          }
          if (all1) {
            break;
          }
    
        }
    
      }
      //This method is used to print our final schedule.
     //This method is used to print our final schedule.
     @Override
     public void print() {
      String mySchedule=""; 
       for (Entry < Integer, ArrayList < Priority >> entry: getsortedTreatmentsFeedingCleaning().entrySet()) {
         boolean volunteer = false;
         Integer key1 = entry.getKey();
         ArrayList < Priority > value = entry.getValue();
         //checking if we neeeded a backup volunteer for that hour or not.
         if (volList.contains(key1)){
           volunteer = true;
         }
         if (value != null) {
           System.out.print("hour: [" + key1 + ".00]");
           mySchedule+= "hour: [" + key1 + ".00]";
           for (Priority item: value) {
            
   
             
             //Printing the volunteer name if called.
             if (volunteer== true){

               System.out.println("   [backup vol]");
               mySchedule+= "   [backup vol]";
              mySchedule+= "\n";
             }
             else{
               System.out.println();
                mySchedule+= "\n";
             }

            
             System.out.println("    description: " + item.getDescription() + "    ");
             System.out.println("     duration: " + item.getDuration() + "    ");
             mySchedule+= "    description: " + item.getDescription() + "    ";
             mySchedule+= "\n";
            mySchedule+= "     duration: " + item.getDuration() + "    ";
            mySchedule+= "\n";
           
             
   
           }
         }
         System.out.println("--------------------------------------------------");
         mySchedule+= "--------------------------------------------------";
          mySchedule+= "\n";
      }
      String filePath = "Schedule.txt";
   

        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(mySchedule);
            fileWriter.close();
            System.out.println("Schedule has been added to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    
}
