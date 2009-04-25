
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
        FileInputStream fin = null;
        try
        {
            fin = new FileInputStream("账户.txt");
            Scanner scan = new Scanner(fin);
            StringBuilder sb = new StringBuilder();
            HashSet<String> set = new HashSet();
            while(scan.hasNext())
            {
                String line = scan.nextLine();
                String tmp [] = line.split("[\\s]+");
                //0姓名，1学号，2手机
                //姓名
                if(!set.contains(tmp[1]))
                {
                    sb.append("3rd"+tmp[1]+" ");
                    sb.append(tmp[2].substring(5)+" ");
                    sb.append(tmp[0]);
                    sb.append("\r\n");
                    set.add(tmp[1]);
                }
            }
            System.out.println(sb);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{fin.close();}catch(Exception e){}
        }
    }
}
