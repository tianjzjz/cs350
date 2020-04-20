package hw8;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.math.BigInteger;
public class UnHash {
  

  

  
  public static String add(int a,int b,int c, int d) {
    String temp="";
    temp+=Integer.toString(d);
    int l1=3-Integer.toString(c).length();
    for(int i=0;i<l1;i++) {
      temp+="0";
    }
    temp+=Integer.toString(c);
    
    int l2=3-Integer.toString(b).length();
    for(int i=0;i<l2;i++) {
      temp+="0";
    }
    temp+=Integer.toString(b);  
    
    int l3=3-Integer.toString(a).length();
    for(int i=0;i<l3;i++) {
      temp+="0";
    }
    temp+=Integer.toString(a);
    return temp;
  }
  
  
  //unhash a compound integer
  public static int[] unhashCompound(String to_unhash) throws NoSuchAlgorithmException {
    

    int[] result=new int[] {-1,-1,-1,-1};
    
    for(int a=0;a<61;a++) {
      for(int b=0;b<61;b++) {
        for(int c=0;c<61;c++) {
          for(int d=0;d<61;d++) {
           String temp=add(a,b,c,d);
           String test=Hash.hashtest(temp);
           if(test.equals(to_unhash)) {
             result[0]=a;
             result[1]=b;
             result[2]=c;
             result[3]=d;
             return result;
           }
          }
        }
      }
    }
    return result;

 

  }
  


 //unhash an integer
  public static int unhash(String to_unhash) throws NoSuchAlgorithmException {
    
    int temp=0;
    for(int i=0;i<1000000;i++) {
      if(Hash.hash(temp).equals(to_unhash)) {
        return temp;
      }
      temp++;
    }
    return -1;

  }

  
  

  
  public static void main(String [] args) throws NoSuchAlgorithmException {
    System.out.println();
    System.out.println(Arrays.toString(unhashCompound("1f0f5e793ed6d583d1a0b38c7f253e17")));
    //unhashCompound("37a050e6a7e0b4ec86bf31d8c1c403c9");
    //System.out.println(add(32,134,98,121));
    //System.out.println(Hash.hashtest(add(1,134,98,121)));
    //System.out.println(Hash.hashtest("121098134001"));

  }


}
