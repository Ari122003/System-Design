package Design_Patterns.Decorator_Pattern.Toppings;

import Design_Patterns.Decorator_Pattern.Pizzas.BasePizza;

public class Mushroom extends ToppingsDecorator {

    BasePizza basepizza;

    public Mushroom(BasePizza pizza) {
        this.basepizza = pizza;
    }

    public int cost() {
        return this.basepizza.cost() + 60;
    }

}
