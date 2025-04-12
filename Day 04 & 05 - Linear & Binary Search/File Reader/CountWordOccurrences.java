package Day4.FileReader;
import java.io.*;
public class CountWordOccurrences {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\sreeh\\Downloads\\Capgemini Training\\Java\\Java Training_Bridge Labs_Week-4\\src\\Day4\\FileReader\\example.txt";
        String targetWord = "Hi";

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            int count = 0;

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (word.toLowerCase().equals(targetWord.toLowerCase())) {
                        count++;
                    }
                }
            }

            System.out.println("The word '" + targetWord + "' appeared " + count + " times.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
