
// This principle states that "Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification" which means you should be able to extend a class behavior, without modifying it.

class Marker { // Base class
    String name;
    String color;
    int price;
    String mfgDate;

    public Marker(String name, String color, int price, String mfgDate) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.mfgDate = mfgDate;
    }

};

class Invoice {
    protected Marker marker;
    private int quantity;
    protected int invoiceNumber;

    public Invoice(Marker mkr, int qty, int ivno) {
        marker = mkr;
        quantity = qty;
        invoiceNumber = ivno;
    }

    public int calculatePrice() {
        return marker.price * this.quantity;
    }
}

// The InvoiceDataAccessLayer is live on production , so its not recomened to
// modify it, to use it's features you can modify it
// that is the main thing here , open for extntion but closed for modification

interface InvoiceDataAccessLayer {

    public void save(Invoice inv);

}

// Whenever a new DB introduced , we will just extend the InvoiceDataAccessLayer
// interface

class AWSSaver implements InvoiceDataAccessLayer {

    @Override
    public void save(Invoice inv) {
        System.out.println("Successfully saved invoice in AWS for - " + inv.marker.name);

    }
}

class MongoSaver implements InvoiceDataAccessLayer {
    @Override

    public void save(Invoice inv) {
        System.out.println("Successfully saved invoice in MongoDB for - " + inv.marker.name);

    }
}

public class Open_Close_Principle {
    public static void main(String[] a) {
        Marker m1 = new Marker("Camel", "Balck", 20, "20/6/25");
        Marker m2 = new Marker("Faver Castle", "Blue", 25, "12/6/25");

        Invoice i1 = new Invoice(m1, 10, 1234);
        Invoice i2 = new Invoice(m2, 20, 4321);

        AWSSaver as = new AWSSaver();
        MongoSaver ms = new MongoSaver();

        as.save(i1);
        ms.save(i2);
    }

}
