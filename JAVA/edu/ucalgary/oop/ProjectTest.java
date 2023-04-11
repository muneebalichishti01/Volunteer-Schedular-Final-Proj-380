package ENSF380_Final_Project;

import org.junit.*;

import edu.ucalgary.oop.Populating;

import static org.junit.Assert.*;

public class ProjectTest {

	public Task task1 = new Task (1,"new task", 20, 2,1);

	public ProjectTest(){}

	@Test
	public void selectAnimalsTest(){
		Populating pop1 = new Populating ();
		ArrayList<Animal> expectedfoxList= new ArrayList<>();
		ArrayList<Animal> expectedracoonsList= new ArrayList<>();
		ArrayList<Animal> expectedbeaverList= new ArrayList<>();
		ArrayList<Animal> expectedporcupineList= new ArrayList<>();
		ArrayList<Animal> expectedcoyoteList= new ArrayList<>();
		ArrayList<Animal> actualfoxList= new ArrayList<>();
		ArrayList<Animal> actualcoyoteList= new ArrayList<>();
		ArrayList<Animal> actualporcupineList= new ArrayList<>();
		ArrayList<Animal> actualraccoonList= new ArrayList<>();
		ArrayList<Animal> actualbeaverList= new ArrayList<>();
		
		
		Animal animalNew = new Animal(1, "Loner", "coyote");
		expectedcoyoteList.add(animalNew);
		animalNew = new Animal(2, "Biter", "coyote");
		expectedcoyoteList.add(animalNew);
		animalNew = new Animal(3, "Bitter", "coyote");
		expectedcoyoteList.add(animalNew);
		animalNew = new Animal(4, "Pencil", "coyote");
		expectedcoyoteList.add(animalNew);
		
		animalNew = new Animal(7, "Slinky", "fox");
		expectedfoxList.add(animalNew);
		animalNew = new Animal(8, "Spike", "porcupine");
		expectedporcupineList.add(animalNew);
		animalNew = new Animal(9, "Javelin", "porcupine");
		expectedporcupineList.add(animalNew);
		animalNew = new Animal(10, "Gatekeeper", "porcupine");
		expectedporcupineList.add(animalNew);
		animalNew = new Animal(11, "Sunshine", "porcupine");
		expectedporcupineList.add(animalNew);
		animalNew = new Animal(12, "Shadow", "porcupine");
		expectedporcupineList.add(animalNew);
		animalNew = new Animal(13, "Boots", "coyote");
		expectedcoyoteList.add(animalNew);
		animalNew = new Animal(14, "Spin", "coyote");
		expectedcoyoteList.add(animalNew);
		animalNew = new Animal(15, "Spot", "coyote");
		expectedcoyoteList.add(animalNew);
		pop1.selectAnimals();
		actualfoxList =pop1.getFoxList ();
		actualbeaverList = pop1.getBeaverList();
		actualcoyoteList= pop1.getCoyoteList();
		actualporcupineList=pop1.getProcupineList();
		actualraccoonList= pop1.getRaccoonsList();
		assertTrue("something wrong in select animals foxlist", expectedfoxList,actualfoxList);
		assertTrue("something wrong in select animals raccoonlist", expectedracoonsList,actualraccoonList);
		assertTrue("something wrong in select animals beaverlist", expectedbeaverList,actualbeaverList);
		assertTrue("something wrong in select animals porcupinelist", expectedporcupineList,actualporcupineList);
		assertTrue("something wrong in select animals foxlist", expectedcoyoteList,actualcoyoteList);
		
		
		
}
@Test
public void selectTasksTest(){
	Populating pop1 = new Populating ();
	ArrayList<Task> expectedTaskList= new ArrayList<>();
	ArrayList<Task> actualTaskList= new ArrayList<>();
	newTask  = new Task (1, "Kit feeding", 30, 2) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (2, "Rebandage leg wound", 20, 1) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (3, "Apply burn ointment back", 10, 3) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (4, "Administer antibiotics", 5, 1) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (5, "Flush neck wound", 25, 1) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (6, "Give fluid injection", 10, 1) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (7, "Give vitamin injection", 5, 5) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (8, "Mange treatment", 15, 4) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (9, "Eyedrops", 25, 1) ;
	expectedTaskList.add(newTask);
	 newTask  = new Task (10, "Inspect broken leg", 5, 2) ;
	expectedTaskList.add(newTask);
	actualTaskList = pop1.getTaskList();
	assertTrue("somethimg wrong with select tasks",expectedTaskList,actualTaskList);
	

	

}
}