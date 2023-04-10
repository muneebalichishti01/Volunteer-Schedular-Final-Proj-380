package edu.ucalgary.oop;

public class Animal {
    private final int ANIMALID;
    private final String ANIMALNICKNAME;
    private final String ANIMALSPECIES;

    public Animal(int animalID, String Nickname, String Species)throws IllegalArgumentException{
        if(animalID < 0 || Nickname == null || Species == null)
            throw new IllegalArgumentException("Invalid input");
        this.ANIMALID = animalID;
        this.ANIMALNICKNAME = Nickname;
        this.ANIMALSPECIES = Species;
    }

    public int getAnimalID(){
        return this.ANIMALID;
    }
    public String getAnimalNickName(){
        return this.ANIMALNICKNAME;
    }
    public String getAnimalSpecies(){
        return this.ANIMALSPECIES;
    }
}
