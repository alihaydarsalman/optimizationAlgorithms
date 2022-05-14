package SimulatedAnnealing;

import java.util.Random;

public class Solution implements Cloneable {

    public double[] solutionString;
    private double objValue = 0;

    public Solution(int dim) {
       solutionString = new double[dim];
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        Solution s1 = new Solution(this.solutionString.length);
        s1.objValue = this.objValue;

        System.arraycopy(this.solutionString, 0, s1.solutionString, 0, this.solutionString.length);

        return s1;
    }

    public void calculateObjValue(){

        double sum=0;

        for (Double solStr : this.solutionString) {
            sum += solStr * solStr;
        }

        this.objValue = sum;
    }

    public Solution generateNeighbor(Random rn, double lB, double uB) throws CloneNotSupportedException {

        double lbN = lB * 0.1;
        double ubN = uB * 0.1;

        Solution neighbor = (Solution) this.clone();

        for (int i=0; i<this.solutionString.length; i++){

            double r = lbN + rn.nextDouble() * (ubN - lbN);
            neighbor.solutionString[i] += r;

            if (neighbor.solutionString[i]<lB){
                neighbor.solutionString[i] = lB;
            }
            else if (neighbor.solutionString[i]>uB){
                neighbor.solutionString[i] = uB;
            }
        }

        return neighbor;
    }

    public double[] getSolutionString() {
        return solutionString;
    }

    public void setSolutionString(double[] solutionString) {
        this.solutionString = solutionString;
    }

    public double getObjValue() {
        return objValue;
    }

    public void setObjValue(double objValue) {
        this.objValue = objValue;
    }

}
