package Design_Patterns.Behavioral.Null_object_creation_pattern;

// 
abstract class Customer {
    protected String name;

    public abstract String getName();

    public abstract boolean isNull();
}

// Real Object
class RealCustomer extends Customer {

    public RealCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isNull() {
        return false;
    }
}

// Null Object
class NullCustomer extends Customer {

    public String getName() {
        return "Not Available";
    }

    public boolean isNull() {
        return true;
    }
}

class CustomerFactory {

    private static final String[] names = { "Aritra", "Rahul", "Soham" };

    public static Customer getCustomer(String name) {
        for (String n : names) {
            if (n.equalsIgnoreCase(name)) {
                return new RealCustomer(name);
            }
        }
        return new NullCustomer(); // No null return!
    }
}

public class Main {

    public static void main(String[] args) {

        Customer c1 = CustomerFactory.getCustomer("Aritra");
        Customer c2 = CustomerFactory.getCustomer("Unknown");

        System.out.println(c1.getName()); // Aritra
        System.out.println(c2.getName()); // Not Available

        // No null check needed!
    }

}
