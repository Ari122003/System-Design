
// interface Worker {
//     void work();
//     void eat();
// }

// class Robot implements Worker {
//     public void work() {}
//     public void eat() {} // ❌ useless
// }

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Robot implements Workable {
    public void work() {
    }
}

class Human implements Workable, Eatable {
    public void work() {
    }

    public void eat() {
    }
}

public class Interface_Segregation_Principle {

}
