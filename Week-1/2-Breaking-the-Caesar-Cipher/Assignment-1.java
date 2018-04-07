import edu.duke.*;

public class WordLengths
{
    public void countWordLengths(FileResource resource, int counts[]){
        for(String word : resource.words()){
            int l = word.length();
            if (!Character.isLetter(word.charAt(0))){
                l--;
            }
            if (!Character.isLetter(word.charAt(word.length()-1)) && word.length()!=1){
                l--;
            }
                    counts[l]++;
        }
        int max=0;
        int index=0;
        for (int i=1;i<counts.length;i++){
            if(counts[i]>max){
                max=counts[i];
                index=i;
            }
            
        }
        System.out.println("The most common word length is " + index);
    }
    
    public int indexOfMax(int[] values){
        int index = 0;
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i]>max){
                max = values[i];
                index = i;
        }
        
    }
    return index;
}
    
    public void testCountWordLengths(){
        int[] counts = new int[31];
        FileResource resource = new FileResource();
        countWordLengths(resource,counts);
    }
    
}