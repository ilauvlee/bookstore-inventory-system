
public class Book {

    private final String title;
    private final String author;
    private final String isbn;
    private final double price;

    public Book(String title, String author, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Title: %-25s | Author: %-20s | ISBN: %-15s | Price: $%.2f",
                             title, author, isbn, price);
    }
}