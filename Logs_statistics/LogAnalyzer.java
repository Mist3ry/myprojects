package wen;
import edu.duke.*;
import java.util.*;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */



public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
     //this method creates a new FileResource and iterate it over all the line in the file. Each line creates a LogEntry and stores it in "records" ArrayList entries
     public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String s: fr.lines()){
            LogEntry le = WebLogParser.parseEntry(s);
            records.add(le);
            
        }
     }
        //print everything in ArrayList
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     //count how many unique Ips in file
     public int countUniqueIPs(){
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry le : records){
            String a = le.getIpAddress();
            if(!unique.contains(a)){
                unique.add(a);
            }
            }
            return unique.size();
     }
     //print each request with StatusCode higher than num
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int b = le.getStatusCode();
             if(b>num){
                 System.out.println(le);
                }
            }
        }
        //return ArrayList with uniqueIp on the specified day in String someday
       public ArrayList<String> uniqueIPVisitsOnDay (String someday ){
         ArrayList<String> visits = new ArrayList<String>();
         for(LogEntry le : records){
             String data = le.getAccessTime().toString();
             //System.out.println("The date is " + data + " someday is " + someday);
             if(data.contains(someday)){
                 String c = le.getIpAddress();
                 if(!visits.contains(c)){
                visits.add(c);
            }
                }
            }
            return visits;
        }
        //Return the number of uniqueIP`s in in records which status code is in range from low to high
     public int countUniqueIPsInRange (int low, int high){
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry le : records){
             int d = le.getStatusCode();
             String ip = le.getIpAddress();
             if(d>=low && d<=high){
                 if(!unique.contains(ip)){
                     unique.add(ip);
                    }
                }
            }
            return unique.size();
        }
        //Return HashMap with uniqueIP as String and number of visits as Integer in records, meaning the number of times this IP visited the website
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
                }
                else{ counts.put(ip, counts.get(ip) + 1);}
            }
            return counts;
     }
     //mostNumberVisitsByIP maps an IP addr to the number of times that IP appears in the web log file, and return the max number of visits to this site by single IP
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         for(String a : counts.keySet()){
             int value = counts.get(a);
             if(value>max){
                 max = value;
                }
            }
            return max;
        }
        //iPsMostVisits return the ArrayList of IP`s that all have the max number of visits to this website
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         ArrayList<String> mostvisits = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for(String a : counts.keySet()){
             int value = counts.get(a);
             if(value == max){
                 mostvisits.add(a);
                }
            }
            return mostvisits;
     }
     //iPsForDays return a HashMap with ArrayList of IP`s that occured on that day
     public HashMap<String, ArrayList<String>> iPsForDays(){
         ArrayList<String> ips = new ArrayList<String>();
         HashMap<String, ArrayList<String>> ipsfordays = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             String data = le.getAccessTime().toString().substring(4,10);
             ips = uniqueIPVisitsOnDay(data);
             ipsfordays.put(data,ips);
     }
     return ipsfordays;
    }
    //This method returns the day with most IP visits
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> counter){
        int MaxVisits = 0;
        HashMap<String, Integer> summ = new HashMap<String, Integer>();
        String day = null;
        for(String b : counter.keySet()){
            ArrayList<String> ips = new ArrayList<String>(counter.get(b));
            if(ips.size() > MaxVisits){
              MaxVisits = ips.size();
              day = b;
            }
        }
        return day;  
    }
    //this method return an ArrayList of IP`s that had the most accesses on the givenday
   public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> days, String data){
       ArrayList<String> visits = new ArrayList<String>();
       HashMap<String,Integer> counts = new HashMap<String,Integer>();
       ArrayList<String> wholeDay = new ArrayList<String>();
       for(String s : days.keySet()){
         if(s.equals(data)){
             visits = days.get(s);
             }
            }
       for(int k=0; k<visits.size(); k++){
           if(!counts.containsKey(visits.get(k))){
             counts.put(visits.get(k),1);  
            }
           else counts.put(visits.get(k), counts.get(visits.get(k))+1);
        }
        wholeDay = iPsMostVisits(counts);
        return wholeDay;
   }
}
