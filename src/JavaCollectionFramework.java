import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JFileChooser;

public class JavaCollectionFramework {
     HashSet<String> words;
    public static void main(String[] args) {
        File fileToCheck;
        JavaCollectionFramework JCF = new JavaCollectionFramework();
        

        JCF.readFile("words.txt");

        fileToCheck = JCF.fileChooser("Select File for input");
        JCF.checkWordsFromFile(fileToCheck);
    }

    private void checkWordsFromFile(File fileToCheck) {
        Scanner fs = null;
        try {
            fs = new Scanner(fileToCheck);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         fs.useDelimiter("[^a-zA-Z]+");
         while(fs.hasNext()){
            String wordToCheck = fs.next();
            if(!words.contains(wordToCheck.toLowerCase())) {
                System.out.print("word " + wordToCheck +" MISPELLED ");
                TreeSet<String> suggestions = corrections(wordToCheck,words);
                for(String suggestion: suggestions){
                    System.out.print(suggestion + " ");
                } System.out.println();

            }
            else System.out.println("word " + wordToCheck +" present in dictionary");

         }
    }

    private TreeSet<String> corrections(String wordToCheck, HashSet<String> words) {
        TreeSet<String> suggestions = new TreeSet<String>();
        for(String word : words){
            if (wordToCheck.contains(word)) {
                suggestions.add(word);
            }
        }
        if (suggestions.isEmpty()) suggestions.add("(no suggestions)");
        return suggestions;
    }

    private File fileChooser(String message) {
        JFileChooser jFc = new JFileChooser();
        jFc.setDialogTitle(message);
        int  option = jFc.showOpenDialog(null);
        if(option != jFc.APPROVE_OPTION){
            return null;
        } else {
            return jFc.getSelectedFile();
        }
    }

    private int readFile(String string) {
        Scanner fs = null;
        this.words = new HashSet<String>();
        try {
            fs = new Scanner(new File("c:\\words.txt"));
 
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int count = 0;
        /*Loading data in the HASHMAP */

        while(fs.hasNext()){
            String line = fs.nextLine();
            words.add(line.toLowerCase());
            count ++;
        }
        if (count != 73845){
            return -1;
        }

        return 0;
    } 
}
