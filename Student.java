/*

Alex Schor

Project #1

Development Environment:
    IDE: IntelliJ IDEA 2019.2.1
    OS: Ubuntu 16.04.6
    Hardware: HP Chromebook 11

A non-computer vocabulary word and its meaning:
    Astringent
        1. (adjective) causing the skin or other tissues to contract
        2. (adjective) slightly bitter or acidic, usually used to describe
            a taste or smell

An inspirational quote:
    "No act of kindness, no matter how small, is ever wasted."
        Aesop (c. 620 - 564 BCE)
*/


/**
 *
 * Student.java - a class representing a student
 * @author Alex Schor
 * @version 1.0
 *
 */

public class Student {

    private String studentId;
    private String firstName;
    private String lastName;
    private String emailAddr;

    private String error;

    /**
     * Constructs a Student object from String parameters.
     *
     * @param studentId the student ID as a String
     * @param firstName the student's first name as a String
     * @param lastName the student's last name as a String
     * @param emailAddr the student's email address as a String
     */
    public Student(String studentId, String firstName, String lastName,
                   String emailAddr) throws IllegalArgumentException {
        error = "";

        // Building up an error message based on any present errors
        if (isEmpty(studentId)) {
            error += "Student ID not provided\n";
        } // End if

        if (isEmpty(firstName)) {
            error += "First name not provided\n";
        } // End if

        if (isEmpty(lastName)) {
            error += "Last name not provided\n";
        } // End if

        if (isEmpty(emailAddr)) {
            error += "Email address not provided\n";
        } else if (!emailAddr.contains("@")) {
            error += "Invalid email address (does not contain @)\n";
        } // End if

        // If any of the above were true, error now contains 1 or more messages
        if (error.length() > 0) {
            throw new IllegalArgumentException("The following errors were" +
                    " present: \n" + error);
        } // End if

        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddr = emailAddr;
    } // End Student constructor

    //**********************************************************************


    public String getStudentId() {
        return this.studentId;
    } // End getSID


    public String getFirstName() {
        return this.firstName;
    } // End getFirstName

    public String getLastName() {
        return this.lastName;
    } // End getLastName

    public String getEmailAddr() {
        return this.emailAddr;
    } // End getEmailAddr

    //**********************************************************************

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student s = (Student) o;
        return (studentId.equals(s.getStudentId()) &&
                firstName.equals(s.getFirstName()) &&
                lastName.equals(s.getLastName()) &&
                emailAddr.equals(s.getEmailAddr()));
    } // End equals

    //**********************************************************************

    @Override
    public String toString() {
        return "Student{" + " sID='" + getStudentId() + "'" + ", firstName='" +
                getFirstName() + "'" + ", lastName='" + getLastName() +
                "'" + ", emailAddr='" + getEmailAddr() + "'" + "}";
    } // End toString

    //**********************************************************************

    /**
     * Checks if a String s is null or empty
     *
     * @param s the String to check
     * @return true if the String s is null or empty
     */
    private Boolean isEmpty(String s) {
        return (s == null || s.equals(""));
    } // End isEmpty



} // End Student