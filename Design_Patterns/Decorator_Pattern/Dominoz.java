package Design_Patterns.Decorator_Pattern;

import Design_Patterns.Decorator_Pattern.Pizzas.BasePizza;
import Design_Patterns.Decorator_Pattern.Pizzas.ChickenTikka;
import Design_Patterns.Decorator_Pattern.Toppings.ExtraCheese;
import Design_Patterns.Decorator_Pattern.Toppings.Pepperoni;

public class Dominoz {
    public static void main(String[] args) {
        BasePizza myPizza = new Pepperoni(new ExtraCheese(new ChickenTikka()));

        System.out.println(myPizza.cost());
    }

}
