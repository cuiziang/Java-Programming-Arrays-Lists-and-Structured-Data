import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabetu = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetl = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabetu = alphabetu.substring(key)+
        alphabetu.substring(0,key);
        String shiftedAlphabetl = alphabetl.substring(key)+
        alphabetl.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if(alphabetu.indexOf(currChar) != -1){
                int idx = alphabetu.indexOf(currChar);
                char newChar = shiftedAlphabetu.charAt(idx);
                encrypted.setCharAt(i, newChar);
            } 
            
            if (alphabetl.indexOf(currChar) != -1){
                int idx = alphabetl.indexOf(currChar);
                char newChar = shiftedAlphabetl.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabetu = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetl = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabeteu = alphabetu.substring(key1)+
        alphabetu.substring(0,key1);
        String shiftedAlphabetou = alphabetu.substring(key2)+
        alphabetu.substring(0,key2);
        String shiftedAlphabetel = alphabetl.substring(key1)+
        alphabetl.substring(0,key1);
        String shiftedAlphabetol = alphabetl.substring(key2)+
        alphabetl.substring(0,key2);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if (i%2 == 0){
            if(alphabetu.indexOf(currChar) != -1){
                int idx = alphabetu.indexOf(currChar);
                char newChar = shiftedAlphabeteu.charAt(idx);
                encrypted.setCharAt(i, newChar);
            } 
            
            if (alphabetl.indexOf(currChar) != -1){
                int idx = alphabetl.indexOf(currChar);
                char newChar = shiftedAlphabetel.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        } else{
            if(alphabetu.indexOf(currChar) != -1){
                int idx = alphabetu.indexOf(currChar);
                char newChar = shiftedAlphabetou.charAt(idx);
                encrypted.setCharAt(i, newChar);
            } 
            
            if (alphabetl.indexOf(currChar) != -1){
                int idx = alphabetl.indexOf(currChar);
                char newChar = shiftedAlphabetol.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
            //If currChar is in the alphabet
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public void testCaesar() {
        System.out.println(encrypt("First Legion", 17));
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
    }
}

