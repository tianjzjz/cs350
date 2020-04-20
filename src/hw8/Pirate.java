package hw8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.math.*;

public class Pirate {
	public static int hint;
	public String filepath;
	public static String file;
	public static String finalpath;
	

	
	public Pirate(String file,String finalfile ) {
		filepath=file;
		finalpath=finalfile;
		
	}
	
	private class Hint {
		int hint;
		int degree;
		Hint next;
		Hint(int hint, int degree,Hint next){
			this.hint=hint;
			this.degree=degree;
			this.next=next;
		}
	}
	private Hint head=new Hint(0,0,null);
	private Hint curr=head;
	public int maxdegree=0;
	public int count=0;
	

	
	public void findTreasure(int position) throws NoSuchAlgorithmException {
		String target;


	  
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			StringBuilder sb = new StringBuilder();
			String currline=br.readLine();

			while(currline!=null){
				sb.append(currline);
				currline=br.readLine();
			}      
			br.close();
			
			//transform string builder to string
			file = sb.toString();
			

			//get the target hint
			target=file.substring(position, position+32);
			treasure(target,position,1);
		}
		catch(Exception e) {
			e.printStackTrace(System.out);
		}
		
		//
		
		int[] hints= new int[count];
		int now=0;

		for(int i=0;i<maxdegree+1;i++) {
			Hint cur=head.next;
			while(cur!=null) {
				if(cur.degree==i) {

					hints[now]=cur.hint;
					now++;
				}
				cur=cur.next;
			}
		}

		
        
        //read the txt file
        File f =new File(finalpath);
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

			
			//transform string builder to string
			String finalfile = sb.toString();
			StringBuilder s=new StringBuilder();

			for(int i=0;i<count;i++) {
				s.append(finalfile.substring(hints[i], hints[i]+1));
			}
			
			String finalhint=s.toString();
			System.out.println(finalhint);

		}


	
	
	
	public void treasure(String target,int pos, int degree) throws NoSuchAlgorithmException {
	  

		if(crack(target)==true) {

			maxdegree=Math.max(maxdegree,degree);

			Hint newHint=new Hint(hint,degree,null);
			curr.next=newHint;

			curr=newHint;
			count++;
			
			return;
		}

		else {


			int[] hts=UnHash.unhashCompound(target);

			for(int i=0;i<4;i++) {
				hts[i]=hts[i]*32+pos;

				int position=hts[i];
				String nextTarget=file.substring(position,position+32);
				treasure(nextTarget,position,degree+1);
			}
		}

		
	}
	
	//return false if it's an compound int
	public static boolean crack(String to_unhash) throws NoSuchAlgorithmException {

		hint=UnHash.unhash(to_unhash);
		if(hint==-1) {
			return false;
		}
		return true;

	}
	
	public static void main(String [] args) throws NoSuchAlgorithmException {

		int X=Integer.parseInt(args[0]);
		Pirate p =new Pirate(args[1],args[2]);
		
		p.findTreasure(X);

	  /*
		System.out.println("testcase 1\n");
		
		int X=45063;
		Pirate p =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw8/treasure_map.txt","/Users/amari/eclipse-workspace/CS350HW/src/hw8/treasure_island.txt");
		p.findTreasure(X);
 
/* 
		System.out.println("testcase 1\n");
		
		int X=547;
		Pirate p =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw7/input1.txt");
		p.findTreasure(X);
		
		System.out.println("testcase 2\n");
		int X2=10180;
		Pirate p2 =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw7/input2.txt");
		p2.findTreasure(X2);
		
		System.out.println("testcase 3\n");

		int X3=34770;
		Pirate p3 =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw7/input3.txt");
		p3.findTreasure(X3);
		
		System.out.println("testcase 3\n");

		int X4=42325;
		Pirate p4 =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw7/input4.txt");
		p4.findTreasure(X4);
*/  


	}

}
