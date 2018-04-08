public class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;


    public CaesarCipher(int key) {
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlphabet = this.alphabet.substring(key)+
                this.alphabet.substring(0,key);
        this.mainKey = key;
    }
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if(this.alphabet.indexOf(currChar) != -1){
                int idx = this.alphabet.indexOf(currChar);
                char newChar = this.shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
