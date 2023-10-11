import java.util.*;
import java.util.ArrayList;

class Main {
    static Scanner input = new Scanner(System.in);
    static Player player;

    public static void main(String[] args) {
        clearScreen();
        System.out.print("What is your name > ");
        String playername = input.nextLine();
        System.out.print("What be thoust title > ");
        String playertitle = input.nextLine();
        player = new Player(playername, playertitle);
        System.out.println("Good morrow " + player.title + " " + player.name + "!");
        pause(1);
        for (int i = 0; i <= 100; i++) {
            System.out.println(player.attack());
        }
    }

    public static ArrayList<String> createDrops(String Item1, String Item2, String Item3) {
        ArrayList<String> drops = new ArrayList<String>();
        drops.add(Item1);
        drops.add(Item2);
        drops.add(Item3);
        return drops;
    }

    public static void battle(Enemy enemy) {
        while (enemy.health >= 0 || player.health >= 0) {

        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public static void pause(int seconds) {
        int miliseconds = seconds * 1000;
        try {
            Thread.sleep(miliseconds);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    // This function return the letter Grade depending on the amount of correct
    // question they got. >90% is an A, >80% is a B, etc.
    public static String letterGrade(int correctQuestions) {
        return " ";
    }
}

class Enemy {
    String name;
    int health;
    int damage;
    int defense;
    ArrayList<String> drops;

    public Enemy(String name, int health, int damage, int defense, ArrayList<String> drops) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.drops = drops;
    }
}

class Player {
    String name;
    String title;
    int health;
    static int damage;
    static int defense;
    static int luck;
    ArrayList<String> inventory;

    public Player(String name, String title) {
        this.name = name;
        this.title = title;
        health = 100;
        defense = 10;
        damage = 3;
        inventory = new ArrayList<String>(Arrays.asList("Basic Sword", "Basic Armor", "Apple"));
    }

    public int attack() {
        Random rand = new Random();
        int attack = (int) (1 + (damage * rand.nextDouble(0.1, 1)));
        return attack;
    }

    public static void updateStats() {

    }
}
