package Day4.StringBuffer;

public class ConcatenateStrings {
    public static String concatenateStrings(String[] strings) {
        StringBuffer sb = new StringBuffer();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strings = {"Hello", " ", "World", "!"};
        String result = concatenateStrings(strings);
        System.out.println(result);
    }
}
