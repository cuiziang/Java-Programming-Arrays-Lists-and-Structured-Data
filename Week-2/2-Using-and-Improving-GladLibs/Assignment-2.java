import edu.duke.*;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {


    private HashMap<String, ArrayList> map;

    public WordsInFiles() {
        this.map = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        FileResource r = new FileResource(f);
        for (String word : r.words()) {
            if (!this.map.containsKey(word)) {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(f.getName());
                this.map.put(word, fileList);
            } else {
                ArrayList<String> fileList = this.map.get(word);
                if (!fileList.contains(f.getName())) {
                    fileList.add(f.getName());
                }
            }
        }
    }

    private void buildWordFileMap() {
        this.map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            this.addWordsFromFile(f);
        }
    }

    private int maxNumber() {
        int max = 0;
        for (String word : map.keySet()) {
            int size = map.get(word).size();
            if (size > max) {
                max = size;
            }
        }
        return max;
    }

    private ArrayList wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<>();
        for (String word : this.map.keySet()) {
            int size = this.map.get(word).size();
            if (size == number) {
                words.add(word);
            }
        }
        return words;
    }

    private void printFilesIn(String word) {
        ArrayList<String> fileNames = this.map.get(word);
        for (String fileName : fileNames) {
            System.out.print(fileName);
        }
    }

    public void test() {
        this.buildWordFileMap();
        int max = this.maxNumber();
        ArrayList<String> words = this.wordsInNumFiles(max);
        for (String word : words) {
            System.out.print(word + " appears in the files ");
            this.printFilesIn(word);
            System.out.println();
        }
    }
}