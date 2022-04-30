// Author: Patrick Tibbals
import java.util.Arrays;

// Custom HashTable
class HashTable {
    int collisionCount = 0;
    int tableSize = 43753;
    private Anagram[] hashTable = new Anagram[tableSize];
    int tableLocation = 0;

    // Custom Hash Code
    public int hashCodes(char[] key) {
        int sum = 1;
        for (char c : key){
            sum = sum * c;
        }
        return sum <<3;
    }

    boolean setNewAnagram = true;
    void add(Anagram anagram) {
        tableLocation = hashCodes(anagram.getKey());
//Uncomment to test for java collisions vs my collisions within my hashtable
        //tableLocation = Arrays.hashCode(anagram.getKey());

        //Transpose hashcode into legal table location
        tableLocation = tableLocation % tableSize;
        if(tableLocation < 0){
            tableLocation *= -1;
        }

        // Its empty place the anagram
        if (hashTable[tableLocation] == null) {
            hashTable[tableLocation] = anagram;
        // It matches add word to list at key location
        } else if (Arrays.equals(hashTable[tableLocation].getKey(), anagram.getKey())) {
            Anagram temp = anagram.addValue(hashTable[tableLocation], anagram.getWordList());
            hashTable[tableLocation] = temp;
        // It doesn't match so found a collision
        } else {
            //Collision
            while (hashTable[tableLocation] != null) {
                //If Collision but key matches
                if (Arrays.equals(hashTable[tableLocation].getKey(), anagram.getKey())) {
                    Anagram temp1 = anagram.addValue(hashTable[tableLocation], anagram.getWordList());
                    hashTable[tableLocation] = temp1;
                    //Set to false, we don't want to overwrite the addValue on line 45 with line 65
                    setNewAnagram = false;
                    break;
                // Collision but no match
                } else {
                    //Linear probe
                    collisionCount++;
                    tableLocation += 1;
                    //Avoid index out of bounds by circling back to zero
                    if(tableLocation >= tableSize){
                        tableLocation = tableLocation-tableSize;
                    }
                }
            }
            // If check to make sure no overwriting our words list
            // Allows for value to set once we reach a null location and leave the while loop
            // If we found a match then we move one to the next word
            if (setNewAnagram == true) {
                hashTable[tableLocation] = anagram;
            }
            setNewAnagram = true;
        }
    }

    String search(char[] key) {
        //Get unique hashcode for key
        tableLocation = hashCodes(key);
//Uncomment to test for java collisions vs my collisions within my hashtable
        //tableLocation = Arrays.hashCode(key);

        //Transpose hashcode into legal table location
        tableLocation = tableLocation % tableSize;
        if(tableLocation<0){
            tableLocation *= -1;
        }
        //Loop to preform linear probe till either element is null or key is found and wordlist is accessed
        while (hashTable[tableLocation] != null && !Arrays.equals(hashTable[tableLocation].getKey(),key)){
            tableLocation += 1;
            if(tableLocation >= tableSize){
                tableLocation = tableLocation-tableSize;
            }
        }
        //We found null before the key so there is no match
        if(hashTable[tableLocation] == null){
            return "Those letters have no solution";
        }else {
            //Return the words list
            return hashTable[tableLocation].getWordList();
        }
    }
}
