/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_c00039405;

public class Person {

    private String name;
    private String race;
    private int gold;
    private int health;
    private int defense;
    private int attack;

    public Person() {
    }

    public Person(String name, String race, int gold, int health, int defense, int attack) {
        this.name = name;
        this.race = race;
        this.gold = gold;
        this.health = health;
        this.defense = defense;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String usersName) {
        name = usersName;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String usersRace) {
        race = usersRace;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int usersGold) {
        gold = usersGold;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int usersHealth) {
        health = usersHealth;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int usersDefense) {
        defense = usersDefense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int usersAttack) {
        attack = usersAttack;
    }

    @Override
    public String toString() {
        return ("Name is: " + name + "\n" + "Race is: " + race + "\n" + "Gold: " + gold + "\n"
                + "Health: " + health + "\n" + "Defense: " + defense + "\n" + "Attack:" + attack);
    }

    public String fight() {
        //do the fight action either here or not idk yet
        return "fight";
    }

    public boolean run() {
        //if run is successful set stuff and
        return true;
    }

    public boolean bribe() {
        //if bribe succesful set stuff and
        return true;
    }
}
