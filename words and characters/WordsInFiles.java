import edu.duke.*;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Write a description of class WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//The idea of that project is to find the words which occur in files. For example if all files contains specified word, or which word is at leat in 3 files
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    WordsInFiles(){
       map = new  HashMap<String, ArrayList<String>>();
    }
    //add all unique words from all files to HashMap
    private void addWordsFromFile(File f) throws Exception{
        FileResource fr = new FileResource(f);
        String name = f.getName();
        for(String s :fr.words()){
                if(!map.containsKey(s)){
                    map.put(s, new ArrayList<String>());
                    map.get(s).add(name);
                }
                else if(map.containsKey(s) && !map.get(s).contains(name)){
                    map.get(s).add(name);
                }
        }
    }
    //add bunch of files
    void buildWordFileMap() throws Exception{
        map.clear();
        //files for testing are in the poems folder
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    //find the maximum number of files that have at least one word in common
    int maxNumber(){
        int maxNumFiles = 0;
        for(String words: map.keySet()){
            int curr = map.get(words).size();
            if( curr>maxNumFiles){
                maxNumFiles = curr;
        }
    }
    return maxNumFiles;
    }
    //show the ArrayList of words that appears in "number" files
    ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> word = new ArrayList<String>();
        for(String w : map.keySet()){
            if(map.get(w).size() == number){
                word.add(w);
        }
    }
    return word;
}
//print name of files that contains word
void printFilesIn(String word){
    ArrayList<String> filepath = map.get(word);
    for(int i =0; i<filepath.size(); i++){
        System.out.println(filepath.get(i));
    }
    
}
void tester() throws Exception{
   WordsInFiles word = new WordsInFiles(); 
   buildWordFileMap();
   System.out.println("The maximum number of files any word appears is: " + maxNumber());
   System.out.println("\nWords that appeared fours: " + wordsInNumFiles(4).size());
   System.out.println(wordsInNumFiles(4).toString());
		
   System.out.println("\nWords that appeared seven: " + wordsInNumFiles(7).size());
   System.out.println(wordsInNumFiles(7).toString());
		
   System.out.println("\nWords that appeared three times: ");
   System.out.println(wordsInNumFiles(3).toString());
   ArrayList<String> numWords = wordsInNumFiles(3);
   System.out.println("The number of words are: " + numWords.size());
   
   System.out.println("\nWord \"laid\" is contained in the following files: ");
   printFilesIn("laid");
   System.out.println("\nWord \"tree\" is contained in the following files: ");
   printFilesIn("tree");
   
}

}
