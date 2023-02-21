package ua.com.alevel;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CharCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я')) {
                int count = charCount.getOrDefault(c, 0);
                charCount.put(c, count + 1);
            }
        }
        Map<Character, Integer> sortedCharCount = new TreeMap<>(charCount);
        for (Map.Entry<Character, Integer> entry : sortedCharCount.entrySet()) {
            System.out.println("'" + entry.getKey() + "' встречается " + entry.getValue() + " раз");
        }
    }
}