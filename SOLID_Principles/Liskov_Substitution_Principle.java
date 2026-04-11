class Bird {
    void fly() {
    }
}

// class Ostrich extends Bird { ..............................Bad Design
// void fly() {
// throw new UnsupportedOperationException();
// }
// }

// Good Design

class FlyingBird extends Bird {
    void fly() {
    }
}

class Ostrich extends Bird {
}

class Sparrow extends FlyingBird {
}

public class Liskov_Substitution_Principle {

    public static void main(String[] a) {

    }

}
