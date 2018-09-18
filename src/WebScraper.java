import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;

public class WebScraper {
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    public static int countWords(String text) {
        int wordCount = 0;
        Scanner textScanner = new Scanner(text);
        textScanner.useDelimiter("[ ,!?.]+");
        while (textScanner.hasNext()) {
            wordCount++;
            textScanner.next();
        }
        return wordCount;
    }

    public static int countAWord(String text, String word) {
        int count = 0;
        Scanner textScanner = new Scanner(text);
        textScanner.useDelimiter("[ ,!?.]+");
        while (textScanner.hasNext()) {
            if (textScanner.next().toLowerCase().equals(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public static int countUniqueWords(String text) {
        int count = 0;
        Scanner textScanner = new Scanner(text);
        textScanner.useDelimiter("[ ,!?.]+");
        ArrayList<String> words = new ArrayList<>();
        String word;
        while (textScanner.hasNext()) {
            word = textScanner.next();
            if (!words.contains(word)) {
                count++;
                words.add(word);
            }
        }
        return count;
    }

    public static void main(String[] unused) {
        System.out.println(urlToString("http://erdani.com/tdpl/hamlet.txt"));
        System.out.println(countWords(urlToString("http://erdani.com/tdpl/hamlet.txt")));
        System.out.println(countAWord(urlToString("http://erdani.com/tdpl/hamlet.txt"), "Prince"));
        System.out.println(countUniqueWords(urlToString("http://erdani.com/tdpl/hamlet.txt")));
    }
}
