package Day5.PracticeProblems;
import java.util.Scanner;
class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}
public class TicketReservationSystem {
    private Ticket head = null;
    private Ticket tail = null;

    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
        System.out.println("Ticket booked successfully!");
    }

    public void removeTicket(int ticketID) {
        if (head == null) {
            System.out.println("No tickets available to remove.");
            return;
        }

        Ticket current = head;
        Ticket previous = tail;
        do {
            if (current.ticketID == ticketID) {
                if (current == head && current == tail) { // Only one ticket
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) {
                    tail = previous;
                    tail.next = head;
                } else {
                    previous.next = current.next;
                }
                System.out.println("Ticket ID " + ticketID + " removed successfully.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket ID " + ticketID + " not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked yet.");
            return;
        }
        Ticket current = head;
        System.out.println("Booked Tickets:");
        do {
            System.out.println("Ticket ID: " + current.ticketID +
                    ", Customer: " + current.customerName +
                    ", Movie: " + current.movieName +
                    ", Seat: " + current.seatNumber +
                    ", Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    public void searchTicket(String searchKey) {
        if (head == null) {
            System.out.println("No tickets booked yet.");
            return;
        }
        Ticket current = head;
        boolean found = false;
        do {
            if (current.customerName.equalsIgnoreCase(searchKey) || current.movieName.equalsIgnoreCase(searchKey)) {
                System.out.println("Ticket Found -> Ticket ID: " + current.ticketID +
                        ", Customer: " + current.customerName +
                        ", Movie: " + current.movieName +
                        ", Seat: " + current.seatNumber +
                        ", Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No ticket found for \"" + searchKey + "\".");
        }
    }

    public void countTickets() {
        if (head == null) {
            System.out.println("Total Booked Tickets: 0");
            return;
        }
        Ticket current = head;
        int count = 0;
        do {
            count++;
            current = current.next;
        } while (current != head);

        System.out.println("Total Booked Tickets: " + count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();

        while (true) {
            System.out.println("\n--- Ticket Reservation Menu ---");
            System.out.println("1. Book New Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search Ticket by Customer or Movie");
            System.out.println("5. Count Total Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume extra newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customer = scanner.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movie = scanner.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seat = scanner.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String time = scanner.nextLine();
                    system.addTicket(id, customer, movie, seat, time);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to Cancel: ");
                    int removeId = scanner.nextInt();
                    system.removeTicket(removeId);
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name or Movie Name to Search: ");
                    String key = scanner.nextLine();
                    system.searchTicket(key);
                    break;
                case 5:
                    system.countTickets();
                    break;
                case 6:
                    System.out.println("Thank you for using the Ticket Reservation System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
