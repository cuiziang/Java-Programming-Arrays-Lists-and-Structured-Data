import edu.duke.*;

import java.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    private HashMap<String, ArrayList> myMap;
    private Random myRandom;

    public GladLibMap() {
        this.myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source) {
        this.myMap = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        this.myMap.put("adjective", readIt(source + "/adjective.txt"));
        this.myMap.put("noun", readIt(source + "/noun.txt"));
        this.myMap.put("color", readIt(source + "/color.txt"));
        this.myMap.put("country", readIt(source + "/country.txt"));
        this.myMap.put("name", readIt(source + "/name.txt"));
        this.myMap.put("animal", readIt(source + "/animal.txt"));
        this.myMap.put("timeframe", readIt(source + "/timeframe.txt"));
    }


    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;

        } else if (myMap.containsKey(label)) {
            return randomFrom(this.myMap.get(label));
        } else {
            return "**UNKNOWN**";
        }
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
    }
}