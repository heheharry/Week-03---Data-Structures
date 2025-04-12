package Day4.ChallengeBufferBuilderReaders;
import java.io.*;
public class PerformanceComparison {
    public static void main(String[] args) {
        String str = "hello";
        int times = 1000000;

        // Measure time for StringBuilder
        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        long endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;
        System.out.println("StringBuilder time: " + stringBuilderTime + " ns");

        startTime = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < times; i++) {
            sbf.append(str);
        }
        endTime = System.nanoTime();
        long stringBufferTime = endTime - startTime;
        System.out.println("StringBuffer time: " + stringBufferTime + " ns");

        String filePath = "C:\\Users\\sreeh\\Downloads\\Capgemini Training\\Java\\Java Training_Bridge Labs_Week-4\\src\\Day4\\FileReader\\example.txt";
        try {
            startTime = System.nanoTime();
            int wordCountFileReader = countWordsUsingFileReader(filePath);
            endTime = System.nanoTime();
            long fileReaderTime = endTime - startTime;
            System.out.println("FileReader time: " + fileReaderTime + " ns");
            System.out.println("FileReader word count: " + wordCountFileReader);

            startTime = System.nanoTime();
            int wordCountInputStreamReader = countWordsUsingInputStreamReader(filePath);
            endTime = System.nanoTime();
            long inputStreamReaderTime = endTime - startTime;
            System.out.println("InputStreamReader time: " + inputStreamReaderTime + " ns");
            System.out.println("InputStreamReader word count: " + wordCountInputStreamReader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countWordsUsingFileReader(String filePath) throws IOException {
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            int wordCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            return wordCount;
        }
    }

    public static int countWordsUsingInputStreamReader(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            int wordCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            return wordCount;
        }
    }
}
