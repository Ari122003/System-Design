
// class Discount {
//     double calculate(String type) {
//         if(type.equals("DIWALI")) return 10;........................Bad Design
//         if(type.equals("NEWUSER")) return 20;
//     }
// }

// Good Design
interface Discount {
    double calculate();
}

class DiwaliDiscount implements Discount {
    public double calculate() {
        return 10;
    }
}

class NewUserDiscount implements Discount {
    public double calculate() {
        return 20;
    }
}

public class Open_Close_Principle {
    public static void main(String[] a) {

    }

}
