import edu.duke.*;

import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> characters;
    private ArrayList<Integer> parts;

    public CharactersInPlay() {
        this.characters = new ArrayList<String>();
        this.parts = new ArrayList<Integer>();
    }

    public void update(String person) {

    }


    public void findAllCharacters() {
        this.characters.clear();
        this.parts.clear();
        FileResource resource = new FileResource();

        for (String l : resource.lines()) {
            l = l.toLowerCase();
            int indexOfPeriod = l.indexOf(".");
            String character = "";
            if (indexOfPeriod != -1) {
                character = l.substring(0, indexOfPeriod);
                int indexOfCharacter = characters.indexOf(character);
                if (indexOfCharacter == -1) {
                    characters.add(character);
                    parts.add(1);
                } else {
                    int freq = parts.get(indexOfCharacter);
                    parts.set(indexOfCharacter, freq + 1);
                }
            }
        }

    }

    public void tester() {
        this.findAllCharacters();
        int indexOfCharacter = 0;
        for (int k = 0; k < characters.size(); k++) {
            if (parts.get(k) > parts.get(indexOfCharacter)) {
                indexOfCharacter = k;
            }
        }
        System.out.println("The speaking parts of main character is: " + characters.get(indexOfCharacter) + " " + parts.get(indexOfCharacter) + "\n");
        charactersWithNumParts(10, 15);
    }

    private void charactersWithNumParts(int num1, int num2) {
        for (int k = 0; k < parts.size(); k++) {
            if (parts.get(k) >= num1 && parts.get(k) <= num2)
                System.out.println(parts.get(k) + " " + characters.get(k));
        }
    }

}
