import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    //return encrypted String which shifted on the alphabet on key value
    public String encrypt(String a, int key) {
        StringBuilder encrypted = new StringBuilder(a);
        String b = alphabet.substring(key) + alphabet.substring(0,key);
         for(int i = 0; i<encrypted.length(); i++){
             if(Character.isLowerCase(encrypted.charAt(i))){
                 char currChar = Character.toUpperCase(encrypted.charAt(i));
                 int c = alphabet.indexOf(currChar);
                 if(c!=-1){
                 char newChar = Character.toLowerCase(b.charAt(c));
                 encrypted.setCharAt(i, newChar);
                }
                }
             else{ char currChar = encrypted.charAt(i);
                   int c = alphabet.indexOf(currChar);
                   if(c!=-1){
                   char newChar = b.charAt(c);
                   encrypted.setCharAt(i, newChar);
                }
                }

            }
            return encrypted.toString();
    }

    public void testCaesar(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        int key = 15;
        int key2 = 24; 
        String a = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", key);
        System.out.println(a);
        //String encrypted = encryptTwoKeys(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21),0,13);
        String decrypted = encryptTwoKeys(a, 16);
        System.out.println("encrypted2 " + decrypted);

    }

    
    //This method makes second encrypt step, it ecnrypts odd character with key2
    public String encryptTwoKeys(String a, int key2){

        StringBuilder encrypted = new StringBuilder(a);
        //System.out.println(encrypted);
        String b = alphabet.substring(key2) + alphabet.substring(0,key2);
        for(int i=0; i<encrypted.length(); i++){
            if(Character.isLowerCase(encrypted.charAt(i))){
                char currChar = Character.toUpperCase(encrypted.charAt(i));
                int c = alphabet.indexOf(currChar);
                if(c!=-1 && ((i & 1) != 0)){
                    char newChar = Character.toLowerCase(b.charAt(c));
                    encrypted.setCharAt(i, newChar);
                }
            }
            else if (Character.isUpperCase(encrypted.charAt(i))){
                   char currChars = encrypted.charAt(i);
                   int e = alphabet.indexOf(currChars);
                   if(e!=-1 && ((i & 1) != 0)){
                       char newChar = b.charAt(e);
                       encrypted.setCharAt(i, newChar);
                    }
                }  
        }
    return encrypted.toString();
}
}

