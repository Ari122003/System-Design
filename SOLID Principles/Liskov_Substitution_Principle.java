import java.util.ArrayList;

// This principle ensures that any class that is the child of a parent class should be usable in place of its parent without any unexpected behaviour.

// class Vehicle {
//     public Integer wheelCount() {
//         return 2;
//     }

//     public Boolean hasEngine() {
//         return true;
//     }
// }

// class Car extends Vehicle {
//     @Override
//     public Integer wheelCount() {
//         return 4;
//     }
// }

// class Bike extends Vehicle {
//     @Override
//     public Integer wheelCount() {
//         return 2;
//     }
// }

// class Cycle extends Vehicle {
//     @Override
//     public Integer wheelCount() {
//         return 2;
//     }

//     public Boolean hasEngine() {
//         return null;
//     }
// }

// public class Liskov_Substitution_Principle {

//     public static void main(String[] a) {
//         ArrayList<Vehicle> vl = new ArrayList<>();

//         vl.add(new Car());
//         vl.add(new Bike());
//         vl.add(new Cycle());

// This will give null pointer exeption

//         for (Vehicle v : vl) {
//             System.out.println("WheelCount : " + v.wheelCount());
//             System.out.println("Does have engine : " + v.hasEngine().toString());
//         }
//     }

// }


// Solution

class Vehicle {
    public Integer wheelCount() {
        return 2;
    }

}

class EnginedVehicles extends Vehicle {
    public Boolean hasEngine() {
        return true;
    }
}

class Car extends EnginedVehicles {
    public Integer wheelCount() {
        return 4;
    }
}

class Bike extends EnginedVehicles{

}

class Cycle extends Vehicle{

}