
/**
 * Write a description of class GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;
//The idea is to make the story replacing the <label> space with a random word from the file. The story in template
public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    private String dataSource = "story-template";
    private int replacedWords;
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSource);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        replacedWords = 0;
    }
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        replacedWords = 0;
    }
    //Create a HashMap with <label> words
    private void initializeFromSource(String source){
        String[] labels = {"country", "adjective", "noun", "color", "name", "animal", "timeframe", "verb", "fruit"};
        for(String s: labels){
            ArrayList<String> list = readIt(source+ "/" + s +".txt");
            myMap.put(s, list);
        }
    }
    //return random word from ArrayList
    private String randomFrom(ArrayList<String> source){
        int idex = myRandom.nextInt(source.size());
        return source.get(idex);
    }
    
    //return random word for specified <label>, For example if <label> is <country> it will return random word from file country and replace <label> with that word
    private String getSubstitute(String label) {
        if(label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        }
        else if(!myMap.keySet().contains(label)){
            return "**UKNOWN**";
        }
        return randomFrom(myMap.get(label));
    }
    
    //Check if in template story the word is <label> and checks that the word is replaced is not repeated 
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">");
        if(first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1, last));
        boolean wordAlreadyUsed = checkIfUsed(sub);
        while (wordAlreadyUsed) {
            sub = getSubstitute(w.substring(first + 1, last));
            wordAlreadyUsed = checkIfUsed(sub);
        }
        replacedWords++;
        return prefix + sub + suffix;
    }
    
    //check if word was used
        private boolean checkIfUsed(String word) {
        int index = usedWords.indexOf(word);
        if (index == -1) {
            usedWords.add(word);
            return false;
        } else {
            return true;
        }
    }
    
    //print the random story with limited line by lineWidth
    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if(charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    //Make the story complete with replaced <labels>
    private String fromTemplate(String source){
        String story = "";
        FileResource resource = new FileResource(source);
        for(String word : resource.words()){
            story = story + processWord(word) + " ";
        }
        return story;
    }
    
    //return ArrayList with words for replacement. It iterates over lines because each word at the new line
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        FileResource resource = new FileResource(source);
        for(String line : resource.lines()){
            list.add(line);
        }
        return list;
    }
    
    //method for testing
    public void makeStory(){
        String template = fromTemplate("story-template/template.txt");
        printOut(template,60);
        System.out.println("\n" + "Number of words replaced: " + usedWords.size());
        System.out.println("Total number of words to pick from " + totalWordsInMap());
        totalWordsConsidereds();
    }
    
    //total number of words which can replace the label
    public int totalWordsInMap(){
        int i =0;
        for(String key : myMap.keySet()){
            i+=myMap.get(key).size();
        }
        return i;
    }
       private void addUsedCategory(String label) {
        if (usedCategories.indexOf(label) == -1) {
            usedCategories.add(label);
        }
    }

    //This method returns the total number of words in the ArrayLists of the categories that were used
    private void totalWordsConsidereds() {
        ArrayList<String> content = new ArrayList<String>();
        int grandTotal = 0;
        System.out.println("\nCategories used in this story:");
        for (int index = 0; index < usedCategories.size(); index++) {
            String category = usedCategories.get(index);
            content = myMap.get(category);
            System.out.println("Category: " + category + "\tWords in category: "
                    + content.size());
            grandTotal += content.size();
        }
        System.out.println("Grand total of possible words: " + grandTotal);
    }
}

