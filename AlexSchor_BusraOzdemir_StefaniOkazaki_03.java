import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintStream;

/*

Alex Schor, Busra Ozdemir, Stefani Okazaki joint submission

Project #3

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
 * AlexSchor_BusraOzdemir_StefaniOkazaki_03.java - runs tests on Student and
 * GradeItem classes
 *
 * @author Alex Schor, Busra Ozdemir, Stefani Okazaki
 * @version 1.1
 *
 */


/*
    Main Class

        - listOfStudents : List

        - listOfGradeItems : List

        - INPUT_FILE : String

        - OUTPUT_FILE : String

        + main (args : String[]) : void

        + processInput () : void

        + processStudentData (info : String[] ) : void

        + processGradeItemData (info : String[] ) : void

        + generateReport () : void
*/

 
public class AlexSchor_BusraOzdemir_StefaniOkazaki_03 {


    private static List<Student> listOfStudents;
    private static List<GradeItem> listOfGradeItems;
    private static String INPUT_FILE;
    private static String OUTPUT_FILE;
    


    /**
     * The entry point of the application.
     *
     * @param args the input arguments. The first argument, if present, is used
     *             as XX in the test case filename Project_02_InputXX.txt
     */

    public static void main(String[] args) {

        String file_suffix = "";
        file_suffix = FileNumber.getFileNumber(args, "Command Line");

        INPUT_FILE = "Project_03_Input" + file_suffix + ".txt";

        listOfStudents = new List<Student>();
        listOfGradeItems = new List<GradeItem>();
        
        processInput(INPUT_FILE);

        OUTPUT_FILE = "Generating report to Project_03_Output" + file_suffix +
                        ".txt...  ";
        System.out.print(OUTPUT_FILE);
        generateReport(OUTPUT_FILE);
        System.out.println("done.");
    }


//******************************************************************************

    public static void generateReport(String filename) {
        try{
            PrintStream outputFile = new PrintStream(filename);
            Student[] students = listOfStudents.toArray(new Student[]{});
            GradeItem[] gradeItems = listOfGradeItems.toArray(new GradeItem[]{});
            outputFile.println("========================================" +
            "========================================");
            for (Student student : students) {
                outputFile.println(student.getStudentId() + "\t" +
                student.getFirstName() + "\t" + student.getLastName() + "\t" +
                student.getEmailAddr());
                boolean anyGradeItems = false;
                for (GradeItem grade : gradeItems) {

                    if (grade.getStudentId().equals(student.getStudentId())) {
                        if (!anyGradeItems) {
                            outputFile.println("Grade Items:");
                            anyGradeItems = true;
                        }
                        outputFile.println("\t" + grade.getGradeId() + "\t" +
                        grade.getCourseId() + "\t" + grade.getType() + "\t" +
                        grade.getDate() + "\t" + grade.getMaxScore() + "\t" +
                        grade.getScore() + "\t" + (grade.getScore() * 100) /
                        grade.getMaxScore());
                    }
                }
                outputFile.println("========================================" +
                                "========================================");
            }
        } catch(FileNotFoundException e) {
            System.err.println("");
        }
    }

//******************************************************************************


    /**
     * Runs Student and GradeItem tests from a file.
     *
     * @param filename the path to the file.
     *
     * @return true if the file was found, false otherwise
     */
    private static void processInput(String filename) {

        Scanner scanner = null;

        try {

            scanner = new Scanner(new File(filename));

            System.out.println("Reading data from file " + filename);
            
            while (scanner.hasNext()) {
                String[] words = scanner.nextLine().split(",");
                parseLine(words);
            } // end while


        } catch (FileNotFoundException e) {

            System.err.println("File " + filename + " not found.");

        } finally {
            if (scanner != null) {
                scanner.close();
            } //end if
        } // end try-catch-finally


    } // end processInput

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

private static Student makeStudent(String[] line) {
        try {
            Student newStudent = new Student(line[2], line[3], line[4],
            line[5]);
            return newStudent;
            
        } catch( IllegalArgumentException e) {
            System.err.println("Error initializing Student, skipping:");
            System.err.println(e.getMessage().replaceAll("(?m)^", "\t"));
        }
        return null;
    }
    
//******************************************************************************
    
private static GradeItem makeGradeItem(String[] line) {
    try {
        GradeItem newGradeItem = new GradeItem(line[2], line[4], line[3],
        line[5], line[6], line[7], line[8]);
        return newGradeItem;
        
    } catch( IllegalArgumentException e) {
        System.err.println("Error initializing GradeItem, skipping:");
        System.err.println(e.getMessage().replaceAll("(?m)^", "\t"));
    }
    return null;
}


//******************************************************************************

    /**
     * Process student data.
     *
     * @param line A String array of the command sequence for the test
     */
    public static void processStudentData (String[] line) {
        if (line[1].equals("ADD")) {

            // Create the student object from the data in the file
            
            Student newStudent = makeStudent(line);

            // If it was created successfully, check if it is in the list and
            // add it if not

            if (newStudent != null) {
                if (listOfStudents.contains(newStudent)) {
                    System.err.println("Student with identical data already in"
                     + " the list, skipping. ID=" + newStudent.getStudentId());
                } else {
                    if (!listOfStudents.add(newStudent)) {
                        System.err.println("Could not add student to list. ID="+
                        newStudent.getStudentId());
                    } else {
                        System.out.println("Student added to list with ID "
                        + newStudent.getStudentId());
                    }
                }
            }

        } else if (line[1].equals("DEL")) {
            
            Student studentToDelete = makeStudent(line);


            if (studentToDelete!=null) {
                if (!listOfStudents.remove(studentToDelete)) {
                    System.err.println("Student not found in list. ID=" +
                    studentToDelete.getStudentId());
                } else {
                    System.out.println("Student removed from list with ID " +
                                        studentToDelete.getStudentId());
                }
            }


        } else {
            System.out.println("Unrecognized command for Student: " + line[1]);
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

            // Create the GradeItem object from the data in the file
            
            GradeItem newGradeItem = makeGradeItem(line);

            // If it was created successfully, check if it is in the list and
            // add it if not

            if (newGradeItem!=null) {
                if (listOfGradeItems.contains(newGradeItem)) {
                    System.err.println("GradeItem with identical data already in"
                     + " the list. ID=" + newGradeItem.getGradeId());
                } else {
                    if (!listOfGradeItems.add(newGradeItem)) {
                        System.err.println("Error adding GradeItem to list. ID=" +
                        newGradeItem.getGradeId());
                    }
                }
            } else {
                System.out.println("Student was not added to the list");
            }

        } else if (line[1].equals("DEL")) {
            
            GradeItem gradeItemToDelete = makeGradeItem(line);


            if (gradeItemToDelete!=null) {
                if (!listOfGradeItems.remove(gradeItemToDelete)) {
                    System.err.println("GradeItem not found in list. ID=" +
                    gradeItemToDelete.getGradeId());
                } else {
                    System.out.println("GradeItem removed from list with ID " +
                                        gradeItemToDelete.getGradeId());
                }
            }


        } else {
            System.out.println("Unrecognized command for GradeItem: " + line[1]);
        }


    }  // end processGradeItemData

} // end AlexSchor_BusraOzdemir_StefaniOkazaki_03 class