package SimulatedAnnealing;

import java.util.Random;
import java.util.Scanner;

public class SimulatedAnnealingTest {

    /**
     *       Linear cooling was used to solve this problem.
     *       The problem has been determined as a global problem.
     *       Objective function can think as f(x,y...) = x^2 + y^2 + ...
     **/

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {

        Random random = new Random();
        int dim; //dimension of problem (decision variable size)
        double lB; //decision parameter lower bound
        double uB; //decision parameter upper bound
        double tS; //starting temperature
        double alpha; //temperature protection rate for each iteration (you can think as step size)
        int maxIter; //maximum iteration
        Scanner scan = new Scanner(System.in);

        StringBuilder result = new StringBuilder("\nf(x) = \r\n");

        System.out.print("\nEnter dimension of problem: ");
        dim = scan.nextInt();

        Solution currSolution = new Solution(dim);

        System.out.print("Enter lower bound of decision parameter: ");
        lB = scan.nextDouble();

        System.out.print("Enter upper bound of decision parameter: ");
        uB = scan.nextDouble();


        for (int i=0; i<dim; i++){

            currSolution.solutionString[i] = lB + random.nextDouble() * (uB - lB);
        }

        currSolution.calculateObjValue();

        result.append("0: ").append(currSolution.getObjValue()).append("\r\n");

        System.out.print("\nEnter starting temperature: ");
        tS = scan.nextDouble();

        System.out.print("\nEnter temp protection rate: ");
        alpha = scan.nextDouble();

        System.out.print("\nEnter maximum iteration: ");
        maxIter = scan.nextInt();

        for (int i=0; i<maxIter; i++){

            Solution nS = currSolution.generateNeighbor(random,lB,uB); //generating neighbor solution
            nS.calculateObjValue();
            double delta = nS.getObjValue() - currSolution.getObjValue();

            if (delta<0 || (random.nextDouble() <= Math.pow(Math.E,(-delta/tS))))
                currSolution= (Solution) nS.clone();

            result.append(i + 1).append(": ").append(currSolution.getObjValue()).append("\r\n");
            tS= tS * alpha;
        }

        System.out.println("\n\n\tCALCULATING...");
        Thread.sleep(2000);
        System.out.println("\nRESULT: "+ result);
    }
}
