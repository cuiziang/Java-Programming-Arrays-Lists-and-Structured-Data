/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        this.records = new ArrayList<>();
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();

            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();

            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIP = new ArrayList<>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            String date = le.getAccessTime().toString().substring(4, 10);
            if (!uniqueIP.contains(ipAddr) && date.equals(someday)) {
                uniqueIP.add(ipAddr);
            }
        }
        return uniqueIP;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<>();
        int count = 0;
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            int status = le.getStatusCode();

            if (!uniqueIPsInRange.contains(ipAddr) && (status >= low && status <= high)) {
                count++;
                uniqueIPsInRange.add(ipAddr);
            }
        }
        return count;
    }

    //    The number of times this IP address visited the website.
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    //    The maximum number of visits to this website by a single IP address.
    public int mostNumberVisitsByIP(HashMap<String, Integer> iPs) {
        int max = 0;
        for (Integer numberVisits : iPs.values()) {
            if (numberVisits > max) {
                max = numberVisits;
            }
        }
        return max;
    }

    //    An ArrayList of Strings of IP addresses that all have the maximum number of visits to this website.
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ips) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<>();
        int max = 0;
        for (String ip : ips.keySet()) {
            if (ips.get(ip) == mostNumberVisitsByIP(ips)) {
                uniqueIPsInRange.add(ip);
            }
        }
        return uniqueIPsInRange;
    }


    //    A HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses that occurred on that day (including repeated IP addresses)
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> iPsForDays = new HashMap<>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4, 10);
            if (!iPsForDays.containsKey(date)) {
                ArrayList<String> IPs = new ArrayList<>();
                IPs.add(le.getIpAddress());
                iPsForDays.put(date, IPs);
            } else {
                iPsForDays.get(date).add(le.getIpAddress());
            }
        }
        return iPsForDays;
    }

    //    The day that has the most IP address visits
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays) {
        int max = 0;
        String dayWithMostIPVisits = "";
        for (String date : iPsForDays.keySet()) {
            if (iPsForDays.get(date).size() > max) {
                max = iPsForDays.get(date).size();
                dayWithMostIPVisits = date;
            }
        }
        return dayWithMostIPVisits;
    }

    //    An ArrayList<String> of IP addresses that had the most accesses on the given day.
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String date) {
        ArrayList<String> iPsForDay = iPsForDays.get(date);
        HashMap<String, Integer> counts = new HashMap<>();
        for (String iP : iPsForDay) {
            if (!counts.containsKey(iP)) {
                counts.put(iP, 1);
            } else {
                counts.put(iP, counts.get(iP) + 1);
            }
        }
        return iPsMostVisits(counts);
    }

    public void readFile(String filename) {
        FileResource resource = new FileResource(filename);
        for (String line : resource.lines()) {
            LogEntry l = WebLogParser.parseEntry(line);
            this.records.add(l);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
