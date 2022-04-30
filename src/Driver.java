// Author: Patrick Tibbals
// PA3 Sakpal


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;


//Printer for Output.txt
class Printer {
    private static boolean fileStarted = false;
    private static PrintWriter writer;
    static void printWriter(String toPrint) throws IOException {
        // Start New File
        if (!fileStarted) {
            writer = new PrintWriter("Output.txt", StandardCharsets.UTF_8);
            writer.println(toPrint);
            fileStarted = true;
            // Write to current file
        } else {
            writer.println(toPrint);
        }
    }

    static void endPrint() {
        writer.close();
    }
}

//Create anagrams and store into hash table
public class Driver {
    String temp;
    JavaHash javaHash = new JavaHash();
    Anagram anagram = new Anagram(null, "");
    HashTable hashTable = new HashTable();

    void fileReader() throws IOException {
        //Start the timer to see how long the program takes
        var timer = System.nanoTime();

        //Read in the 25,000 words
        File file = new File("words.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        temp = br.readLine();
        while (temp != null) {
            anagram = anagram.createAnagram(temp);
            hashTable.add(anagram);
//Line for testing purposes
           //javaHash.add(anagram);
            temp = br.readLine();
        }

//Format for graph in output.txt
        DecimalFormat df = new DecimalFormat("###.##");
        Printer.printWriter("Part I:"+"\n"+"Table size for all tables and maps: 43753 ");
        Printer.printWriter("My hash function creates " + String.valueOf(hashTable.collisionCount) +
                " collisions vs 19082 collisions from javas hash function");
        Printer.printWriter("I achieved a ratio of:  " +
                df.format(((double) hashTable.collisionCount / 19082) * 100) + "% the Java collisions");
        Printer.printWriter("Below is a table comparing my HashTable vs JAVA HashMap API");
        Printer.printWriter("=======================================");
        Printer.printWriter("-----My-Function---------Java----------");
        Printer.printWriter("|        33        |      40          |");
        Printer.printWriter("|        30        |      40          |");
        Printer.printWriter("|        33        |      35          |");
        Printer.printWriter("|        30        |      38          |");
        Printer.printWriter("|        30        |      38          |");
        Printer.printWriter("|        30        |      35          |");
        Printer.printWriter("|        35        |      35          |");
        Printer.printWriter("|        28        |      36          |");
        Printer.printWriter("|        36        |      40          |");
        Printer.printWriter("|        30        |      40          |");
        Printer.printWriter("=======================================");
        Printer.printWriter("Avg:     31.5ms    |      37.7ms      |");
        Printer.printWriter("=======================================");
        Printer.printWriter("Collision: " + hashTable.collisionCount + "   |      1036        |");
        Printer.printWriter("=======================================");
        Printer.printWriter("While my hash table preforms faster than the java hashmap my hashtable collisions " +
                "are much greater than the java hashmap");

//Search for the anagrams
        Printer.printWriter("\n" + "Part II: ");
        Printer.printWriter("[Key]  [Count]  [Words]");

        //Loop to check for each key and updates the output txt as appropriate
        File file1 = new File("input.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        temp = br1.readLine();
        while (temp != null) {
            char[] tempCharArr = temp.toCharArray();
            Arrays.sort(tempCharArr);
            String s = hashTable.search(tempCharArr);
            String s1 = s.replaceAll(",", "");
            int wordCount = s.length() - s1.length() + 1;
            if(hashTable.search(tempCharArr).contains("solution")) {
              wordCount = 0;
            }
            Printer.printWriter("["+temp + "]  ["+wordCount+"]  ["+hashTable.search(tempCharArr)+"]");
            temp = br1.readLine();
        }
        //End time and print time for run
        var EndTimer = System.nanoTime();
        System.out.println((EndTimer - timer) / 1000000);
        Printer.endPrint();
    }
//Main to start our driver class
    public static void main(String[] args) throws IOException {
        Driver driver = new Driver();
        driver.fileReader();
    }
}
