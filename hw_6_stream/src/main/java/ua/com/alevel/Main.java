package ua.com.alevel;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void WordCount() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Ваш текст:");

            String string = reader.readLine().toLowerCase()
                    .replaceAll("[^a-zA-Z ]", "")
                    .replaceAll("[^а-яА-Я ]", "")
                    .trim().replaceAll("\\s+", "");

            List<String> word = Arrays.asList(string.split("\\s+"));

            Map<String, Long> procent = word.stream()
                    .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

            List<Map.Entry<String, Long>> sorted = procent.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).toList();
            ;

            long total = sorted.stream().mapToLong(Map.Entry::getValue).sum();
            System.out.println("");
            System.out.println("Слова   Кількість   Процент");

            for (Map.Entry<String, Long> entrance : sorted) {
                String Word = entrance.getKey();
                Long Amount = entrance.getValue();
                double Percent = Amount * 100.0 / total;
                System.out.printf(" %-5s | %8d | %f%% \n", Word, Amount, Percent);
            }

            System.out.println("");
            System.out.println("Загальна кількість без пропусів і знаків" + " " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


