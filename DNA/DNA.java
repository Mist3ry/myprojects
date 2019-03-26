import edu.duke.*;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;

/**
 * Write a description of class DNA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DNA {
    HashMap<String, Integer> map;
    void DNA() {
        map = new HashMap<String, Integer>();
    }
    //each codon of DNA has 3 letters. That method divide string to codons of DNA and put unique codons to HashMap
    //start means from which frame to start counting codons
    void buildCodonMap(int start, String dna){
        map.clear();
        String current;
        for(int i=start; i+3<dna.length(); i+=3){
            current = dna.substring(i,i+3);          
            if(map.containsKey(current)){
                map.put(current,map.get(current)+1);
            }
            else{
                map.put(current,1);
            }
    }
    //show list of DNA codons and how many times it occurs
    for(String w  : map.keySet()){
        int occurrences = map.get(w);
        System.out.println(occurrences + "\t" +w);
    }
}
//return most common Codon
    String getMostCommonCodon(){
      int value = 0;  
      int largestcount = 0;
      String largestkey = null;
      for(String key : map.keySet()){
          value = map.get(key);
          if(largestcount < map.get(key)){
            largestcount = map.get(key);
            largestkey = key;
            }
              
    }
    return largestkey;
}
//print all Codons which occur from start to end times
void printCodonCounts(int start, int end){
    int value = 0;
    for(String key:map.keySet()){
        value = map.get(key);
        if(value >= start && value <= end){
            System.out.println(key + "\t" + value);
        }
    }
}
    void test(){
        DNA a = new DNA();
        FileResource resource = new FileResource("DNA");
        String dna = resource.asString().trim().toUpperCase();
        int start = 0;
        int end = 7;
        buildCodonMap(1, dna);
        System.out.println("Reading frame starting with 0 results in "+map.size()+" unique codons"+"\t");
         String largest = getMostCommonCodon();
         System.out.println("Most common codon is "+largest+" with count "+map.get(largest)+"\t"); 
         System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
         printCodonCounts(start, end);
    }
}
