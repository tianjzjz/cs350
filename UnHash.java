package hw5;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UnHash {
  

  
  public static int unhash(String to_unhash) throws NoSuchAlgorithmException {
    
    int temp=0;
    while(true) {
      if(Hash.hash(temp).equals(to_unhash)) {
        return temp;
      }
      temp++;
    }
    

  }
  

  
  public static void main(String [] args) throws NoSuchAlgorithmException {
    String to_unhash = args[0];
    unhash(to_unhash);
  }


}
