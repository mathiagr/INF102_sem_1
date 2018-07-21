import edu.princeton.cs.algs4.LinearRegression;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class CountSort {

    public static void main(String[] args) {

        int N = 10; //Number of array elements

        int [] myArray = new int[N];
        //Comparable [] myArray = new Comparable[N]; //When comparing to linearithmic profile I use Quicksort which uses Comparable

//Initialize random array of length N with values 0 to 99
        for(int i = 0; i <N; i++){
            myArray[i] = (int) (Math.random()*100);
        }

        double runtime = 0;
        int runs = 1000000; //Number of runs (runtime is taken as an average)

//I do 2x runs and disregard the first half of them
//The reason for this is that the first runs seems to always take longer time
//I suspect it's caused by uneven allocation of processing power initially since the runtimes seem to be stable after a certain number of runs
        for(int i = 0; i < runs*2; i++)
        {
            Stopwatch stopwatch = new Stopwatch();
            sort(myArray);
            //QuickSort.sort(myArray); //I use this when comparing Countsort and Quicksort
            if (i>runs){
                runtime += stopwatch.elapsedTime();
            }
            StdRandom.shuffle(myArray);
        }
        System.out.printf("%.10f%n", runtime/runs);

/*
//RESULTS AND LINEAR REGRESSION:

//Results for different runs (used for linear regression)
        double[] numberOfElements = new double[]{2.80617997398389,	3.10720996964787,	3.40823996531185,	3.70926996097583,	4.01029995663981,	4.31132995230379, 4.31132995230379,	4.61235994796777,	4.91338994363176,	5.21441993929574,	5.51544993495972,	5.8164799306237};
        double[] runTimes = new double[]{-5.73282827159699,	-5.45842075605342,	-5.18442225167573,	-4.93479387194569,	-4.5888855814491,	-4.29791427856417, -4.29791427856417,	-4.31264943044197,	-4.00766744095254,	-3.68623820370756,	-3.37726803483528,	-3.081235968972};
//Linear Regression
        LinearRegression linreg = new LinearRegression(numberOfElements, runTimes);

        System.out.printf("Linear regression for Countsort:\nSlope: " + linreg.slope() + " intercept: " + linreg.intercept());
*/

    }


    public static int[] sort(int[] array) {

//Auxiliary array for temporarily storing values. This is not an in place sorting method
        int[] aux = new int[array.length];


//Array for storing the frequencies of occurrence for each number from 0 to 99
        int[] counts = new int[100];

//Populating the frequency array
        for (int i = 0;  i < array.length; i++) {
            counts[array[i]]++;
        }

//The next step is to find the positions
//this is done by each element gets added the sum of all previous counts to it

        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i-1];
        }

//Now we populate the auxiliary. The position will be counts[array[i]] for the input value array[i]
//We sort the array from right to left.
        for (int i = array.length - 1; i >= 0; i--) {
            aux[counts[array[i]]--] = array[i];
        }
        return aux;
    }
}