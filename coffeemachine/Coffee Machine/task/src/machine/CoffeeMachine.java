package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Machine machine = new Machine();
        machine.start();
    }
}

enum State {
    BUY, FILL, TAKE, REMAINING, EXIT
}
enum Flavors {
    ESPRESSO(250,0,16,4),
    LATTE(350,75,20,7),
    CAPPUCCINO(200,100,12,6);
    private final int water;
    private final int milk;
    private final int beans;
    private final int money;
    private static final int cups = 1;

    Flavors(int water, int milk, int beans, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
    }

    public int getWater() {
        return water;
    }
    public int getMilk() {
        return milk;
    }
    public int getBeans() {
        return beans;
    }
    public int getMoney() {
        return money;
    }
    public int getCups() {
        return cups;
    }
}

class Machine {
    Scanner in = new Scanner(System.in);
    private int water = 400; // in ml
    private int milk = 540; // in ml
    private int beans = 120; // in grams
    private int cups = 9; // disposable cups
    private int money = 550; // in $

    public void start() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        State action = State.valueOf(in.next().toUpperCase());
        while (action != State.EXIT) {
            switch (action) {
                case BUY:
                    buy();
                    break;
                case FILL:
                    fill();
                    break;
                case TAKE:
                    take();
                    break;
                case REMAINING:
                    remaining();
                    break;
                default:
                    System.out.println("Invalid option");
            }
            action = State.valueOf(in.next().toUpperCase());
        }
    }

    private void buy() {
        System.out.println("Choose your coffee flavor (espresso, latte or cappuccino):");
        System.out.println("1 - Espresso, 2 - Latte, 3 - Cappuccino");
        System.out.println("Type \"back\" to return to the main screen");
        String flavor = in.next();
        boolean resupply = false;
        switch (flavor) {
            case "back":
                back();
                break;
            case "1":  // Espresso
                if (getWater() < Flavors.ESPRESSO.getWater()) {
                    System.out.println("Sorry, not enough water!");
                    resupply = true;
                }
                if (getBeans() < Flavors.ESPRESSO.getBeans()) {
                    System.out.println("Sorry, not enough coffee beans!");
                    resupply = true;
                }
                if (getCups() < Flavors.ESPRESSO.getCups()) {
                    System.out.println("Sorry, not enough disposable cups!");
                    resupply = true;
                }
                if (!resupply) {
                    System.out.println("I have enough resources, making you a coffee!");
                    buyEspresso();
                }
                break;
            case "2":  // Latte
                if (getWater() < Flavors.LATTE.getWater()) {
                    System.out.println("Sorry, not enough water!");
                    resupply = true;
                }
                if (getMilk() < Flavors.LATTE.getMilk()) {
                    System.out.println("Sorry, not enough milk!");
                    resupply = true;
                }
                if (getBeans() < Flavors.LATTE.getBeans()) {
                    System.out.println("Sorry, not enough coffee beans!");
                    resupply = true;
                }
                if (getCups() < Flavors.LATTE.getCups()) {
                    System.out.println("Sorry, not enough disposable cups!");
                    resupply = true;
                }
                if (!resupply) {
                    System.out.println("I have enough resources, making you a coffee!");
                    buyLatte();
                }
                break;
            case "3":  // Cappuccino
                if (getWater() < Flavors.CAPPUCCINO.getWater()) {
                    System.out.println("Sorry, not enough water!");
                    resupply = true;
                }
                if (getMilk() < Flavors.CAPPUCCINO.getMilk()) {
                    System.out.println("Sorry, not enough milk!");
                    resupply = true;
                }
                if (getBeans() < Flavors.CAPPUCCINO.getBeans()) {
                    System.out.println("Sorry, not enough coffee beans!");
                    resupply = true;
                }
                if (getCups() < Flavors.CAPPUCCINO.getCups()) {
                    System.out.println("Sorry, not enough disposable cups!");
                    resupply = true;
                }
                if (!resupply) {
                    System.out.println("I have enough resources, making you a coffee!");
                    buyCappuccino();
                }
                break;
        }
    }

    private void fill() {
        in.nextLine();
        System.out.println("How much water do you want to refill?");
        int water = in.nextInt();
        setWater(getWater() + water);
        System.out.println("How much milk do you want to refill?");
        int milk = in.nextInt();
        setMilk(getMilk() + milk);
        System.out.println("How much coffee do you want to refill?");
        int beans = in.nextInt();
        setBeans(getBeans() + beans);
        System.out.println("How many cups do you want to refill?");
        int cups = in.nextInt();
        setCups(getCups() + cups);
    }

    private void take() {
        System.out.printf("$%d has been withdrew\n", getMoney());
        setMoney(0);
    }

    private void remaining() {
        System.out.println("The coffee machine has: ");
        System.out.printf("%d ml of water\n", this.water);
        System.out.printf("%d ml of milk\n", this.milk);
        System.out.printf("%d g of coffee beans\n", this.beans);
        System.out.printf("%d disposable cups\n", this.cups);
        System.out.printf("$%d of money\n", this.money);
    }

    private void back() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private void buyEspresso() {
        setWater(getWater() - Flavors.ESPRESSO.getWater());
        setBeans(getBeans() - Flavors.ESPRESSO.getBeans());
        setMoney(getMoney() + Flavors.ESPRESSO.getMoney());
        setCups(getCups() - Flavors.ESPRESSO.getCups());
    }
    private void buyLatte() {
        setWater(getWater() - Flavors.LATTE.getWater());
        setMilk(getMilk() - Flavors.LATTE.getMilk());
        setBeans(getBeans() - Flavors.LATTE.getBeans());
        setMoney(getMoney() + Flavors.LATTE.getMoney());
        setCups(getCups() - Flavors.LATTE.getCups());
    }
    private void buyCappuccino() {
        setWater(getWater() - Flavors.CAPPUCCINO.getWater());
        setMilk(getMilk() - Flavors.CAPPUCCINO.getMilk());
        setBeans(getBeans() - Flavors.CAPPUCCINO.getBeans());
        setMoney(getMoney() + Flavors.CAPPUCCINO.getMoney());
        setCups(getCups() - Flavors.CAPPUCCINO.getCups());
    }

    // Getters
    private int getWater() {return water;}
    private int getMilk() {return milk;}
    private int getBeans() {return beans;}
    private int getCups() {return cups;}
    private int getMoney() {return money;}

    // Setters
    private void setWater(int water) {this.water = water;}
    private void setMilk(int milk) {this.milk = milk;}
    private void setBeans(int beans) {this.beans = beans;}
    private void setCups(int cups) {this.cups = cups;}
    private void setMoney(int money) {this.money = money;}
}