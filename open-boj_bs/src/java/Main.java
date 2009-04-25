import java.util.*;


public class Main
{




    public static void main(String [] args)
    {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextInt())
        {
            int n = scan.nextInt();
            double h,r;
            for(int i=0;i<n;i++)
            {
                h = scan.nextDouble();
                r = scan.nextDouble();
                double res = Math.sqrt(2*(h-r)/9.8);
                System.out.format("%1$.2f\n",res);
            }
        }
    }
}
