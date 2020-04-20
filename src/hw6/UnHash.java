package hw6;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
  
  public static int[] unhashCompound(String to_unhash) throws NoSuchAlgorithmException {
    

    int[] result=new int[] {-1,-1,-1,-1};
    
    for(int a=0;a<51;a++) {
      for(int b=0;b<51;b++) {
        for(int c=0;c<51;c++) {
          for(int d=0;d<51;d++) {
           String temp=add(a,b,c,d);
           String test=Hash.hashtest(temp);
           if(test.equals(to_unhash)) {
             System.out.println(a);
             System.out.println(b);
             System.out.println(c);
             System.out.println(d);
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
  
  
  public static int[] unhashCompound1(String to_unhash) throws NoSuchAlgorithmException {
    

    int[] result=new int[] {-1,-1,-1,-1};
    
    for(int a=0;a<201;a++) {
      for(int b=0;b<201;b++) {
        for(int c=0;c<201;c++) {
          for(int d=0;d<201;d++) {
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
  
  public static int unhash(String to_unhash) throws NoSuchAlgorithmException {
    
    int temp=0;
    for(int i=0;i<10000000;i++) {
      if(Hash.hash(temp).equals(to_unhash)) {
        return temp;
      }
      temp++;
    }
    return -1;

  }
  
 
  

  
  public static void main(String [] args) throws NoSuchAlgorithmException {
    String to_unhash = args[0];
    unhashCompound(to_unhash);
    //unhashCompound("37a050e6a7e0b4ec86bf31d8c1c403c9");
    //System.out.println(add(32,134,98,121));
    //System.out.println(Hash.hashtest(add(1,134,98,121)));
    //System.out.println(Hash.hashtest("121098134001"));

  }


}
