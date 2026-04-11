
// class UserService {
//     void saveUser() { }        .......................Bad Design
//     void sendEmail() { }
// }

// Good Design
class UserService {
    void saveUser() {
    }
}

class EmailService {
    void sendEmail() {
    }
}

public class Single_Responsibility {
    public static void main(String[] args) {
        System.out.println("Single Responsibility Principle");
    }

}
