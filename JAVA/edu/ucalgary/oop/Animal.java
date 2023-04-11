/**
* 
* This ANimal Class is used to create animal objects which will have 3 data members: AnimalID ,AnimalNickname, and Animal Species
/* @author  Mohamad Jamal Hammoud, Qazi Ali, Mirza Hassan Baig, Muneeb Ali
* @version 3.0
* @since   2023-03-25 
*/

package edu.ucalgary.oop;

public class Animal implements Print{
    private final int ANIMALID;
    private final String ANIMALNICKNAME;
    private final String ANIMALSPECIES;

    public Animal(int animalID, String nickname, String species)throws IllegalArgumentException{ // A Constructor that throw illegal argument exception when bad data is given
        if(animalID < 1 || nickname == null || species == null)
            throw new IllegalArgumentException("Invalid input");
        this.ANIMALID = animalID;
        this.ANIMALNICKNAME = nickname;
        this.ANIMALSPECIES = species;
    }
    // Getters 
    public int getAnimalID(){
        return this.ANIMALID;
    }
    public String getAnimalNickName(){
        return this.ANIMALNICKNAME;
    }
    public String getAnimalSpecies(){
        return this.ANIMALSPECIES;
    }
    // Implementation of abstract method included in the interface that the class implements
    public void print() {
        // TODO Auto-generated method stub
        System.out.println("Animal ID: " + this.ANIMALID + " Animal Nickname: " + this.ANIMALNICKNAME + " Animal Species: " + this.ANIMALSPECIES);
    }
}
