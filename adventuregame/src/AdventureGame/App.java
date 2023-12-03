package AdventureGame;
import java.util.Random;
import java.util.Scanner;

class Character {
    String name;
    int health;
    int attack;

    Character(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    void attack(Character opponent) {
        int damageDealt = new Random().nextInt(attack);
        opponent.takeDamage(damageDealt);
        System.out.println("> " + name + " strikes " + opponent.name + " for " + damageDealt + " damage.");
    }

    void takeDamage(int damage) {
        health -= damage;
        System.out.println("> " + name + " receives " + damage + " damage.");
    }

    boolean isAlive() {
        return health > 0;
    }
}

class Player extends Character {
    int numHealthPotions;
    int healthPotionHeal;
    int healthPotionDropChance;

    Player(String name, int health, int attack, int numHealthPotions, int healthPotionHeal, int healthPotionDropChance) {
        super(name, health, attack);
        this.numHealthPotions = numHealthPotions;
        this.healthPotionHeal = healthPotionHeal;
        this.healthPotionDropChance = healthPotionDropChance;
    }

    void drinkHealthPotion() {
        if (numHealthPotions > 0) {
            health += healthPotionHeal;
            numHealthPotions--;
            System.out.println("> You drink a health potion, healing yourself for " + healthPotionHeal + "."
                    + "\n> You now have " + health + " HP." + "\n> You have " + numHealthPotions
                    + " health potions left. \n");
        } else {
            System.out.println("> You have no health potions left! Defeat enemies for a chance to get one!\n");
        }
    }
}

class Enemy extends Character {
    Enemy(String name, int health, int attack) {
        super(name, health, attack);
    }
}

class Game {
    private static final String[] ENEMIES = { "Wolves", "Razorbeaks", "Krugs", "Gromp" };
    private static final int MAX_ENEMY_HEALTH = 75;
    private static final int ENEMY_ATTACK = 25;

    private Player player;
    private Enemy enemy;
    private Scanner scanner;

    Game() {
        scanner = new Scanner(System.in);
        player = new Player("Player", 100, 50, 2, 20, 50);
    }

    void startGame() {
        System.out.println("Welcome to the Dungeon!");

        while (player.isAlive()) {
            spawnEnemy();
            battle();
        }
    }

    private void spawnEnemy() {
        int enemyHealth = new Random().nextInt(MAX_ENEMY_HEALTH);
        String enemyName = ENEMIES[new Random().nextInt(ENEMIES.length)];
        enemy = new Enemy(enemyName, enemyHealth, ENEMY_ATTACK);

        System.out.println("\n# " + enemyName + " appeared! #\n");
    }

    private void battle() {
        while (enemy.isAlive() && player.isAlive()) {
            displayStats();

            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Attack");
            System.out.println("2. Drink health potion");
            System.out.println("3. Run!");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    player.attack(enemy);
                    enemy.attack(player);
                    break;
                case "2":
                    player.drinkHealthPotion();
                    enemy.attack(player);
                    break;
                case "3":
                    System.out.println("You run away from the " + enemy.name + "!");
                    return;
                default:
                    System.out.println("Invalid command!");
            }
        }

        endBattle();
    }

    private void displayStats() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\tYour HP: " + player.health);
        System.out.println("\t" + enemy.name + "'s HP: " + enemy.health);
    }

    private void endBattle() {
        if (!player.isAlive()) {
            System.out.println("> You have taken too much damage, you are too weak to go on!");
        } else {
            System.out.println("> You defeated the " + enemy.name + "!");
        }
    }
}

public class App {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}


// import java.util.Scanner;
// import java.util.Random;




// import java.util.Random;
// import java.util.Scanner;

// public class App {
//     public static void main(String[] args) throws Exception {
        
//         // System objective
//         Scanner sc = new Scanner(System.in);
//         Random rand = new Random();

//         //Game variables
//         String[] enemies = {"Wolves", "Razorbeaks", "Krugs", "Gromp"};
//         int maxEnemyHealth = 75;
//         int enemyAttack = 25;

//         //Player variables
//         int health = 100;
//         int playerAttack = 50;
//         int numHealthPotions = 2;
//         int healthPotionHeal = 20;
//         int healthPotionDropChance = 50;

//         boolean running = true; 

//         System.out.println("Welcome to the Dungeon!");
 
//         GAME:
//         while (running) {
//                     System.out.println("-------------------------------------------------------------------------------");
//             int enemyHealth = rand.nextInt(maxEnemyHealth);
//             String enemy = enemies[rand.nextInt( enemies.length)];

//             System.out.println("\t# " + enemy + " appeared! #\n");

//             while (enemyHealth > 0 ) {
//                 System.out.println("\tYour HP: " + health);
//                 System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
//                 System.out.println("\n\tWhat would like to do?");
//                 System.out.println("\t1. Attack");
//                 System.out.println("\t2. Drink health potion");
//                 System.out.println("\t3. Run!");

//                 String input = sc.nextLine();
//                 if(input.equals("1")){
//                  int dmgDealt = rand.nextInt(playerAttack);
//                  int dmgTaken = rand.nextInt(enemyAttack);

//                  enemyHealth -= dmgDealt;
//                  health -= dmgTaken;
                 
//                  System.out.println("\t> You Strike the " + enemy + "for " + dmgDealt + " damage.");
//                  System.out.println("\t> You Recieve " + dmgTaken +  " in retaliation!");
                 
//                  if(health < 1 ){
//                     System.out.println("\t> You have taken too much damage, you are too weak to go on!");
//                     break;
//                  }

//                 }else if (input.equals("2")){
//                     if(numHealthPotions > 0 ){
//                         health += healthPotionHeal;
//                         numHealthPotions--;
//                         System.out.println("\t> You drink a health potion, healing yourseldf for "+ healthPotionHeal + "."
//                                             + "\n\t You now have " + health + " HP."
//                                             + "\n\t You have " + numHealthPotions + " health potions left. \n");                   
//                 }else{
//                     System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
//                 }

//                 }else if(input.equals("3")){
//                     System.out.println("\t  You run away from the " + enemy + "!");
//                     continue GAME;
//                 }else {
//                     System.out.println("\t Invalid command!");
//                 }
//             }
//             if(health <1 ){

//                 System.out.println("You limp out of the dungeo, weak from the battle");
//                 break;
//             }
//                 System.out.println("============================================================================");
//                 System.out.println("# " + enemy + " was defeated! #");
//                 System.out.println("# You have " + health + "HP left. #");
//                 if(rand.nextInt(100)< healthPotionDropChance){
//                     numHealthPotions++;
//                     System.out.println("# The " + enemy + " dropped a health potion #");
//                     System.out.println("# You have now " + numHealthPotions + " health potion(s). #");
  
//                 }
//                 System.out.println("============================================================================");
//                 System.out.println("What would you like to do now?");
//                 System.out.println("1. Continue fighting");
//                 System.out.println("2. Exit dungeon");
                
//                 String input = sc.nextLine();

//                 while (!input.equals("1") && !input.equals("2")) {
//                     System.out.println("Invalid Input");
//                     input = sc.nextLine();
//                 }
//                 if(input.equals("1")){

//                     System.out.println("You continue on your adventure");
//                 }else if(input.equals("2")){
//                     System.out.println("You exit the dungeon, successful from your adventure!");
//                     break;
//                 }
           
//         }
//     }
// }





 
 



