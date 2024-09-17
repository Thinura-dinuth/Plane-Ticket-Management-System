import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    // Add a constructor
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    // Add getter for row
    public int getRow() {
        return row;
    }
    // Add setter for row
    public void setRow(int row) {
        this.row = row;
    }
    // Add getter for seat
    public int getSeat() {
        return seat;
    }
    // Add setter for seat
    public void setSeat(int seat) {
        this.seat = seat;
    }
    // Add getter for price
    public double getPrice() {
        return price;
    }
    // Add setter for price
    public void setPrice(double price) {
        this.price = price;
    }
    // Add getter for person
    public Person getPerson() {
        return person;
    }
    // Add setter for person
    public void setPerson(Person person) {
        this.person = person;
    }

    // Add a method to print the ticket's information
    public void printTicketInfo() {
        String rowLetter = PlaneManagement.getRowLetter(row+1);
        System.out.println("Row: " + rowLetter+ " Seat: " + seat  + " Price: £" + price);
    }

    // Add a method to save the ticket information to a file
    public void saveFile() {
        String rowLetter = PlaneManagement.getRowLetter(row+1);
        String filename = rowLetter + seat + ".txt";
        try {
            FileWriter file = new FileWriter(filename);
            file.write("Seat Information: \n");
            file.write("Row: " + rowLetter + "\n");
            file.write("Seat: " + seat + "\n");
            file.write("Price: £" + price + "\n");
            file.write("Ticket Holder Information: \n");
            file.write("Name: " + person.getName() + "\n");
            file.write("Surname: " + person.getSurname() + "\n");
            file.write("Email: " + person.getEmail() + "\n");
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file");
            e.printStackTrace();
        }
    }

    // Add a method to delete the file
    public void deleteFile() {
        String rowLetter = PlaneManagement.getRowLetter(row + 1);
        String filename = rowLetter + String.valueOf(seat) + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
