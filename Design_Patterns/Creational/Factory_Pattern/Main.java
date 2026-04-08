package Design_Patterns.Creational.Factory_Pattern;

interface Car {
    public void drive();
}

class BMW implements Car {
    @Override
    public void drive() {
        System.out.println("Driving a BMW");
    }
}

class Audi implements Car {
    @Override
    public void drive() {
        System.out.println("Driving Audi");
    }
}

class CarFactory {

    public static Car getCar(String type) {

        if (type.equalsIgnoreCase("BMW")) {
            return new BMW();
        } else if (type.equalsIgnoreCase("Audi")) {
            return new Audi();
        }

        throw new IllegalArgumentException("Unknown car type");
    }
}

public class Main {
    public static void main(String[] args) {
        // Bad Design
        // Car car = new BMW(); // tightly coupled
        // car.drive();

        // Good Design
        Car car = CarFactory.getCar("BmW"); // loosely coupled
        car.drive();

    }

}
