public class LatLng
{
    public double calculateDistance(double txPower, double rssi) 
    {   
        txPower = -59;
        if (rssi == 0) 
        {
            return -1.0; // if we cannot determine distance, return -1.
        }

        double ratio = rssi * 1.0 / txPower;

        if (ratio < 1.0) 
        {
            return Math.pow(ratio, 10);
        } 
        else 
        {
            double accuracy = (0.89976) * Math.pow(ratio, 7.7095) + 0.111;
            return accuracy;
        }
    }
    double latitude,longitude;
    public LatLng(double x,double y)
    {
        latitude=x;
        longitude=y;
    }
    public LatLng getLocationByTrilateration(LatLng location1,int rssi1, LatLng location2,int rssi2, LatLng location3,int rssi3 )
    {
        double distance1=calculateDistance(-59,rssi1);
        double distance2=calculateDistance(-59,rssi2);
        double distance3=calculateDistance(-59,rssi3);

        double[] P1 = new double[2];
        double[] P2 = new double[2];
        double[] P3 = new double[2];
        double[] ex = new double[2];
        double[] ey = new double[2];
        double[] p3p1 = new double[2];
        double jval = 0;
        double temp = 0;
        double ival = 0;    
        double p3p1i = 0;
        double triptx;
        double tripty;
        double xval;
        double yval;
        double t1;
        double t2;
        double t3;
        double t;
        double exx;
        double d;
        double eyy;
        //TRANSALTE POINTS TO VECTORS
        //POINT 1
        P1[0] = location1.latitude;
        P1[1] = location1.longitude;
        //POINT 2
        P2[0] = location2.latitude;
        P2[1] = location2.longitude;
        //POINT 3
        P3[0] = location3.latitude;
        P3[1] = location3.longitude;
       
        for (int i = 0; i < P1.length; i++) 
        {
            t1 = P2[i];
            t2 = P1[i];
            t = t1 - t2;
            temp += (t*t);
        }
        d = Math.sqrt(temp);
        for (int i = 0; i < P1.length; i++) 
        {
            t1 = P2[i];
            t2 = P1[i];
            exx = (t1 - t2)/(Math.sqrt(temp));
            ex[i] = exx;
        }
        for (int i = 0; i < P3.length; i++) 
        {
            t1 = P3[i];
            t2 = P1[i];
            t3 = t1 - t2;
            p3p1[i] = t3;
        }
        for (int i = 0; i < ex.length; i++) 
        {
            t1 = ex[i];
            t2 = p3p1[i];
            ival += (t1*t2);
        }
        for (int i = 0; i < P3.length; i++) 
        {
            t1 = P3[i];
            t2 = P1[i];
            t3 = ex[i] * ival;
            t = t1 - t2 -t3;
            p3p1i += (t*t);
        }
        for (int i = 0; i < P3.length; i++) 
        {
            t1 = P3[i];
            t2 = P1[i];
            t3 = ex[i] * ival;
            eyy = (t1 - t2 - t3)/Math.sqrt(p3p1i);
            ey[i] = eyy;
        }
        for (int i = 0; i < ey.length; i++) 
        {
            t1 = ey[i];
            t2 = p3p1[i];
            jval += (t1*t2);
        }
        xval = (Math.pow(distance1, 2) - Math.pow(distance2, 2) + Math.pow(d, 2))/(2*d);
        yval = ((Math.pow(distance1, 2) - Math.pow(distance3, 2) + Math.pow(ival, 2) + Math.pow(jval, 2))/(2*jval)) - ((ival/jval)*xval);
        t1 = location1.latitude;
        t2 = ex[0] * xval;
        t3 = ey[0] * yval;
        triptx = t1 + t2 + t3;
        t1 = location1.longitude;
        t2 = ex[1] * xval;
        t3 = ey[1] * yval;
        tripty = t1 + t2 + t3;
        return new LatLng(triptx,tripty);
        
    }
    public static void main(String args[])
    {
        LatLng l1=new LatLng(0.0,0.0);
        LatLng l2=new LatLng(0.0,69.0);
        LatLng l3=new LatLng(69.0,0.0);
        LatLng l4=l1.getLocationByTrilateration(l1,-40,l2,-40,l3,-90);
        System.out.println("Latitude= "+l4.latitude);
        System.out.println("Longitude= "+l4.longitude);
    }
}
