package cs350;

import java.util.*;

public class Custom {
    
    public static double getCustom(double[] outcomes, double[] probabilities){

        Random rdm = new Random();
        double r = rdm.nextDouble();

        double temp = 0;
    
      
        for(int i = 0; i < probabilities.length; i++){
            if(r >= probabilities[i]){
                r-= probabilities[i];
            }
            else{
                r=outcomes[i];
            break;
            }
        }
        return temp;
    }
    
    public static void main(String[] args) {
    //Parse arguments and put into proper arrays
        double []outcomes = new double[5];
        double []probabilities = new double[5];
        int n = Integer.parseInt(args[10]);
        int outcomesIndex = 0;
        int probabilitiesIndex = 0;
    
        for(int i = 0; i < 10; i++){
            if(i%2 == 0){
                outcomes[outcomesIndex] = Double.parseDouble(args[i]);
                outcomesIndex += 1;
            }
            else{
                probabilities[probabilitiesIndex] = Double.parseDouble(args[i]);
                probabilitiesIndex += 1;
            }
        }
    //Run function n times
        for(int i = 0; i < n; i++){
            System.out.println(getCustom(outcomes, probabilities));
        }
    }
}
