import java.io.FileReader;
import java.io.FileWriter;

public class Pareser {
    private static char[] string = new char[27];
    private static char currentChar;

    public static void main(String args[]) throws Exception {

        FileReader fr = new FileReader("index.html");
        try {
            FileWriter fw = new FileWriter("output.html");
            int i;
            while ((i = fr.read()) != -1) {
                currentChar = (char) i;
                pushForward();

                if (string[0] != '\u0000') {
                    String current = new String(string);
                    String snippet = current.substring(2);
                    if ((string[0] != '.' && string[0] != '!' && string[1] != '>') && snippet.equals("</p>\n<p " +
                            "class=\"calibre1" +
                            "\">")) {
                        fw.write(string[0]);
                        fw.write(string[1]);
                        fw.write(' ');
                        string = new char[27];
                    } else {
                        fw.write(string[0]);
                    }
                }
            }
            fw.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        fr.close();
    }

    public static void pushForward() {
        for (int i = 1; i < string.length; i++) {
            string[i - 1] = string[i];
        }
        string[string.length - 1] = currentChar;
    }
}
