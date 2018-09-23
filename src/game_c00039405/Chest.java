package game_c00039405;

import java.util.ArrayList;
import java.util.Random;

public class Chest {

    Random rand = new Random();
    Random randSel = new Random();

    private int weapon = rand.nextInt(101);
    private int armor = rand.nextInt(101);
    private int gold = rand.nextInt(101);
    private int healthPotion = rand.nextInt(101);
    private int empty = 0;
    private int selection = rand.nextInt(5);

    public Chest() {
    }

    public Chest(int selection, int empty, int weapon, int armor, int gold, int healthPotion) {
        this.empty = empty;
        this.weapon = weapon;
        this.armor = armor;
        this.gold = gold;
        this.healthPotion = healthPotion;
    }

    public int stuff() {
        int n = selection;
        ArrayList<Integer> stuffArray = new ArrayList<>(5);
        stuffArray.add(empty);
        stuffArray.add(weapon);
        stuffArray.add(armor);
        stuffArray.add(gold);
        stuffArray.add(healthPotion);

        return stuffArray.get(n);
    }

    public void setSelection(int s) {
        selection = s;
    }

    public String getWhatsInChest() {
        switch (selection) {
            case 0:
                return "empty";
            case 1:
                return "weapon";
            case 2:
                return "armor";
            case 3:
                return "gold";
            case 4:
                return "healthPotion";
            default:
                break;
        }
        return "broken";
    }

    /*
    public int getChestStuff(int selection){   
        switch (selection) {
            case 0:
                return empty;
            case 1:
                return weapon;
            case 2:
                return armor;
            case 3:
                return gold;
            case 4:
                return healthPotion;
            default:
                break;
        }
        return empty;  
    }*/
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return ("a chest.");

    }

}
