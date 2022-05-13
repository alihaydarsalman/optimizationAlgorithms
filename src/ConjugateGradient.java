import java.util.Scanner;

public class ConjugateGradient {
    //for f(x,y,z) = x2 + 2y2 + 2z2 + 2xy + 2yz function
    //(x,y,z) parameters as x = x1 , y = x2 , z = x3 => (x1,x2,x3)

    public static double[] calculateGradient(double[] decParams){

        double[] gV = new double[decParams.length];

        gV[0] = 2*decParams[0] + 2*decParams[1];
        gV[1] = (4*decParams[1]) + (2*decParams[0]) + (2*decParams[2]);
        gV[2] = (4*decParams[2]) + (2*decParams[1]);

        return gV;
    }

    public static double calculateAmplitude(double[] gV){

        return Math.sqrt((gV[0]*gV[0]) +
                (gV[1]*gV[1])  +
                (gV[2]*gV[2]));
    }

    public static void main(String[] args) {

        double[] decisionParameters = new double[3]; //initial solution
        double[] direction = new double[3];
        double alpha; //step size
        double epsilon; //tolerance threshold
        int counter = 0;
        double previousAmp = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nPlease enter decision parameters to initial solution.\n");

        for (int i=0; i<decisionParameters.length;i++){
            System.out.print("Enter "+(i+1) + ". decision parameter: ");
            decisionParameters[i] = scan.nextDouble();
        }

        double[] gradientVector = calculateGradient(decisionParameters);

        System.out.print("\nEnter step size: ");
        alpha = scan.nextDouble();
        System.out.print("\nEnter tolerance threshold: ");
        epsilon = scan.nextDouble();

        while (calculateAmplitude(gradientVector) > epsilon){

            if (counter == 0){
                for (int i=0; i<decisionParameters.length; i++) {
                    direction[i] = (-1) * gradientVector[i];
                }
            }
            else{
                double beta = Math.pow((calculateAmplitude(gradientVector)/previousAmp), 2);
                for (int i=0; i<decisionParameters.length; i++) {
                    direction[i] = (-1) * gradientVector[i] + beta * direction[i];
                }
            }

            for (int i=0; i<decisionParameters.length; i++) {
                decisionParameters[i] = decisionParameters[i] + alpha * direction[i];
            }

            counter++;
            previousAmp = calculateAmplitude(gradientVector);
            gradientVector = calculateGradient(decisionParameters);
        }

        System.out.println("Iteration: "+ counter+"\n");
        for (int i=0; i<decisionParameters.length;i++){
            System.out.println((i+1)+". decision value: "+ decisionParameters[i]);
        }
    }
}
