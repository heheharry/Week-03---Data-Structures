package Day5.PracticeProblems;
import java.util.Scanner;
class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList {
    Movie head;
    Movie tail;

    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null || current.next == null) {
            addAtEnd(title, director, year, rating);
        } else {
            newMovie.next = current.next;
            newMovie.prev = current;
            current.next.prev = newMovie;
            current.next = newMovie;
        }
    }

    public void removeByTitle(String title) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) head.prev = null;
                    else tail = null;
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }

    public void searchByDirector(String director) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.director.equalsIgnoreCase(director)) {
                System.out.println(current.title + " (" + current.year + ") - Rating: " + current.rating);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found by " + director);
        }
    }

    public void searchByRating(double rating) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.rating == rating) {
                System.out.println(current.title + " directed by " + current.director + " (" + current.year + ")");
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found with rating " + rating);
        }
    }

    public void updateRatingByTitle(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                current.rating = newRating;
                return;
            }
            current = current.next;
        }
    }

    public void displayForward() {
        Movie current = head;
        while (current != null) {
            System.out.println(current.title + " | " + current.director + " | " + current.year + " | " + current.rating);
            current = current.next;
        }
    }

    public void displayReverse() {
        Movie current = tail;
        while (current != null) {
            System.out.println(current.title + " | " + current.director + " | " + current.year + " | " + current.rating);
            current = current.prev;
        }
    }
}
public class MovieManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieList movieList = new MovieList();
        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by Title\n5. Search by Director\n6. Search by Rating\n7. Update Rating\n8. Display Forward\n9. Display Reverse\n10. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title1 = sc.nextLine();
                    System.out.print("Enter Director: ");
                    String director1 = sc.nextLine();
                    System.out.print("Enter Year: ");
                    int year1 = sc.nextInt();
                    System.out.print("Enter Rating: ");
                    double rating1 = sc.nextDouble();
                    movieList.addAtBeginning(title1, director1, year1, rating1);
                    break;
                case 2:
                    System.out.print("Enter Title: ");
                    String title2 = sc.nextLine();
                    System.out.print("Enter Director: ");
                    String director2 = sc.nextLine();
                    System.out.print("Enter Year: ");
                    int year2 = sc.nextInt();
                    System.out.print("Enter Rating: ");
                    double rating2 = sc.nextDouble();
                    movieList.addAtEnd(title2, director2, year2, rating2);
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title3 = sc.nextLine();
                    System.out.print("Enter Director: ");
                    String director3 = sc.nextLine();
                    System.out.print("Enter Year: ");
                    int year3 = sc.nextInt();
                    System.out.print("Enter Rating: ");
                    double rating3 = sc.nextDouble();
                    movieList.addAtPosition(pos, title3, director3, year3, rating3);
                    break;
                case 4:
                    System.out.print("Enter Title to Remove: ");
                    String remTitle = sc.nextLine();
                    movieList.removeByTitle(remTitle);
                    break;
                case 5:
                    System.out.print("Enter Director to Search: ");
                    String searchDirector = sc.nextLine();
                    movieList.searchByDirector(searchDirector);
                    break;
                case 6:
                    System.out.print("Enter Rating to Search: ");
                    double searchRating = sc.nextDouble();
                    movieList.searchByRating(searchRating);
                    break;
                case 7:
                    System.out.print("Enter Title to Update: ");
                    String updateTitle = sc.nextLine();
                    System.out.print("Enter New Rating: ");
                    double newRating = sc.nextDouble();
                    movieList.updateRatingByTitle(updateTitle, newRating);
                    break;
                case 8:
                    movieList.displayForward();
                    break;
                case 9:
                    movieList.displayReverse();
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
