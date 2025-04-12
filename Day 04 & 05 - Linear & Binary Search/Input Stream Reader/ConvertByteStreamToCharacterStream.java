package Day4.InputStreamReader;
import java.io.*;
public class ConvertByteStreamToCharacterStream {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\sreeh\\Downloads\\Capgemini Training\\Java\\Java Training_Bridge Labs_Week-4\\src\\Day4\\FileReader\\example.txt";

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8"); // Specify charset (e.g., UTF-8)
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
