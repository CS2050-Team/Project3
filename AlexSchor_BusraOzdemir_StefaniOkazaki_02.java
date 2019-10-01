import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*

Alex Schor, Busra Ozdemir, Stefani Okazaki joint submission

Project #2

Development Environment (Alex):
    IDE: IntelliJ IDEA 2019.2.1
    OS: Ubuntu 16.04.6
    Hardware: HP Chromebook 11

A non-computer vocabulary word and its meaning:
    Afforest (verb) - to establish a forest on land which was previously
                     unforested

A quote:
    "Things that are impossible just take longer."
        - Ian Hickson (Still alive, age not available on Internet)
*/


/**
 *
 * AlexSchor_BusraOzdemir_StefaniOkazaki_02.java - runs tests on Student and
 * GradeItem classes
 *
 * @author Alex Schor, Busra Ozdemir, Stefani Okazaki
 * @version 1.1
 *
 */
 
public class AlexSchor_BusraOzdemir_StefaniOkazaki_02 {


    private static Student student1;        // Student variables, to be used
    private static Student student2;        // for testing.

    private static GradeItem gradeItem1;    // GradeItem variables, to be
    private static GradeItem gradeItem2;    // used for testing.

    private static TestPrinter printer;           // TestPrinter class to simplify
                                            // printing (code is attached)


    /**
     * The entry point of the application.
     *
     * @param args the input arguments. The first argument, if present, is used
     *             as XX in the test case filename Project_02_InputXX.txt
     */

    public static void main(String[] args) {

        String file_suffix = "";
        file_suffix = FileNumber.getFileNumber(args, "Command Line");

        final String INPUT_FILENAME = "Project_02_Input" + file_suffix + ".txt";

        printer = new TestPrinter();

        printer.line();
        printer.newSection("TEST SET 1");
        printer.indent();

        printer.newSection("1A: Student toString");

        // Test 1A: check the toString and constructor of Student
        student1 = new Student("90012345","A","Student","email@example.com");
        printer.out(student1.toString());


        printer.newSection("1B: GradeItem toString");

        // Test 1B: check the toString and constructor of GradeItem
        gradeItem1 = new GradeItem("73","CS2050","90012345","HW",
                "9-10-2019", "100", "95");
        printer.out(gradeItem1.toString());

        printer.line();

        printer.unindent();
        printer.newSection("TEST SET 2");

        // Run the tests from the file. If the file isn't found, show an error
        // message
        if (!testFromFile(INPUT_FILENAME)) {
            printer.err("File '" + INPUT_FILENAME + "' not found, skipping");
        } // End if

        printer.line();


        printer.newSection("TEST SET 3");
        printer.indent();

        printer.newSection("3A: Student equals");

        // Test 3A: check the Student equals method
        student1 = new Student("90012345","A","Student","email@example.com");
        student2 = new Student("90012345","A","Student","email@example.com");
        
        printer.out("Comparing students with IDs " + student1.getStudentId() +
            " and " + student2.getStudentId());
        
        // Make sure students with identical data are equal
        printer.checkEqual(student1, student2);

        student2 = new Student("1234","Another","Student","name@example.com");

        printer.out("Comparing students with IDs " + student1.getStudentId() +
        " and " + student2.getStudentId());

        // Make sure students with non-identical data are not equal
        printer.checkNotEqual(student1, student2);


        printer.newSection("3B: GradeItem equals");

        // Test 3B: check the GradeItem equals method
        gradeItem1 = new GradeItem("73","CS2050","90012345","HW",
                "9-10-2019", "100", "95");
        gradeItem2 = new GradeItem("73","CS2050","90012345","HW",
                "9-10-2019", "100", "95");

        printer.out("Comparing grade items with IDs " + gradeItem1.getStudentId() +
            " and " + gradeItem2.getStudentId());

        // Make sure grade items with identical data are equal
        printer.checkEqual(gradeItem1, gradeItem2);

        gradeItem2 = new GradeItem("72","CS2051","90012344","HW",
                "9-10-2019", "100", "92");

        printer.out("Comparing grade items with IDs " + gradeItem1.getStudentId() +
            " and " + gradeItem2.getStudentId());

        // Make sure grade items with non-identical data are not equal
        printer.checkNotEqual(gradeItem1, gradeItem2);

        printer.line();


    } // end main method

//******************************************************************************


    /**
     * Runs Student and GradeItem tests from a file.
     *
     * @param filename the path to the file.
     *
     * @return true if the file was found, false otherwise
     */
    private static boolean testFromFile(String filename) {

        printer.indent();

        Scanner scanner = null;

        boolean success = true;

        try {

            scanner = new Scanner(new File(filename));

            while (scanner.hasNext()) {
                String[] words = scanner.nextLine().split(",");
                parseLine(words);
            } // end while

        } catch (FileNotFoundException e) {

            success = false;

        } finally {
            if (scanner != null) {
                scanner.close();
            } //end if

            printer.unindent();

            return success;
        } // end try-catch-finally


    } // end testFromFile

//******************************************************************************

    /**
     * Parses a line from a testing file and runs the test specified.
     *
     * @param line an String array of the command sequence in the testing file
     */
    private static void parseLine(String[] line) {
        if (line[0].equals("STUDENT")) {
            processStudentData(line);
        } else if (line[0].equals("GRADE ITEM")) {
            processGradeItemData(line);
        } // end if
    } // end parseLine

//******************************************************************************

    /**
     * Process student data.
     *
     * @param line A String array of the command sequence for the test
     */
    public static void processStudentData (String[] line) {
        if (line[1].equals("ADD")) {
            try {
                student2 = new Student(line[2], line[3], line[4], line[5]);
                printer.pass("Student successfully initialized from file ");
                printer.out("STUDENT INFORMATION:");
				printer.out("ID: "+student2.getStudentId());
				printer.out("First Name: "+ student2.getFirstName()); 
				printer.out("Last Name: "+ student2.getLastName());
                printer.out("Email ID: "+ student2.getEmailAddr());
                
            } catch( IllegalArgumentException e) {
                printer.out("Error initializing Student from file:");
                printer.err(e.getMessage());
            }
        } else {
            printer.out("Unrecognized command for Student: " + line[1]);
        }


    }  // end processStudentData

//******************************************************************************

    /**
     * Process grade item data.
     *
     * @param line A String array of the command sequence for the test
     */
    public static void processGradeItemData (String[] line) {

        if (line[1].equals("ADD")) {
            try {
                gradeItem2 = new GradeItem(line[2], line[3], line[4], line[5],
                        line[6], line[7], line[8]);
                printer.pass("GradeItem successfully initialized from file ");
                printer.out("GRADE INFORMATION: ");
				printer.out("Student Id : " +gradeItem2.getStudentId());
				printer.out("Grade Id : " + gradeItem2.getGradeId());
				printer.out("Course Id : " + gradeItem2.getCourseId());
				printer.out("Item Type: " + gradeItem2.getType());
				printer.out("Date : " + gradeItem2.getDate());
				printer.out("Max Score : " + gradeItem2.getMaxScore());
                printer.out("Actual Score : " + gradeItem2.getScore());
                
            } catch( IllegalArgumentException e) {
                printer.out("Error initializing GradeItem from file:");
                printer.err(e.getMessage());
            } // end try-catch
        } else {
            printer.out("Unrecognized command for GradeItem: " + line[1]);
        } // end if

    } // end processStudentData

} // end AlexSchor_BusraOzdemir_StefaniOkazaki_02 class