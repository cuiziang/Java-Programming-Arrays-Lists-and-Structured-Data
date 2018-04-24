import edu.duke.*;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        this.myWords = new ArrayList<String>();
        this.myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();

        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
        }
    }


    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for (int k = 0; k < myFreqs.size(); k++) {
            if (myFreqs.get(k) > max) {
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        int index = findIndexOfMax();
        for (int k = 0; k < myFreqs.size(); k++) {
            System.out.println(myFreqs.get(k) + " " + myWords.get(k));
        }
        System.out.println("The word that occurs most often and its count are: " + myWords.get(index) + " " + myFreqs.get(index));
        System.out.println("The unique word is: " + myWords.size());
    }

}
