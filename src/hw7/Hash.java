package hw7;

import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Hash {
  

  private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
      'e', 'f' };

  
  public static String hashBigInt(BigInteger b2) throws NoSuchAlgorithmException {
    String a = b2.toString();
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(a.getBytes());
    
    byte[] bytes=md.digest();
    
    StringBuilder str = new StringBuilder();
    for(byte b:bytes) {
      str.append(HEX[(b&0xf0)>>4]).append(HEX[(b&0x0f)]);
    }
    
    return str.toString();
  }
  
  public static String hashtest(String to_hash) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(to_hash.getBytes());
    
    byte[] bytes=md.digest();
    
    StringBuilder str = new StringBuilder();
    for(byte b:bytes) {
      str.append(HEX[(b&0xf0)>>4]).append(HEX[(b&0x0f)]);
    }
    
    return str.toString();
  }
  
  public static String hash(int to_hash) throws NoSuchAlgorithmException {
    String a = Integer.toString(to_hash);
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(a.getBytes());
    
    byte[] bytes=md.digest();
    
    StringBuilder str = new StringBuilder();
    for(byte b:bytes) {
      str.append(HEX[(b&0xf0)>>4]).append(HEX[(b&0x0f)]);
    }
    
    return str.toString();
  }
  

  
  public static void main(String [] args) throws NoSuchAlgorithmException {
    int to_hash = Integer.parseInt(args[0]);
    BigInteger b = new BigInteger(Integer.toString(to_hash));

    hashBigInt(b);

    //BigInteger b = new BigInteger("7000000000");
    //System.out.println(hash(b));
  }

}
