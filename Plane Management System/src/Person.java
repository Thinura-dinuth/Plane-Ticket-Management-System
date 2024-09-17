public class Person {
    private String name;
    private String surname;
    private String email;

    // Add a constructor
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    // Add getter for name
    public String getName() {
        return name;
    }
    // Add setter for name
    public void setName(String name) {
        this.name = name;
    }
    // Add getter for surname
    public String getSurname() {
        return surname;
    }
    // Add setter for surname
    public void setSurname(String surname) {
        this.surname = surname;
    }
    // Add getter for email
    public String getEmail() {
        return email;
    }
    // Add setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Add a method to print the person's information
    public void printPersonInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }
}
