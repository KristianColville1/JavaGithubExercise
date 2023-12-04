package src;

public class Main {
    public static String firstDimension;
    public static String[] secondDimension;
    public static String[][] thirdDimension;
    public static String[][][] fourthDimension;

    public static void main(String[] args) {
        setUpBasicBookshelf();
    }

    public static void setUpBasicBookshelf() {
        firstDimension = "My Bookshelf";
        secondDimension = new String[] { "Fiction", "Non-Fiction" };
        thirdDimension = new String[][] {
                { "The Hobbit", "1984" },
                { "Sapiens", "A Brief History of Time" }
        };
        fourthDimension = new String[][][] {
                { { "The Hobbit", "J.R.R. Tolkien", "1937" }, { "1984", "George Orwell", "1949" } },
                { { "Sapiens", "Yuval Noah Harari", "2011" }, { "A Brief History of Time", "Stephen Hawking", "1988" } }
        };

        System.out.println("Welcome to " + firstDimension);
        for (int i = 0; i < secondDimension.length; i++) {
            System.out.println("\nCategory: " + secondDimension[i]);
            for (int j = 0; j < thirdDimension[i].length; j++) {
                System.out.println("Book: " + thirdDimension[i][j]);
                System.out.println("Details: " + String.join(", ", fourthDimension[i][j]));
            }
        }
    }
}
