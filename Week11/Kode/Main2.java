package cphbusiness.ufo.letterfrequencies;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "C:/Users/Andreas Vikke/OneDrive/Documents/Skole/SoftwareUdvikling/CPH-Business-UFO/Week13/letterfrequencies/src/main/resources/FoundationSeries.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName), 16384);
        long freq[] = new long[256];
        tallyChars(reader, freq);
        print_tally(freq);
    }

    public static double Run() throws FileNotFoundException, IOException {
        String fileName = "C:/Users/Andreas Vikke/OneDrive/Documents/Skole/SoftwareUdvikling/CPH-Business-UFO/Week13/letterfrequencies/src/main/resources/FoundationSeries.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName), 16384);
        long freq[] = new long[256];
        tallyChars(reader, freq);
        print_tally(freq);
        return 1;
    }

    private static void tallyChars(Reader reader, long[] freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            freq[b]++;
        }
    }

    private static void print_tally(long[] freq) {
        int dist = 'a' - 'A';
        Map<Character, Long> upperAndlower = new LinkedHashMap();
        for (Character c = 'A'; c <= 'Z'; c++) {
            upperAndlower.put(c, freq[c] + freq[c + dist]);
        }

        Map<Character, Long> sorted = upperAndlower
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
    }
}