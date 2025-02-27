package blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// This method takes a string and applies SHA256 algorithm to it, and returns the generated signature as a string.
public class StringUtil {
    // applies SHA256 to a string and returns a result
    public static String applySHA256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies SHA-256 to our input
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexadecimal
            for(int i=0; i<hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
