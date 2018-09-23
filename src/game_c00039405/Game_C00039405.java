// Your Name: Coadiey Bryan
// Your ID: C00039405
// CMPS 260
// Programming Assignment : Extra Credit Game
// Due Date : end of semester
// Program Description: 
// Certificate of Authenticity:
// I certify that the code in the method function main of this project is entirely my own
// work.
package game_c00039405;

import java.util.ArrayList;
import java.util.Random;

public class Game_C00039405 {

    public static void main(String[] args) {
        java.util.Scanner keyboard = new java.util.Scanner(System.in);
        Random randomizer = new Random();
        Random rand1 = new Random();
        Random rand2 = new Random();
        String[] enemyNames = {"Bad Mamma Jamma", "Dirty Dan", "Diablo", "Scratch"};
        String[] enemyRaces = {"The Bad Azz", "The Dirtys", "The Demons", "Pure Evil"};
        ArrayList<Object> objects = new ArrayList<>();//List with all possible things a new space can have at it
        objects.add(new Nothing());
        objects.add(new Chest(rand1.nextInt(5), rand1.nextInt(101), rand2.nextInt(101), rand2.nextInt(101), rand1.nextInt(101), rand2.nextInt(101)));
        objects.add(new Enemy(enemyNames[rand1.nextInt(3)], enemyRaces[rand2.nextInt(3)], rand1.nextInt(101), rand2.nextInt(101), rand1.nextInt(101), rand2.nextInt(101)));
        // objects.get(randomizer.nextInt(3);  Use this to get a random object and put it at whatever new location
        String input1;//assigning all user inputs to a variable in 
        String input2;//order to be able to check them against 'q' in the while loop
        String input3;
        String input4 = "";
        String input5 = "";
        String input6 = "";

        do {//do loop that keeps the game going until user asks to quit
            System.out.println("Hello, Adventurer. What is your name? ");//Intro Stuff
            input1 = keyboard.nextLine();
            System.out.println("Welcome, " + input1 + ". Are you an Elf or Nord?");
            input2 = keyboard.nextLine().toLowerCase();
            if (input2.equals("elf") == false & input2.equals("nord") == false & input2.equalsIgnoreCase("q") == false) {//captures if user inputs not elf or nord
                while (input2.equals("elf") == false & input2.equals("nord") == false & input2.equalsIgnoreCase("q") == false) {
                    System.out.println(input1 + ", please enter only Elf or Nord. What are you?");
                    input2 = keyboard.nextLine().toLowerCase();
                }
            }
            Person mainCharacter = new Person(input1, input2, 0, 100, rand2.nextInt(101), rand1.nextInt(101));
            GameBoard gameBoard = new GameBoard(new int[2], new ArrayList<>(), new ArrayList<>());
            System.out.println(mainCharacter.toString());
            System.out.println();
            System.out.println("Which direction do you want to go?");
            input3 = keyboard.nextLine().toLowerCase();
            OUTER:
            do { //While Loop that keeps the game going while maincharacter is alive
                if (input3.equalsIgnoreCase("nw")) {//direction nw start
                    int x = (gameBoard.getLocation())[0] - 1;
                    int y = (gameBoard.getLocation())[1] + 1;
                    gameBoard.setGameBoard(x, y, objects.get(randomizer.nextInt(3)));
                    System.out.println(gameBoard.toString());
                    int ind = gameBoard.getIndexForObjectsArray();
                    int ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                    OUTER_1:
                    switch (ObjectType) {
                        case 0:
                            // here is nothing object
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 1:
                            //chest object
                            System.out.println("Do you want to open the chest? (yes/no)");
                            input4 = keyboard.nextLine();
                            if (input4.equalsIgnoreCase("yes")) {
                                String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                    int currentDef = mainCharacter.getDefense();//if armor item
                                    int totalNewDef = currentDef + stuffStats;
                                    mainCharacter.setDefense(totalNewDef);
                                } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                    int currentAttack = mainCharacter.getAttack();
                                    int totalNewAttack = currentAttack + stuffStats;
                                    mainCharacter.setAttack(totalNewAttack);
                                } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                    int currentGold = mainCharacter.getGold();
                                    int totalNewGold = currentGold + stuffStats;
                                    mainCharacter.setGold(totalNewGold);
                                } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                    int currentHealth = mainCharacter.getHealth();
                                    int totalNewHealth = currentHealth + stuffStats;
                                    mainCharacter.setHealth(totalNewHealth);
                                }
                                ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                            } else if (input4.equalsIgnoreCase("no")) {
                                System.out.println("Chest remains unopened.");
                            } else if (input4.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                    System.out.println("You must decide. Will you open the chest or not?");
                                    input4 = keyboard.nextLine();
                                }
                            }
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 2://Enemy Object
                            System.out.println("You can either fight, bribe, or run. What will you do?");
                            input5 = keyboard.nextLine();
                            while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("run") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                System.out.println("You must either fight the enemy, bribe the enemy, or run from the enemy. \n Trust me he won't listen to reason. \n What will you do? ");
                                input5 = keyboard.nextLine();
                            }
                            if (input5.equalsIgnoreCase("fight")) {
                                Person attacker;
                                Person defender;
                                Person temporaryHolder;
                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                } else {
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                }
                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                    int damage = defender.getDefense() - attacker.getAttack();
                                    if (damage < 0) {//if they broke through the defenders defenses
                                        int newDefHealth = defender.getHealth() + damage;
                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                    }
                                    temporaryHolder = defender;//trading places to give next person their turn
                                    defender = attacker;
                                    attacker = defender;
                                }
                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                    mainCharacter.setGold(newTotalGold);
                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                } else if (mainCharacter.getHealth() <= 0) {
                                    System.out.println("You have lost and are now dead... \n"
                                            + " but if you'd like to play again just press anything.\n"
                                            + "If you would like to quit press 'q' now.");
                                    break OUTER;
                                } else {
                                    System.out.println("error");
                                }
                            } else if (input5.equalsIgnoreCase("run")) {
                                int[] possibleNewLocationsX = {x, x + 1, x - 1};
                                int[] possibleNewLocationsY = {y, y + 1, y - 1};
                                gameBoard.setGameBoard(possibleNewLocationsX[rand1.nextInt(2)], possibleNewLocationsY[rand1.nextInt(2)], objects.get(randomizer.nextInt(3)));
                                System.out.println(gameBoard.toString());
                                ind = gameBoard.getIndexForObjectsArray();
                                ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                                switch (ObjectType) {
                                    case 2:
                                        //if theres a enemy at new location
                                        System.out.println("You have not escaped the enemy. So the run fails.. \n"
                                                + "You have to either bribe or fight this enemy");
                                        input5 = keyboard.nextLine();
                                        while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                            System.out.println("You must either fight the enemy or bribe the enemy \n"
                                                    + "Trust me he won't listen to reason. \n What will you do? ");
                                            input5 = keyboard.nextLine();
                                        }
                                        if (input5.equalsIgnoreCase("fight")) {//actions for fight
                                            //fight to the death
                                            Person attacker;
                                            Person defender;
                                            Person temporaryHolder;
                                            if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            } else {
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            }
                                            while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                int damage = defender.getDefense() - attacker.getAttack();
                                                if (damage < 0) {//if they broke through the defenders defenses
                                                    int newDefHealth = defender.getHealth() + damage;
                                                    defender.setHealth(newDefHealth);//set health to health minus damage
                                                }
                                                temporaryHolder = defender;//trading places to give next person their turn
                                                defender = attacker;
                                                attacker = defender;
                                            }
                                            if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                mainCharacter.setGold(newTotalGold);
                                                System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                            } else if (mainCharacter.getHealth() <= 0) {
                                                System.out.println("You have lost and are now dead... \n"
                                                        + " but if you'd like to play again just press anything.\n"
                                                        + "If you would like to quit press 'q' now.");
                                                break OUTER;
                                            } else {
                                                System.out.println("error");
                                            }
                                            break;
                                        } else if (input5.equalsIgnoreCase("bribe")) {
                                            int mainsGold = mainCharacter.getGold();
                                            int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                            System.out.println("How much gold will you try to bribe the enemy with?");
                                            int bribe = keyboard.nextInt();
                                            if (bribe > mainsGold) {
                                                System.out.println("It Looks like you don't have the money to back that up..");
                                                while (bribe > mainsGold) {
                                                    System.out.println("How much will you bribe, out of how much you have available?");
                                                    bribe = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                }
                                            }
                                            if (bribe > enemyStandards) {//bribe worked
                                                System.out.println("Great! The bribe worked!");
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int totalEnemyGold = enemysGold + bribe;
                                                ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                                int newLocX = x + rand1.nextInt(11);
                                                int newLocY = y + rand2.nextInt(11);
                                                int[] newLoc = {newLocX, newLocY};
                                                if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                                    while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                                        newLocX = newLocX + rand1.nextInt(11);
                                                        newLocY = newLocY + rand2.nextInt(11);
                                                        newLoc[0] = newLocX;
                                                        newLoc[1] = newLocY;
                                                    }
                                                    int indexHere = gameBoard.getIndexForObjectsArray();
                                                    gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                                    gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                                    gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                                }

                                            } else {//bribe didnt work
                                                System.out.println("It looks like your bribe wasn't enough. \n"
                                                        + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                                //fight to the death
                                                Person attacker;
                                                Person defender;
                                                Person temporaryHolder;
                                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                } else {
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                }
                                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                    int damage = defender.getDefense() - attacker.getAttack();
                                                    if (damage < 0) {//if they broke through the defenders defenses
                                                        int newDefHealth = defender.getHealth() + damage;
                                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                                    }
                                                    temporaryHolder = defender;//trading places to give next person their turn
                                                    defender = attacker;
                                                    attacker = defender;
                                                }
                                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                    mainCharacter.setGold(newTotalGold);
                                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                                } else if (mainCharacter.getHealth() <= 0) {
                                                    System.out.println("You have lost and are now dead... \n"
                                                            + " but if you'd like to play again just press anything.\n"
                                                            + "If you would like to quit press 'q' now.");
                                                    break OUTER;
                                                } else {
                                                    System.out.println("error");
                                                }
                                            }
                                        }//end of bribe
                                        //make sure they can still fight or bribe, but not run.
                                        break;
                                    //end of run into chest option
                                    case 1:
                                        // if theres a chest at new location
                                        System.out.println("You ran away from the enemy and straight into a chest!");
                                        System.out.println("Do you want to open the chest? (yes/no)");
                                        input4 = keyboard.nextLine();
                                        if (input4.equalsIgnoreCase("yes")) {
                                            String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                            int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                            System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                            if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                                int currentDef = mainCharacter.getDefense();//if armor item
                                                int totalNewDef = currentDef + stuffStats;
                                                mainCharacter.setDefense(totalNewDef);
                                            } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                                int currentAttack = mainCharacter.getAttack();
                                                int totalNewAttack = currentAttack + stuffStats;
                                                mainCharacter.setAttack(totalNewAttack);
                                            } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                                int currentGold = mainCharacter.getGold();
                                                int totalNewGold = currentGold + stuffStats;
                                                mainCharacter.setGold(totalNewGold);
                                            } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                                int currentHealth = mainCharacter.getHealth();
                                                int totalNewHealth = currentHealth + stuffStats;
                                                mainCharacter.setHealth(totalNewHealth);
                                            }
                                            ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                                        } else if (input4.equalsIgnoreCase("no")) {
                                            System.out.println("Chest remains unopened.");
                                        } else if (input4.equalsIgnoreCase("q")) {
                                            break OUTER;
                                        } else {
                                            while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                                System.out.println("You must decide. Will you open the chest or not?");
                                                input4 = keyboard.nextLine();
                                            }
                                        }
                                        System.out.println("Which direction do you want to go?");
                                        input3 = keyboard.nextLine().toLowerCase();
                                        break OUTER_1;
                                    default:
                                        // if theres a nothing at new location you ran into
                                        System.out.println("You have succesfully ran, Phew!");
                                        break;
                                }
                            } else if (input5.equalsIgnoreCase("bribe")) {//actions for bribe
                                int mainsGold = mainCharacter.getGold();
                                int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                System.out.println("How much gold will you try to bribe the enemy with?");
                                int bribe = keyboard.nextInt();
                                if (bribe > mainsGold) {
                                    System.out.println("It Looks like you don't have the money to back that up..");
                                    while (bribe > mainsGold) {
                                        System.out.println("How much will you bribe, out of how much you have available?");
                                        bribe = keyboard.nextInt();
                                    }
                                }
                                if (bribe > enemyStandards) {//bribe worked
                                    System.out.println("Great! The bribe worked!");
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int totalEnemyGold = enemysGold + bribe;
                                    ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                    int newLocX = x + rand1.nextInt(11);
                                    int newLocY = y + rand2.nextInt(11);
                                    int[] newLoc = {newLocX, newLocY};
                                    if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                        while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                            newLocX = newLocX + rand1.nextInt(11);
                                            newLocY = newLocY + rand2.nextInt(11);
                                            newLoc[0] = newLocX;
                                            newLoc[1] = newLocY;
                                        }
                                        int indexHere = gameBoard.getIndexForObjectsArray();
                                        gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                        gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                        gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                    }

                                } else {//bribe didnt work
                                    System.out.println("It looks like your bribe wasn't enough. \n"
                                            + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                    //fight to the death
                                    Person attacker;
                                    Person defender;
                                    Person temporaryHolder;
                                    if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    } else {
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    }
                                    while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                        int damage = defender.getDefense() - attacker.getAttack();
                                        if (damage < 0) {//if they broke through the defenders defenses
                                            int newDefHealth = defender.getHealth() + damage;
                                            defender.setHealth(newDefHealth);//set health to health minus damage
                                        }
                                        temporaryHolder = defender;//trading places to give next person their turn
                                        defender = attacker;
                                        attacker = defender;
                                    }
                                    if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                        int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                        int newTotalGold = mainCharacter.getGold() + enemysGold;
                                        mainCharacter.setGold(newTotalGold);
                                        System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                    } else if (mainCharacter.getHealth() <= 0) {
                                        System.out.println("You have lost and are now dead... \n"
                                                + " but if you'd like to play again just press anything.\n"
                                                + "If you would like to quit press 'q' now.");
                                        break OUTER;
                                    } else {
                                        System.out.println("error");
                                    }

                                }

                            }//end of bribe
                            else if (input5.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                System.out.println("I'm sorry, but your options are limited here..");
                            }
                            if (mainCharacter.getHealth() <= 0) {//if main character is dead
                                System.out.println("You have died. \n"
                                        + " If you're ready to play again just enter anything, but if you want to exit enter 'q'.");
                                input6 = keyboard.nextLine();
                            } else {//if main character is still alive
                                System.out.println("Which direction do you want to go?");
                                input3 = keyboard.nextLine().toLowerCase();
                            }
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }//direction nw end
                } else if (input3.equalsIgnoreCase("n")) {// direction n start  
                    int x = (gameBoard.getLocation())[0];
                    int y = (gameBoard.getLocation())[1] + 1;
                    gameBoard.setGameBoard(x, y, objects.get(randomizer.nextInt(3)));
                    System.out.println(gameBoard.toString());
                    int ind = gameBoard.getIndexForObjectsArray();
                    int ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                    OUTER_1:
                    switch (ObjectType) {
                        case 0:
                            // here is nothing object
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 1:
                            //chest object
                            System.out.println("Do you want to open the chest? (yes/no)");
                            input4 = keyboard.nextLine();
                            if (input4.equalsIgnoreCase("yes")) {
                                String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                    int currentDef = mainCharacter.getDefense();//if armor item
                                    int totalNewDef = currentDef + stuffStats;
                                    mainCharacter.setDefense(totalNewDef);
                                } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                    int currentAttack = mainCharacter.getAttack();
                                    int totalNewAttack = currentAttack + stuffStats;
                                    mainCharacter.setAttack(totalNewAttack);
                                } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                    int currentGold = mainCharacter.getGold();
                                    int totalNewGold = currentGold + stuffStats;
                                    mainCharacter.setGold(totalNewGold);
                                } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                    int currentHealth = mainCharacter.getHealth();
                                    int totalNewHealth = currentHealth + stuffStats;
                                    mainCharacter.setHealth(totalNewHealth);
                                }
                                ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                            } else if (input4.equalsIgnoreCase("no")) {
                                System.out.println("Chest remains unopened.");
                            } else if (input4.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                    System.out.println("You must decide. Will you open the chest or not?");
                                    input4 = keyboard.nextLine();
                                }
                            }
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 2://Enemy Object
                            System.out.println("You can either fight, bribe, or run. What will you do?");
                            input5 = keyboard.nextLine();
                            while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("run") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                System.out.println("You must either fight the enemy, bribe the enemy, or run from the enemy. \n Trust me he won't listen to reason. \n What will you do? ");
                                input5 = keyboard.nextLine();
                            }
                            if (input5.equalsIgnoreCase("fight")) {
                                Person attacker;
                                Person defender;
                                Person temporaryHolder;
                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                } else {
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                }
                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                    int damage = defender.getDefense() - attacker.getAttack();
                                    if (damage < 0) {//if they broke through the defenders defenses
                                        int newDefHealth = defender.getHealth() + damage;
                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                    }
                                    temporaryHolder = defender;//trading places to give next person their turn
                                    defender = attacker;
                                    attacker = defender;
                                }
                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                    mainCharacter.setGold(newTotalGold);
                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                } else if (mainCharacter.getHealth() <= 0) {
                                    System.out.println("You have lost and are now dead... \n"
                                            + " but if you'd like to play again just press anything.\n"
                                            + "If you would like to quit press 'q' now.");
                                    break OUTER;
                                } else {
                                    System.out.println("error");
                                }
                            } else if (input5.equalsIgnoreCase("run")) {
                                int[] possibleNewLocationsX = {x, x + 1, x - 1};
                                int[] possibleNewLocationsY = {y, y + 1, y - 1};
                                gameBoard.setGameBoard(possibleNewLocationsX[rand1.nextInt(2)], possibleNewLocationsY[rand1.nextInt(2)], objects.get(randomizer.nextInt(3)));
                                System.out.println(gameBoard.toString());
                                ind = gameBoard.getIndexForObjectsArray();
                                ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                                switch (ObjectType) {
                                    case 2:
                                        //if theres a enemy at new location
                                        System.out.println("You have not escaped the enemy. So the run fails.. \n"
                                                + "You have to either bribe or fight this enemy");
                                        input5 = keyboard.nextLine();
                                        while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                            System.out.println("You must either fight the enemy or bribe the enemy \n"
                                                    + "Trust me he won't listen to reason. \n What will you do? ");
                                            input5 = keyboard.nextLine();
                                        }
                                        if (input5.equalsIgnoreCase("fight")) {//actions for fight
                                            //fight to the death
                                            Person attacker;
                                            Person defender;
                                            Person temporaryHolder;
                                            if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            } else {
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            }
                                            while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                int damage = defender.getDefense() - attacker.getAttack();
                                                if (damage < 0) {//if they broke through the defenders defenses
                                                    int newDefHealth = defender.getHealth() + damage;
                                                    defender.setHealth(newDefHealth);//set health to health minus damage
                                                }
                                                temporaryHolder = defender;//trading places to give next person their turn
                                                defender = attacker;
                                                attacker = defender;
                                            }
                                            if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                mainCharacter.setGold(newTotalGold);
                                                System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                            } else if (mainCharacter.getHealth() <= 0) {
                                                System.out.println("You have lost and are now dead... \n"
                                                        + " but if you'd like to play again just press anything.\n"
                                                        + "If you would like to quit press 'q' now.");
                                                break OUTER;
                                            } else {
                                                System.out.println("error");
                                            }
                                            break;
                                        } else if (input5.equalsIgnoreCase("bribe")) {
                                            int mainsGold = mainCharacter.getGold();
                                            int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                            System.out.println("How much gold will you try to bribe the enemy with?");
                                            int bribe = keyboard.nextInt();
                                            if (bribe > mainsGold) {
                                                System.out.println("It Looks like you don't have the money to back that up..");
                                                while (bribe > mainsGold) {
                                                    System.out.println("How much will you bribe, out of how much you have available?");
                                                    bribe = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                }
                                            }
                                            if (bribe > enemyStandards) {//bribe worked
                                                System.out.println("Great! The bribe worked!");
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int totalEnemyGold = enemysGold + bribe;
                                                ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                                int newLocX = x + rand1.nextInt(11);
                                                int newLocY = y + rand2.nextInt(11);
                                                int[] newLoc = {newLocX, newLocY};
                                                if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                                    while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                                        newLocX = newLocX + rand1.nextInt(11);
                                                        newLocY = newLocY + rand2.nextInt(11);
                                                        newLoc[0] = newLocX;
                                                        newLoc[1] = newLocY;
                                                    }
                                                    int indexHere = gameBoard.getIndexForObjectsArray();
                                                    gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                                    gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                                    gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                                }

                                            } else {//bribe didnt work
                                                System.out.println("It looks like your bribe wasn't enough. \n"
                                                        + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                                //fight to the death
                                                Person attacker;
                                                Person defender;
                                                Person temporaryHolder;
                                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                } else {
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                }
                                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                    int damage = defender.getDefense() - attacker.getAttack();
                                                    if (damage < 0) {//if they broke through the defenders defenses
                                                        int newDefHealth = defender.getHealth() + damage;
                                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                                    }
                                                    temporaryHolder = defender;//trading places to give next person their turn
                                                    defender = attacker;
                                                    attacker = defender;
                                                }
                                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                    mainCharacter.setGold(newTotalGold);
                                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                                } else if (mainCharacter.getHealth() <= 0) {
                                                    System.out.println("You have lost and are now dead... \n"
                                                            + " but if you'd like to play again just press anything.\n"
                                                            + "If you would like to quit press 'q' now.");
                                                    break OUTER;
                                                } else {
                                                    System.out.println("error");
                                                }
                                            }
                                        }//end of bribe
                                        //make sure they can still fight or bribe, but not run.
                                        break;
                                    //end of run into chest option
                                    case 1:
                                        // if theres a chest at new location
                                        System.out.println("You ran away from the enemy and straight into a chest!");
                                        System.out.println("Do you want to open the chest? (yes/no)");
                                        input4 = keyboard.nextLine();
                                        if (input4.equalsIgnoreCase("yes")) {
                                            String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                            int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                            System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                            if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                                int currentDef = mainCharacter.getDefense();//if armor item
                                                int totalNewDef = currentDef + stuffStats;
                                                mainCharacter.setDefense(totalNewDef);
                                            } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                                int currentAttack = mainCharacter.getAttack();
                                                int totalNewAttack = currentAttack + stuffStats;
                                                mainCharacter.setAttack(totalNewAttack);
                                            } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                                int currentGold = mainCharacter.getGold();
                                                int totalNewGold = currentGold + stuffStats;
                                                mainCharacter.setGold(totalNewGold);
                                            } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                                int currentHealth = mainCharacter.getHealth();
                                                int totalNewHealth = currentHealth + stuffStats;
                                                mainCharacter.setHealth(totalNewHealth);
                                            }
                                            ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                                        } else if (input4.equalsIgnoreCase("no")) {
                                            System.out.println("Chest remains unopened.");
                                        } else if (input4.equalsIgnoreCase("q")) {
                                            break OUTER;
                                        } else {
                                            while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                                System.out.println("You must decide. Will you open the chest or not?");
                                                input4 = keyboard.nextLine();
                                            }
                                        }
                                        System.out.println("Which direction do you want to go?");
                                        input3 = keyboard.nextLine().toLowerCase();
                                        break OUTER_1;
                                    default:
                                        // if theres a nothing at new location you ran into
                                        System.out.println("You have succesfully ran, Phew!");
                                        break;
                                }
                            } else if (input5.equalsIgnoreCase("bribe")) {//actions for bribe
                                int mainsGold = mainCharacter.getGold();
                                int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                System.out.println("How much gold will you try to bribe the enemy with?");
                                int bribe = keyboard.nextInt();
                                if (bribe > mainsGold) {
                                    System.out.println("It Looks like you don't have the money to back that up..");
                                    while (bribe > mainsGold) {
                                        System.out.println("How much will you bribe, out of how much you have available?");
                                        bribe = keyboard.nextInt();
                                    }
                                }
                                if (bribe > enemyStandards) {//bribe worked
                                    System.out.println("Great! The bribe worked!");
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int totalEnemyGold = enemysGold + bribe;
                                    ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                    int newLocX = x + rand1.nextInt(11);
                                    int newLocY = y + rand2.nextInt(11);
                                    int[] newLoc = {newLocX, newLocY};
                                    if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                        while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                            newLocX = newLocX + rand1.nextInt(11);
                                            newLocY = newLocY + rand2.nextInt(11);
                                            newLoc[0] = newLocX;
                                            newLoc[1] = newLocY;
                                        }
                                        int indexHere = gameBoard.getIndexForObjectsArray();
                                        gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                        gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                        gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                    }

                                } else {//bribe didnt work
                                    System.out.println("It looks like your bribe wasn't enough. \n"
                                            + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                    //fight to the death
                                    Person attacker;
                                    Person defender;
                                    Person temporaryHolder;
                                    if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    } else {
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    }
                                    while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                        int damage = defender.getDefense() - attacker.getAttack();
                                        if (damage < 0) {//if they broke through the defenders defenses
                                            int newDefHealth = defender.getHealth() + damage;
                                            defender.setHealth(newDefHealth);//set health to health minus damage
                                        }
                                        temporaryHolder = defender;//trading places to give next person their turn
                                        defender = attacker;
                                        attacker = defender;
                                    }
                                    if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                        int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                        int newTotalGold = mainCharacter.getGold() + enemysGold;
                                        mainCharacter.setGold(newTotalGold);
                                        System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                    } else if (mainCharacter.getHealth() <= 0) {
                                        System.out.println("You have lost and are now dead... \n"
                                                + " but if you'd like to play again just press anything.\n"
                                                + "If you would like to quit press 'q' now.");
                                        break OUTER;
                                    } else {
                                        System.out.println("error");
                                    }

                                }

                            }//end of bribe
                            else if (input5.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                System.out.println("I'm sorry, but your options are limited here..");
                            }
                            if (mainCharacter.getHealth() <= 0) {//if main character is dead
                                System.out.println("You have died. \n"
                                        + " If you're ready to play again just enter anything, but if you want to exit enter 'q'.");
                                input6 = keyboard.nextLine();
                            } else {//if main character is still alive
                                System.out.println("Which direction do you want to go?");
                                input3 = keyboard.nextLine().toLowerCase();
                            }
                            break;
                        default:
                            System.out.println("Error");
                            break;

                    }
                }// direction ne end
                else if (input3.equalsIgnoreCase("w")) {// direction w start                
                    int x = (gameBoard.getLocation())[0] - 1;
                    int y = (gameBoard.getLocation())[1];
                    gameBoard.setGameBoard(x, y, objects.get(randomizer.nextInt(3)));
                    System.out.println(gameBoard.toString());
                    int ind = gameBoard.getIndexForObjectsArray();
                    int ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                    OUTER_1:
                    switch (ObjectType) {
                        case 0:
                            // here is nothing object
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 1:
                            //chest object
                            System.out.println("Do you want to open the chest? (yes/no)");
                            input4 = keyboard.nextLine();
                            if (input4.equalsIgnoreCase("yes")) {
                                String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                    int currentDef = mainCharacter.getDefense();//if armor item
                                    int totalNewDef = currentDef + stuffStats;
                                    mainCharacter.setDefense(totalNewDef);
                                } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                    int currentAttack = mainCharacter.getAttack();
                                    int totalNewAttack = currentAttack + stuffStats;
                                    mainCharacter.setAttack(totalNewAttack);
                                } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                    int currentGold = mainCharacter.getGold();
                                    int totalNewGold = currentGold + stuffStats;
                                    mainCharacter.setGold(totalNewGold);
                                } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                    int currentHealth = mainCharacter.getHealth();
                                    int totalNewHealth = currentHealth + stuffStats;
                                    mainCharacter.setHealth(totalNewHealth);
                                }
                                ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                            } else if (input4.equalsIgnoreCase("no")) {
                                System.out.println("Chest remains unopened.");
                            } else if (input4.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                    System.out.println("You must decide. Will you open the chest or not?");
                                    input4 = keyboard.nextLine();
                                }
                            }
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 2://Enemy Object
                            System.out.println("You can either fight, bribe, or run. What will you do?");
                            input5 = keyboard.nextLine();
                            while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("run") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                System.out.println("You must either fight the enemy, bribe the enemy, or run from the enemy. \n Trust me he won't listen to reason. \n What will you do? ");
                                input5 = keyboard.nextLine();
                            }
                            if (input5.equalsIgnoreCase("fight")) {
                                Person attacker;
                                Person defender;
                                Person temporaryHolder;
                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                } else {
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                }
                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                    int damage = defender.getDefense() - attacker.getAttack();
                                    if (damage < 0) {//if they broke through the defenders defenses
                                        int newDefHealth = defender.getHealth() + damage;
                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                    }
                                    temporaryHolder = defender;//trading places to give next person their turn
                                    defender = attacker;
                                    attacker = defender;
                                }
                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                    mainCharacter.setGold(newTotalGold);
                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                } else if (mainCharacter.getHealth() <= 0) {
                                    System.out.println("You have lost and are now dead... \n"
                                            + " but if you'd like to play again just press anything.\n"
                                            + "If you would like to quit press 'q' now.");
                                    break OUTER;
                                } else {
                                    System.out.println("error");
                                }
                            } else if (input5.equalsIgnoreCase("run")) {
                                int[] possibleNewLocationsX = {x, x + 1, x - 1};
                                int[] possibleNewLocationsY = {y, y + 1, y - 1};
                                gameBoard.setGameBoard(possibleNewLocationsX[rand1.nextInt(2)], possibleNewLocationsY[rand1.nextInt(2)], objects.get(randomizer.nextInt(3)));
                                System.out.println(gameBoard.toString());
                                ind = gameBoard.getIndexForObjectsArray();
                                ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                                switch (ObjectType) {
                                    case 2:
                                        //if theres a enemy at new location
                                        System.out.println("You have not escaped the enemy. So the run fails.. \n"
                                                + "You have to either bribe or fight this enemy");
                                        input5 = keyboard.nextLine();
                                        while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                            System.out.println("You must either fight the enemy or bribe the enemy \n"
                                                    + "Trust me he won't listen to reason. \n What will you do? ");
                                            input5 = keyboard.nextLine();
                                        }
                                        if (input5.equalsIgnoreCase("fight")) {//actions for fight
                                            //fight to the death
                                            Person attacker;
                                            Person defender;
                                            Person temporaryHolder;
                                            if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            } else {
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            }
                                            while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                int damage = defender.getDefense() - attacker.getAttack();
                                                if (damage < 0) {//if they broke through the defenders defenses
                                                    int newDefHealth = defender.getHealth() + damage;
                                                    defender.setHealth(newDefHealth);//set health to health minus damage
                                                }
                                                temporaryHolder = defender;//trading places to give next person their turn
                                                defender = attacker;
                                                attacker = defender;
                                            }
                                            if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                mainCharacter.setGold(newTotalGold);
                                                System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                            } else if (mainCharacter.getHealth() <= 0) {
                                                System.out.println("You have lost and are now dead... \n"
                                                        + " but if you'd like to play again just press anything.\n"
                                                        + "If you would like to quit press 'q' now.");
                                                break OUTER;
                                            } else {
                                                System.out.println("error");
                                            }
                                            break;
                                        } else if (input5.equalsIgnoreCase("bribe")) {
                                            int mainsGold = mainCharacter.getGold();
                                            int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                            System.out.println("How much gold will you try to bribe the enemy with?");
                                            int bribe = keyboard.nextInt();
                                            if (bribe > mainsGold) {
                                                System.out.println("It Looks like you don't have the money to back that up..");
                                                while (bribe > mainsGold) {
                                                    System.out.println("How much will you bribe, out of how much you have available?");
                                                    bribe = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                }
                                            }
                                            if (bribe > enemyStandards) {//bribe worked
                                                System.out.println("Great! The bribe worked!");
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int totalEnemyGold = enemysGold + bribe;
                                                ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                                int newLocX = x + rand1.nextInt(11);
                                                int newLocY = y + rand2.nextInt(11);
                                                int[] newLoc = {newLocX, newLocY};
                                                if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                                    while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                                        newLocX = newLocX + rand1.nextInt(11);
                                                        newLocY = newLocY + rand2.nextInt(11);
                                                        newLoc[0] = newLocX;
                                                        newLoc[1] = newLocY;
                                                    }
                                                    int indexHere = gameBoard.getIndexForObjectsArray();
                                                    gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                                    gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                                    gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                                }

                                            } else {//bribe didnt work
                                                System.out.println("It looks like your bribe wasn't enough. \n"
                                                        + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                                //fight to the death
                                                Person attacker;
                                                Person defender;
                                                Person temporaryHolder;
                                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                } else {
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                }
                                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                    int damage = defender.getDefense() - attacker.getAttack();
                                                    if (damage < 0) {//if they broke through the defenders defenses
                                                        int newDefHealth = defender.getHealth() + damage;
                                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                                    }
                                                    temporaryHolder = defender;//trading places to give next person their turn
                                                    defender = attacker;
                                                    attacker = defender;
                                                }
                                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                    mainCharacter.setGold(newTotalGold);
                                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                                } else if (mainCharacter.getHealth() <= 0) {
                                                    System.out.println("You have lost and are now dead... \n"
                                                            + " but if you'd like to play again just press anything.\n"
                                                            + "If you would like to quit press 'q' now.");
                                                    break OUTER;
                                                } else {
                                                    System.out.println("error");
                                                }
                                            }
                                        }//end of bribe
                                        //make sure they can still fight or bribe, but not run.
                                        break;
                                    //end of run into chest option
                                    case 1:
                                        // if theres a chest at new location
                                        System.out.println("You ran away from the enemy and straight into a chest!");
                                        System.out.println("Do you want to open the chest? (yes/no)");
                                        input4 = keyboard.nextLine();
                                        if (input4.equalsIgnoreCase("yes")) {
                                            String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                            int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                            System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                            if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                                int currentDef = mainCharacter.getDefense();//if armor item
                                                int totalNewDef = currentDef + stuffStats;
                                                mainCharacter.setDefense(totalNewDef);
                                            } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                                int currentAttack = mainCharacter.getAttack();
                                                int totalNewAttack = currentAttack + stuffStats;
                                                mainCharacter.setAttack(totalNewAttack);
                                            } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                                int currentGold = mainCharacter.getGold();
                                                int totalNewGold = currentGold + stuffStats;
                                                mainCharacter.setGold(totalNewGold);
                                            } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                                int currentHealth = mainCharacter.getHealth();
                                                int totalNewHealth = currentHealth + stuffStats;
                                                mainCharacter.setHealth(totalNewHealth);
                                            }
                                            ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                                        } else if (input4.equalsIgnoreCase("no")) {
                                            System.out.println("Chest remains unopened.");
                                        } else if (input4.equalsIgnoreCase("q")) {
                                            break OUTER;
                                        } else {
                                            while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                                System.out.println("You must decide. Will you open the chest or not?");
                                                input4 = keyboard.nextLine();
                                            }
                                        }
                                        System.out.println("Which direction do you want to go?");
                                        input3 = keyboard.nextLine().toLowerCase();
                                        break OUTER_1;
                                    default:
                                        // if theres a nothing at new location you ran into
                                        System.out.println("You have succesfully ran, Phew!");
                                        break;
                                }
                            } else if (input5.equalsIgnoreCase("bribe")) {//actions for bribe
                                int mainsGold = mainCharacter.getGold();
                                int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                System.out.println("How much gold will you try to bribe the enemy with?");
                                int bribe = keyboard.nextInt();
                                if (bribe > mainsGold) {
                                    System.out.println("It Looks like you don't have the money to back that up..");
                                    while (bribe > mainsGold) {
                                        System.out.println("How much will you bribe, out of how much you have available?");
                                        bribe = keyboard.nextInt();
                                    }
                                }
                                if (bribe > enemyStandards) {//bribe worked
                                    System.out.println("Great! The bribe worked!");
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int totalEnemyGold = enemysGold + bribe;
                                    ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                    int newLocX = x + rand1.nextInt(11);
                                    int newLocY = y + rand2.nextInt(11);
                                    int[] newLoc = {newLocX, newLocY};
                                    if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                        while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                            newLocX = newLocX + rand1.nextInt(11);
                                            newLocY = newLocY + rand2.nextInt(11);
                                            newLoc[0] = newLocX;
                                            newLoc[1] = newLocY;
                                        }
                                        int indexHere = gameBoard.getIndexForObjectsArray();
                                        gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                        gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                        gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                    }

                                } else {//bribe didnt work
                                    System.out.println("It looks like your bribe wasn't enough. \n"
                                            + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                    //fight to the death
                                    Person attacker;
                                    Person defender;
                                    Person temporaryHolder;
                                    if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    } else {
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    }
                                    while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                        int damage = defender.getDefense() - attacker.getAttack();
                                        if (damage < 0) {//if they broke through the defenders defenses
                                            int newDefHealth = defender.getHealth() + damage;
                                            defender.setHealth(newDefHealth);//set health to health minus damage
                                        }
                                        temporaryHolder = defender;//trading places to give next person their turn
                                        defender = attacker;
                                        attacker = defender;
                                    }
                                    if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                        int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                        int newTotalGold = mainCharacter.getGold() + enemysGold;
                                        mainCharacter.setGold(newTotalGold);
                                        System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                    } else if (mainCharacter.getHealth() <= 0) {
                                        System.out.println("You have lost and are now dead... \n"
                                                + " but if you'd like to play again just press anything.\n"
                                                + "If you would like to quit press 'q' now.");
                                        break OUTER;
                                    } else {
                                        System.out.println("error");
                                    }

                                }

                            }//end of bribe
                            else if (input5.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                System.out.println("I'm sorry, but your options are limited here..");
                            }
                            if (mainCharacter.getHealth() <= 0) {//if main character is dead
                                System.out.println("You have died. \n"
                                        + " If you're ready to play again just enter anything, but if you want to exit enter 'q'.");
                                input6 = keyboard.nextLine();
                            } else {//if main character is still alive
                                System.out.println("Which direction do you want to go?");
                                input3 = keyboard.nextLine().toLowerCase();
                            }
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }
                }//direction w end
                else if (input3.equalsIgnoreCase("e")) {// direction e start   
                    int x = (gameBoard.getLocation())[0] + 1;
                    int y = (gameBoard.getLocation())[1];
                    gameBoard.setGameBoard(x, y, objects.get(randomizer.nextInt(3)));
                    System.out.println(gameBoard.toString());
                    int ind = gameBoard.getIndexForObjectsArray();
                    int ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                    OUTER_1:
                    switch (ObjectType) {
                        case 0:
                            // here is nothing object
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 1:
                            //chest object
                            System.out.println("Do you want to open the chest? (yes/no)");
                            input4 = keyboard.nextLine();
                            if (input4.equalsIgnoreCase("yes")) {
                                String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                    int currentDef = mainCharacter.getDefense();//if armor item
                                    int totalNewDef = currentDef + stuffStats;
                                    mainCharacter.setDefense(totalNewDef);
                                } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                    int currentAttack = mainCharacter.getAttack();
                                    int totalNewAttack = currentAttack + stuffStats;
                                    mainCharacter.setAttack(totalNewAttack);
                                } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                    int currentGold = mainCharacter.getGold();
                                    int totalNewGold = currentGold + stuffStats;
                                    mainCharacter.setGold(totalNewGold);
                                } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                    int currentHealth = mainCharacter.getHealth();
                                    int totalNewHealth = currentHealth + stuffStats;
                                    mainCharacter.setHealth(totalNewHealth);
                                }
                                ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                            } else if (input4.equalsIgnoreCase("no")) {
                                System.out.println("Chest remains unopened.");
                            } else if (input4.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                    System.out.println("You must decide. Will you open the chest or not?");
                                    input4 = keyboard.nextLine();
                                }
                            }
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 2://Enemy Object
                            System.out.println("You can either fight, bribe, or run. What will you do?");
                            input5 = keyboard.nextLine();
                            while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("run") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                System.out.println("You must either fight the enemy, bribe the enemy, or run from the enemy. \n Trust me he won't listen to reason. \n What will you do? ");
                                input5 = keyboard.nextLine();
                            }
                            if (input5.equalsIgnoreCase("fight")) {
                                Person attacker;
                                Person defender;
                                Person temporaryHolder;
                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                } else {
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                }
                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                    int damage = defender.getDefense() - attacker.getAttack();
                                    if (damage < 0) {//if they broke through the defenders defenses
                                        int newDefHealth = defender.getHealth() + damage;
                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                    }
                                    temporaryHolder = defender;//trading places to give next person their turn
                                    defender = attacker;
                                    attacker = defender;
                                }
                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                    mainCharacter.setGold(newTotalGold);
                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                } else if (mainCharacter.getHealth() <= 0) {
                                    System.out.println("You have lost and are now dead... \n"
                                            + " but if you'd like to play again just press anything.\n"
                                            + "If you would like to quit press 'q' now.");
                                    break OUTER;
                                } else {
                                    System.out.println("error");
                                }
                            } else if (input5.equalsIgnoreCase("run")) {
                                int[] possibleNewLocationsX = {x, x + 1, x - 1};
                                int[] possibleNewLocationsY = {y, y + 1, y - 1};
                                gameBoard.setGameBoard(possibleNewLocationsX[rand1.nextInt(2)], possibleNewLocationsY[rand1.nextInt(2)], objects.get(randomizer.nextInt(3)));
                                System.out.println(gameBoard.toString());
                                ind = gameBoard.getIndexForObjectsArray();
                                ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                                switch (ObjectType) {
                                    case 2:
                                        //if theres a enemy at new location
                                        System.out.println("You have not escaped the enemy. So the run fails.. \n"
                                                + "You have to either bribe or fight this enemy");
                                        input5 = keyboard.nextLine();
                                        while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                            System.out.println("You must either fight the enemy or bribe the enemy \n"
                                                    + "Trust me he won't listen to reason. \n What will you do? ");
                                            input5 = keyboard.nextLine();
                                        }
                                        if (input5.equalsIgnoreCase("fight")) {//actions for fight
                                            //fight to the death
                                            Person attacker;
                                            Person defender;
                                            Person temporaryHolder;
                                            if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            } else {
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            }
                                            while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                int damage = defender.getDefense() - attacker.getAttack();
                                                if (damage < 0) {//if they broke through the defenders defenses
                                                    int newDefHealth = defender.getHealth() + damage;
                                                    defender.setHealth(newDefHealth);//set health to health minus damage
                                                }
                                                temporaryHolder = defender;//trading places to give next person their turn
                                                defender = attacker;
                                                attacker = defender;
                                            }
                                            if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                mainCharacter.setGold(newTotalGold);
                                                System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                            } else if (mainCharacter.getHealth() <= 0) {
                                                System.out.println("You have lost and are now dead... \n"
                                                        + " but if you'd like to play again just press anything.\n"
                                                        + "If you would like to quit press 'q' now.");
                                                break OUTER;
                                            } else {
                                                System.out.println("error");
                                            }
                                            break;
                                        } else if (input5.equalsIgnoreCase("bribe")) {
                                            int mainsGold = mainCharacter.getGold();
                                            int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                            System.out.println("How much gold will you try to bribe the enemy with?");
                                            int bribe = keyboard.nextInt();
                                            if (bribe > mainsGold) {
                                                System.out.println("It Looks like you don't have the money to back that up..");
                                                while (bribe > mainsGold) {
                                                    System.out.println("How much will you bribe, out of how much you have available?");
                                                    bribe = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                }
                                            }
                                            if (bribe > enemyStandards) {//bribe worked
                                                System.out.println("Great! The bribe worked!");
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int totalEnemyGold = enemysGold + bribe;
                                                ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                                int newLocX = x + rand1.nextInt(11);
                                                int newLocY = y + rand2.nextInt(11);
                                                int[] newLoc = {newLocX, newLocY};
                                                if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                                    while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                                        newLocX = newLocX + rand1.nextInt(11);
                                                        newLocY = newLocY + rand2.nextInt(11);
                                                        newLoc[0] = newLocX;
                                                        newLoc[1] = newLocY;
                                                    }
                                                    int indexHere = gameBoard.getIndexForObjectsArray();
                                                    gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                                    gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                                    gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                                }

                                            } else {//bribe didnt work
                                                System.out.println("It looks like your bribe wasn't enough. \n"
                                                        + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                                //fight to the death
                                                Person attacker;
                                                Person defender;
                                                Person temporaryHolder;
                                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                } else {
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                }
                                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                    int damage = defender.getDefense() - attacker.getAttack();
                                                    if (damage < 0) {//if they broke through the defenders defenses
                                                        int newDefHealth = defender.getHealth() + damage;
                                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                                    }
                                                    temporaryHolder = defender;//trading places to give next person their turn
                                                    defender = attacker;
                                                    attacker = defender;
                                                }
                                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                    mainCharacter.setGold(newTotalGold);
                                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                                } else if (mainCharacter.getHealth() <= 0) {
                                                    System.out.println("You have lost and are now dead... \n"
                                                            + " but if you'd like to play again just press anything.\n"
                                                            + "If you would like to quit press 'q' now.");
                                                    break OUTER;
                                                } else {
                                                    System.out.println("error");
                                                }
                                            }
                                        }//end of bribe
                                        //make sure they can still fight or bribe, but not run.
                                        break;
                                    //end of run into chest option
                                    case 1:
                                        // if theres a chest at new location
                                        System.out.println("You ran away from the enemy and straight into a chest!");
                                        System.out.println("Do you want to open the chest? (yes/no)");
                                        input4 = keyboard.nextLine();
                                        if (input4.equalsIgnoreCase("yes")) {
                                            String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                            int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                            System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                            if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                                int currentDef = mainCharacter.getDefense();//if armor item
                                                int totalNewDef = currentDef + stuffStats;
                                                mainCharacter.setDefense(totalNewDef);
                                            } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                                int currentAttack = mainCharacter.getAttack();
                                                int totalNewAttack = currentAttack + stuffStats;
                                                mainCharacter.setAttack(totalNewAttack);
                                            } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                                int currentGold = mainCharacter.getGold();
                                                int totalNewGold = currentGold + stuffStats;
                                                mainCharacter.setGold(totalNewGold);
                                            } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                                int currentHealth = mainCharacter.getHealth();
                                                int totalNewHealth = currentHealth + stuffStats;
                                                mainCharacter.setHealth(totalNewHealth);
                                            }
                                            ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                                        } else if (input4.equalsIgnoreCase("no")) {
                                            System.out.println("Chest remains unopened.");
                                        } else if (input4.equalsIgnoreCase("q")) {
                                            break OUTER;
                                        } else {
                                            while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                                System.out.println("You must decide. Will you open the chest or not?");
                                                input4 = keyboard.nextLine();
                                            }
                                        }
                                        System.out.println("Which direction do you want to go?");
                                        input3 = keyboard.nextLine().toLowerCase();
                                        break OUTER_1;
                                    default:
                                        // if theres a nothing at new location you ran into
                                        System.out.println("You have succesfully ran, Phew!");
                                        break;
                                }
                            } else if (input5.equalsIgnoreCase("bribe")) {//actions for bribe
                                int mainsGold = mainCharacter.getGold();
                                int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                System.out.println("How much gold will you try to bribe the enemy with?");
                                int bribe = keyboard.nextInt();
                                if (bribe > mainsGold) {
                                    System.out.println("It Looks like you don't have the money to back that up..");
                                    while (bribe > mainsGold) {
                                        System.out.println("How much will you bribe, out of how much you have available?");
                                        bribe = keyboard.nextInt();
                                    }
                                }
                                if (bribe > enemyStandards) {//bribe worked
                                    System.out.println("Great! The bribe worked!");
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int totalEnemyGold = enemysGold + bribe;
                                    ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                    int newLocX = x + rand1.nextInt(11);
                                    int newLocY = y + rand2.nextInt(11);
                                    int[] newLoc = {newLocX, newLocY};
                                    if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                        while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                            newLocX = newLocX + rand1.nextInt(11);
                                            newLocY = newLocY + rand2.nextInt(11);
                                            newLoc[0] = newLocX;
                                            newLoc[1] = newLocY;
                                        }
                                        int indexHere = gameBoard.getIndexForObjectsArray();
                                        gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                        gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                        gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                    }

                                } else {//bribe didnt work
                                    System.out.println("It looks like your bribe wasn't enough. \n"
                                            + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                    //fight to the death
                                    Person attacker;
                                    Person defender;
                                    Person temporaryHolder;
                                    if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    } else {
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    }
                                    while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                        int damage = defender.getDefense() - attacker.getAttack();
                                        if (damage < 0) {//if they broke through the defenders defenses
                                            int newDefHealth = defender.getHealth() + damage;
                                            defender.setHealth(newDefHealth);//set health to health minus damage
                                        }
                                        temporaryHolder = defender;//trading places to give next person their turn
                                        defender = attacker;
                                        attacker = defender;
                                    }
                                    if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                        int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                        int newTotalGold = mainCharacter.getGold() + enemysGold;
                                        mainCharacter.setGold(newTotalGold);
                                        System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                    } else if (mainCharacter.getHealth() <= 0) {
                                        System.out.println("You have lost and are now dead... \n"
                                                + " but if you'd like to play again just press anything.\n"
                                                + "If you would like to quit press 'q' now.");
                                        break OUTER;
                                    } else {
                                        System.out.println("error");
                                    }

                                }

                            }//end of bribe
                            else if (input5.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                System.out.println("I'm sorry, but your options are limited here..");
                            }
                            if (mainCharacter.getHealth() <= 0) {//if main character is dead
                                System.out.println("You have died. \n"
                                        + " If you're ready to play again just enter anything, but if you want to exit enter 'q'.");
                                input6 = keyboard.nextLine();
                            } else {//if main character is still alive
                                System.out.println("Which direction do you want to go?");
                                input3 = keyboard.nextLine().toLowerCase();
                            }
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }
                }// direction e end
                else if (input3.equalsIgnoreCase("sw")) {//direction sw start             
                    int x = (gameBoard.getLocation())[0] - 1;
                    int y = (gameBoard.getLocation())[1] - 1;
                    gameBoard.setGameBoard(x, y, objects.get(randomizer.nextInt(3)));
                    System.out.println(gameBoard.toString());
                    int ind = gameBoard.getIndexForObjectsArray();
                    int ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                    OUTER_1:
                    switch (ObjectType) {
                        case 0:
                            // here is nothing object
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 1:
                            //chest object
                            System.out.println("Do you want to open the chest? (yes/no)");
                            input4 = keyboard.nextLine();
                            if (input4.equalsIgnoreCase("yes")) {
                                String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                    int currentDef = mainCharacter.getDefense();//if armor item
                                    int totalNewDef = currentDef + stuffStats;
                                    mainCharacter.setDefense(totalNewDef);
                                } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                    int currentAttack = mainCharacter.getAttack();
                                    int totalNewAttack = currentAttack + stuffStats;
                                    mainCharacter.setAttack(totalNewAttack);
                                } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                    int currentGold = mainCharacter.getGold();
                                    int totalNewGold = currentGold + stuffStats;
                                    mainCharacter.setGold(totalNewGold);
                                } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                    int currentHealth = mainCharacter.getHealth();
                                    int totalNewHealth = currentHealth + stuffStats;
                                    mainCharacter.setHealth(totalNewHealth);
                                }
                                ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                            } else if (input4.equalsIgnoreCase("no")) {
                                System.out.println("Chest remains unopened.");
                            } else if (input4.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                    System.out.println("You must decide. Will you open the chest or not?");
                                    input4 = keyboard.nextLine();
                                }
                            }
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 2://Enemy Object
                            System.out.println("You can either fight, bribe, or run. What will you do?");
                            input5 = keyboard.nextLine();
                            while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("run") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                System.out.println("You must either fight the enemy, bribe the enemy, or run from the enemy. \n Trust me he won't listen to reason. \n What will you do? ");
                                input5 = keyboard.nextLine();
                            }
                            if (input5.equalsIgnoreCase("fight")) {
                                Person attacker;
                                Person defender;
                                Person temporaryHolder;
                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                } else {
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                }
                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                    int damage = defender.getDefense() - attacker.getAttack();
                                    if (damage < 0) {//if they broke through the defenders defenses
                                        int newDefHealth = defender.getHealth() + damage;
                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                    }
                                    temporaryHolder = defender;//trading places to give next person their turn
                                    defender = attacker;
                                    attacker = defender;
                                }
                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                    mainCharacter.setGold(newTotalGold);
                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                } else if (mainCharacter.getHealth() <= 0) {
                                    System.out.println("You have lost and are now dead... \n"
                                            + " but if you'd like to play again just press anything.\n"
                                            + "If you would like to quit press 'q' now.");
                                    break OUTER;
                                } else {
                                    System.out.println("error");
                                }
                            } else if (input5.equalsIgnoreCase("run")) {
                                int[] possibleNewLocationsX = {x, x + 1, x - 1};
                                int[] possibleNewLocationsY = {y, y + 1, y - 1};
                                gameBoard.setGameBoard(possibleNewLocationsX[rand1.nextInt(2)], possibleNewLocationsY[rand1.nextInt(2)], objects.get(randomizer.nextInt(3)));
                                System.out.println(gameBoard.toString());
                                ind = gameBoard.getIndexForObjectsArray();
                                ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                                switch (ObjectType) {
                                    case 2:
                                        //if theres a enemy at new location
                                        System.out.println("You have not escaped the enemy. So the run fails.. \n"
                                                + "You have to either bribe or fight this enemy");
                                        input5 = keyboard.nextLine();
                                        while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                            System.out.println("You must either fight the enemy or bribe the enemy \n"
                                                    + "Trust me he won't listen to reason. \n What will you do? ");
                                            input5 = keyboard.nextLine();
                                        }
                                        if (input5.equalsIgnoreCase("fight")) {//actions for fight
                                            //fight to the death
                                            Person attacker;
                                            Person defender;
                                            Person temporaryHolder;
                                            if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            } else {
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            }
                                            while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                int damage = defender.getDefense() - attacker.getAttack();
                                                if (damage < 0) {//if they broke through the defenders defenses
                                                    int newDefHealth = defender.getHealth() + damage;
                                                    defender.setHealth(newDefHealth);//set health to health minus damage
                                                }
                                                temporaryHolder = defender;//trading places to give next person their turn
                                                defender = attacker;
                                                attacker = defender;
                                            }
                                            if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                mainCharacter.setGold(newTotalGold);
                                                System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                            } else if (mainCharacter.getHealth() <= 0) {
                                                System.out.println("You have lost and are now dead... \n"
                                                        + " but if you'd like to play again just press anything.\n"
                                                        + "If you would like to quit press 'q' now.");
                                                break OUTER;
                                            } else {
                                                System.out.println("error");
                                            }
                                            break;
                                        } else if (input5.equalsIgnoreCase("bribe")) {
                                            int mainsGold = mainCharacter.getGold();
                                            int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                            System.out.println("How much gold will you try to bribe the enemy with?");
                                            int bribe = keyboard.nextInt();
                                            if (bribe > mainsGold) {
                                                System.out.println("It Looks like you don't have the money to back that up..");
                                                while (bribe > mainsGold) {
                                                    System.out.println("How much will you bribe, out of how much you have available?");
                                                    bribe = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                }
                                            }
                                            if (bribe > enemyStandards) {//bribe worked
                                                System.out.println("Great! The bribe worked!");
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int totalEnemyGold = enemysGold + bribe;
                                                ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                                int newLocX = x + rand1.nextInt(11);
                                                int newLocY = y + rand2.nextInt(11);
                                                int[] newLoc = {newLocX, newLocY};
                                                if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                                    while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                                        newLocX = newLocX + rand1.nextInt(11);
                                                        newLocY = newLocY + rand2.nextInt(11);
                                                        newLoc[0] = newLocX;
                                                        newLoc[1] = newLocY;
                                                    }
                                                    int indexHere = gameBoard.getIndexForObjectsArray();
                                                    gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                                    gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                                    gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                                }

                                            } else {//bribe didnt work
                                                System.out.println("It looks like your bribe wasn't enough. \n"
                                                        + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                                //fight to the death
                                                Person attacker;
                                                Person defender;
                                                Person temporaryHolder;
                                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                } else {
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                }
                                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                    int damage = defender.getDefense() - attacker.getAttack();
                                                    if (damage < 0) {//if they broke through the defenders defenses
                                                        int newDefHealth = defender.getHealth() + damage;
                                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                                    }
                                                    temporaryHolder = defender;//trading places to give next person their turn
                                                    defender = attacker;
                                                    attacker = defender;
                                                }
                                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                    mainCharacter.setGold(newTotalGold);
                                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                                } else if (mainCharacter.getHealth() <= 0) {
                                                    System.out.println("You have lost and are now dead... \n"
                                                            + " but if you'd like to play again just press anything.\n"
                                                            + "If you would like to quit press 'q' now.");
                                                    break OUTER;
                                                } else {
                                                    System.out.println("error");
                                                }
                                            }
                                        }//end of bribe
                                        //make sure they can still fight or bribe, but not run.
                                        break;
                                    //end of run into chest option
                                    case 1:
                                        // if theres a chest at new location
                                        System.out.println("You ran away from the enemy and straight into a chest!");
                                        System.out.println("Do you want to open the chest? (yes/no)");
                                        input4 = keyboard.nextLine();
                                        if (input4.equalsIgnoreCase("yes")) {
                                            String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                            int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                            System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                            if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                                int currentDef = mainCharacter.getDefense();//if armor item
                                                int totalNewDef = currentDef + stuffStats;
                                                mainCharacter.setDefense(totalNewDef);
                                            } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                                int currentAttack = mainCharacter.getAttack();
                                                int totalNewAttack = currentAttack + stuffStats;
                                                mainCharacter.setAttack(totalNewAttack);
                                            } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                                int currentGold = mainCharacter.getGold();
                                                int totalNewGold = currentGold + stuffStats;
                                                mainCharacter.setGold(totalNewGold);
                                            } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                                int currentHealth = mainCharacter.getHealth();
                                                int totalNewHealth = currentHealth + stuffStats;
                                                mainCharacter.setHealth(totalNewHealth);
                                            }
                                            ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                                        } else if (input4.equalsIgnoreCase("no")) {
                                            System.out.println("Chest remains unopened.");
                                        } else if (input4.equalsIgnoreCase("q")) {
                                            break OUTER;
                                        } else {
                                            while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                                System.out.println("You must decide. Will you open the chest or not?");
                                                input4 = keyboard.nextLine();
                                            }
                                        }
                                        System.out.println("Which direction do you want to go?");
                                        input3 = keyboard.nextLine().toLowerCase();
                                        break OUTER_1;
                                    default:
                                        // if theres a nothing at new location you ran into
                                        System.out.println("You have succesfully ran, Phew!");
                                        break;
                                }
                            } else if (input5.equalsIgnoreCase("bribe")) {//actions for bribe
                                int mainsGold = mainCharacter.getGold();
                                int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                System.out.println("How much gold will you try to bribe the enemy with?");
                                int bribe = keyboard.nextInt();
                                if (bribe > mainsGold) {
                                    System.out.println("It Looks like you don't have the money to back that up..");
                                    while (bribe > mainsGold) {
                                        System.out.println("How much will you bribe, out of how much you have available?");
                                        bribe = keyboard.nextInt();
                                    }
                                }
                                if (bribe > enemyStandards) {//bribe worked
                                    System.out.println("Great! The bribe worked!");
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int totalEnemyGold = enemysGold + bribe;
                                    ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                    int newLocX = x + rand1.nextInt(11);
                                    int newLocY = y + rand2.nextInt(11);
                                    int[] newLoc = {newLocX, newLocY};
                                    if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                        while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                            newLocX = newLocX + rand1.nextInt(11);
                                            newLocY = newLocY + rand2.nextInt(11);
                                            newLoc[0] = newLocX;
                                            newLoc[1] = newLocY;
                                        }
                                        int indexHere = gameBoard.getIndexForObjectsArray();
                                        gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                        gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                        gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                    }

                                } else {//bribe didnt work
                                    System.out.println("It looks like your bribe wasn't enough. \n"
                                            + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                    //fight to the death
                                    Person attacker;
                                    Person defender;
                                    Person temporaryHolder;
                                    if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    } else {
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    }
                                    while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                        int damage = defender.getDefense() - attacker.getAttack();
                                        if (damage < 0) {//if they broke through the defenders defenses
                                            int newDefHealth = defender.getHealth() + damage;
                                            defender.setHealth(newDefHealth);//set health to health minus damage
                                        }
                                        temporaryHolder = defender;//trading places to give next person their turn
                                        defender = attacker;
                                        attacker = defender;
                                    }
                                    if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                        int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                        int newTotalGold = mainCharacter.getGold() + enemysGold;
                                        mainCharacter.setGold(newTotalGold);
                                        System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                    } else if (mainCharacter.getHealth() <= 0) {
                                        System.out.println("You have lost and are now dead... \n"
                                                + " but if you'd like to play again just press anything.\n"
                                                + "If you would like to quit press 'q' now.");
                                        break OUTER;
                                    } else {
                                        System.out.println("error");
                                    }

                                }

                            }//end of bribe
                            else if (input5.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                System.out.println("I'm sorry, but your options are limited here..");
                            }
                            if (mainCharacter.getHealth() <= 0) {//if main character is dead
                                System.out.println("You have died. \n"
                                        + " If you're ready to play again just enter anything, but if you want to exit enter 'q'.");
                                input6 = keyboard.nextLine();
                            } else {//if main character is still alive
                                System.out.println("Which direction do you want to go?");
                                input3 = keyboard.nextLine().toLowerCase();
                            }
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }
                }//direction sw end
                else if (input3.equalsIgnoreCase("s")) {//direction s start     
                    int x = (gameBoard.getLocation())[0];
                    int y = (gameBoard.getLocation())[1] - 1;
                    gameBoard.setGameBoard(x, y, objects.get(randomizer.nextInt(3)));
                    System.out.println(gameBoard.toString());
                    int ind = gameBoard.getIndexForObjectsArray();
                    int ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                    OUTER_1:
                    switch (ObjectType) {
                        case 0:
                            // here is nothing object
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 1:
                            //chest object
                            System.out.println("Do you want to open the chest? (yes/no)");
                            input4 = keyboard.nextLine();
                            if (input4.equalsIgnoreCase("yes")) {
                                String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                    int currentDef = mainCharacter.getDefense();//if armor item
                                    int totalNewDef = currentDef + stuffStats;
                                    mainCharacter.setDefense(totalNewDef);
                                } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                    int currentAttack = mainCharacter.getAttack();
                                    int totalNewAttack = currentAttack + stuffStats;
                                    mainCharacter.setAttack(totalNewAttack);
                                } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                    int currentGold = mainCharacter.getGold();
                                    int totalNewGold = currentGold + stuffStats;
                                    mainCharacter.setGold(totalNewGold);
                                } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                    int currentHealth = mainCharacter.getHealth();
                                    int totalNewHealth = currentHealth + stuffStats;
                                    mainCharacter.setHealth(totalNewHealth);
                                }
                                ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                            } else if (input4.equalsIgnoreCase("no")) {
                                System.out.println("Chest remains unopened.");
                            } else if (input4.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                    System.out.println("You must decide. Will you open the chest or not?");
                                    input4 = keyboard.nextLine();
                                }
                            }
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 2://Enemy Object
                            System.out.println("You can either fight, bribe, or run. What will you do?");
                            input5 = keyboard.nextLine();
                            while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("run") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                System.out.println("You must either fight the enemy, bribe the enemy, or run from the enemy. \n Trust me he won't listen to reason. \n What will you do? ");
                                input5 = keyboard.nextLine();
                            }
                            if (input5.equalsIgnoreCase("fight")) {
                                Person attacker;
                                Person defender;
                                Person temporaryHolder;
                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                } else {
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                }
                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                    int damage = defender.getDefense() - attacker.getAttack();
                                    if (damage < 0) {//if they broke through the defenders defenses
                                        int newDefHealth = defender.getHealth() + damage;
                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                    }
                                    temporaryHolder = defender;//trading places to give next person their turn
                                    defender = attacker;
                                    attacker = defender;
                                }
                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                    mainCharacter.setGold(newTotalGold);
                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                } else if (mainCharacter.getHealth() <= 0) {
                                    System.out.println("You have lost and are now dead... \n"
                                            + " but if you'd like to play again just press anything.\n"
                                            + "If you would like to quit press 'q' now.");
                                    break OUTER;
                                } else {
                                    System.out.println("error");
                                }
                            } else if (input5.equalsIgnoreCase("run")) {
                                int[] possibleNewLocationsX = {x, x + 1, x - 1};
                                int[] possibleNewLocationsY = {y, y + 1, y - 1};
                                gameBoard.setGameBoard(possibleNewLocationsX[rand1.nextInt(2)], possibleNewLocationsY[rand1.nextInt(2)], objects.get(randomizer.nextInt(3)));
                                System.out.println(gameBoard.toString());
                                ind = gameBoard.getIndexForObjectsArray();
                                ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                                switch (ObjectType) {
                                    case 2:
                                        //if theres a enemy at new location
                                        System.out.println("You have not escaped the enemy. So the run fails.. \n"
                                                + "You have to either bribe or fight this enemy");
                                        input5 = keyboard.nextLine();
                                        while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                            System.out.println("You must either fight the enemy or bribe the enemy \n"
                                                    + "Trust me he won't listen to reason. \n What will you do? ");
                                            input5 = keyboard.nextLine();
                                        }
                                        if (input5.equalsIgnoreCase("fight")) {//actions for fight
                                            //fight to the death
                                            Person attacker;
                                            Person defender;
                                            Person temporaryHolder;
                                            if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            } else {
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            }
                                            while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                int damage = defender.getDefense() - attacker.getAttack();
                                                if (damage < 0) {//if they broke through the defenders defenses
                                                    int newDefHealth = defender.getHealth() + damage;
                                                    defender.setHealth(newDefHealth);//set health to health minus damage
                                                }
                                                temporaryHolder = defender;//trading places to give next person their turn
                                                defender = attacker;
                                                attacker = defender;
                                            }
                                            if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                mainCharacter.setGold(newTotalGold);
                                                System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                            } else if (mainCharacter.getHealth() <= 0) {
                                                System.out.println("You have lost and are now dead... \n"
                                                        + " but if you'd like to play again just press anything.\n"
                                                        + "If you would like to quit press 'q' now.");
                                                break OUTER;
                                            } else {
                                                System.out.println("error");
                                            }
                                            break;
                                        } else if (input5.equalsIgnoreCase("bribe")) {
                                            int mainsGold = mainCharacter.getGold();
                                            int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                            System.out.println("How much gold will you try to bribe the enemy with?");
                                            int bribe = keyboard.nextInt();
                                            if (bribe > mainsGold) {
                                                System.out.println("It Looks like you don't have the money to back that up..");
                                                while (bribe > mainsGold) {
                                                    System.out.println("How much will you bribe, out of how much you have available?");
                                                    bribe = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                }
                                            }
                                            if (bribe > enemyStandards) {//bribe worked
                                                System.out.println("Great! The bribe worked!");
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int totalEnemyGold = enemysGold + bribe;
                                                ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                                int newLocX = x + rand1.nextInt(11);
                                                int newLocY = y + rand2.nextInt(11);
                                                int[] newLoc = {newLocX, newLocY};
                                                if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                                    while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                                        newLocX = newLocX + rand1.nextInt(11);
                                                        newLocY = newLocY + rand2.nextInt(11);
                                                        newLoc[0] = newLocX;
                                                        newLoc[1] = newLocY;
                                                    }
                                                    int indexHere = gameBoard.getIndexForObjectsArray();
                                                    gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                                    gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                                    gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                                }

                                            } else {//bribe didnt work
                                                System.out.println("It looks like your bribe wasn't enough. \n"
                                                        + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                                //fight to the death
                                                Person attacker;
                                                Person defender;
                                                Person temporaryHolder;
                                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                } else {
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                }
                                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                    int damage = defender.getDefense() - attacker.getAttack();
                                                    if (damage < 0) {//if they broke through the defenders defenses
                                                        int newDefHealth = defender.getHealth() + damage;
                                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                                    }
                                                    temporaryHolder = defender;//trading places to give next person their turn
                                                    defender = attacker;
                                                    attacker = defender;
                                                }
                                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                    mainCharacter.setGold(newTotalGold);
                                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                                } else if (mainCharacter.getHealth() <= 0) {
                                                    System.out.println("You have lost and are now dead... \n"
                                                            + " but if you'd like to play again just press anything.\n"
                                                            + "If you would like to quit press 'q' now.");
                                                    break OUTER;
                                                } else {
                                                    System.out.println("error");
                                                }
                                            }
                                        }//end of bribe
                                        //make sure they can still fight or bribe, but not run.
                                        break;
                                    //end of run into chest option
                                    case 1:
                                        // if theres a chest at new location
                                        System.out.println("You ran away from the enemy and straight into a chest!");
                                        System.out.println("Do you want to open the chest? (yes/no)");
                                        input4 = keyboard.nextLine();
                                        if (input4.equalsIgnoreCase("yes")) {
                                            String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                            int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                            System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                            if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                                int currentDef = mainCharacter.getDefense();//if armor item
                                                int totalNewDef = currentDef + stuffStats;
                                                mainCharacter.setDefense(totalNewDef);
                                            } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                                int currentAttack = mainCharacter.getAttack();
                                                int totalNewAttack = currentAttack + stuffStats;
                                                mainCharacter.setAttack(totalNewAttack);
                                            } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                                int currentGold = mainCharacter.getGold();
                                                int totalNewGold = currentGold + stuffStats;
                                                mainCharacter.setGold(totalNewGold);
                                            } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                                int currentHealth = mainCharacter.getHealth();
                                                int totalNewHealth = currentHealth + stuffStats;
                                                mainCharacter.setHealth(totalNewHealth);
                                            }
                                            ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                                        } else if (input4.equalsIgnoreCase("no")) {
                                            System.out.println("Chest remains unopened.");
                                        } else if (input4.equalsIgnoreCase("q")) {
                                            break OUTER;
                                        } else {
                                            while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                                System.out.println("You must decide. Will you open the chest or not?");
                                                input4 = keyboard.nextLine();
                                            }
                                        }
                                        System.out.println("Which direction do you want to go?");
                                        input3 = keyboard.nextLine().toLowerCase();
                                        break OUTER_1;
                                    default:
                                        // if theres a nothing at new location you ran into
                                        System.out.println("You have succesfully ran, Phew!");
                                        break;
                                }
                            } else if (input5.equalsIgnoreCase("bribe")) {//actions for bribe
                                int mainsGold = mainCharacter.getGold();
                                int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                System.out.println("How much gold will you try to bribe the enemy with?");
                                int bribe = keyboard.nextInt();
                                if (bribe > mainsGold) {
                                    System.out.println("It Looks like you don't have the money to back that up..");
                                    while (bribe > mainsGold) {
                                        System.out.println("How much will you bribe, out of how much you have available?");
                                        bribe = keyboard.nextInt();
                                    }
                                }
                                if (bribe > enemyStandards) {//bribe worked
                                    System.out.println("Great! The bribe worked!");
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int totalEnemyGold = enemysGold + bribe;
                                    ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                    int newLocX = x + rand1.nextInt(11);
                                    int newLocY = y + rand2.nextInt(11);
                                    int[] newLoc = {newLocX, newLocY};
                                    if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                        while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                            newLocX = newLocX + rand1.nextInt(11);
                                            newLocY = newLocY + rand2.nextInt(11);
                                            newLoc[0] = newLocX;
                                            newLoc[1] = newLocY;
                                        }
                                        int indexHere = gameBoard.getIndexForObjectsArray();
                                        gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                        gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                        gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                    }

                                } else {//bribe didnt work
                                    System.out.println("It looks like your bribe wasn't enough. \n"
                                            + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                    //fight to the death
                                    Person attacker;
                                    Person defender;
                                    Person temporaryHolder;
                                    if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    } else {
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    }
                                    while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                        int damage = defender.getDefense() - attacker.getAttack();
                                        if (damage < 0) {//if they broke through the defenders defenses
                                            int newDefHealth = defender.getHealth() + damage;
                                            defender.setHealth(newDefHealth);//set health to health minus damage
                                        }
                                        temporaryHolder = defender;//trading places to give next person their turn
                                        defender = attacker;
                                        attacker = defender;
                                    }
                                    if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                        int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                        int newTotalGold = mainCharacter.getGold() + enemysGold;
                                        mainCharacter.setGold(newTotalGold);
                                        System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                    } else if (mainCharacter.getHealth() <= 0) {
                                        System.out.println("You have lost and are now dead... \n"
                                                + " but if you'd like to play again just press anything.\n"
                                                + "If you would like to quit press 'q' now.");
                                        break OUTER;
                                    } else {
                                        System.out.println("error");
                                    }

                                }

                            }//end of bribe
                            else if (input5.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                System.out.println("I'm sorry, but your options are limited here..");
                            }
                            if (mainCharacter.getHealth() <= 0) {//if main character is dead
                                System.out.println("You have died. \n"
                                        + " If you're ready to play again just enter anything, but if you want to exit enter 'q'.");
                                input6 = keyboard.nextLine();
                            } else {//if main character is still alive
                                System.out.println("Which direction do you want to go?");
                                input3 = keyboard.nextLine().toLowerCase();
                            }
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }
                }//direction s end
                else if (input3.equalsIgnoreCase("se")) {// direction se start
                    int x = (gameBoard.getLocation())[0] + 1;
                    int y = (gameBoard.getLocation())[1] - 1;
                    gameBoard.setGameBoard(x, y, objects.get(randomizer.nextInt(3)));
                    System.out.println(gameBoard.toString());
                    int ind = gameBoard.getIndexForObjectsArray();
                    int ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                    OUTER_1:
                    switch (ObjectType) {
                        case 0:
                            // here is nothing object
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 1:
                            //chest object
                            System.out.println("Do you want to open the chest? (yes/no)");
                            input4 = keyboard.nextLine();
                            if (input4.equalsIgnoreCase("yes")) {
                                String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                    int currentDef = mainCharacter.getDefense();//if armor item
                                    int totalNewDef = currentDef + stuffStats;
                                    mainCharacter.setDefense(totalNewDef);
                                } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                    int currentAttack = mainCharacter.getAttack();
                                    int totalNewAttack = currentAttack + stuffStats;
                                    mainCharacter.setAttack(totalNewAttack);
                                } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                    int currentGold = mainCharacter.getGold();
                                    int totalNewGold = currentGold + stuffStats;
                                    mainCharacter.setGold(totalNewGold);
                                } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                    int currentHealth = mainCharacter.getHealth();
                                    int totalNewHealth = currentHealth + stuffStats;
                                    mainCharacter.setHealth(totalNewHealth);
                                }
                                ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                            } else if (input4.equalsIgnoreCase("no")) {
                                System.out.println("Chest remains unopened.");
                            } else if (input4.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                    System.out.println("You must decide. Will you open the chest or not?");
                                    input4 = keyboard.nextLine();
                                }
                            }
                            System.out.println("Which direction do you want to go?");
                            input3 = keyboard.nextLine().toLowerCase();
                            break;
                        case 2://Enemy Object
                            System.out.println("You can either fight, bribe, or run. What will you do?");
                            input5 = keyboard.nextLine();
                            while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("run") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                System.out.println("You must either fight the enemy, bribe the enemy, or run from the enemy. \n Trust me he won't listen to reason. \n What will you do? ");
                                input5 = keyboard.nextLine();
                            }
                            if (input5.equalsIgnoreCase("fight")) {
                                Person attacker;
                                Person defender;
                                Person temporaryHolder;
                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                } else {
                                    attacker = mainCharacter;//set our attacker and our defenders
                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                }
                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                    int damage = defender.getDefense() - attacker.getAttack();
                                    if (damage < 0) {//if they broke through the defenders defenses
                                        int newDefHealth = defender.getHealth() + damage;
                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                    }
                                    temporaryHolder = defender;//trading places to give next person their turn
                                    defender = attacker;
                                    attacker = defender;
                                }
                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                    mainCharacter.setGold(newTotalGold);
                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                } else if (mainCharacter.getHealth() <= 0) {
                                    System.out.println("You have lost and are now dead... \n"
                                            + " but if you'd like to play again just press anything.\n"
                                            + "If you would like to quit press 'q' now.");
                                    break OUTER;
                                } else {
                                    System.out.println("error");
                                }
                            } else if (input5.equalsIgnoreCase("run")) {
                                int[] possibleNewLocationsX = {x, x + 1, x - 1};
                                int[] possibleNewLocationsY = {y, y + 1, y - 1};
                                gameBoard.setGameBoard(possibleNewLocationsX[rand1.nextInt(2)], possibleNewLocationsY[rand1.nextInt(2)], objects.get(randomizer.nextInt(3)));
                                System.out.println(gameBoard.toString());
                                ind = gameBoard.getIndexForObjectsArray();
                                ObjectType = (gameBoard.getGameBoardObjects(ind)).hashCode();
                                switch (ObjectType) {
                                    case 2:
                                        //if theres a enemy at new location
                                        System.out.println("You have not escaped the enemy. So the run fails.. \n"
                                                + "You have to either bribe or fight this enemy");
                                        input5 = keyboard.nextLine();
                                        while (input5.equalsIgnoreCase("fight") == false & input5.equalsIgnoreCase("bribe") == false & input5.equalsIgnoreCase("q") == false) {
                                            System.out.println("You must either fight the enemy or bribe the enemy \n"
                                                    + "Trust me he won't listen to reason. \n What will you do? ");
                                            input5 = keyboard.nextLine();
                                        }
                                        if (input5.equalsIgnoreCase("fight")) {//actions for fight
                                            //fight to the death
                                            Person attacker;
                                            Person defender;
                                            Person temporaryHolder;
                                            if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            } else {
                                                attacker = mainCharacter;//set our attacker and our defenders
                                                defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                            }
                                            while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                int damage = defender.getDefense() - attacker.getAttack();
                                                if (damage < 0) {//if they broke through the defenders defenses
                                                    int newDefHealth = defender.getHealth() + damage;
                                                    defender.setHealth(newDefHealth);//set health to health minus damage
                                                }
                                                temporaryHolder = defender;//trading places to give next person their turn
                                                defender = attacker;
                                                attacker = defender;
                                            }
                                            if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                mainCharacter.setGold(newTotalGold);
                                                System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                            } else if (mainCharacter.getHealth() <= 0) {
                                                System.out.println("You have lost and are now dead... \n"
                                                        + " but if you'd like to play again just press anything.\n"
                                                        + "If you would like to quit press 'q' now.");
                                                break OUTER;
                                            } else {
                                                System.out.println("error");
                                            }
                                            break;
                                        } else if (input5.equalsIgnoreCase("bribe")) {
                                            int mainsGold = mainCharacter.getGold();
                                            int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                            System.out.println("How much gold will you try to bribe the enemy with?");
                                            int bribe = keyboard.nextInt();
                                            if (bribe > mainsGold) {
                                                System.out.println("It Looks like you don't have the money to back that up..");
                                                while (bribe > mainsGold) {
                                                    System.out.println("How much will you bribe, out of how much you have available?");
                                                    bribe = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                }
                                            }
                                            if (bribe > enemyStandards) {//bribe worked
                                                System.out.println("Great! The bribe worked!");
                                                int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                int totalEnemyGold = enemysGold + bribe;
                                                ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                                int newLocX = x + rand1.nextInt(11);
                                                int newLocY = y + rand2.nextInt(11);
                                                int[] newLoc = {newLocX, newLocY};
                                                if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                                    while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                                        newLocX = newLocX + rand1.nextInt(11);
                                                        newLocY = newLocY + rand2.nextInt(11);
                                                        newLoc[0] = newLocX;
                                                        newLoc[1] = newLocY;
                                                    }
                                                    int indexHere = gameBoard.getIndexForObjectsArray();
                                                    gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                                    gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                                    gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                                }

                                            } else {//bribe didnt work
                                                System.out.println("It looks like your bribe wasn't enough. \n"
                                                        + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                                //fight to the death
                                                Person attacker;
                                                Person defender;
                                                Person temporaryHolder;
                                                if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                } else {
                                                    attacker = mainCharacter;//set our attacker and our defenders
                                                    defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                                }
                                                while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                                    int damage = defender.getDefense() - attacker.getAttack();
                                                    if (damage < 0) {//if they broke through the defenders defenses
                                                        int newDefHealth = defender.getHealth() + damage;
                                                        defender.setHealth(newDefHealth);//set health to health minus damage
                                                    }
                                                    temporaryHolder = defender;//trading places to give next person their turn
                                                    defender = attacker;
                                                    attacker = defender;
                                                }
                                                if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                                    int newTotalGold = mainCharacter.getGold() + enemysGold;
                                                    mainCharacter.setGold(newTotalGold);
                                                    System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                                } else if (mainCharacter.getHealth() <= 0) {
                                                    System.out.println("You have lost and are now dead... \n"
                                                            + " but if you'd like to play again just press anything.\n"
                                                            + "If you would like to quit press 'q' now.");
                                                    break OUTER;
                                                } else {
                                                    System.out.println("error");
                                                }
                                            }
                                        }//end of bribe
                                        //make sure they can still fight or bribe, but not run.
                                        break;
                                    //end of run into chest option
                                    case 1:
                                        // if theres a chest at new location
                                        System.out.println("You ran away from the enemy and straight into a chest!");
                                        System.out.println("Do you want to open the chest? (yes/no)");
                                        input4 = keyboard.nextLine();
                                        if (input4.equalsIgnoreCase("yes")) {
                                            String stuff = ((Chest) (gameBoard.getGameBoardObjects(ind))).getWhatsInChest();
                                            int stuffStats = ((Chest) (gameBoard.getGameBoardObjects(ind))).stuff();
                                            System.out.println("Inside the chest is " + stuff + " with stats of " + stuffStats);//casting array object to chest and then opening of the chest
                                            if (stuff.equalsIgnoreCase("armor")) {//character gets stuff in chest and accounts for all possible chest items
                                                int currentDef = mainCharacter.getDefense();//if armor item
                                                int totalNewDef = currentDef + stuffStats;
                                                mainCharacter.setDefense(totalNewDef);
                                            } else if (stuff.equalsIgnoreCase("weapon")) {//if weapon item
                                                int currentAttack = mainCharacter.getAttack();
                                                int totalNewAttack = currentAttack + stuffStats;
                                                mainCharacter.setAttack(totalNewAttack);
                                            } else if (stuff.equalsIgnoreCase("gold")) {//if gold
                                                int currentGold = mainCharacter.getGold();
                                                int totalNewGold = currentGold + stuffStats;
                                                mainCharacter.setGold(totalNewGold);
                                            } else if (stuff.equalsIgnoreCase("healthpotion")) {//if healthpotion
                                                int currentHealth = mainCharacter.getHealth();
                                                int totalNewHealth = currentHealth + stuffStats;
                                                mainCharacter.setHealth(totalNewHealth);
                                            }
                                            ((Chest) (gameBoard.getGameBoardObjects(ind))).setSelection(0);//leave chest empty
                                        } else if (input4.equalsIgnoreCase("no")) {
                                            System.out.println("Chest remains unopened.");
                                        } else if (input4.equalsIgnoreCase("q")) {
                                            break OUTER;
                                        } else {
                                            while (input4.equalsIgnoreCase("yes") == false && input4.equalsIgnoreCase("no") == false && input4.equalsIgnoreCase("q") == false) {
                                                System.out.println("You must decide. Will you open the chest or not?");
                                                input4 = keyboard.nextLine();
                                            }
                                        }
                                        System.out.println("Which direction do you want to go?");
                                        input3 = keyboard.nextLine().toLowerCase();
                                        break OUTER_1;
                                    default:
                                        // if theres a nothing at new location you ran into
                                        System.out.println("You have succesfully ran, Phew!");
                                        break;
                                }
                            } else if (input5.equalsIgnoreCase("bribe")) {//actions for bribe
                                int mainsGold = mainCharacter.getGold();
                                int enemyStandards = rand2.nextInt(81) + 20;//value that the bribe must be over to be succesful
                                System.out.println("How much gold will you try to bribe the enemy with?");
                                int bribe = keyboard.nextInt();
                                if (bribe > mainsGold) {
                                    System.out.println("It Looks like you don't have the money to back that up..");
                                    while (bribe > mainsGold) {
                                        System.out.println("How much will you bribe, out of how much you have available?");
                                        bribe = keyboard.nextInt();
                                    }
                                }
                                if (bribe > enemyStandards) {//bribe worked
                                    System.out.println("Great! The bribe worked!");
                                    int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                    int totalEnemyGold = enemysGold + bribe;
                                    ((Enemy) (gameBoard.getGameBoardObjects(ind))).setGold(totalEnemyGold);//enemy has bribe now
                                    int newLocX = x + rand1.nextInt(11);
                                    int newLocY = y + rand2.nextInt(11);
                                    int[] newLoc = {newLocX, newLocY};
                                    if (gameBoard.hasIndexForObjectsArray(newLoc)) { //if there is something at this location already
                                        while (gameBoard.hasIndexForObjectsArray(newLoc)) {
                                            newLocX = newLocX + rand1.nextInt(11);
                                            newLocY = newLocY + rand2.nextInt(11);
                                            newLoc[0] = newLocX;
                                            newLoc[1] = newLocY;
                                        }
                                        int indexHere = gameBoard.getIndexForObjectsArray();
                                        gameBoard.setGameBoard(newLocX, newLocY, ((Enemy) (gameBoard.getGameBoardObjects(ind))));//moves the enemy object to new location
                                        gameBoard.setLocation(x, y);//puts back the location of mainCharacter that setGameBoard messed up just now
                                        gameBoard.clearGameBoardOfObject(indexHere, new Nothing());//clear the space the object was at  
                                    }

                                } else {//bribe didnt work
                                    System.out.println("It looks like your bribe wasn't enough. \n"
                                            + "You can no longer run away or bribe. You and the enemy are going to fight!\n");
                                    //fight to the death
                                    Person attacker;
                                    Person defender;
                                    Person temporaryHolder;
                                    if (rand1.nextInt(2) == 0) {//coin flip for who start the battle off
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    } else {
                                        attacker = mainCharacter;//set our attacker and our defenders
                                        defender = ((Enemy) (gameBoard.getGameBoardObjects(ind)));
                                    }
                                    while (mainCharacter.getHealth() > 0 & ((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth() > 0) {//the battle
                                        int damage = defender.getDefense() - attacker.getAttack();
                                        if (damage < 0) {//if they broke through the defenders defenses
                                            int newDefHealth = defender.getHealth() + damage;
                                            defender.setHealth(newDefHealth);//set health to health minus damage
                                        }
                                        temporaryHolder = defender;//trading places to give next person their turn
                                        defender = attacker;
                                        attacker = defender;
                                    }
                                    if (((((Enemy) (gameBoard.getGameBoardObjects(ind))).getHealth()) <= 0)) {
                                        int enemysGold = (((Enemy) (gameBoard.getGameBoardObjects(ind))).getGold());
                                        int newTotalGold = mainCharacter.getGold() + enemysGold;
                                        mainCharacter.setGold(newTotalGold);
                                        System.out.println("Congratulations, you have won the battle! You now have aquired all of your enemies gold.");
                                    } else if (mainCharacter.getHealth() <= 0) {
                                        System.out.println("You have lost and are now dead... \n"
                                                + " but if you'd like to play again just press anything.\n"
                                                + "If you would like to quit press 'q' now.");
                                        break OUTER;
                                    } else {
                                        System.out.println("error");
                                    }

                                }

                            }//end of bribe
                            else if (input5.equalsIgnoreCase("q")) {
                                break OUTER;
                            } else {
                                System.out.println("I'm sorry, but your options are limited here..");
                            }
                            if (mainCharacter.getHealth() <= 0) {//if main character is dead
                                System.out.println("You have died. \n"
                                        + " If you're ready to play again just enter anything, but if you want to exit enter 'q'.");
                                input6 = keyboard.nextLine();
                            } else {//if main character is still alive
                                System.out.println("Which direction do you want to go?");
                                input3 = keyboard.nextLine().toLowerCase();
                            }
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }
                }//direction se end
                else if (input3.equals("x")) {// if they want their stats
                    System.out.println(mainCharacter.toString());
                    System.out.println();
                    System.out.println("Which direction do you want to go?");
                    input3 = keyboard.nextLine().toLowerCase();
                } else if (input3.equalsIgnoreCase("q")) {// if they want to quit
                    break;
                } else {// if they dont put any of the above ill act as though they entered help
                    System.out.print("To move, type the abbreviation of the direction." + "\n"
                            + "nw     n     ne" + "\n"
                            + "w      x     e          x means display stats" + "\n"
                            + "sw     s     se");
                    System.out.println();
                    System.out.println("Which direction do you want to go?");
                    input3 = keyboard.nextLine().toLowerCase();
                }
            } while (mainCharacter.getHealth() > 0 & mainCharacter.getGold() < 1000
                    & input1.equalsIgnoreCase("q") == false && input2.equalsIgnoreCase("q") == false
                    && input3.equalsIgnoreCase("q") == false && input4.equalsIgnoreCase("q") == false
                    && input5.equalsIgnoreCase("q") == false && input6.equalsIgnoreCase("q") == false);

        } while (input1.equalsIgnoreCase("q") == false && input2.equalsIgnoreCase("q") == false
                && input3.equalsIgnoreCase("q") == false && input4.equalsIgnoreCase("q") == false
                && input5.equalsIgnoreCase("q") == false && input6.equalsIgnoreCase("q") == false);//add && conditions here to check for q in user input if more inputs are made
        System.out.println("Do you want to save your game?");//gonna use seralization to save once the rest of the program works
        if (keyboard.nextLine().toLowerCase().equals("yes")) {
            System.out.println("Okay I'll work on that");//save the game
        } else {
            System.out.println("Till next time!");
        }
    }

}
