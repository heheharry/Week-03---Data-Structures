package Day5.PracticeProblems;
class LibraryManager {
    private BookNode head;
    private BookNode tail;

    public void addBookAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        BookNode newBook = new BookNode(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addBookAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        BookNode newBook = new BookNode(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void addBookAtPosition(int position, String title, String author, String genre, int bookId, boolean isAvailable) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            addBookAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }

        BookNode newBook = new BookNode(title, author, genre, bookId, isAvailable);
        BookNode temp = head;
        int count = 1;
        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }
        if (temp == null) {
            addBookAtEnd(title, author, genre, bookId, isAvailable);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            if (temp.next != null) {
                temp.next.prev = newBook;
            } else {
                tail = newBook;
            }
            temp.next = newBook;
        }
    }

    public void removeBookById(int bookId) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        BookNode temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                if (temp == head) {
                    head = temp.next;
                    if (head != null) head.prev = null;
                } else if (temp == tail) {
                    tail = temp.prev;
                    if (tail != null) tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                System.out.println("Book ID " + bookId + " removed.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID " + bookId + " not found.");
    }

    public void searchByTitle(String title) {
        BookNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                displayBookDetails(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Book Title '" + title + "' not found.");
        }
    }

    public void searchByAuthor(String author) {
        BookNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                displayBookDetails(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Author '" + author + "' not found.");
        }
    }

    public void updateAvailability(int bookId, boolean status) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = status;
                System.out.println("Availability updated for Book ID " + bookId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID " + bookId + " not found.");
    }

    public void displayBooksForward() {
        BookNode temp = head;
        if (temp == null) {
            System.out.println("No books to display.");
            return;
        }
        while (temp != null) {
            displayBookDetails(temp);
            temp = temp.next;
        }
    }

    public void displayBooksBackward() {
        BookNode temp = tail;
        if (temp == null) {
            System.out.println("No books to display.");
            return;
        }
        while (temp != null) {
            displayBookDetails(temp);
            temp = temp.prev;
        }
    }

    public int countBooks() {
        int count = 0;
        BookNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    private void displayBookDetails(BookNode book) {
        System.out.println("[Title: " + book.title +
                ", Author: " + book.author +
                ", Genre: " + book.genre +
                ", ID: " + book.bookId +
                ", Available: " + (book.isAvailable ? "Yes" : "No") + "]");
    }
}

// Book Node class for each book
class BookNode {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    BookNode next;
    BookNode prev;

    public BookNode(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        library.addBookAtEnd("The Alchemist", "Paulo Coelho", "Fiction", 201, true);
        library.addBookAtBeginning("1984", "George Orwell", "Dystopian", 202, true);
        library.addBookAtPosition(2, "To Kill a Mockingbird", "Harper Lee", "Classic", 203, true);

        System.out.println("\nDisplaying books forward:");
        library.displayBooksForward();

        System.out.println("\nDisplaying books backward:");
        library.displayBooksBackward();

        System.out.println("\nSearching for book by Title:");
        library.searchByTitle("1984");

        System.out.println("\nSearching for book by Author:");
        library.searchByAuthor("Harper Lee");

        System.out.println("\nUpdating Availability Status:");
        library.updateAvailability(201, false);

        System.out.println("\nRemoving a book by ID:");
        library.removeBookById(202);

        System.out.println("\nDisplaying books forward after removal:");
        library.displayBooksForward();

        System.out.println("\nTotal number of books:");
        System.out.println(library.countBooks());
    }
}
