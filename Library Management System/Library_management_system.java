package project1;

import java.util.*;

class Book {
    int id;
    String name;
    String author;
    boolean isIssued;
    String issuedTo;
    int days;

    Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = "";
        this.days = 0;
    }
}

public class Library_management_system {

    static ArrayList<Book> books = new ArrayList<>();
    static int idCounter = 1;

    static void addBook(Scanner sc) {
        System.out.print("Enter book name: ");
        String name = sc.nextLine();

        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        books.add(new Book(idCounter++, name, author));
        System.out.println("Book added successfully!");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n===== ALL BOOKS =====");
        for (Book b : books) {
            System.out.println("ID: " + b.id + " | " + b.name + " by " + b.author +
                    (b.isIssued ? " (Issued to: " + b.issuedTo + ")" : " (Available)"));
        }
    }

    static void searchBook(Scanner sc) {
        System.out.print("Enter book name to search: ");
        String key = sc.nextLine();

        boolean found = false;

        for (Book b : books) {
            if (b.name.toLowerCase().contains(key.toLowerCase())) {
                System.out.println("ID: " + b.id + " | " + b.name + " by " + b.author);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    static void issueBook(Scanner sc) {
        System.out.print("Enter book ID to issue: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Book b : books) {
            if (b.id == id) {
                if (b.isIssued) {
                    System.out.println("Already issued.");
                } else {
                    System.out.print("Enter user name: ");
                    b.issuedTo = sc.nextLine();

                    System.out.print("Enter days: ");
                    b.days = sc.nextInt();
                    sc.nextLine();

                    b.isIssued = true;
                    System.out.println("Book issued successfully!");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    static void returnBook(Scanner sc) {
        System.out.print("Enter book ID to return: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Book b : books) {
            if (b.id == id) {
                if (!b.isIssued) {
                    System.out.println("This book is not issued.");
                } else {
                    int fine = 0;

                    if (b.days > 7) {
                        fine = (b.days - 7) * 5;
                    }

                    System.out.println("Returned by: " + b.issuedTo);

                    b.isIssued = false;
                    b.issuedTo = "";
                    b.days = 0;

                    if (fine > 0) {
                        System.out.println("Fine: ₹" + fine);
                    } else {
                        System.out.println("No fine.");
                    }
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    static void viewIssuedBooks() {
        System.out.println("\n===== ISSUED BOOKS =====");

        boolean found = false;

        for (Book b : books) {
            if (b.isIssued) {
                System.out.println("ID: " + b.id + " | " + b.name + " -> " + b.issuedTo);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No issued books.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. View Issued Books");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input!");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1: addBook(sc); break;
                case 2: viewBooks(); break;
                case 3: searchBook(sc); break;
                case 4: issueBook(sc); break;
                case 5: returnBook(sc); break;
                case 6: viewIssuedBooks(); break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}