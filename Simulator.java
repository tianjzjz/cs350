package HW4;


public class Simulator {
  
  //average arrival rate of requests per milliseconds
  static double lambda;
  
  
  //average service time milliseconds
  static double Ts0;
  static double Ts1;
  static double Ts2;
  static double t1;
  static double p1;
  static double t2;
  static double p2;
  static double t3;
  static double p3;
  static int K2;
  static double p01;
  static double p02;
  static double p3out;
  static double p31;
  static double p32;

  

  
  public static void simulate(double time) {
    


      /*probs0 = new double[]{p01,p02};
      outcomes0 =new int[]{1,2};

    
    probs3=new double[] {p1,p2,p3};
    outcomes3=new double[] {t1,t2,t3};
    
    probsend=new double[] {p3out,p31,p32};
    outcomesend=new int[] {0,1,2};
  */
    
    
    new controller(time, lambda, Ts0, Ts1, Ts2, t1, p1, t2, p2, t3, p3, K2, p01, p02, p3out, p31, p32) ;
     
    
  
  }
  
  public static void main(String args[]) {
   /*
    double time = Double.parseDouble(args[0]);
    lambda = Double.parseDouble(args[1]);
    Ts0=Double.parseDouble(args[2]);
    Ts1=Double.parseDouble(args[3]);
    Ts2 = Double.parseDouble(args[4]);
    t1=Double.parseDouble(args[5]);
    p1=Double.parseDouble(args[6]);
    t2=Double.parseDouble(args[7]);
    p2=Double.parseDouble(args[8]);
    t3=Double.parseDouble(args[9]);
    p3=Double.parseDouble(args[10]);
    K2=Integer.parseInt(args[11]);
    p01=Double.parseDouble(args[12]);
    p02=Double.parseDouble(args[13]);
    p3out=Double.parseDouble(args[14]);
    p31=Double.parseDouble(args[15]);
    p32=Double.parseDouble(args[16]);
  
  */
    
    //100000 0.012 40 43 85 32 0.35 40 0.4 65 0.25 10 0.8 0.2 0.5 0.3 0.2
   /*double time=100000;
   lambda=0.04;
    Ts0=20;
    Ts1=23;
    Ts2=40;
    t1=5;
    p1=0.2;
    t2=15;
    p2=0.5;
    t3=10;
    p3=0.3;
    K2=5;
    p01=0.5;
    p02=0.5;
    p3out=0.2;
    p31=0.5;
    p32=0.3;
  */
    
    double time=100000;
    lambda=0.012;
     Ts0=40;
     Ts1=43;
     Ts2=85;
     t1=32;
     p1=0.35;
     t2=40;
     p2=0.4;
     t3=65;
     p3=0.25;
     K2=10;
     p01=0.8;
     p02=0.2;
     p3out=0.5;
     p31=0.3;
     p32=0.2;
   
    simulate(time);
  }
  
  

}
