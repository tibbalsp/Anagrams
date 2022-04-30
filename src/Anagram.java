// Author: Patrick Tibbals


import java.util.Arrays;

class Anagram{
    private char[] key;
    private String wordList;

    public Anagram(char[] key , String element) {
        this.setKey(key);
        this.setWordList(element);
    }

    void setKey(char[] key){
        this.key = key;
    }

    void setWordList(String wordList){
        this.wordList = wordList;
    }

    public String getWordList(){
        return this.wordList;
    }

    public char[] getKey(){
        return this.key;
    }

    Anagram addValue(Anagram anagram, String input){
        anagram.setWordList(anagram.getWordList()+ " , " +input);
        return anagram;
    }

    Anagram createAnagram(String input){
        char[] temp = input.toCharArray();
        Arrays.sort(temp);
        Anagram anagram = new Anagram(temp , input);
        return anagram;
    }
}
