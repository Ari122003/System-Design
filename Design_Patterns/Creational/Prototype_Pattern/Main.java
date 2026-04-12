package Design_Patterns.Creational.Prototype_Pattern;

class User implements Cloneable {
    String name;
    int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }
}

class Address {
    String city;

    Address(String city) {
        this.city = city;
    }
}

class Customer implements Cloneable {
    String name;
    Address address;

    Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Customer cloned = (Customer) super.clone();
        cloned.address = new Address(this.address.city); // deep copy
        return cloned;
    }
}

public class Main {

}
