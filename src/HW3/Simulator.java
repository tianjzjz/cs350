package HW3;


public class Simulator {
  
  //average arrival rate of requests per milliseconds
  static double lambda;
  
  //average service time milliseconds
  static double Ts1;
  static double Ts2;
  
  //maximum queue length
  static int maxlen;
  
  public static void simulate(double time) {
    
    Controller.simulation(time, lambda, maxlen, Ts1, Ts2);
  
  }
  
  public static void main(String args[]) {
    double time = Double.parseDouble(args[0]);
    lambda = Double.parseDouble(args[1]);
    Ts1 = Double.parseDouble(args[2]);
    maxlen = Integer.parseInt(args[3]);
    Ts2 = Double.parseDouble(args[4]);
    
    //LinkedList q = new LinkedList();
    //double len = q.size();
    //System.out.println("the length is "+len);
    
    /*double time=150.0;
    lambda = 3;
    Ts1=1.0;
    maxlen=3;
    Ts2=2.0;
    */
    
    simulate(time);
  }
  
  

}
