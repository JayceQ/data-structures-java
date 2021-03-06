import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileUtil {

    public static boolean readFile(String fileName, ArrayList<String> words) {
        if (fileName == null || words == null) {
            System.err.println("fileName is null or words is null.");
            return false;
        }

        Scanner scanner;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                System.err.println("File is not exist");
                return false;
            }
        } catch (IOException e) {
            System.err.println("Cannot open " + fileName);
            return false;
        }

        //简单分词
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();
            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); ) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    i++;
                }
            }
        }
        return true;
    }

    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                return i;
        }
        return s.length();
    }
}
