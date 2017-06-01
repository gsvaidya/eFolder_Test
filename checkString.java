import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gandhar on 5/31/17.
 */
public class checkString {

    //public static boolean matchString(String match,String documentpath){
        public static boolean searchUsingBufferedReader(String searchQuery, String filePath) throws IOException
        {
            searchQuery = searchQuery.trim();
            BufferedReader br = null;

            try
            {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                String line;
                while ((line = br.readLine()) != null)
                {
                    if (line.contains(searchQuery))
                    {
                        return true;
                    }
                    else
                    {
                    }
                }
            }
            finally
            {
                try
                {
                    if (br != null)
                        br.close();
                }
                catch (Exception e)
                {
                    System.err.println("Exception while closing bufferedreader " + e.toString());
                }
            }

            return false;
        }
}
