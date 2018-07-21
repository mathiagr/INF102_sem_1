import edu.princeton.cs.algs4.*;


public class QuicksortEmpirically {
    public static void main(String[] args) {

        int N = 5000;
        int Nmax = 320000;
        int runs =10; //Number of runs (runtime is taken as an average
        double runtime = 0;

        boolean shuffle = true; //Use this to run the code for Quicksort with or without shuffle
        String shuffleStr = null; //String used in output print
        if (shuffle){shuffleStr = "with";}else {shuffleStr = "without";}

//Arrays where run times and number of elements are stored
        double[] numberOfElements = new double[7];
        double[] runtimes = new double[7];

        int k = 0; //I use this to as an array index inside the loop below

//I do some extra runs initially which are not accounted for in total runtime
//The reason I do this is that the first sorts usually take longer time for some reason (maybe processing power allocation)
        int warmupRuns = 2;

//With the java compiler optimisation the code yields very different results for each run and is not reliable.

        for (int i = N; i <= Nmax; i *= 2) {
            numberOfElements[k] = Math.log10(i);
            Comparable[] myArray = new Comparable[i];
            runtime = 0;

            //Filling arrays with random doubles
            for (int l = 0; l < runs+warmupRuns; l++) {
                for (int j = 0; j < myArray.length; j++) {
                    myArray[j] = StdRandom.uniform();
                }
                Stopwatch stopwatch = new Stopwatch();
                if (shuffle) {
                    StdRandom.shuffle(myArray); //The shuffle is removed from Quicksort.java. Adding it here is equivalent to not removing it.
                    QuickSort.sort(myArray);
                } else {
                    QuickSort.sort(myArray);
                }
                if(l>=warmupRuns) runtime += stopwatch.elapsedTime();
                StdRandom.shuffle(myArray);
            }

            runtimes[k] = Math.log10(runtime/runs);
            k++;
        }
        for (int j = 0; j < numberOfElements.length; j++) {
            //System.out.println("Elements: " + numberOfElements[j] + " runtime: " + runtimes[j]);
            System.out.println(runtimes[j]);
            // System.out.printf("\n\nAverage runtime: %.5f%n", runtime/runs);
        }
//Linear Regression
        LinearRegression linreg = new LinearRegression(numberOfElements, runtimes);
        System.out.printf("Linear regression for Quicksort "+ shuffleStr +" shuffle:\nSlope: " + linreg.slope() + " intercept: " + linreg.intercept());
    }
}