package HW3;

public class SimulatorK {
  //average arrival rate of requests per milliseconds
  static double lambda;
  
  //average service time milliseconds
  static double Ts;
  
  //maximum queue length
  static int maxlen;
  
  public static void simulate(double time) {
    
    Controller.simulate(time, lambda, maxlen, Ts);
  
  }
  
  public static void main(String args[]) {
    double time = Double.parseDouble(args[0]);
    lambda = Double.parseDouble(args[1]);
    Ts = Double.parseDouble(args[2]);
    maxlen = Integer.parseInt(args[3]);
    //LinkedList q = new LinkedList();
    //double len = q.size();
    //System.out.println("the length is "+len);
    /*double time=10.0;
    lambda = 0.5;
    Ts=2.0;
    maxlen=1;
    */
    simulate(time);
  }
  
}
