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

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
