package Design_Patterns.Creational.Object_Pool_Pattern;

import java.util.*;

// Object Pool Pattern Example: Database Connection Pool
class DBConnection {
    private final int id;

    public DBConnection(int id) {
        this.id = id;
        System.out.println("Creating DB Connection: " + id);
    }

    public void executeQuery(String query) {
        System.out.println("Executing on connection " + id + ": " + query);
    }

    public void reset() {
        System.out.println("Resetting connection " + id);
    }
}

// Object Pool for DBConnection
class ConnectionPool {
    private Queue<DBConnection> available;
    private Set<DBConnection> inUse;
    private int maxSize;
    private int currentSize = 0;

    public ConnectionPool(int maxSize) {
        this.maxSize = maxSize;
        available = new LinkedList<>();
        inUse = new HashSet<>();
    }

    public synchronized DBConnection acquire() {
        if (!available.isEmpty()) {
            DBConnection conn = available.poll();
            inUse.add(conn);
            return conn;
        }

        if (currentSize < maxSize) {
            DBConnection conn = new DBConnection(++currentSize);
            inUse.add(conn);
            return conn;
        }

        throw new RuntimeException("No available connections!");
    }

    public synchronized void release(DBConnection conn) {
        if (conn == null)
            return;

        conn.reset();
        inUse.remove(conn);
        available.offer(conn);
    }
}

public class Main {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(2);

        DBConnection c1 = pool.acquire();
        c1.executeQuery("SELECT * FROM users");

        DBConnection c2 = pool.acquire();
        c2.executeQuery("SELECT * FROM orders");

        pool.release(c1);

        DBConnection c3 = pool.acquire(); // reuses c1
        c3.executeQuery("SELECT * FROM products");

        pool.release(c2);
        pool.release(c3);
    }
}
