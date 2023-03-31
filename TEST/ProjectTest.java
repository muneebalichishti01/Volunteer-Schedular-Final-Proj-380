// package edu.ucalgary.oop;
package ENSF380_Final_Project;

import org.junit.*;
import static org.junit.Assert.*;

public class ProjectTest {

	public Task task1 = new Task (1,"new task", 20, 2,1);

	public ProjectTest(){}

	@Test
	public void testGettersTasks(){
		
		int actualResultID = task1.getTaskID();
		int expectedResultID = 1;

		assertEquals("getTaskID does not work", expectedResultID, actualResultID);
		String actualResultDescription= task1.getDescription();
		String expectedResultDescription = "new task";
		assertEquals("getDescription does not work", expectedResultDescription,actualResultDescription);
		int actualResultDuration=  taks1.getDuration();
		int expectedResultDuration = 20;
		assertEquals("getDuration does not work", expectedResultDuration,actualResultDuration);
		int actualResultMaxWindow = task1.getMaxWindow(); 
		int expectedResultMaxWindow = 2;
		assertEquals("getMaxWindow does not work, expectedResultMaxWindow, actualResultMaxWindow");
		int actualResultAnimalID = task1.getAnimalID();
		int expectedResultAnimalID =1;
		assertEquals("getAnimalID does not work", expectedResultAnimalID,actualResultAnimalID);
	}

	@Test
	public void testGettersTreatment(){

		Treatment treatment1 = new Treatment(1,1,1);
		int actualResultTaskID = treatment1.getTaskID();
		int expectedResultTaskID = 1;
		assertEquals("getTaskID for treatment does not work", expectedResultTaskID,actualResultTaskID);
		int actualResultAnimalID = treatment1.getAnimalID();
		int expectedResultAnimalID = 1;
		assertEquals("getAnimalID for treatment does not work", expectedResultAnimalID,actualResultAnimalID);
		int actualResultStartHour = treatment1.getStartHour();
		int expectedResultStartHour = 1;
		assertEquals("getStartHourID for treatment does not work", expectedResultStartHour,actualResultStartHour);
	}
		
	@Test
    public void testGettersAnimals() {

        Animal animal1 = new Animal (1, "loner", "coyote", task1);

        int actualResultID = animal1.getTaskId();
        int expectedResultID = 1;
        String actualName = animal1.getAnimalNickName();  
        String expectedName = "loner";
        String actualSpecies = animal1.getAnimalSpecies();
        String expectedSpecies = "coyote";
        Task actualTask = animal1.getTaskAssigned();
        Task expectedTask = task1;

        assertEquals("testing animalID",expectedResultID, actualResultID);
        assertEquals("testing animalName",expectedName, actualName);
        assertEquals("testing animalSpecie",expectedSpecies, actualSpecies);
        assertEquals("tesing task object",expectedTask, actualTask);
    }

    @Test 
    public void testGettersVolunteers() {

        Volunteer volunteer1 = new Volunteer ("1", "John", "Doe");

        String actualResultID = volunteer1.getVolunteerId();
        String expectedResultID = "1";
        String actualVolunteer1 = volunteer1.getVolunteerName();
        String expectedName = "John";
        String actualLVolunteer2 = volunteer1.getBackupVol();
        String expectedVolunteer2 = "Doe";

        assertEquals("getvolunteerID does not work",expectedResultID, actualResultID);
        assertEquals("getvolunteerName does not work",expectedName, actualVolunteer1);
        assertEquals("getBackupVol does not work",expectedVolunteer2, actualLVolunteer2);
    }

    @Test
    public void testSettersVolunteer(){

        Volunteer volunteer1 = new Volunteer ("1", "John", "Doe");

        volunteer1.setVolunteerId("2");
        volunteer1.setVolunteerName("Jane");
        volunteer1.setBackupVol("Doe");

        String actualResultID = volunteer1.getVolunteerId();
        String expectedResultID = "2";
        String actualVolunteer1 = volunteer1.getVolunteerName();
        String expectedName = "Jane";
        String actualLVolunteer2 = volunteer1.getBackupVol();
        String expectedVolunteer2 = "Doe";  

        assertEquals("setvolunteerID does not work",expectedResultID, actualResultID);
        assertEquals("setvolunteerName does not work",expectedName, actualVolunteer1);
        assertEquals("setBackupVol does not work",expectedVolunteer2, actualLVolunteer2);
    }
    
	@Test
	public void testGetterScheduling(){

		//int[][] array1 = new int[23][3];
		Task [23][3] = array1;

		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 3; j++) {
			array1[i][j] = task1;
			}
		}		
		
		Scheduling scheduling1 = new Scheduling(array1);
		Task[][] actualResult = scheduling1.getTimeArray();
		Task [][] expectedResult = array1;

		assertEquals("getTimeArray getter does not work ",expectedResult, actualResult);
	}
		
	@Test
	public void testSetterScheduling(){

		//int[][] array1 = new int[23][3];
		Task [23][3] = array1;
		for (int i = 0; i < 24; i++){
			for (int j = 0; j < 3; j++){
			array1[i][j]= task1;
			}
		}		
		
		Scheduling scheduling1 = null;
		scheduling1.setTimeArray(array1);
		Task [][] actualResult = scheduling1.getTimeArray();
		Task [][] expectedResult = array1;

		assertEquals("getTimeArray getter does not work ",expectedResult, actualResult);	
	}
		
	@Test
	public void testAddTask(){

		//int[][] array1 = new int[23][4];
		Task [23][4] = array1;
		for (int i = 0; i < 24; i++){
			for (int j = 0; j < 3; j++){
			array1[i][j]= task1;
			}
		}		
		
		Task task2 = new Task (2,"new2 task", 30, 2,1);
		array1[22][3]= task2;
		Scheduling scheduling1 = new Scheduling(array1);
		scheduling1.addTask(task2);
		Task expectedResult = array1[22][3];
		Task[][] actualarray = scheduling1.getTimeArray();
		Task actualResult = actualarray[22][3];

		assertEquals("method addResult does not work ",expectedResult,actualResult);
	}
}
		
		