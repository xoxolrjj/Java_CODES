package AdventureGame;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        // System objective
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        //Game variables
        String[] enemies = {"Wolves", "Razorbeaks", "Krugs", "Gromp"};
        int maxEnemyHealth = 75;
        int enemyAttack = 25;

        //Player variables
        int health = 100;
        int playerAttack = 50;
        int numHealthPotions = 2;
        int healthPotionHeal = 20;
        int healthPotionDropChance = 50;

        boolean running = true; 

        System.out.println("Welcome to the Dungeon!");
 
        GAME:
        while (running) {
                    System.out.println("-------------------------------------------------------------------------------");
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt( enemies.length)];

            System.out.println("\t# " + enemy + " appeared! #\n");

            while (enemyHealth > 0 ) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                String input = sc.nextLine();
                if(input.equals("1")){
                 int dmgDealt = rand.nextInt(playerAttack);
                 int dmgTaken = rand.nextInt(enemyAttack);

                 enemyHealth -= dmgDealt;
                 health -= dmgTaken;
                 
                 System.out.println("\t> You Strike the " + enemy + "for " + dmgDealt + " damage.");
                 System.out.println("\t> You Recieve " + dmgDealt +  " in retaliation!");
                 
                 if(health < 1 ){
                    System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                    break;
                 }

                }else if (input.equals("2")){
                    
                 }else if(input.equals("3")){

                }else {

                }

            }
        }
    }
}
