public class Main 
{
	public static void main(String[] args) {
	    double[] testData = {-53,-66,-50,-49,-52,-68,-69,-49,-67,-68,-52,-52,-68,-53,-66,-50,-52,-53,-68,-66,-68,-68,-66,-65,-52,-68,-52,-52,-52,-66,-53,-52,-52,-54,-49,-66,-49,-68,-51,-52,-68,-70,-49,-66};                             //put the RSSI values.
	    double var = KalmanFilter.variance(testData);
	    KalmanFilter test = new KalmanFilter(0.008,var);
	    int n=testData.length;
	    double fin[]=new double[n];
        for(int i=0;i<n;i++){
            System.out.println("Input data: " + testData[i]);
            //System.out.println("Filtered data: " + (int)test.filter(testData[i]));
            fin[i]=test.filter(testData[i]);
            System.out.println("Filtered data: " + fin[i]);
            }
            double k=KalmanFilter.mn(fin);
            int rssi=(int)k;
            System.out.println("Mean" + k );
            
	}
}