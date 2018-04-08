import edu.duke.*;

public class TestCaesarCipher {

    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values) {
        int index = 0;
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                index = i;
            }
        }
        return index;
    }


    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }

    public void simpleTests() {
        FileResource resource = new FileResource();
        CaesarCipher cc = new CaesarCipher(18);
        String message = resource.asString();
        String encryptMessage = cc.encrypt(message);
        System.out.println(encryptMessage + "\n" + cc.decrypt(encryptMessage));
        String decryptMessage = this.breakCaesarCipher(encryptMessage);
        System.out.println(decryptMessage);
    }
}
