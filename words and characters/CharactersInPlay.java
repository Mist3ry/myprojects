import edu.duke.*;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    //store characters in names Array
    void update(String person) {
        person = person.toLowerCase();
        int index = names.indexOf(person);
        if(!names.contains(person)){
            names.add(person);
            counts.add(1);
        }
        else{
            int value = counts.get(index);
            counts.set(index, value +1);
        }
    }
    //find all characters in file
    void findAllCharacters(){
        names.clear();
        counts.clear();
        //files for testing are in the poems folder
        FileResource resource = new FileResource();
        for(String s: resource.lines()){
             int periodInLine = s.indexOf(".");
             if(periodInLine != -1){
                 String name = s.substring(0, periodInLine);
                 update(name);
                }
        }
    }
    //character who occurs most often
    int Max(){
        IntSummaryStatistics stats = counts.stream().mapToInt((x) -> x).summaryStatistics();
        return stats.getMax();
    }
    void tester(){
        //testing files in poems
        findAllCharacters();
        charactersWithNumParts(2, 100);
        int biggestIndex = Max();
        System.out.println("Character with most speaking parts: " + names.get(biggestIndex)
                + "\t" + counts.get(biggestIndex));
    }
    //show characters who occurs fron num1 to num2 times
        public void charactersWithNumParts(int num1, int num2) {
        for (int k = 0; k < names.size(); k++) {
            if (counts.get(k) >= num1 && counts.get(k) <= num2) {
                System.out.println(names.get(k) + "\t\t" + counts.get(k));
            }
        }
    }
}
