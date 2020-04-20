package cs350;


public class Simulator {
  
  //simulates the arrival and execution of requests at a generic server for time msec,
  //where time is passed as a parameter to the method
  
  //average arrival rate of requests per milliseconds
  static double lambda;
  
  //average service time milliseconds
  static double Ts;
  
  public static void simulate(double time) {
    
    Controller.simulation(time, lambda, Ts);
    
  }
  
  public static void main(String args[]) {
    double time = Double.parseDouble(args[0]);
    lambda = Double.parseDouble(args[1]);
    Ts = Double.parseDouble(args[2]);
    
    //double time=10.0;
    //lambda = 2;
    //Ts=1.0;
    
    simulate(time);
  }
  
  
  

}
