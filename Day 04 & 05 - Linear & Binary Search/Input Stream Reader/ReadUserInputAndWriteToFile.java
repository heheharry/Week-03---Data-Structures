package Day4.InputStreamReader;
import java.io.*;
public class ReadUserInputAndWriteToFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\sreeh\\Downloads\\Capgemini Training\\Java\\Java Training_Bridge Labs_Week-4\\src\\Day4\\FileReader\\example.txt";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileWriter fileWriter = new FileWriter(filePath, true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            System.out.println("Enter text (type 'exit' to stop):");

            String userInput;
            while (true) {
                userInput = reader.readLine();
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.write("\n" + userInput);
                writer.newLine();
            }

            System.out.println("User input has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
