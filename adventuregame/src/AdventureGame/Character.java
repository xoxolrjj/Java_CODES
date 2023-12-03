 package AdventureGame;

import java.util.Random;
import java.util.Scanner;

public class Character {
    String name;
    int health;
    int attack;

    public Character(String name, int health, int attack){
        this.name = name;
        this.health = health;
        this.attack = attack;
    }
    public void takeDamage(int damage){
        health -= damage;
     }
     public boolean isAlive(){
        return health > 0;
     }
}

class Enemy extends Character{
        public Enemy(String name, int maxHealth, int attack){
            super(name, new Random().nextInt(maxHealth) + 1, attack);
        }
    }

class Player extends Character{
        
        int numHealthPotions;
        int healthPotionHeal;
        int healthPotionDropChance;

        public Player(String name, int health, int attack, int numHealthPotions, 
             int healthPotionHeal, int healthPotionDropChance){
                            super(name, health, attack);
        this.numHealthPotions = numHealthPotions;
        this.healthPotionHeal = healthPotionHeal;
        this.healthPotionDropChance = healthPotionDropChance;

             }
        public void useHealthPotion(){
            if(numHealthPotions > 0){
                health += healthPotionHeal;
                numHealthPotions --;
                 System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHeal + "."
                     + "\n\t You now have " + health + " HP."
                     + "\n\t You have " + numHealthPotions + " health potions left. \n");
            }else{
                System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
            }
        } 
    }

class Game {
    private static final String[] ENEMIES = {"Wolves", "Razorbeaks", "Krugs", "Gromp"};
    private static final int MAX_ENEMY_HEALTH = 75;
    private static final int ENEMY_ATTACK = 25;   

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Dungeon!");

        Player player = new Player("Player", 100, 50, 2, 20, 50);

        while (player.isAlive()) {
            Enemy enemy = new Enemy(ENEMIES[new Random().nextInt(ENEMIES.length)], MAX_ENEMY_HEALTH , ENEMY_ATTACK );
             System.out.println("\t# " + enemy.name + " appeared! #\n");
             
             while(enemy.isAlive()){
                displayGameState(player, enemy);

                String input = sc.nextLine();
                handlePlayerInput(player,input,enemy);
             }
                 handleEnemyDefeated(player, enemy);
                if(!continueGame(sc)){
                    break;
                }
             }
        }
    
    private static void displayGameState(Player player, Enemy enemy) {
        System.out.println("\tYour HP: " + player.health);
        System.out.println("\t" + enemy.name + "'s HP: " + enemy.health);
        System.out.println("\n\tWhat would you like to do?");
        System.out.println("\t1. Attack");
        System.out.println("\t2. Drink health potion");
        System.out.println("\t3. Run!");    
    }

    private static void handlePlayerInput(Player player, String input, Enemy enemy){
        switch (input) {
            case "1":
                int damageDealt = new Random().nextInt(player.attack);
                int damageTaken = new Random().nextInt(enemy.attack);
                
                enemy.takeDamage(damageDealt);
                player.takeDamage(damageTaken);

        System.out.println("\t> You Strike the " + enemy.name + " for " + damageDealt + " damage.");
        System.out.println("\t> You Receive " + damageTaken + " in retaliation!");

        if (!player.isAlive()) {
                System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                    break;
                }
                break;
            case "2":
                player.useHealthPotion();
                break;
            case "3":
                System.out.println("\t  You run away from the " + enemy.name + "!");
                break;
        
            default:
                System.out.println("\t Invalid command!");
                break;
        }
    }
    private static void handleEnemyDefeated(Player player, Enemy enemy) {
         System.out.println("============================================================================");
        System.out.println("# " + enemy.name + " was defeated! #");
         System.out.println("# You have " + player.health + " HP left. #");

         if (new Random().nextInt(100) < player.healthPotionDropChance) {
             player.numHealthPotions++;
             System.out.println("# The " + enemy.name + " dropped a health potion #");
             System.out.println("# You have now " + player.numHealthPotions + " health potion(s). #");
         }

         System.out.println("============================================================================");
     }

     private static boolean continueGame(Scanner sc) {
         System.out.println("What would you like to do now?");
         System.out.println("1. Continue fighting");
         System.out.println("2. Exit dungeon");

         String input = sc.nextLine();

         while (!input.equals("1") && !input.equals("2")) {
             System.out.println("Invalid Input");
             input = sc.nextLine();
         }

        if (input.equals("1")) {
             System.out.println("You continue on your adventure");
             return true;

         } else if (input.equals("2")) {
             System.out.println("You exit the dungeon, successful from your adventure!");
             return false;
         }

         return true;
     }
    }

    
    


















// import java.util.Random;
// import java.util.Scanner;

// class Character {
//     String name;
//     int health;
//     int attack;

//     public Character(String name, int health, int attack) {
//         this.name = name;
//         this.health = health;
//         this.attack = attack;
//     }

//     public void takeDamage(int damage) {
//         health -= damage;
//     }

//     public boolean isAlive() {
//         return health > 0;
//     }
// }

// class Enemy extends Character {
//     public Enemy(String name, int maxHealth, int attack) {
//         super(name, new Random().nextInt(maxHealth) + 1, attack);
//     }
// }

// class Player extends Character {
//     int numHealthPotions;
//     int healthPotionHeal;
//     int healthPotionDropChance;

//     public Player(String name, int health, int attack, int numHealthPotions, 
            // int healthPotionHeal, int healthPotionDropChance) {
//         super(name, health, attack);
//         this.numHealthPotions = numHealthPotions;
//         this.healthPotionHeal = healthPotionHeal;
//         this.healthPotionDropChance = healthPotionDropChance;
//     }

//     public void useHealthPotion() {
//         if (numHealthPotions > 0) {
//             health += healthPotionHeal;
//             numHealthPotions--;
//             System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHeal + "."
//                     + "\n\t You now have " + health + " HP."
//                     + "\n\t You have " + numHealthPotions + " health potions left. \n");
//         } else {
//             System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
//         }
//     }
// }

// class Game {
//     private static final String[] ENEMIES = {"Wolves", "Razorbeaks", "Krugs", "Gromp"};
//     private static final int MAX_ENEMY_HEALTH = 75;
//     private static final int ENEMY_ATTACK = 25;

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         System.out.println("Welcome to the Dungeon!");

//         Player player = new Player("Player", 100, 50, 2, 20, 50);

//         while (player.isAlive()) {
//             Enemy enemy = new Enemy(ENEMIES[new Random().nextInt(ENEMIES.length)], MAX_ENEMY_HEALTH, ENEMY_ATTACK);
//             System.out.println("\t# " + enemy.name + " appeared! #\n");

//             while (enemy.isAlive()) {
//                 displayGameState(player, enemy);

//                 String input = sc.nextLine();
//                 handlePlayerInput(player, input, enemy);
//             }

//             handleEnemyDefeated(player, enemy);
//             if (!continueGame(sc)) {
//                 break;
//             }
//         }
//     }

//     private static void displayGameState(Player player, Enemy enemy) {
//         System.out.println("\tYour HP: " + player.health);
//         System.out.println("\t" + enemy.name + "'s HP: " + enemy.health);
//         System.out.println("\n\tWhat would you like to do?");
//         System.out.println("\t1. Attack");
//         System.out.println("\t2. Drink health potion");
//         System.out.println("\t3. Run!");
//     }

//     private static void handlePlayerInput(Player player, String input, Enemy enemy) {
//         switch (input) {
//             case "1":
//                 int damageDealt = new Random().nextInt(player.attack);
//                 int damageTaken = new Random().nextInt(enemy.attack);

//                 enemy.takeDamage(damageDealt);
//                 player.takeDamage(damageTaken);

//                 System.out.println("\t> You Strike the " + enemy.name + " for " + damageDealt + " damage.");
//                 System.out.println("\t> You Receive " + damageTaken + " in retaliation!");

//                 if (!player.isAlive()) {
//                     System.out.println("\t> You have taken too much damage, you are too weak to go on!");
//                     break;
//                 }
//                 break;

//             case "2":
//                 player.useHealthPotion();
//                 break;

//             case "3":
//                 System.out.println("\t  You run away from the " + enemy.name + "!");
//                 break;

//             default:
//                 System.out.println("\t Invalid command!");
//                 break;
//         }
//     }

//     private static void handleEnemyDefeated(Player player, Enemy enemy) {
//         System.out.println("============================================================================");
//         System.out.println("# " + enemy.name + " was defeated! #");
//         System.out.println("# You have " + player.health + " HP left. #");

//         if (new Random().nextInt(100) < player.healthPotionDropChance) {
//             player.numHealthPotions++;
//             System.out.println("# The " + enemy.name + " dropped a health potion #");
//             System.out.println("# You have now " + player.numHealthPotions + " health potion(s). #");
//         }

//         System.out.println("============================================================================");
//     }

//     private static boolean continueGame(Scanner sc) {
//         System.out.println("What would you like to do now?");
//         System.out.println("1. Continue fighting");
//         System.out.println("2. Exit dungeon");

//         String input = sc.nextLine();

//         while (!input.equals("1") && !input.equals("2")) {
//             System.out.println("Invalid Input");
//             input = sc.nextLine();
//         }

//         if (input.equals("1")) {
//             System.out.println("You continue on your adventure");
//             return true;
//         } else if (input.equals("2")) {
//             System.out.println("You exit the dungeon, successful from your adventure!");
//             return false;
//         }

//         return true;
//     }
// }
