package Day5.RuntimeBigO;
import java.io.*;
public class FileReadingComparison {
    public static void fileReaderRead(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        while (reader.read() != -1) {}
        reader.close();
    }

    public static void inputStreamReaderRead(String filePath) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath));
        while (reader.read() != -1) {}
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\sreeh\\Downloads\\Capgemini Training\\Java\\Java Training_Bridge Labs_Week-4\\src\\Day4\\FileReader\\example.txt";

        long startFR = System.nanoTime();
        fileReaderRead(filePath);
        long endFR = System.nanoTime();
        long durationFR = (endFR - startFR) / 1_000_000;
        System.out.println("FileReader Time: " + durationFR + " ms");

        long startISR = System.nanoTime();
        inputStreamReaderRead(filePath);
        long endISR = System.nanoTime();
        long durationISR = (endISR - startISR) / 1_000_000;
        System.out.println("InputStreamReader Time: " + durationISR + " ms");
    }
}
