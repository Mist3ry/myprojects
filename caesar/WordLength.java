import edu.duke.*;
import java.util.*;
/**
 * Write a description of class WordLength here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//that program figure out the most common word length of words from a file
public class WordLength {
    //This method read in the words from resource and count the number of words of each length for all the words in resource. The word must consist only of letters without symbols
    public void countWordLength(FileResource resource, int[] counts){
        String message = resource.asString();
        String[] words = message.split("\\s");
        for(String subStr:words) {
            int b = subStr.length();
            StringBuilder a = new StringBuilder(subStr);
            for(int c =0; c<a.length(); c++){
                char currChar = a.charAt(c);
                if(!(Character.isLetter(currChar))){
                    b -= 1;
                }
            }
            counts[b] += 1;
        }
        for(int i =1; i<counts.length; i++) {
            System.out.println(i + "\t" + counts[i]);
        }
    }
    //return the index position of the largest element
    public int indexOfMax(int[] a){
        Integer[] what = Arrays.stream(a).boxed().toArray(Integer[]::new);
        List<Integer> number = Arrays.asList(what);
        IntSummaryStatistics stats = number.stream().mapToInt((x) -> x).summaryStatistics();
        return number.indexOf(stats.getMax());
    }
    public void test(){
        FileResource res = new FileResource();
        int[] a = new int[30];
        countWordLength(res, a);
        System.out.println("Max index is " + indexOfMax(a));
    }

}
