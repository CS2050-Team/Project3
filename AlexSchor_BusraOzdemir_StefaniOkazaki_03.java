import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

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
    private static String INPUT_FILE = "Project_03_Input01.txt";
    private static String OUTPUT_FILE;
    


    /**
     * The entry point of the application.
     *
     * @param args the input arguments. The first argument, if present, is used
     *             as XX in the test case filename Project_02_InputXX.txt
     */

    public static void main(String[] args) {
        listOfStudents = new List<Student>();
        listOfGradeItems = new List<GradeItem>();
        
        processInput(INPUT_FILE);
        generateReport();
    }


//******************************************************************************

    public static void generateReport() {
        Student[] students = listOfStudents.toArray(new Student[]{});
        GradeItem[] gradeItems = listOfGradeItems.toArray(new GradeItem[]{});

        for (Student student : students) {
            System.out.println(student.getStudentId() + "\t" +
            student.getFirstName() + " " + student.getLastName() + "\t" +
            student.getEmailAddr());
            System.out.println("Grade Items");
            for (GradeItem grade : gradeItems) {
                
                
                // System.out.println("GRADE INFORMATION: ");
				System.out.println("Student Id : " +grade.getStudentId());
				// System.out.println("Grade Id : " + grade.getGradeId());
				// System.out.println("Course Id : " + grade.getCourseId());
				// System.out.println("Item Type: " + grade.getType());
				// System.out.println("Date : " + grade.getDate());
				// System.out.println("Max Score : " + grade.getMaxScore());
                // System.out.println("Actual Score : " + grade.getScore());


                if (grade.getStudentId().equals(student.getStudentId())) {
                    System.out.println("\t" + grade.getGradeId() + "\t" +
                    grade.getCourseId() + "\t" + grade.getType() + "\t" +
                    grade.getDate() + "\t" + grade.getMaxScore() + "\t" +
                    grade.getScore() + "\t" + (grade.getScore() * 100) /
                    grade.getMaxScore());
                }
            }
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
            System.out.println("Error initializing Student, skipping:");
            System.out.println(e.getMessage());
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
        System.out.println("Error initializing GradeItem, skipping:");
        System.out.println(e.getMessage());
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

            if (newStudent!=null) {
                if (listOfStudents.contains(newStudent)) {
                    System.err.println("Student with identical data already in"
                     + " the list. ID=" + newStudent.getStudentId());
                } else {
                    if (!listOfStudents.add(newStudent)) {
                        System.err.println("Error adding student to list. ID=" +
                        newStudent.getStudentId());
                    }
                }
            }

        } else if (line[1].equals("DEL")) {
            
            Student studentToDelete = makeStudent(line);


            if (studentToDelete!=null) {
                if (!listOfStudents.remove(studentToDelete)) {
                    System.err.println("Student not found in list. ID=" +
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
            }

        } else if (line[1].equals("DEL")) {
            
            GradeItem gradeItemToDelete = makeGradeItem(line);


            if (gradeItemToDelete!=null) {
                if (!listOfGradeItems.remove(gradeItemToDelete)) {
                    System.err.println("GradeItem not found in list. ID=" +
                    gradeItemToDelete.getGradeId());
                }
            }


        } else {
            System.out.println("Unrecognized command for GradeItem: " + line[1]);
        }


    }  // end processGradeItemData

} // end AlexSchor_BusraOzdemir_StefaniOkazaki_03 class