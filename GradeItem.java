/**
 *
 * GradeItem.java - a class representing a single score on an assignment
 * @author Alex Schor
 * @version 1.0
 *
 */


import java.util.Arrays;
import java.util.List;

public class GradeItem {
    private int gradeId;
    private String courseId;
    private String studentId;
    private String type;
    private String date;
    private int maxScore;
    private int score;

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

    //**********************************************************************

    /**
     *
     * @param s the String to convert to Integer
     * @return the Integer representation of the string or null if it cannot
     *      be converted
     */
    private Integer parseInteger(String s){
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e) {
            return null;
        } // End try-catch
    } // End parseInteger

    //**********************************************************************

    /**
     * Constructs a GradeItem object from String parameters
     *
     * @param strGradeId the grade item ID, as a String. Must be able to
     *                   be converted to an integer > 0
     * @param courseId the course ID, as a String
     * @param studentId the student ID, as a String
     * @param type the grade item type. Must be one of "HW", "Quiz",
     *             "Class Work", "Test", "Final"
     * @param date the date of the grade item
     * @param strMaxScore the maximum possible score, as a String.
     *                    Must be the String representation of an
     *                    integer >=0
     * @param strScore the specific score for this gradeitem, as a String.
     *                 Must be the String representation of an
     *                 integer <= maxScore
     */
    public GradeItem(String strGradeId, String courseId, String studentId,
                     String type, String date, String strMaxScore,
                     String strScore) {

        // Building up an error message based on any present errors
        String error = "";

        if (isEmpty(strGradeId)) {
            error += "Grade ID not provided\n";
        } // End if
        if (isEmpty(courseId)) {
            error += "Course ID not provided\n";
        } // End if
        if (isEmpty(studentId)) {
            error += "Student ID not provided\n";
        } // End if
        if (isEmpty(type)) {
            error += "Grade item type not provided\n";
        } // End if
        if (isEmpty(date)) {
            error += "Date not provided\n";
        } // End if
        if (isEmpty(strMaxScore)) {
            error += "Maximum possible score not provided\n";
        } // End if
        if (isEmpty(strScore)) {
            error += "Score not provided\n";
        } // End if

        List<String> validTypes = Arrays.asList("HW", "Quiz", "Class Work",
                "Test", "Final");

        if (!validTypes.contains(type)) {
            error += "Invalid type (Must be one of " + validTypes + ")\n";
        } // End if

        // Convert the fields that must be integers from String.
        // parseInteger returns null if the String can't be converted.
        Integer gradeID = parseInteger(strGradeId);
        Integer maxScore = parseInteger(strMaxScore);
        Integer score = parseInteger(strScore);

        if (gradeID == null) {
            error += "Grade ID must be an integer\n";
        } else if (gradeID <= 0) {
            error += "Grade ID must be greater than 0\n";
        } // End if

        if (maxScore == null) {
            error += "Max Score must be an integer\n";
        } else if( maxScore <= 0) {
            error += "Max Score must be greater than zero\n";
        } // End if

        if (score == null) {
            error += "Actual Score must be an integer\n";
        } else if (score < 0) {
            error += "Actual Score must be 0 or greater\n";
        }  else if (score > maxScore) {
            error += "Actual Score must less than or equal to the Max Score\n";
        } // End if// End if

        if (error.length() > 0) {
            throw new IllegalArgumentException("The following errors were" +
            " present: \n" + error);
        } // End if


        this.gradeId = gradeID.intValue();
        this.courseId = courseId;
        this.type = type;
        this.date = date;
        this.maxScore = maxScore.intValue();
        this.score = score.intValue();
        this.studentId = studentId;
    } // End GradeItem constructor

    //**********************************************************************

    public int getGradeId() {
        return this.gradeId;
    } // End getGradeID
    
    public String getCourseId() {
        return this.courseId;
    } // End getCourseID

    public String getStudentId() {
        return this.studentId;
    }  // End getStudentID

    public String getType() {
        return this.type;
    } // End getType

    public String getDate() {
        return this.date;
    } // End getDate


    public int getMaxScore() {
        return this.maxScore;
    } // End getMaxScore


    public int getScore() {
        return this.score;
    } // End getScore

    //**********************************************************************

    @Override
    public String toString() {
        return "GradeItem{" + " gradeID='" + getGradeId() + "'" + ", courseID='"
        + getCourseId() + "'" + ", studentID='" + getStudentId() + "'"
        + ", type='" + getType() + "'" + ", date='" + getDate() + "'"
        + ", maxScore='" + getMaxScore() + "'"
        + ", score='" + getScore() + "'" + "}";
    } // End toString

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GradeItem)) {
            return false;
        } //end if
        GradeItem g = (GradeItem) o;
        return (gradeId == g.getGradeId() &&
                courseId.equals(g.getCourseId()) &&
                studentId.equals(g.getStudentId()) &&
                type.equals(g.getType()) &&
                date.equals(g.getDate()) &&
                maxScore == g.getMaxScore() &&
                score == g.getScore());
    } // End equals

} // End GradeItem
