package gr.academic.city.mc.studentmanagement;

/**
 * Created by trumpets on 5/11/16.
 */
public class Student {

    private String firstName;
    private String lastName;
    private String age;

    // Necessary for JSON serialization
    public Student() {

    }

    public Student(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        // This is called by the ArrayAdapter class to get a textual description of the item
        return firstName + " " + lastName;
    }
}