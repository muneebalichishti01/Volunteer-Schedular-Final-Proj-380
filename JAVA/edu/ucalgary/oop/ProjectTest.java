/* @author  Mohamad Jamal Hammoud, Qazi Ali, Mirza Hassan Baig, Muneeb Ali
* @version 3.0
* @since   2023-03-25 
*/

package edu.ucalgary.oop;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class ProjectTest {

	public ProjectTest(){
		//empty constructor
	}


	@Test  //based on the values in database given if you want to change the expected value change the value in database
	public void selectAnimalsTest(){
		Populating pop1 = new Populating ();
		Animal [] expectedList= new Animal[15];
		Animal [] actualAnimalList= new Animal[15];
		Animal animalNew = new Animal(1, "Loner", "coyote");

		expectedList[0]= animalNew;
		animalNew = new Animal(2, "Biter", "coyote");
		expectedList[1]= animalNew;
		animalNew = new Animal(3, "Bitter", "coyote");
		expectedList[2]= animalNew;
		animalNew = new Animal(4, "Pencil", "coyote");
		expectedList[3]= animalNew;
		animalNew = new Animal(	5, "Eraser", "coyote");
		expectedList[4]= animalNew;
		animalNew  = new Animal(6, "Annie, Oliver and Mowgli", "fox");
		expectedList[5]= animalNew;
		animalNew = new Animal(7, "Slinky", "fox");
		expectedList[6]= animalNew;
		animalNew = new Animal(8, "Spike", "porcupine");
		expectedList[7]= animalNew;
		animalNew = new Animal(9, "Javelin", "porcupine");
		expectedList[8]= animalNew;
		animalNew = new Animal(10, "Gatekeeper", "porcupine");
		expectedList[9]= animalNew;
		animalNew = new Animal(11, "Sunshine", "porcupine");
		expectedList[10]= animalNew;
		animalNew = new Animal(12, "Shadow", "porcupine");
		expectedList[11]= animalNew;
		animalNew = new Animal(13, "Boots", "coyote");
		expectedList[12]= animalNew;
		animalNew = new Animal(14, "Spin", "coyote");
		expectedList[13]= animalNew;
		animalNew = new Animal(15, "Spot", "coyote");

		expectedList[14]= animalNew;
		pop1.createConnection();
		pop1.selectAnimals();
		actualAnimalList = pop1.getAnimalList(); 

		for(int i = 0; i<15; i++){
			boolean check = expectedList[i].getAnimalID()==actualAnimalList[i].getAnimalID();
			assertEquals("something wrong in select animals foxlist id for index "+ i, true, check);
			check = expectedList[i].getAnimalNickName().equals(actualAnimalList[i].getAnimalNickName());
			assertEquals("something wrong in select animals foxlist species" + i, true, check);
			check = expectedList[i].getAnimalSpecies().equals(actualAnimalList[i].getAnimalSpecies());
			assertEquals("something wrong in select animals foxlist species" + i, true, check);
		}
}


	@Test
	public void AnimalClassTest(){
		Animal myAnimalactual = new Animal(1, "Spin", "coyote");
		int expectedAnimalID = 1;
		String expectedAnimalNickname = "Spin";
		String expectedAnimalSpecies = "coyote";
		int actualAnimalID = myAnimalactual.getAnimalID();
		String actualAnimalNickname = myAnimalactual.getAnimalNickName();
		String actualAnimalSpecies = myAnimalactual.getAnimalSpecies();

		assertEquals("something wrong in animal id", expectedAnimalID,actualAnimalID);
		assertEquals("something wrong in animal nickname", expectedAnimalNickname,actualAnimalNickname);
		assertEquals("something wrong in animal species", expectedAnimalSpecies,actualAnimalSpecies);
	}


	@Test(expected=IllegalArgumentException.class)
	public void AnimalClassTestException(){
		Animal myAnimalActual = new Animal(0, "Spin", "coyote");
	}


	@Test
	public void PriorityClassTest(){
		Priority myPriorityActual = new Priority(1, 2,20,2,"feeding",0);
		int expectedTaskID = 1;
		int expectedAnimalID = 2;
		int expectedDuration = 20;
		int expectedMaxWindow = 2;
		String expectedDesc = "feeding";
		int expectedStart = 0;
		int actualTaskID = myPriorityActual.getTaskID();
		int actualAnimalID = myPriorityActual.getAnimalId();
		int actualDuration = myPriorityActual.getDuration();
		int actualMaxWindow = myPriorityActual.getMaxWindow();
		String actualDesc = myPriorityActual.getDescription();
		int actualStart = myPriorityActual.getStartHour();

		assertEquals("something wrong in task id", expectedTaskID,actualTaskID);
		assertEquals("something wrong in animal id", expectedAnimalID,actualAnimalID);
		assertEquals("something wrong in duration", expectedDuration,actualDuration);

		assertEquals("something wrong in max window", expectedMaxWindow,actualMaxWindow);
		assertEquals("something wrong in desc", expectedDesc,actualDesc);
		assertEquals("something wrong in start", expectedStart,actualStart);
	}

	@Test(expected=IllegalArgumentException.class)
	public void PriorityClassTestException(){
		Priority myPriorityActual = new Priority(0, 2,20,2,"feeding",0);
	}


	@Test
	public void TreatmentClassTest(){
		Treatment myTreatmentActual = new Treatment(1, 2,4);
		int expectedAnimalID = 1;
		int expectedTaskID = 2;
		int expectedStartHour = 4;
		int actualAnimalID = myTreatmentActual.getAnimalID();
		int actualTaskID = myTreatmentActual.getTaskID();
		int actualStartHour = myTreatmentActual.getStartHour();

		assertEquals("something wrong in animal id", expectedAnimalID,actualAnimalID);
		assertEquals("something wrong in task id", expectedTaskID,actualTaskID);
		assertEquals("something wrong in start hour", expectedStartHour,actualStartHour);
	}


	@Test(expected=IllegalArgumentException.class)
	public void TreatmentClassTestException(){
		Treatment myTreatmentActual = new Treatment(0, 2,4);
	}


	@Test	
	public void TestTaskClass(){
		Task myTaskActual = new Task(1, "feeding", 20, 2);
		int expectedTaskID = 1;
		String expectedDesc = "feeding";
		int expectedDuration = 20;
		int expectedMaxWindow = 2;
		int actualTaskID = myTaskActual.getTaskID();
		String actualDesc = myTaskActual.getDescription();
		int actualDuration = myTaskActual.getDuration();
		int actualMaxWindow = myTaskActual.getMaxWindow();

		assertEquals("something wrong in task id", expectedTaskID,actualTaskID);
		assertEquals("something wrong in desc", expectedDesc,actualDesc);
		assertEquals("something wrong in duration", expectedDuration,actualDuration);
		assertEquals("something wrong in max window", expectedMaxWindow,actualMaxWindow);
	}


	@Test(expected=IllegalArgumentException.class)
	public void TestTaskClassException(){
		Task myTaskActual = new Task(1, "feeding",20,0);
	}

	@Test  //based on the values in database given if you want to change the expected value change the value in database
	public void selectTaskTest(){
		Populating pop1 = new Populating ();
		Task [] expectedList= new Task[15];
		Task [] actualTaskList= new Task[10];
		Task taskNew = new Task(1, "Kit feeding", 30, 2);

		expectedList[0]= taskNew;
		taskNew = new Task(2, "Rebandage leg wound", 20, 1);
		expectedList[1]= taskNew;
		taskNew = new Task(3, "Apply burn ointment back", 10, 3);
		expectedList[2]= taskNew;
		taskNew = new Task(4, "Administer antibiotics", 5, 1);
		expectedList[3]= taskNew;
		taskNew = new Task(5, "Flush neck wound", 25, 1);
		expectedList[4]= taskNew;
		taskNew = new Task(6, "Give fluid injection", 10, 1);
		expectedList[5]= taskNew;
		taskNew = new Task(7, "Give vitamin injection", 5, 5);
		expectedList[6]= taskNew;
		taskNew = new Task(8, "Mange treatment", 15, 4);
		expectedList[7]= taskNew;
		taskNew = new Task(9, "Eyedrops", 25, 1);
		expectedList[8]= taskNew;
		taskNew = new Task(10, "Inspect broken leg", 5, 2);
		expectedList[9]= taskNew;
		
		pop1.createConnection();
		pop1.selectTasks();
		actualTaskList = pop1.getTaskList(); 

		for (int i=0;i<10;i++){
			assertTrue("something wrong in selecttask()", (expectedList[i].getTaskID()==actualTaskList[i].getTaskID()));
		}	
	}


	@Test  //based on the values in database given if you want to change the expected value change the value in database
	public void selectTreatmentTest(){
		Populating pop1 = new Populating ();
		Treatment [] expectedList= new Treatment[10];
		Treatment [] actualTreatmentList= new Treatment[10];
		Treatment treatmentNew = new Treatment(6, 1, 0);

		expectedList[0]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 2);
		expectedList[1]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 4);
		expectedList[2]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 6);
		expectedList[3]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 8);
		expectedList[4]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 10);
		expectedList[5]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 12);
		expectedList[6]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 14);
		expectedList[7]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 16);
		expectedList[8]= treatmentNew;
		treatmentNew = new Treatment(6, 1, 18);
		expectedList[9]= treatmentNew;
		
		pop1.createConnection();
		pop1.selectTreatments();
		actualTreatmentList = pop1.getTreatmentList();

		for (int i=0;i<10;i++){
			assertTrue("something wrong in selectTreatment()", (expectedList[i].getTaskID()==actualTreatmentList[i].getTaskID()));
			assertTrue("something wrong in selectTreatment()", (expectedList[i].getAnimalID()==actualTreatmentList[i].getAnimalID()));
			assertTrue("something wrong in selectTreatment()", (expectedList[i].getStartHour()==actualTreatmentList[i].getStartHour()));
		}	
	}


	@Test
	public void copyingTest(){
		Populating pop1 = new Populating();
		pop1.createConnection();
		pop1.selectAnimals();
		pop1.selectTasks();
		pop1.selectTreatments();
		pop1.setPriority();
		
		Scheduling mySchedule = new Scheduling();
		mySchedule.copying();
		HashMap<Integer, ArrayList<Priority>> actualValue  = mySchedule.getsortedTreatmentsFeedingCleaning();
		
		assertEquals(24, actualValue.size());
		assertTrue(actualValue.containsKey((Integer)0));
		assertTrue(actualValue.containsKey((Integer)1));
	}


	@Test //based on the values in database given if you want to change the expected value change the value in database
	public void TestSetPriority(){
		String expected = "[0, 2, 4, 6, 8, 10, 12, 13, 14, 15, 16, 18, 19, 20, 22, 23]";
		Scheduling schedule = new Scheduling();
		HashMap < Integer, ArrayList < Priority >> actualHashMap = schedule.getHoursMap();
		ArrayList<Integer> actualKeys = new ArrayList<Integer>();

		for (Integer key : actualHashMap.keySet()) {
			actualKeys.add(key); 
		}

		Collections.sort(actualKeys);
		String actualString = actualKeys.toString();
		assertEquals("something wrong in setPriority()", expected,actualString);
	}
	@Test //based on the values in database given if you want to change the expected value change the value in database
	public void Testfeeding(){
		Scheduling mySchedule= new Scheduling();
		mySchedule.feeding();
		HashMap<Integer, ArrayList<Priority>> actualValue  = mySchedule.getsortedTreatmentsFeedingCleaning();
		for (int i=0; i<24; i++){
			assertTrue(actualValue.containsKey((Integer)i));
		}
		mySchedule.cleaning();
		for (int i=0; i<24; i++){
			assertTrue(actualValue.containsKey((Integer)i));
		}


}
	@Test //based on the values in database given if you want to change the expected value change the value in database
	//this test is for the scheduleAdjust method Scheduling Treatment and Feeding and Cleaning so at the end of the day the sortedTreatmentsFeedingCleaning will have these expected keys
	public void TestFinal(){

		String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]";
		Scheduling schedule = new Scheduling();
		HashMap < Integer, ArrayList < Priority >> actualHashMap = schedule.getsortedTreatmentsFeedingCleaning();
		ArrayList<Integer> actualKeys = new ArrayList<Integer>();

		for (Integer key : actualHashMap.keySet()) {
			actualKeys.add(key); 
		}

		Collections.sort(actualKeys);
		String actualString = actualKeys.toString();
		assertEquals("something wrong in scheduleAdjust", expected,actualString);
	}
	}

