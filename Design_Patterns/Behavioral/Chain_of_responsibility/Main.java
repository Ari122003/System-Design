package Design_Patterns.Behavioral.Chain_of_responsibility;

// Handler interface

abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handle(String username, String password);
}

// Concrete Handler 1: Checks if the username is provided
class UsernameCheck extends Handler {

    @Override
    public void handle(String username, String password) {
        if (username == null || username.isEmpty()) {
            System.out.println("Username is missing ❌");
            return;
        }
        System.out.println("Username OK ✅");

        if (next != null)
            next.handle(username, password);
    }
}

//
class PasswordCheck extends Handler {

    @Override
    public void handle(String username, String password) {
        if (password == null || password.isEmpty()) {
            System.out.println("Password is missing ❌");
            return;
        }
        System.out.println("Password OK ✅");

        if (next != null)
            next.handle(username, password);
    }
}

// Concrete Handler 3: Validates the user credentials
class UserValidation extends Handler {

    public void handle(String username, String password) {
        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Login Successful 🎉");
        } else {
            System.out.println("Invalid Credentials ❌");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Handler h1 = new UsernameCheck();
        Handler h2 = new PasswordCheck();
        Handler h3 = new UserValidation();

        h1.setNext(h2);
        h2.setNext(h3);

        // Test
        h1.handle("admin", "1234");
    }

}
