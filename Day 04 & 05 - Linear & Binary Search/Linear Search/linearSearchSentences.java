package Day4.LinearSearch;

public class linearSearchSentences {
    public static void main(String[] args) {
        String[] sentences = {
                "The quick brown fox jumps over the lazy dog.",
                "Hello world!",
                "Java programming is fun.",
                "Learning Linear Search is useful."
        };

        String wordToFind = "Java";
        String result = findSentenceWithWord(sentences, wordToFind);

        System.out.println(result);
    }

    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }
}
