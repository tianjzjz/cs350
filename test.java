package hw8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class test {
  
  public static void main(String [] args) throws NoSuchAlgorithmException {
    File f =new File("/Users/amari/eclipse-workspace/CS350HW/src/hw8/treasure_island.txt");
    Scanner scan=null;
    
    try {
      scan=new Scanner(f);

  }
  catch(Exception e) {
      e.printStackTrace(System.out);
  }
    scan.useDelimiter(",");
    StringBuffer sb=new StringBuffer();
    while(scan.hasNext()) {
      sb.append(scan.next());
      sb.append(",");
      
    }
    //System.out.println(sb);
    System.out.println(sb.substring(14663,14664));

  }

}
