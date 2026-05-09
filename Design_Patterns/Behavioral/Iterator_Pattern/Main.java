package Design_Patterns.Behavioral.Iterator_Pattern;

class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Collection Interface
interface Library {
    Iterator createIterator();
}

// Concrete Collection
class BookCollection implements Library {

    private Book[] books;
    private int index = 0;

    public BookCollection(int size) {
        books = new Book[size];
    }

    public void addBook(Book book) {
        if (index < books.length) {
            books[index++] = book;
        }
    }

    public Iterator createIterator() {
        return new BookIterator(books);
    }
}

// Iterator Interface
interface Iterator {
    boolean hasNext();

    Book next();
}

// Concrete Iterator
class BookIterator implements Iterator {

    private Book[] books;
    private int position = 0;

    public BookIterator(Book[] books) {
        this.books = books;
    }

    public boolean hasNext() {
        return position < books.length && books[position] != null;
    }

    public Book next() {
        return books[position++];
    }
}

public class Main {

    public static void main(String[] args) {

        BookCollection library = new BookCollection(5);

        library.addBook(new Book("Java"));
        library.addBook(new Book("Spring Boot"));
        library.addBook(new Book("Microservices"));

        Iterator iterator = library.createIterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book.getName());
        }
    }
}