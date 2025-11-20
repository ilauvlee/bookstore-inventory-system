
import java.util.LinkedList;
import java.util.Scanner;



public class Inventory {
    
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Book Inventory Menu ===");
            System.out.println("1. Add book");
            System.out.println("2. Display all books");
            System.out.println("3. Sort books by title");
            System.out.println("4. Search books by title");
            System.out.println("5. Add order to queue");
            System.out.println("6. Process next order");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    inventory.addBook(scanner);
                    break;
                case 2:
                    inventory.displayAllBooks();
                    break;
                case 3:
                    inventory.sortBooksByTitle();
                    break;
                case 4:
                    inventory.searchBooksByTitle(scanner);
                    break;
                case 5:
                    inventory.addOrderToQueue(scanner);
                    break;
                case 6:
                    inventory.processNextOrder();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private final LinkedList<Book> books = new LinkedList<>();
    private final LinkedList<String> orderQueue = new LinkedList<>();

    public Inventory() {

    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the inventory.");
            return;
        }

        System.out.println("\nCurrent books in inventory:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        double price;
        while (true) {
            System.out.print("Enter book price: ");
            String priceInput = scanner.nextLine();
            try {
                price = Double.parseDouble(priceInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Please enter a valid number.");
            }
        }

        Book newBook = new Book(title, author, isbn, price);
        books.add(newBook);
        System.out.println("Book added successfully.");
    }

    public void sortBooksByTitle() {
        if (books.size() < 2) {
            System.out.println("Not enough books to sort.");
            return;
        }

        boolean swapped;
        int n = books.size();
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                Book current = books.get(i);
                Book next = books.get(i + 1);
                if (current.getTitle().compareToIgnoreCase(next.getTitle()) > 0) {
                    books.set(i, next);
                    books.set(i + 1, current);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);

        System.out.println("Books sorted by title.");
    }

    public void searchBooksByTitle(Scanner scanner) {
        if (books.isEmpty()) {
            System.out.println("No books in the inventory to search.");
            return;
        }

        System.out.print("Enter title to search: ");
        String searchTitle = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().equals(searchTitle)) {
                System.out.println("Book found: " + book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Adds a customer order (by book title) to the queue
    public void addOrderToQueue(Scanner scanner) {
        System.out.print("Enter the title of the book to order: ");
        String title = scanner.nextLine();

        if (title == null || title.trim().isEmpty()) {
            System.out.println("Order title cannot be empty.");
            return;
        }

        orderQueue.add(title.trim());
        System.out.println("Order added to queue for book: " + title.trim());
    }

    // Processes the next order in the queue
    public void processNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue to process.");
            return;
        }

        String nextTitle = orderQueue.removeFirst();
        System.out.println("Processed order for book: " + nextTitle);
    }
}