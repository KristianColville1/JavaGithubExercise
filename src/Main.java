package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static ArrayList<Object> bookShelf;

    public static void main(String[] args) {
        setUpBasicBookshelf();
        printBookshelf();
    }

    public static void setUpBasicBookshelf() {
        bookShelf = new ArrayList<>();

        String firstDimension = "My Bookshelf";
        bookShelf.add(firstDimension);

        String[] secondDimension = new String[] { "Fiction", "Non-Fiction" };
        bookShelf.add(secondDimension);

        String[][] thirdDimension = new String[][] {
                { "The Hobbit", "1984" },
                { "Sapiens", "A Brief History of Time" }
        };
        bookShelf.add(thirdDimension);

        String[][][] fourthDimension = new String[][][] {
                {
                        { "The Hobbit", "J.R.R. Tolkien", "1937" },
                        { "1984", "George Orwell", "1949" }
                },
                {
                        { "Sapiens", "Yuval Noah Harari", "2011" },
                        { "A Brief History of Time", "Stephen Hawking", "1988" }
                }
        };
        bookShelf.add(fourthDimension);
    }

    public static void printBookshelf() {
        System.out.println("Welcome to " + bookShelf.get(0));

        String[] categories = (String[]) bookShelf.get(1);
        String[][] books = (String[][]) bookShelf.get(2);
        String[][][] bookDetails = (String[][][]) bookShelf.get(3);

        for (int i = 0; i < categories.length; i++) {
            System.out.println("\nCategory: " + categories[i]);
            for (int j = 0; j < books[i].length; j++) {
                System.out.println("Book: " + books[i][j]);
                System.out.println("Details: " + String.join(", ", bookDetails[i][j]));
            }
        }
    }


}
