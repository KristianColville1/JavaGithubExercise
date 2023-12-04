package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static ArrayList<Object> bookShelf;

    public static void main(String[] args) {
        setUpBasicBookshelf();
        printBookshelf();
        buildBookShelf();
        addBookToShelf();
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

    public static void buildBookShelf() {
        File file = new File("bookshelf.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);

                writer.write("{\n\"bookshelf\": \"" + bookShelf.get(0) + "\",\n");

                String[] categories = (String[]) bookShelf.get(1);
                writer.write("\"categories\": [\n");
                for (int i = 0; i < categories.length; i++) {
                    writer.write("  {\"name\": \"" + categories[i] + "\",\n");

                    String[][] books = (String[][]) bookShelf.get(2);
                    writer.write("   \"books\": [\n");
                    for (int j = 0; j < books[i].length; j++) {
                        String[][][] bookDetails = (String[][][]) bookShelf.get(3);
                        writer.write("    {\"title\": \"" + books[i][j] + "\",\n");
                        writer.write("     \"author\": \"" + bookDetails[i][j][1] + "\",\n");
                        writer.write("     \"year\": \"" + bookDetails[i][j][2] + "\"}");
                        if (j < books[i].length - 1)
                            writer.write(",");
                        writer.write("\n");
                    }
                    writer.write("   ]\n");
                    if (i < categories.length - 1)
                        writer.write("  },\n");
                    else
                        writer.write("  }\n");
                }
                writer.write("]\n}");

                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private static String getInput(Scanner sc, String prompt, String validationRegex, String errorMessage) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = sc.nextLine().trim();
            if (input.matches(validationRegex)) {
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    private static String getCategory(Scanner sc) {
        return getInput(sc, "Enter category (Fiction or Non-Fiction):",
                "Fiction|Non-Fiction", "Invalid category. Please enter 'Fiction' or 'Non-Fiction'.");
    }

    private static String getBookTitle(Scanner sc) {
        return getInput(sc, "Enter book title:", ".+", "Book title cannot be empty.");
    }

    private static String getAuthor(Scanner sc) {
        return getInput(sc, "Enter author name:", ".+", "Author name cannot be empty.");
    }

    private static String getYear(Scanner sc) {
        return getInput(sc, "Enter publication year:", "\\d{4}", "Invalid year. Please enter a 4-digit year.");
    }

    public static void addBookToShelf() {
        Scanner sc = new Scanner(System.in);
        String category = getCategory(sc);
        String title = getBookTitle(sc);
        String author = getAuthor(sc);
        String year = getYear(sc);

        String[] categories = (String[]) bookShelf.get(1);
        int categoryIndex = -1;
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equalsIgnoreCase(category)) {
                categoryIndex = i;
                break;
            }
        }

        if (categoryIndex == -1) {
            System.out.println("Category not found!");
            return;
        }

        String[][] books = (String[][]) bookShelf.get(2);
        String[][][] bookDetails = (String[][][]) bookShelf.get(3);

        ArrayList<String> newBooksList = new ArrayList<>(Arrays.asList(books[categoryIndex]));
        newBooksList.add(title);
        books[categoryIndex] = newBooksList.toArray(new String[0]);

        ArrayList<ArrayList<String>> newDetailsList = new ArrayList<>();
        for (String[] detail : bookDetails[categoryIndex]) {
            newDetailsList.add(new ArrayList<>(Arrays.asList(detail)));
        }
        newDetailsList.add(new ArrayList<>(Arrays.asList(title, author, year)));
        bookDetails[categoryIndex] = newDetailsList.stream().map(l -> l.toArray(new String[0]))
                .toArray(String[][]::new);

        writeBookToDatabase();
    }

    public static void writeBookToDatabase() {
        try {
            FileWriter writer = new FileWriter("bookshelf.json");
            writer.write("{\n\"bookshelf\": \"" + bookShelf.get(0) + "\",\n");

            String[] categories = (String[]) bookShelf.get(1);
            writer.write("\"categories\": [\n");
            for (int i = 0; i < categories.length; i++) {
                writer.write("  {\"name\": \"" + categories[i] + "\",\n");

                String[][] books = (String[][]) bookShelf.get(2);
                writer.write("   \"books\": [\n");
                for (int j = 0; j < books[i].length; j++) {
                    String[][][] bookDetails = (String[][][]) bookShelf.get(3);
                    writer.write("    {\"title\": \"" + books[i][j] + "\",\n");
                    writer.write("     \"author\": \"" + bookDetails[i][j][1] + "\",\n");
                    writer.write("     \"year\": \"" + bookDetails[i][j][2] + "\"}");
                    if (j < books[i].length - 1)
                        writer.write(",");
                    writer.write("\n");
                }
                writer.write("   ]\n");
                if (i < categories.length - 1)
                    writer.write("  },\n");
                else
                    writer.write("  }\n");
            }
            writer.write("]\n}");

            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
