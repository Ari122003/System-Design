package Design_Patterns.Behavioral.Visitor_Pattern;

// Element Interface
interface Product {
    void accept(Visitor visitor);
}

// Visitor Interface
interface Visitor {
    void visit(Book book);

    void visit(Laptop laptop);
}

// Concrete Element
class Book implements Product {

    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Laptop implements Product {

    private String brand;
    private int price;

    public Laptop(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Visitors
class PriceVisitor implements Visitor {

    @Override
    public void visit(Book book) {
        System.out.println(
                "Book: " + book.getName() +
                        " Price: " + book.getPrice());
    }

    @Override
    public void visit(Laptop laptop) {
        System.out.println(
                "Laptop: " + laptop.getBrand() +
                        " Price: " + laptop.getPrice());
    }
}

class DiscountVisitor implements Visitor {

    @Override
    public void visit(Book book) {
        int discountedPrice = (int) (book.getPrice() * 0.9); // 10% discount
        System.out.println(
                "Book: " + book.getName() +
                        " Discounted Price: " + discountedPrice);
    }

    @Override
    public void visit(Laptop laptop) {
        int discountedPrice = (int) (laptop.getPrice() * 0.85); // 15% discount
        System.out.println(
                "Laptop: " + laptop.getBrand() +
                        " Discounted Price: " + discountedPrice);
    }
}

public class Main {

    public static void main(String[] args) {
        Product book = new Book("Java Programming", 50);
        Product laptop = new Laptop("Dell XPS", 1000);

        Visitor priceVisitor = new PriceVisitor();
        Visitor discountVisitor = new DiscountVisitor();

        System.out.println("Price Information:");
        book.accept(priceVisitor);
        laptop.accept(priceVisitor);

        System.out.println("\nDiscount Information:");
        book.accept(discountVisitor);
        laptop.accept(discountVisitor);
    }

}
