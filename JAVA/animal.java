// package edu.ucalgary.oop;
// package ENSF380_Final_Project;

public class Animal{
	private final int ANIMALID;
	private final String ANIMALNICKNAME;
	private final String ANINALSPECIES;
	public Animal(int animalID, String Nickname, String Species){
		this.ANIMALID= animalID;
		this.ANIMALNICKNAME = Nickname;
		this.ANINALSPECIES= Species;
	}
	public int getAnimalID(){
		return this.ANIMALID;
	}
	public String getAnimalNickName(){
		return this.ANIMALNICKNAME;
	}
	public String getAnimalSpecies(){
		return this.ANINALSPECIES;
	}
	
}
