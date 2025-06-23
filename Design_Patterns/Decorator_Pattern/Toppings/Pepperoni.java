package Design_Patterns.Decorator_Pattern.Toppings;

import Design_Patterns.Decorator_Pattern.Pizzas.BasePizza;

public class Pepperoni extends ToppingsDecorator {
    BasePizza basepizza;

    public Pepperoni(BasePizza pizza) {
        this.basepizza = pizza;
    }

    public int cost() {
        return this.basepizza.cost() + 50;
    }
}
