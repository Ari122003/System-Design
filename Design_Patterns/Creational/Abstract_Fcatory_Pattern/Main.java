package Design_Patterns.Creational.Abstract_Fcatory_Pattern;

// Base Interfaces

interface Car {
    void drive();
}

interface Engine {
    void start();
}

interface Interior {
    void design();
}

// Concrete Implementations

// BMW Family
class BMW implements Car {
    public void drive() {
        System.out.println("Driving BMW");
    }
}

class BMWEngine implements Engine {
    public void start() {
        System.out.println("BMW Engine started");
    }
}

class BMWInterior implements Interior {
    public void design() {
        System.out.println("BMW Interior design");
    }
}

// Audi Family

class Audi implements Car {
    public void drive() {
        System.out.println("Driving Audi");
    }
}

class AudiEngine implements Engine {
    public void start() {
        System.out.println("Audi Engine started");
    }
}

class AudiInterior implements Interior {
    public void design() {
        System.out.println("Audi Interior design");
    }
}

// Abstract Factory Interface
interface CarFactory {
    Car createCar();

    Engine createEngine();

    Interior createInterior();
}

// Concrete Factories

// BMW Factory
class BMWFactory implements CarFactory {

    public Car createCar() {
        return new BMW();
    }

    public Engine createEngine() {
        return new BMWEngine();
    }

    public Interior createInterior() {
        return new BMWInterior();
    }
}

// Audi Factory
class AudiFactory implements CarFactory {

    public Car createCar() {
        return new Audi();
    }

    public Engine createEngine() {
        return new AudiEngine();
    }

    public Interior createInterior() {
        return new AudiInterior();
    }
}

public class Main {
    public static void main(String[] args) {
        CarFactory bmwFactory = new BMWFactory();

        Car bmwCar = bmwFactory.createCar();
        Engine bmwEngine = bmwFactory.createEngine();
        Interior bmwInterior = bmwFactory.createInterior();

        bmwCar.drive();
        bmwEngine.start();
        bmwInterior.design();
    }
}
