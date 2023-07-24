import java.util.Scanner;
import java.util.Arrays;

class Coffee {
    int water;
    int milk;
    int coffeeBeans;
    int cost;


    public Coffee (int water, int milk, int coffeeBeans, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
    }
}

class CoffeeMachinee {
    int waterM;
    int milkM;
    int coffeeBeansM;
    int cups;
    int money;

    public CoffeeMachinee (int waterM, int milkM, int coffeeBeansM, int cups, int money) {
        this.waterM = waterM;
        this.milkM = milkM;
        this.coffeeBeansM = coffeeBeansM;
        this.cups = cups;
        this.money = money;
    }

    public void cups (Coffee coffee, int cups) {
        int waterCups = this.waterM / coffee.water;
        int milkCups = this.milkM / coffee.milk;
        int coffeeBeamsCups = this.coffeeBeansM / coffee.coffeeBeans;
        int [] availableCups = {waterCups,milkCups,coffeeBeamsCups};
        int min = Arrays.stream(availableCups).min().getAsInt();
        if(min < cups) {                 //If the amount of given resources is not enough to make the specified amount of coffee, the program should output "No, I can make only N cup(s) of coffee".
            System.out.println("No, I can make only "+ min +" cup(s) of coffee");
        }else if(min == cups) {
            System.out.println("Yes, I can make that amount of coffee");
        }else {
            int more = min - cups;
            System.out.println("Yes, I can make that amount of coffee (and even " + more +" more than that)");
        }
    }

    public void info () {
        System.out.println("The coffee machine has:");
        System.out.println(waterM + " ml of water");
        System.out.println(milkM + " ml of milk");
        System.out.println(coffeeBeansM + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money +" of money");
        System.out.println();
    }
    public CoffeeMachinee takeCoffee(Coffee coffee) {
        int water = this.waterM - coffee.water;
        int milk = this.milkM - coffee.milk;
        int coffeeBeans = this.coffeeBeansM - coffee.coffeeBeans;
        int money = this.money + coffee.cost;
        int cups = this.cups - 1;
        if(water >= 0 && milk >= 0 && coffeeBeans >= 0 && cups>=0){
            System.out.println("I have enough resources, making you a coffee!");
            System.out.println();
            return new CoffeeMachinee(water,milk,coffeeBeans,cups,money);
        }else {
            int [] availableResorces = {water,milk,coffeeBeans,cups};
            int min = Arrays.stream(availableResorces).min().getAsInt();
            if(min == water) {
                System.out.println("Sorry, not enough water!");
            }else if(min == milk) {
                System.out.println("Sorry, not enough milk!");
            }else if(min == coffeeBeans) {
                System.out.println("Sorry, not enough coffee beans!");
            }else if(min == cups) {
                System.out.println("Sorry, not enough disposable cups!");
            }
            System.out.println();
            return new CoffeeMachinee(this.waterM, this.milkM, this.coffeeBeansM, this.cups, this.money);
        }
    }
}

public class Main {

    public static void menu(CoffeeMachinee coffeeMachinee) {
        boolean enoughtResorces = true;
        while(enoughtResorces) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            Scanner sc = new Scanner(System.in);
            String option = sc.nextLine();
            switch (option) {
                case "buy":
                    System.out.println();
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    String coffee = sc.nextLine();
                    switch (coffee) {
                        case "1":
                            Coffee espresso = new Coffee(250, 0, 16, 4);
                            coffeeMachinee = coffeeMachinee.takeCoffee(espresso);
                            break;
                        case "2":
                            Coffee latte = new Coffee(350, 75, 20, 7);
                            coffeeMachinee = coffeeMachinee.takeCoffee(latte);
                            break;
                        case "3":
                            Coffee cappuccino = new Coffee(200, 100, 12, 6);
                            coffeeMachinee = coffeeMachinee.takeCoffee(cappuccino);
                            break;
                        case "back":
                            System.out.println();
                            break;
                    }
                    break;
                case "fill":
                    System.out.println();
                    System.out.println("Write how many ml of water you want to add:");
                    int water = sc.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    int milk = sc.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    int coffeeBeans = sc.nextInt();
                    System.out.println("Write how many disposable cups you want to add:");
                    int cups = sc.nextInt();
                    coffeeMachinee.waterM += water;
                    coffeeMachinee.milkM += milk;
                    coffeeMachinee.coffeeBeansM += coffeeBeans;
                    coffeeMachinee.cups += cups;
                    break;
                case "take":
                    System.out.println();
                    int takenMoney = coffeeMachinee.money;
                    System.out.println("I gave you $" + takenMoney);
                    System.out.println();
                    coffeeMachinee.money -= takenMoney;
                    break;
                case "remaining":
                    System.out.println();
                    coffeeMachinee.info();
                    break;
                case "exit":
                    enoughtResorces = false;
            }
        }
    }

    public static void main(String[] args) {

        CoffeeMachinee coffeeMachinee = new CoffeeMachinee(400,540,120,9,550);
        menu(coffeeMachinee);

    }
}