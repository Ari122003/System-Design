package Design_Patterns.Creational.Builder_Pattern;

class User {
    private String name;
    private int age;
    private String email;
    private String phone;

    // Private constructor
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    // Static nested Builder class
    public static class Builder {
        // Required fields
        private String name;
        private int age;

        // Optional fields
        private String email;
        private String phone;

        // Constructor for required fields
        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}

public class Main {

    public static void main(String[] args) {
        User user = new User.Builder("Aritra", 22)
                .setEmail("aritra@gmail.com")
                .setPhone("1234567890")
                .build();

        System.out.println(user);
    }

}
