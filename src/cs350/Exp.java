package cs350;

import java.util.*;

public class Exp {
    
    public static double getExp(double lambda){
        Random rand = new Random();
        double Y = rand.nextDouble();
        double x = -(Math.log(1 - Y)/lambda);
        return x;
    }

    public static void main(String[] args) {

        double lambda;
        int n;
        lambda = Double.parseDouble(args[0]);
        n = Integer.parseInt(args[1]);

    //Run function n times
        for(int i = 0; i < n; i++){
            System.out.println(getExp(lambda));
        }
    }
}