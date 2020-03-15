public class Main
{
	public static double variance(double a[]){
        double sum = 0; 
	    int n = a.length;
        for (int i = 0; i < n; i++) 
            sum += a[i]; 
        double mean = (double)sum/(double)n; 
      
        // Compute sum squared  
        // differences with mean. 
        double sqDiff = 0; 
        for (int i = 0; i < n; i++)  
            sqDiff += (a[i] - mean) * (a[i] - mean); 
          
        double var = (double)sqDiff / n;
        System.out.println("Variance: "+var);
        return var;
    }
	public static void main(String[] args) {
	    double[] testData = {66,64,63,63,63,66,65,67,58};                             //put the RSSI values.
	    double var = variance(testData);
	    KalmanFilter test = new KalmanFilter(0.008,var);
        for(double x: testData){
            System.out.println("Input data: " + x);
            System.out.println("Filtered data: " + (int)test.filter(x));
        }
	}
}
