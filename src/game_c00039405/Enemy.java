package game_c00039405;

import java.util.Random;

public class Enemy extends Person {

    Random rand = new Random();
    String name = "Enemy";
    String race = "Evil race";
    int gold = rand.nextInt(101);
    int health = rand.nextInt(101);
    int defense = rand.nextInt(101);
    int attack = rand.nextInt(101);

    public Enemy() {
    }

    public Enemy(String name, String race, int gold, int health, int defense, int attack) {
        super(name, race, gold, health, defense, attack);
        this.name = name;
        this.race = race;
        this.gold = gold;
        this.health = health;
        this.defense = defense;
        this.attack = attack;
    }

    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public String toString() {
        return ("a Enemy,'" + name + "' here." + "\n" + "Race is: " + race + "\n" + "Gold: " + gold + "\n"
                + "Health: " + health + "\n" + "Defense: " + defense + "\n" + "Attack:" + attack + "\n");
    }
}
