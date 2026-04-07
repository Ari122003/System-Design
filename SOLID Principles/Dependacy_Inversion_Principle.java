
// Bad Design
// class MySQLDatabase {}

// class UserService {
//     MySQLDatabase db = new MySQLDatabase();..................user service and MySQLDatabase are tightly coupled,
// }

// Good Design

interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() {
    }
}

class UserService {
    Database db;

    UserService(Database db) {
        this.db = db;
    }
}

public class Dependacy_Inversion_Principle {

}
