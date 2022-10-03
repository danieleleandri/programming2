import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import javax.swing.JFileChooser;

/**
 * The class was designed to implement a simple spelling checker.
 * The user has to chose 2 files:
 * 1) The dictionary (the file word.txt provided by our instructor)
 * 2) The file to check
 * The program, then performs a search of all the words present in the file to
 * check against the dictionary,
 * then prints:
 * - a line telling if the word is correctly spelled
 * OR
 * - a line reporting the world seems MISPELLED followed by a list of
 * corrections.
 */
public class JavaCollectionFramework {
    public static void main(String[] args) {
        // This will contain the word in word.txt
        HashSet<String> dictionary = new HashSet<String>();
        // I used the method readDictionary to load data in dictionary
        readDictionary(fileChooser("Select dictionary"), dictionary);
        // Here I performed the test
        checkWordsFromFile(fileChooser("Select File for input"), dictionary);
    }

    /**
     * The method checks every single word in "fileTocheck" against the provided
     * dictionary
     * then prints:
     * - a line telling if the word is correctly spelled
     * OR
     * - a line reporting the world seems MISPELLED followed by a list of
     * corrections.
     * 
     * @param fileToCheck The file to check
     * @param dictionary  The dictionary
     */
    private static void checkWordsFromFile(File fileToCheck, HashSet<String> dictionary) {
        Scanner fs = null;
        // Scanning of the received file
        try {
            fs = new Scanner(fileToCheck);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //I used the suggested REGEX to split the words
        fs.useDelimiter("[^a-zA-Z]+");

        //Reads of the file and check
        while (fs.hasNext()) {
            String wordToCheck = fs.next();
            if (!dictionary.contains(wordToCheck.toLowerCase())) {
                System.out.print("The word \"" + wordToCheck + "\" seems MISPELLED, try with one of the following: ");
                TreeSet<String> suggestions = corrections(wordToCheck, dictionary);
                for (String suggestion : suggestions) {
                    System.out.print("\"" + suggestion + "\" ");
                }
                System.out.println();

            } else
                System.out.println("The word \"" + wordToCheck + "\" is in dictionary;");
        }
    }
/**
 * The method produces a TreeSet containing the possible corrections for a Mispelled word
 * @param wordToCheck The word to check
 * @param dictionary The provided dictionary
 * @return a TreeSet containing the possible suggestions
 */
    private static TreeSet<String> corrections(String wordToCheck, HashSet<String> dictionary) {
        TreeSet<String> suggestions = new TreeSet<String>();
        for (String word : dictionary) {
            if (wordToCheck.contains(word)) {
                suggestions.add(word);
            }
        }
        if (suggestions.isEmpty())
            suggestions.add("(no suggestions)");
        return suggestions;
    }

    /**
     * Method use to select the input files using a dialogo
     * @param message the title of the dialog
     * @return the File reference to the selected file.
     */
    private static File fileChooser(String message) {
        JFileChooser jFc = new JFileChooser();
        jFc.setDialogTitle(message);
        int option = jFc.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION) {
            return null;
        } else {
            return jFc.getSelectedFile();
        }
    }

    /**
     * Method to load all words in the provided dictionary
     * The method will check if the selected file contains the expected number of words
     * @param file
     * @param dictionary
     */
    private static void readDictionary(File file, HashSet<String> dictionary) {
        int expectedNumLines = 73845;
        Scanner fs = null;
        try {
            fs = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        /* Loading data in the HASHMAP */

        while (fs.hasNext()) {
            String line = fs.nextLine();
            dictionary.add(line.toLowerCase());
            count++;
        }
        if (count != 73845) {
            System.out.println("The file contains less than " + expectedNumLines + " lines!");
        }
    }
}
