
package Design_Patterns.Structural.Proxy_Pattern;

// Subject interface
interface Image {
    void display();
}

// Real Subject class
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(); // expensive operation
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
    }

    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}

// Proxy class
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // lazy initialization
        }
        realImage.display();
    }
}

public class Main {

    public static void main(String[] args) {
        Image image = new ProxyImage("photo.png");

        // First time → loads from disk
        image.display();

        System.out.println();

        // Second time → no loading, uses cached object
        image.display();
    }

}