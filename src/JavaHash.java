// Author: Patrick Tibbals


import java.util.Arrays;
import java.util.HashMap;

// All provided java UTIL's
class JavaHash {
    int JavaCollision = 0;
    private HashMap<Integer,Anagram> map = new HashMap<>(43753);
    //ADD will add to list of words or add new anagram to the map
    void add(Anagram anagram){
        int hash = Arrays.hashCode(anagram.getKey());
        if(map.containsKey(hash)){
            Anagram temp = map.get(hash);
            while (temp != null) {
                JavaCollision++;
                if (Arrays.equals(temp.getKey(), anagram.getKey())) {
                    map.put(hash, anagram.addValue(temp, anagram.getWordList()));
                    break;
                } else {
                    hash += 1;
                    temp = map.get(hash);
                }
            }
        }else {
            map.put(hash, anagram);
        }
    }
}
