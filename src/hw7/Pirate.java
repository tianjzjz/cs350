package hw7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;

public class Pirate {
  public static int hint;
  public static int[] hints;
  public String filepath;
  public static String file;
  
  public Pirate(String file) {
    filepath=file;
  }
  
  public void findTreasure(int position) throws NoSuchAlgorithmException {
    String target;
    
    //read the txt file
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
      treasure(target,position);



    }
    catch(Exception e) {
      e.printStackTrace(System.out);
    }
    
    
    
    

  
  }
  
  public static void treasure(String target,int pos) throws NoSuchAlgorithmException {
    if(crack(target)==true) {
      return;
    }

    else {

      int[] hts=UnHash.unhashCompound(target);
      for(int i=0;i<4;i++) {
        hts[i]=hts[i]*32+pos;

        int position=hts[i];
        String nextTarget=file.substring(position,position+32);
        treasure(nextTarget,position);
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
/*
   int X=Integer.parseInt(args[0]);

    Pirate p =new Pirate(args[1]);
    p.findTreasure(X);

 */
 
    System.out.println("testcase 1\n");
    
    int X=45063;
    Pirate p =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw7/input1.txt");
    p.findTreasure(X);
 /*   
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
/*
    if(crack(X)==true) {
      hint=UnHash.unhash(X);
    }
    */
/*
    int X4=3066;
    Pirate p4 =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw6/input5.txt");
    p4.findTreasure(X4);
 
    
    int X=763;
    Pirate p =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw6/input1.txt");
    p.findTreasure(X);
    int X1=401;
    Pirate p1 =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw6/input2.txt");
    p1.findTreasure(X1);
    
    int X2=1110;
    Pirate p2 =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw6/input3.txt");
    p2.findTreasure(X2);
    
    int X3=1622;
    Pirate p3 =new Pirate("/Users/amari/eclipse-workspace/CS350HW/src/hw6/input4.txt");
    p3.findTreasure(X3);
  
  
*/
    /*
    String a="74d7727aef6793077d99101a103f823310bff783846b06e7908cbc9883483d96219650a36d6198dc8d5b380ab7461e862fb5d3403e14065c3568eda7338ac678d91371f0ad47150f49b70bf2b5f499f679d72b8923793b1de6e0415015b2c4013f5c65584addd709bb07636ff00e52030a5bd98334ac5238e212817614219816b5b0e246b939c9ade938a6b43c82a2898b7528e7dfbaa104b88dd2d0488a7a408caeebcbe5215f19b482353f47add77eee0870f24e2a03f2eeaeea5090c40867d55d4aaebcd16751cb6f6b19877eb313b021574a4865c9c40624ff8466bbaf5f9c96add42aebaf3eaaf062028b14b69f4e94c8bbe47ddb36dcd669e107eb10bd5a040ecd6664c5c25f0c386dc044a874ed57ffeb0613cffd337fcf3ddbd00e3c9fcab6689f5773eca11c415b4c6f4aa25ea097834b097a448fe00b0068e76762615a5b8d92b8e891b97c723746dc38b0d2446d072be0a3d5764d03b2d4422b415449a63dd02985ba4b43214e6ffe095026441d902acf5fe75e3980849f4385f179dcc2b180d8597dc3d113895cf3a6afca5ab51504d57b823fff653e91c6d29d695e9349bae66303273fbc27e23ba9fcb4e7a2f344896968d8a4b469e0ab1a17e452024208b5f0971c82d18b294613f4929ac0b343951043647645789100ad49deb26bfa9492868e179b9e29c91d1d0e368a1c6d2dcfa1eb0d31f2712efdefa44d7f7282c60ec2bd6a017cc4b63e8cf190b6753110df5415f36278c5cc6990ad86e92b1181a1942b2b6afa82fb454c9290f17048ec005768edb66349ca61973b88e006b9e123d9234bc1c513a888526cfeaa7fde07e0a4aa552182cb7abd9f1e45eb56cd23fa6e4430ee4dd57f1a2c5b87dbd861d035b113bc7288f4026cfb1db7b5cf8156cd230224238a470566dd525072bc23d4856f1339fc81d47d47b1b1cf995db503bb50fa5161255a6fc1350fbe4100b8a70cb90a56933b0323aaf6023c9bb9305184f9eb0481f9ee90b3e858d209dbebeca9bfce865086922ecfc261394325ba1d8c9c0b57f1d2650090c004ccf9624897c1c7389c1a999f22535793fcc257024680432b834a832913ad500fbc77cb8ab4ed356ddbd5c92c3c0a4ab5466572bbc2c75bf233bb4a9fc82918eea90ad057537d61e721a3c0081860322f1edf4c3b6d965dae387b85ef82829426a09ad2b0628bcc0de3e21bc0b6f827eab79042abc35ab5fc61146d2fa95885a33b8f15dc50d0c654ef6c14a782921cb9ae168d76a5f316fe76440fac8325be97b2aeddb5e9fc453f0f747937f275d618917eaccafbddbd378f0053741eac29ed95e366f31cb589c865cd580251149eefdc5105420a6ee490e938feb815b05b09f2414c96a2fad1889fa5b71ff4d4691ce5dfd09d69c1384c5ccbfccbfed00c2be8c1e3a10ccd0e8484740be5afacf09d1a0b48dc2c7726f321f2a23a97f296c79dbfc8c73a71442d5e5dc98d789c37af3d056bf126502687c032467765d1dffa3912df78ea3e63ccd9181b08833d9673bcf39676f126ae4e0f5b4a73742bf026cfc6dca58b803be541592045123e3081623fdd26b7396c6b2af525d3744c560c2588be2b6b1ff9d53418167cb1b91381a27707a869bea1e415423c00c8004b5853f301f9ae11dbc7666ea404c417a4ca7286250c1bb4180c4cea9dfda6df79ac0615780e9eee1dfc12dc9efcd202dc6f97415c69ee0ff364accd39fc92bafa87e16f69904bc0bd62d572edb211ce614aadf2acb99ed6144dc1455b8d9df64c6c51117a11b45abdb348f08cfcfd5cb3f7bbb715b07f2d746eb9141343075da903512c1dac21d1bc3b5c72f8dc3986bea82704ccf4dfd4aeab60f8625c31a88ca5097604fffe932f6abef9a9b959a003bdee22ae60e12a5abe7ec002de49924a00bfbd8d7c3f7ef93aea50d58610a16974378663f056d2b5e425e7b9a23199ce3b731541329ea0b663c4851cdf62cf708457c0bdd9f51b2d16a7a41211faabc85e691c1e2d13c811563ce877ab893a513f12b449979598bc338c9b8dc26ecd8c776ff94793148823647016da37785784e654fce1a22a65da1021f6bd2c8fc32e6aafc032c0f9b86878b8c4e6e5b884b828ca071c676e1179487c5b45c9ef1d1bedda900a0f5d3fe48e61f61f1a9ef96edda0310fa32fa399b2036c432d8218da5f5216f5fab4ce0d05c8f02ec5aa6ab5b4dedc19f203894de8a25a284e34a2621fefb1ce8890ce69b8cfe04ce7556d1f5617ee9ada0c810422aa228428f4898d489191c31fb97dc56e05b52395e06801d6655c7e03d1a1c423efd444cf8b1d5133e0a0da9fb6c9fa932e2c0d7fb5d49a11e5355f5a2778eaf0abe663cee8b2c0b6bd2f2643777b35ebf562a5a0d35636415db7a43279116d2cb2ab46760ce37368e938bdcb077207f7cdf7a995aa1f9fa3e33cf05bc6d62f2ef6702f49d3a58b7e1ca6aff2671705b7bfbbf2f29f4f0be8457d94420fa5e02b9fbce19c5ae44c5b73ddecfa12085c4d11cbed78618bfab3d5e3ae3f18aa3618776deeadb4252a27e21d7a47d8327d6df56bc77b22e27272860fd41aba5ca7dde9c0a1e352f5f3cd3f27cf9c8d0da07c0c2e8eff5d97ab392f885a371a363595c66870cbc227d13a9beddd3aea97ba885b77aa96f666fbcb39b0b4854a18e36e197e48ffb6bdc48d947d535100f5f80de5ce0f33c908bf075209fa23a14c996c2bb7c76b1f3cd96514af1d9d9d7e68f2f676b27bb442702ba833e9126d10af07ca9ca8aaf4f4815f1fabba3b84ac138c30091853193d2d4b86bae65d4d4a0b6e5b93a215fedefb8ffdec3676ecb114fc176f8c78bba67292b7e3ce21f59a40f2fd11f6ce3cb86fb5a879c406e133c0e288edb949fe51ea13";
    String b=a.substring(3066, 3098);
    System.out.println(b);
    System.out.println(UnHash.unhashCompound("f96edda0310fa32fa399b2036c432d82"));
    System.out.println(b);
*/
  }

}
