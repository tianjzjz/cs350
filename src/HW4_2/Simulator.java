package HW4_2;

public class Simulator {
  static double time = 0;
  static double lambda = 0.0; 
  static double m0 = 0 ;
  static double m1 = 0 ;
  static double m2 = 0 ;
  static double t1 = 0 ;
  static double p1 = 0 ;
  static double t2 = 0 ;
  static double p2 = 0 ;
  static double t3 = 0 ;
  static double p3 = 0 ;
  static int K = 0;
  static double p01 = 0 ;
  static double p02 = 0 ;
  static double pout = 0 ;
  static double p31 = 0 ;
  static double p32 = 0 ;
  
  public static void simulate(double time){
    new system(time, lambda, m0, m1, m2, t1, p1, t2, p2, t3, p3, K, p01, p02, pout, p31, p32) ;
  }
    

 public static void main(String[] args) {

  /*time = Double.parseDouble(args[0]);
  lambda = Double.parseDouble(args[1]);
  m0 = Double.parseDouble(args[2]);
  m1 = Double.parseDouble(args[3]);
  m2 = Double.parseDouble(args[4]);
  t1 = Double.parseDouble(args[5]);
  p1 = Double.parseDouble(args[6]);
  t2 = Double.parseDouble(args[7]);
  p2 = Double.parseDouble(args[8]);
  t3 = Double.parseDouble(args[9]);
  p3 = Double.parseDouble(args[10]);
  K = Integer.parseInt(args[11]);
  p01 = Double.parseDouble(args[12]);
  p02 = Double.parseDouble(args[13]);
  pout = Double.parseDouble(args[14]);
  p31 = Double.parseDouble(args[15]);
  p32 = Double.parseDouble(args[16]);
  simulate(time);*/
   double time=100000;
   lambda=0.012;
    m0=40;
    m1=43;
    m2=85;
    t1=32;
    p1=0.35;
    t2=40;
    p2=0.4;
    t3=65;
    p3=0.25;
    K=10;
    p01=0.8;
    p02=0.2;
    pout=0.5;
    p31=0.3;
    p32=0.2;
  
   simulate(time);
 }
 
 

}