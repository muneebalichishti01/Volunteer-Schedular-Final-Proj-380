package edu.ucalgary.oop;

public class Animal {
    private final int ANIMALID;
    private final String ANIMALNICKNAME;
    private final String ANIMALSPECIES;

    public Animal(int animalID, String Nickname, String Species){
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
