public class Main 
{
	public static double mn(double a[]){
        double sum = 0; 
	    int n = a.length;
        for (int i = 0; i < n; i++) 
            sum += a[i]; 
        double mean = (double)sum/(double)n; 
        return mean;
	}
    public static double variance(double a[]){
        double sqDiff = 0; 
        int n = a.length;
        for (int i = 0; i < n; i++)  
            sqDiff += (a[i] - mn(a)) * (a[i] - mn(a)); 
          
        double var = (double)sqDiff / n;
        System.out.println("Variance: "+var);
        return var;
    }
	public static void main(String[] args) {
	    double[] testData = {66,64,63,63,63,66,65,67,58};                             //put the RSSI values.
	    double var = variance(testData);
	    KalmanFilter test = new KalmanFilter(0.008,var);
	    int n=testData.length;
	    double fin[]=new double[n];
        for(int i=0;i<n;i++){
            System.out.println("Input data: " + testData[i]);
            //System.out.println("Filtered data: " + (int)test.filter(testData[i]));
            fin[i]=test.filter(testData[i]);
            System.out.println("Filtered data: " + fin[i]);
            }
            double k=mn(fin);
            int rssi=(int)k;
            System.out.println("Mean" + rssi );
            
	}
}
