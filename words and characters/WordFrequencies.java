import edu.duke.*;
import java.util.ArrayList;
import java.util.*;

/**
 * Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//That class counts how many unique words in file and find the word which occurs most often. The idea was implement it with using ArrayList`s
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    //counting unique words and add them to the ArrayList
    void findUnique(){
        myWords.clear();
        myFreqs.clear();
        //files for testing are in the poems folder
        FileResource resource = new FileResource();
        for(String s: resource.words()){ // iterate over all words in file
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){ 
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int b = myFreqs.get(index);
                myFreqs.set(index, b+1);
            }
        }
    }
    //find the most used word. stats.getMax() show the index with the most used word.
    void findIndexOfMax(){
      
        IntSummaryStatistics stats = myFreqs.stream().mapToInt((x) -> x).summaryStatistics();
        int c = myFreqs.indexOf(stats.getMax());
        System.out.println("The word " + myWords.get(c) + " was used at most. The "  + stats.getMax() + " times");
       
    }
    void tester(){
        findUnique();
        //show all words and how many times it occurs
        for(int k=0; k < myFreqs.size(); k++){
            System.out.println(myFreqs.get(k) +"\t" + myWords.get(k));
        }
        findIndexOfMax();
        System.out.println("# unique words: " + myWords.size());
    }
}
