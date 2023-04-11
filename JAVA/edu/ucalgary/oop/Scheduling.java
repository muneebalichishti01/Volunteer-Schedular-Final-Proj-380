package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;


public class  Scheduling extends Populating implements Print , ActionListener {


    public Scheduling (){
        super();
        createConnection();
        selectTreatments();
        selectAnimals();
        selectTasks();
        setPriority();
        newa();
        copying();
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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
      public void actionPerformed(ActionEvent event) {
    
        SchedulingTreatment(); //fun1
        feeding();
        cleaning();
        JOptionPane.showMessageDialog(this, "You are all good");
        print();
        System.exit(0);
    
      }
    public void copying() {
        for (int i = 0; i < 24; i++) {
          verynewb.put(i, new ArrayList < Priority > ());
        }
        for (int key: verynewb.keySet()) {
          if (newb.containsKey(key)) {
            verynewb.put(key, newb.get(key));
          }
        }
      }
      public void SchedulingTreatment() {
        boolean adjustSchedule = false;
        this.verynewb = new HashMap < > (newb);
    
        for (int i = 0; i < 24; i++) {
    
          this.timearray[i] = 60;
    
        }
        for (Entry < Integer, ArrayList < Priority >> entry: this.newb.entrySet()) {
          Integer key = entry.getKey();
          ArrayList < Priority > value = entry.getValue();
          for (Priority item: value) {
            if (item.getduration() <= timearray[key]) {
              int y = this.timearray[key];
              this.timearray[key] = y - item.getduration();
            } else if (item.getMaxWindow() == 1) {
              int result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?", "Title", JOptionPane.YES_NO_OPTION);
              if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Calling back up volunteer");
    
              } else {
                while (result == JOptionPane.NO_OPTION) {
                  JOptionPane.showMessageDialog(this, "Please select yes or come up with a new schedule");
                  result = JOptionPane.showConfirmDialog(this, "Should I call for backup volunteer?", "Title", JOptionPane.YES_NO_OPTION);
                }
              }
              timearray[key] += 60;
              this.volList.add(key);
            } else {
              keystorm.add(key);
              valuestorm.add(item);
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
    
      public void scheduleAdjust() { //fun2
    
        for (int i = 0; i < this.keystorm.size(); i++) {
          ArrayList < Priority > valueForKeyB = this.newb.get(keystorm.get(i));
          valueForKeyB.remove(valuestorm.get(i));
          this.verynewb.put(keystorm.get(i), valueForKeyB);
          //value is not added yet
        }
        int max = 0;
    
        int key1 = 0;
        Priority item = new Priority();
        for (int i = 0; i < this.valuestorm.size(); i++) {
    
          item = this.valuestorm.get(i);
    
          if (item.getMaxWindow() > 1) {
            max = item.getMaxWindow();
            int j = 1;
            key1 = keystorm.get(i);
    
            while (j < max) {
    
              if (key1 + j > 23) {
                System.out.println("Day finished cant add");
    
                break;
              }
    
              if (item.getduration() <= this.timearray[key1 + j]) {
    
                int y = this.timearray[key1 + j];
                this.timearray[key1 + j] = y - item.getduration();
                ArrayList < Priority > valueForKeyB = this.verynewb.get(key1 + j);
                if (valueForKeyB == null) {
                  valueForKeyB = new ArrayList < Priority > ();
                }
                valueForKeyB.add(item);
                this.verynewb.put(key1 + j, valueForKeyB);
                //value added succesfully
                //we should add break here to prevent further adding into other hours after this hour
                break;
              } else if (item.getduration() >= this.timearray[key1 + j] && j == max - 1) {
                int z = key1 + j;
                JOptionPane.showMessageDialog(this, "adding vol to hour" + z);
                this.timearray[key1 + j] += 60;
                this.volList.add(key1 + j);
                this.timearray[key1 + j] -= item.getduration();
                if (item.getduration() <= this.timearray[key1 + j]) {
                  ArrayList < Priority > valueForKeyB = this.verynewb.get(key1 + j);
                  if (valueForKeyB == null) {
                    valueForKeyB = new ArrayList < Priority > ();
                  }
                  valueForKeyB.add(item);
                  this.verynewb.put(key1 + j, valueForKeyB);
    
                }
              } else {
                System.out.print("please change schedule");
              }
              j++;
    
            }
    
          }
        }
    
        sort(); //this will sort veynewb
      }
      public void feeding() {
   
        if (this.foxList.size() != 0) {
          boolean done = false;
          int feedingtime = 5 + (this.foxList.size() * 5);
          String desc = "feeding";
          for (int i = 0; i < this.foxList.size(); i++) {
            desc = desc + foxList.get(i) + " , ";
    
          }
          Priority newpro = new Priority(this.foxList, feedingtime, 3, desc);
    
          for (int i = 0; i < 3; i++) {
            ArrayList < Priority > u = verynewb.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            if (feedingtime <= this.timearray[i]) {
              u.add(newpro);
              this.verynewb.put(i, u);
              this.timearray[i] = this.timearray[i] - feedingtime;
              done = true;
              break;
            }
          }
          if (done == false) {
    
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;
            for (int i = 0; i < 3; i++) {
    
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();
              if (this.timearray[i] >= 10) {
    
                int timeleft = this.timearray[i];
                timeleft = timeleft - 5;
                int z = timeleft / 5;
                s += z;
    
                if (z > foxList.size() - alreadyfed) {
                  z = foxList.size() - alreadyfed;
                }
    
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
                ArrayList < Priority > u = verynewb.get(i);
                if (u == null) {
                  u = new ArrayList < Priority > ();
                }
                u.add(newpro1);
                this.verynewb.put(i, u);
                this.timearray[i] = this.timearray[i] - (z * 5) - 5;
                if (alreadyfed <= foxList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra foxes");
    
                  break;
                }
    
              }
    
            }
          }
        }
        if (this.raccoonList.size() != 0) {
          boolean done = false;
          int feedingtime = (this.raccoonList.size() * 5);
          String desc = "feeding";
          for (int i = 0; i < this.raccoonList.size(); i++) {
            desc = desc + raccoonList.get(i) + " , ";
    
          }
          Priority newpro = new Priority(this.raccoonList, feedingtime, 3, desc);
    
          for (int i = 0; i < 3; i++) {
            ArrayList < Priority > u = verynewb.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            if (feedingtime < this.timearray[i]) {
              u.add(newpro);
              this.verynewb.put(i, u);
              this.timearray[i] = this.timearray[i] - feedingtime;
              done = true;
              break;
            }
          }
          if (done == false) {
    
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;
            for (int i = 0; i < 3; i++) {
    
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();
              if (this.timearray[i] >= 5) {
    
                int timeleft = this.timearray[i];
    
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
                ArrayList < Priority > u = verynewb.get(i);
                if (u == null) {
                  u = new ArrayList < Priority > ();
                }
                u.add(newpro1);
                this.verynewb.put(i, u);
                this.timearray[i] = this.timearray[i] - (z * 5);
                if (alreadyfed <= raccoonList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra raccons");
    
                  break;
                }
    
              }
    
            }
          }
        }
        if (this.beaverList.size() != 0) {
          boolean done = false;
          int feedingtime = (this.beaverList.size() * 5);
          String desc = "feeding";
          for (int i = 0; i < this.beaverList.size(); i++) {
            desc = desc + beaverList.get(i) + " , ";
    
          }
          Priority newpro = new Priority(this.beaverList, feedingtime, 3, desc);
    
          for (int i = 7; i < 10; i++) {
            ArrayList < Priority > u = verynewb.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            if (feedingtime < this.timearray[i]) {
              u.add(newpro);
              this.verynewb.put(i, u);
              this.timearray[i] = this.timearray[i] - feedingtime;
              done = true;
              break;
            }
          }
          if (done == false) {
    
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;
            for (int i = 7; i < 10; i++) {
    
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();
              if (this.timearray[i] >= 5) {
    
                int timeleft = this.timearray[i];
    
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
                ArrayList < Priority > u = verynewb.get(i);
                if (u == null) {
                  u = new ArrayList < Priority > ();
                }
                u.add(newpro1);
                this.verynewb.put(i, u);
                this.timearray[i] = this.timearray[i] - (z * 5);
                if (alreadyfed <= raccoonList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra beavers");
    
                  break;
                }
    
              }
    
            }
          }
    
        }
    
        if (this.coyoteList.size() != 0) {
          boolean done = false;
          int feedingtime = 10 + (this.coyoteList.size() * 5);
          String desc = "feeding";
          for (int i = 0; i < this.coyoteList.size(); i++) {
            desc = desc + " , " + coyoteList.get(i);
    
          }
          Priority newpro = new Priority(this.coyoteList, feedingtime, 3, desc);
    
          for (int i = 7; i < 10; i++) {
            ArrayList < Priority > u = verynewb.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            if (feedingtime < this.timearray[i]) {
              u.add(newpro);
              this.verynewb.put(i, u);
              this.timearray[i] = this.timearray[i] - feedingtime;
              done = true;
              break;
            }
          }
          if (done == false) {
    
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;
            for (int i = 7; i < 10; i++) {
    
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();
              if (this.timearray[i] >= 10) {
    
                int timeleft = this.timearray[i];
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
                ArrayList < Priority > u = verynewb.get(i);
                if (u == null) {
                  u = new ArrayList < Priority > ();
                }
                u.add(newpro1);
                this.verynewb.put(i, u);
                this.timearray[i] = this.timearray[i] - (z * 5) - 10;
                if (alreadyfed <= coyoteList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra coyotes");
    
                  break;
                }
    
              }
    
            }
          }
        }
        if (this.procupineList.size() != 0) {
          boolean done = false;
          int feedingtime = 10 + (this.procupineList.size() * 5);
          String desc = "feeding";
          for (int i = 0; i < this.procupineList.size(); i++) {
            desc = desc + " , " + procupineList.get(i);
    
          }
          Priority newpro = new Priority(this.procupineList, feedingtime, 3, desc);
    
          for (int i = 7; i < 10; i++) {
            ArrayList < Priority > u = verynewb.get(i);
            if (u == null) {
              u = new ArrayList < Priority > ();
            }
            if (feedingtime < this.timearray[i]) {
              u.add(newpro);
              this.verynewb.put(i, u);
              this.timearray[i] = this.timearray[i] - feedingtime;
              done = true;
              break;
            }
          }
          if (done == false) {
    
            int alreadyfed = 0;
            int needtofeed = 0;
            int s = 0;
            for (int i = 7; i < 10; i++) {
    
              String desc1 = "feeding";
              ArrayList < String > first = new ArrayList < String > ();
              if (this.timearray[i] >= 5) {
    
                int timeleft = this.timearray[i];
    
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
                ArrayList < Priority > u = verynewb.get(i);
                if (u == null) {
                  u = new ArrayList < Priority > ();
                }
                u.add(newpro1);
                this.verynewb.put(i, u);
                this.timearray[i] = this.timearray[i] - (z * 5);
                if (alreadyfed <= procupineList.size()) {
                  JOptionPane.showMessageDialog(this, "please change schedule or hit okay to discard feeding for extra procipines");
    
                  break;
                }
    
              }
    
            }
          }
        }
    
      }
    
      public void newa() {
        for (Entry < Integer, ArrayList < Priority >> entry: hoursMap.entrySet()) {
          Integer key = entry.getKey();
          ArrayList < Priority > value = entry.getValue();
    
          Priority[] myArray = value.toArray(new Priority[value.size()]);
    
          Arrays.sort(myArray);
          ArrayList < Priority > list = new ArrayList < > (Arrays.asList(myArray));
    
          this.newb.put(key, list);
        }
      }
    
      public void sort() { //sorts verynewB after adding values
        for (Entry < Integer, ArrayList < Priority >> entry: verynewb.entrySet()) {
          Integer key = entry.getKey();
          ArrayList < Priority > value = entry.getValue();
          Priority[] myArray = value.toArray(new Priority[value.size()]);
          Arrays.sort(myArray);
          ArrayList < Priority > list = new ArrayList < > (Arrays.asList(myArray));
          this.verynewb.put(key, list);
        }
    
      }
      public void cleaning() {
        ArrayList < Animal > animaltoclean = new ArrayList < > ();
    
        ArrayList < Animal > procupinetoclean = new ArrayList < > ();
    
        ArrayList < String > k = new ArrayList < > ();
        for (int i = 0; i < this.animalList.length; i++) {
    
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
    
          if (this.timearray[i] > 0) {
            int x = this.timearray[i] / 5;
            if (x > animaltoclean.size() - alreadyclean) {
              x = animaltoclean.size() - alreadyclean;
              all = true;
    
            }
            for (int j = alreadyclean; j < x + alreadyclean; j++) {
              k.add(animaltoclean.get(j).getAnimalNickName());
              desc = desc + " ," + animaltoclean.get(j).getAnimalNickName();
            }
    
            Priority kk = new Priority(k, x * 5, 24, desc);
            ArrayList < Priority > h = this.verynewb.get(i);
            if (h == null) {
              h = new ArrayList < Priority > ();
            }
            alreadyclean += x;
            h.add(kk);
            this.verynewb.put(i, h);
            timearray[i] -= (x * 5);
            if (alreadyclean == animaltoclean.size()) {
              break;
            }
            if (i == 23 && alreadyclean != x) {
    
              for (int l = 0; l < 24; l++) {
                if (!volList.contains(l)) {
                  this.timearray[l] += 60;
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
                h = this.verynewb.get(l);
                if (h == null) {
                  h = new ArrayList < Priority > ();
                }
    
                h.add(kk);
                this.verynewb.put(l, h);
                timearray[l] -= (animaltoclean.size() - alreadyclean) * 5;
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
    
          if (this.timearray[i] > 0) {
            int x = this.timearray[i] / 10;
            if (x > procupinetoclean.size() - alreadyclean1) {
              x = procupinetoclean.size() - alreadyclean1;
              all1 = true;
    
            }
            for (int j = alreadyclean1; j < x + alreadyclean1; j++) {
              k.add(procupinetoclean.get(j).getAnimalNickName());
              desc = desc + " ," + procupinetoclean.get(j).getAnimalNickName();
            }
    
            Priority kk = new Priority(k, x * 10, 24, desc);
            ArrayList < Priority > h = this.verynewb.get(i);
            if (h == null) {
              h = new ArrayList < Priority > ();
            }
            alreadyclean1 += x;
            h.add(kk);
            this.verynewb.put(i, h);
            timearray[i] -= (x * 10);
            if (alreadyclean1 == procupinetoclean.size()) {
              break;
            }
            if (i == 23 && alreadyclean1 != x) {
             
              for (int l = 0; l < 24; l++) {
                if (!volList.contains(l)) {
                  this.timearray[l] += 60;
                  volList.add(l);
                }
                k = new ArrayList < > ();
                desc = "cleaning";
    
                for (int a = alreadyclean1; a < procupinetoclean.size(); a++) {
                  k.add(procupinetoclean.get(a).getAnimalNickName());
                  desc = desc + " ," + procupinetoclean.get(a).getAnimalNickName();
                }
                kk = new Priority(k, x * 10, 24, desc);
                h = this.verynewb.get(l);
                if (h == null) {
                  h = new ArrayList < Priority > ();
                }
    
                h.add(kk);
                this.verynewb.put(l, h);
                timearray[l] -= (procupinetoclean.size() - alreadyclean1) * 10;
                all1 = true;
    
              }
            }
          }
          if (all1) {
            break;
          }
    
        }
    
      }
    
      public void print() {
        for (Entry < Integer, ArrayList < Priority >> entry: getverynewb().entrySet()) {
          Integer key1 = entry.getKey();
          ArrayList < Priority > value = entry.getValue();
          if (value != null) {
            for (Priority item: value) {
    
              System.out.print("hour: " + key1 + "    ");
    
              System.out.print("maxwindow:" + item.getMaxWindow() + "    ");
              System.out.print("description: " + item.getdescription() + "    ");
    
              System.out.print("duration: " + item.getduration() + "    ");
              System.out.print("\n");
    
            }
          }
        }

    
      }
      public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
          new Scheduling().setVisible(true);
    
        });
    
      }
    
    
}
