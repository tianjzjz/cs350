package HW4;

import java.util.Random;

public class Custom {
  
  public static double custom(double[] outcomes, double[] probs) {
    Random rm = new Random();
    double r=rm.nextDouble();
    double sum=0;
    double curr=0;
    for(int i=0;i<probs.length;i++) {
      if(r>=sum && r<sum+probs[i]) {
        curr=outcomes[i];
      }
      sum+=probs[i];
    }
    return curr;
  }

  public static double custom1(int[] outcomes, double[] probs) {
    Random rm = new Random();
    double r=rm.nextDouble();
    double sum=0;
    double curr=0;
    for(int i=0;i<probs.length;i++) {
      if(r>=sum && r<sum+probs[i]) {
        curr=outcomes[i];
      }
      sum+=probs[i];
    }
    return curr;
  }
}
