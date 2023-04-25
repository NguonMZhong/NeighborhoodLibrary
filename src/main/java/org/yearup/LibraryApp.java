package org.yearup;

import javax.xml.namespace.QName;
import java.util.Scanner;

public class LibraryApp {

    static final Scanner scanner = new Scanner(System.in);
    static final Book[] books = new Book[5];


    public static void main(String[] args) {

        libraryInventory(); //initialize inventory

        while (true) {
            int choice = homeScreen();
            switch (choice) {
                case 1 -> showAvailableBooks();
                case 2 -> showCheckedOutBooks();
                case 3 -> {
                    System.out.println("Thank you for visiting our Neighborhood Library!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please choose either 1, 2, or 3.");
            }


        }

    }

    //Library book array
    public static void libraryInventory() {
        //Initialize inventory
        books[0] = new Book(1, "978-148-154-053-3", "Animal Garment");
        books[1] = new Book(2, "978-148-999-053-4", "Dragon Warrior");
        books[2] = new Book(3, "345-148-945-908-8", "The Day I get to Enjoy Life");
        books[3] = new Book(4, "978-254-232-343-7", "Aaron Tales");
        books[4] = new Book(5, "968-244-523-232-2", "Wish I can Fly");
    }

    //Display Home Screen options
    public static int homeScreen() {

        System.out.println();
        System.out.println("Home");
        System.out.println("------------------------------------------------------------------");
        System.out.println();
        System.out.println("Welcome to our Neighborhood Library. What would you like to do?");
        System.out.println("1) Show Available Books");
        System.out.println("2) Show Checked Out Books");
        System.out.println("3) Exit");
        System.out.print("Please Enter your Option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice;

    }

    //Show Available Books
    public static void showAvailableBooks() {
        System.out.println("Available Book"); //display available books

        //loop through all book and print details if not checked out
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                System.out.println(book.getId() + " - " + book.getIsbn() + " - " + book.getTitle());
            }
        }

        System.out.println("Enter the ID of the book you want to check out or X to return back to home screen.");
        String input = scanner.nextLine();

        //If user enter X, return to home screen
        if (input.equalsIgnoreCase("X")) {
            return;
        }
        //parse input as integer to select book ID
        int selectId;
        try {
            selectId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input try again.");
            showAvailableBooks();
            return;
        }
        //Find selected book in array to check out
        for (Book book : books) {
            if (book.getId() == selectId) {
                if (book.isCheckedOut()) {
                    System.out.println("This book ID is already checked out.");
                } else {
                    System.out.println("Enter your name to check out the book");
                    String name = scanner.nextLine();
                    book.checkOut(name);
                    System.out.println("book " + book.getTitle() + " has been checked out to " + name + ".");
                }
            }
        }

    }

    public static void showCheckedOutBooks() {

        System.out.println();
        System.out.println("Checkout Books");
        System.out.println("------------------------------------------------------------------");

        for (Book book : books) {
            if (book.isCheckedOut()) {
                System.out.println(book.getId() + ". " + book.getIsbn() + " - " + book.getTitle() + " (Checked out to " + book.getCheckedOutTo() + ")");
            }
        }
        System.out.println("Enter C to check in a book or X to return to home screen.");
        String input = scanner.nextLine();


        if (input.equalsIgnoreCase("X")) {
            return;
        } else if (input.equalsIgnoreCase("C")) {
            System.out.println("Enter the ID of the book to check in: ");
            int bookId = scanner.nextInt();

            //loop array to find book with given ID
            boolean found = false;
            for (Book book : books) {
                book.checkIn();
                if (book.getId() == bookId && book.isCheckedOut()) {
                    book.checkIn();
                    System.out.println("Book with ID " + bookId + " has been checked in.");
                    found = true;
                    break;
                }

            }
        }
    }
}