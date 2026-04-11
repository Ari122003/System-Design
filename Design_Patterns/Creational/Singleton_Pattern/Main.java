package Design_Patterns.Creational.Singleton_Pattern;

// Basic Singleton Implementation
// Not thread-safe, but demonstrates the core concept of a singleton pattern.
class BasicSingleton {

    // Step 1: Create a static instance
    private static BasicSingleton instance;

    // Step 2: Make constructor private
    private BasicSingleton() {
    }

    // Step 3: Provide global access method
    public static BasicSingleton getInstance() {
        if (instance == null) {
            instance = new BasicSingleton();
        }
        return instance;
    }
}

// Synchronized Singleton Implementation
// Thread-safe but can be less efficient due to synchronization overhead.

class SynchronizedSingleton {

    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
    }

    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}

// Double-Checked Locking Singleton Implementation
// Thread-safe and more efficient than the synchronized method.

class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}

// Bill Pugh Singleton Implementation
// Thread-safe and efficient, using a static inner helper class.

class Singleton {

    private Singleton() {
    }

    private static class Helper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Helper.INSTANCE;
    }
}

public class Main {

}
