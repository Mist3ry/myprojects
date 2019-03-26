package wen;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;


public class Tester
{
    //tester for each method
    public void testLogAnalyzer() {
        String fr = "test-files/weblog3-short_log";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(fr);
        System.out.println("The number of unique IP`s is " + la.countUniqueIPs());
        ArrayList<String> visitor = new ArrayList<String> ();
        visitor = la.uniqueIPVisitsOnDay("Sep 27"); //Should return the ArrayList of IP`s occured on 27 September
        System.out.println("The number of uniqueIPVisitsOnDay is " + visitor.size()); //Should return the number of unique IP`s on 27 Sep
        System.out.println("The unique IP`s in range from 400 to 499 is " + la.countUniqueIPsInRange(400,499)); //number of requests with StatusCode from 400 to 499
        HashMap<String, Integer> count = la.countVisitsPerIP();
       System.out.println("mostNumberVisitsByIP by ip " + la.mostNumberVisitsByIP(count)); //should return the number with the highest accesses number per IP
       System.out.println();
       
       //Should pritn the list of IP`s with max num of visits to the website
        HashMap<String, ArrayList<String>> another = la.iPsForDays();
       ArrayList<String> check = la.iPsMostVisits(count);
         System.out.println("ipsmostvisits");
        for(String a: check){
         System.out.println(a);   
        }
        
        System.out.println();
        System.out.println("dayWithMostIPVisits" + la.dayWithMostIPVisits(another)); //print the most accesses day
        System.out.println();
        System.out.println("iPsWithMostVisitsOnDay");
        //print the list of IP`s with most visits on specified day
         for(String b : visitor){
             System.out.println(b);
            }
    }
}
