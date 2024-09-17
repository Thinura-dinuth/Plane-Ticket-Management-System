import java.util.Scanner;
import java.util.InputMismatchException;

public class PlaneManagement {
    static Scanner read = new Scanner(System.in);
    // 2D array representing the seating plan of the plane
    public static int[][] seatingPlan = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    // Array of tickets Sold
    public static Ticket[] tickets = new Ticket[seatingPlan.length * seatingPlan[0].length];
    // Counter for the tickets sold
    public static int ticketCount = 0;

    public static void main(String[] args) {

        System.out.println("Welcome to the Plane Management application");
        showMenu();
    }

    // Method to show the menu options
    public static void showMenu() {
        System.out.println("*".repeat(50));
        System.out.println("*" + " ".repeat(18) + "MENU OPTIONS" + " ".repeat(18) + "*");
        System.out.println("*".repeat(50));
        System.out.println(" ".repeat(5) + "1) Buy a seat ");
        System.out.println(" ".repeat(5) + "2) Cancel a seat");
        System.out.println(" ".repeat(5) + "3) Find first available seat");
        System.out.println(" ".repeat(5) + "4) Show seating plan ");
        System.out.println(" ".repeat(5) + "5) Print tickets information and total sales");
        System.out.println(" ".repeat(5) + "6) Search ticket");
        System.out.println(" ".repeat(5) + "0) Quit");
        System.out.println("*".repeat(50));
        System.out.print("Please select an option: ");

        try{
            int option = read.nextInt();
            switch (option) {
                case 1:
                    System.out.println("You have selected option 1: Buy a seat");
                    buy_seat(seatingPlan);
                    break;
                case 2:
                    System.out.println("You have selected option 2: Cancel a seat");
                    cancel_seat(seatingPlan);
                    break;
                case 3:
                    System.out.println("You have selected option 3: Find first available seat");
                    find_first_available(seatingPlan);
                    break;
                case 4:
                    System.out.println("You have selected option 4: Show seating plan");
                    show_seating_plan(seatingPlan);
                    break;
                case 5:
                    System.out.println("You have selected option 5: Print tickets information and total sales");
                    print_tickets_info(tickets, ticketCount);
                    break;
                case 6:
                    System.out.println("You have selected option 6: Search ticket");
                    search_ticket(seatingPlan);
                    break;
                case 0:
                    System.out.println("You have selected option 0: Quit");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            showMenu();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid input.");
            read.next();
            showMenu();
        }
    }
    // Method to get the row and seat number from the user
    public static int[] getSeatInput(int[][] seatingPlan) {
        boolean validInput = false;
        int rowNumber = -1;
        int seatNumber = -1;
        char row = ' ';

        while (!validInput) {
            try {
                System.out.println("Enter row letter (A-D): ");
                row = read.next().toUpperCase().charAt(0);

                switch (row) {
                    case 'A':
                        rowNumber = 0;
                        break;
                    case 'B':
                        rowNumber = 1;
                        break;
                    case 'C':
                        rowNumber = 2;
                        break;
                    case 'D':
                        rowNumber = 3;
                        break;
                    default:
                        System.out.println("Invalid row. Please enter a valid row (A-D).");
                        continue;
                }
                // Get the number of seats in the row
                int maxSeats = seatingPlan[rowNumber].length;

                System.out.println("Enter seat number (1-" + maxSeats + "): ");
                seatNumber = read.nextInt();
                // Check if the seat number is valid
                if (seatNumber >= 1 && seatNumber <= maxSeats) {
                    validInput = true;
                } else {
                    System.out.println("Invalid seat number. Please enter a valid seat number (1-" + maxSeats + ").");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid input.");
                read.next();
            }
        }
        // Return the row and seat number
        return new int[] {rowNumber, seatNumber};
    }

    // Method to get the row letter from the row number
    public static String getRowLetter(int rowNumber) {
        switch (rowNumber) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
        }
        return null;
    }

    // Method to get the person details
    public static Person getPersonDetails() {
        System.out.println("Enter your name: ");
        String name = read.next();
        System.out.println("Enter your surname: ");
        String surname = read.next();
        System.out.println("Enter your email: ");
        String email = read.next();
        // Return a new person object
        return new Person(name, surname, email);
    }

    // Method to calculate the price of a seat
    public static double calculatePrice(int seatNumber) {
        if (seatNumber <= 5) {
            return 200.0;
        } else if (seatNumber <= 9) {
            return 150.0;
        } else {
            return 180.0;
        }
    }

    // Method to buy a seat
    public static void buy_seat(int[][] seatingPlan) {
        // Get the row and seat number from the user
        int[] seatInfo = getSeatInput(seatingPlan);
        int rowNumber = seatInfo[0];
        int seatNumber = seatInfo[1];
        String row = getRowLetter(rowNumber+ 1);
        // Check if the seat is available
        if (seatingPlan[rowNumber][seatNumber - 1] == 0) {
            seatingPlan[rowNumber][seatNumber - 1] = 1;

            Person person = getPersonDetails();
            double price = calculatePrice(seatNumber);
            //Create a ticket object
            Ticket ticket = new Ticket(rowNumber, seatNumber, price, person);
            tickets[ticketCount++] = ticket;
            // Save the ticket information to a file
            ticket.saveFile();
            System.out.println("Congratulations! You have successfully bought seat " + row + seatNumber + ".");
        } else {
            System.out.println("Seat " + row + seatNumber + " is not available. Please try another one.");
        }
    }

    // Method to cancel a seat
    public static void cancel_seat(int[][] seatingPlan) {
        int[] seatInfo = getSeatInput(seatingPlan);
        int rowNumber = seatInfo[0];
        int seatNumber = seatInfo[1];
        String row = getRowLetter(rowNumber +1);

        if (seatingPlan[rowNumber][seatNumber - 1] == 0) {
            System.out.println("Seat " + row + seatNumber + " is already available.");
        } else {
            seatingPlan[rowNumber][seatNumber - 1] = 0;
            // Find the ticket and delete it
            for (int i = 0; i < ticketCount; i++) {
                if (tickets[i].getRow() == rowNumber && tickets[i].getSeat() == seatNumber) {
                    // Delete the ticket information file
                    tickets[i].deleteFile();
                    for (int j = i; j < ticketCount - 1; j++) {
                        tickets[j] = tickets[j + 1];
                    }
                    ticketCount--;
                    break;
                }
            }
            System.out.println("Seat " + row + seatNumber + " reservation has been cancelled.");
        }
    }

    // Method to find the first available seat
    public static void find_first_available(int[][] seatingPlan) {
        boolean seatFound = false;
        // Loop through the seating plan to find the first available seat
        for (int row = 0; row < seatingPlan.length; row++) {
            for (int seat = 0; seat < seatingPlan[row].length; seat++) {
                if (seatingPlan[row][seat] == 0) {
                    String rowLetter = getRowLetter(row + 1);
                    System.out.println("First available seat found at Row " + rowLetter + ", Seat " + (seat + 1));
                    seatFound = true;
                    break;
                }
            }
            if (seatFound == true) {
                break;
            }
        }
    }

    // Method to show the seating plan
    public static void show_seating_plan(int[][] seatingPlan) {
        // Loop through the seating plan and print the seat status
        for (int[] row : seatingPlan) {
            for (int seat : row) {
                if (seat == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    // Method to print the tickets information and total sales
    public static void print_tickets_info(Ticket[] tickets, int ticketCount) {
        if (ticketCount == 0) {
            System.out.println("No tickets sold.");
        } else {
            double totalSales = 0;
            // Loop through the tickets and print the ticket information
            for (int i = 0; i < ticketCount; i++) {
                System.out.println("Ticket " + (i + 1));
                tickets[i].printTicketInfo();
                totalSales += tickets[i].getPrice();
            }
            System.out.println("Total sales:  £" + totalSales);
        }
    }

    // Method to search for a ticket
    public static void search_ticket(int[][] seatingPlan) {
        int[] seatInfo = getSeatInput(seatingPlan);
        int rowNumber = seatInfo[0];
        int seatNumber = seatInfo[1];
        String row = getRowLetter(rowNumber);

        if (seatingPlan[rowNumber][seatNumber - 1] == 1) {
            for (int i = 0; i < ticketCount; i++) {
                // Find the ticket and print the ticket and person information
                if (tickets[i].getRow() == rowNumber && tickets[i].getSeat() == seatNumber) {
                    // Print the ticket and person information
                    tickets[i].printTicketInfo();
                    tickets[i].getPerson().printPersonInfo();
                    return;
                }
            }
        } else {
            System.out.println("This seat is available.");
        }
    }
}
