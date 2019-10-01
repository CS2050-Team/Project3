import java.io.PrintStream;

/**
 *
 * TestPrinter.java - a class which makes printing nicely-formatted program
 * tests easy.
 * 
 * @author Alex Schor, Busra Ozdemir, Stefani Okazaki
 * @version 1.1
 *
 */

public class TestPrinter {


    /*
     * The TestPrinter class provides functionality to produce test output
     * formatted like this:
     * 
     *  [*] Header
     *      [-] Subheader
     *          Some test output
     *          Some error message
     *      [-] Subheader
     *          [!] Test fail
     *          [+] Test pass
     * ************************************ (Line separator)
     *      [-] Subheader
     *          [+] Test pass
     * 
     *  
     * The symbols used as bullet points can be customized, as can the
     * indentation string, line separator, test pass and fail symbols,
     * and PrintStreams used for output and error output.
     * 
     */

    private int indentLevel;
    private HeaderMode headerMode;
    private String[] headerSymbols;
    private String symbol_pass;
    private String symbol_fail;
    private String indent;
    private String line;

    private PrintStream out;
    private PrintStream err;

//******************************************************************************

    /**
     * The enum Header mode, contains constants for the two ways the printer can
     * treat the header symbol array after using all the symbols once:
     * either wrapping around through it or stopping and using the last symbol
     * over and over.
     */
    public enum HeaderMode {
        /**
         * Wrap header mode, wraps through the symbol array if there are more
         * levels than symbols, e.g. [*], [-], [*], [-]
         */
        WRAP,
        /**
         * Stop header mode, uses the last symbol for all indent levels >= the
         * number of symbols in the symbol array, e.g [*], [-], [-], [-]
         */
        STOP
    } //end HeaderMode enum

//******************************************************************************


    /**
     * Instantiates a new TestPrinter object.
     *
     * @param indentLevel   the indent level to begin with
     * @param headerMode    the header mode
     * @param headerSymbols the symbols to use as bullets in the headers
     * @param symbol_pass   the symbol for a test pass
     * @param symbol_fail   the symbol for a test fail
     * @param indent        the indent symbol, will be used to pad indents
     * @param line          the line symbol, will be printed when line() is called
     * @param out           the out PrintStream, used for all general output
     * @param err           the err PrintStream, used by err() method
     */
    public TestPrinter(int indentLevel, HeaderMode headerMode,
                       String[] headerSymbols, String symbol_pass,
                       String symbol_fail, String indent, String line,
                       PrintStream out, PrintStream err) {

        this.indentLevel = indentLevel;
        this.headerMode = headerMode;
        this.headerSymbols = headerSymbols;
        this.symbol_pass = symbol_pass;
        this.symbol_fail = symbol_fail;
        this.indent = indent;
        this.line = line;
        this.out = out;
        this.err = err;

    } // end TestPrinter constructor

//******************************************************************************

    
    /**
     * Instantiates a new Test printer with default configuration.
     */
    public TestPrinter() {
        indentLevel = 0;
        headerMode = HeaderMode.STOP;
        headerSymbols = new String[] {"[*]", "[-]"};
        symbol_pass = "[+]";
        symbol_fail = "[!]";
        indent = "  ";
        line = "\n*************************************************\n";
        out = System.out;
        err = System.err;
    } // end TestPrinter default constructor

//******************************************************************************


    /**
     * Gets the current indentation String (e.g. the indent String repeated
     * indentLevel times)
     *
     * @return the current indentation String
     */
    private String getIndent() {
        // Initialize a new char array of length indentLevel, convert it into
        // a String, and replace every character with the indent String.
        // This is just a quick way of "multiplying" a String by a number.
        return new String(new char[indentLevel]).replace("\0", indent);
    } // end getIndent

//******************************************************************************


    /**
     * Gets the header symbol for the current indentation level. Uses the
     * headerMode to determine the behavior.
     *
     * @return the header symbol.
     */
    private String getHeader() {
        int idx = indentLevel;

        if (idx >= headerSymbols.length) {
            if (headerMode == HeaderMode.WRAP) {
                idx %= headerSymbols.length;
            } else if (headerMode == HeaderMode.STOP) {
                idx = headerSymbols.length - 1;
            } // end if
        } // end if

        return headerSymbols[idx];

    } // end getHeader

//******************************************************************************


    /**
     * Make a new section at the current indentation level.
     * Does not change the indent level.
     *
     * @param title the title
     */
    public void newSection(String title) {
        out.println(getIndent() + getHeader()
                + " " + title);
    } // end newSection

//******************************************************************************


    /**
     * Alias for indent() and then newSection(), it indents one level and then
     * makes a section.
     *
     * @param title the title
     */
    public void newSubSection(String title) {
        indent();
        newSection(title);
    } // end newSubSection

//******************************************************************************


    /**
     * Display a test fail message.
     *
     * @param message the message
     */
    public void fail(String message) {
        out.println(getIndent() + symbol_fail + " " + message);
    } // end fail

//******************************************************************************

    /**
     * Display a test pass message.
     *
     * @param message the message
     */
    public void pass(String message) {
        out.println(getIndent() + symbol_pass +
                " " + message);
    } // end pass

//******************************************************************************

    /**
     * Display some output, one level indented from the current level.
     * Does not affect the indentation level.
     *
     * @param message the message
     */
    public void out(String message) {
        out.println(getIndent() + indent + message);
    } // end out

//******************************************************************************

    /**
     * Display a message to the err PrintStream, one level indented from the
     * current level. Does not affect the indentation level.
     *
     * @param message the message
     */
    public void err(String message) {
        err.println(getIndent() + indent + message);
    } // end err

//******************************************************************************

    /**
     * Unindent one level.
     */
    public void unindent() {
        indentLevel--;
    } // end unindent

//******************************************************************************

    /**
     * Indent one level
     */
    public void indent() {
        indentLevel++;
    } // end indent

//******************************************************************************


    /**
     * Sets the indent level to a certain number
     * (0=no indent, 1=one indent, etc.)
     * 
     * @param level the integer level to set the indent level to
     */
    public void setIndentLevel(int level) {
        indentLevel = level;
    }

//******************************************************************************


    /**
     * Print the line character to the out printstream.
     */
    public void line() {
        out.println(line);
    } // end line

//******************************************************************************

    /**
     * Tests that two objects are equal. If they are, show a test pass message
     * and if they aren't, show a test fail message.
     *
     * @param a one object
     * @param b the other object
     */
    public void checkEqual(Object a, Object b) {
        indent();
        if (a.equals(b)) {
            pass("Pass: objects which should be equal are equal");
        } else {
            fail("Fail: objects which should be equal are not equal");
        } // end if
        unindent();
    } // end checkEqual

//******************************************************************************


    /**
     * Tests that two objects are not equal. If they are equal, show a test
     * fail message. Otherwise, show a test pass message.
     *
     * @param a one object
     * @param b the other object
     */
    public void checkNotEqual(Object a, Object b) {
        indent();
        if (a.equals(b)) {
            fail("Fail: objects which should not be equal are equal");
        } else {
            pass("Pass: objects which should not be equal are not equal");
        } // end if
        unindent();
    } // end checkNotEqual




} // end TestPrinter class
