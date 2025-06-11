
// This principle states that "A class should have only one reason to change" which means every class should have a single responsibility or single job or single purpose. In other words, a class should have only one job or purpose within the software system.

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

// here the class Invoice has 3 reasons to chnage 1 is the calculate price
// method 2 is the printInvoice method
// 3 is the save method.........but single responsibility principle says a class
// must have a single reason to chnage

// class Invoice {
// private Marker marker;
// private int quantity;

// public Invoice(Marker mkr, int qty) {
// marker = mkr;
// quantity = qty;
// }

// public int calculatePrice() {
// return marker.price * this.quantity;
// }

// public void printInvoice() {
// // Print the invoice
// }

// public void save() {
// // save the invoice
// }
// }

// solution

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

class InvoiceDataAccessLayer {
    private Invoice invoice;

    public InvoiceDataAccessLayer(Invoice inv) {
        invoice = inv;
    }

    public void save() {
        // save into the DB
        System.out.println("Successfully saved invoice for - " + invoice.marker.name);
    }
}

class InvoicePrinter {
    private Invoice invoice;

    public InvoicePrinter(Invoice inv) {
        invoice = inv;
    }

    public void print() {
        System.out.println("Printed invoice number - " + invoice.invoiceNumber);
    }
}

public class Single_Responsibility_Principle {
    public static void main(String[] a) {
        Marker marker = new Marker("Camel", "Green", 20, "25/3/25");

        Invoice invoice = new Invoice(marker, 5, 4167);

        InvoiceDataAccessLayer ivda = new InvoiceDataAccessLayer(invoice);

        ivda.save();

        InvoicePrinter ivptr = new InvoicePrinter(invoice);

        ivptr.print();
    }

}