package ua.com.alevel;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Count {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(input);

        int sum = 0;
        while (matcher.find()) {
            int number = Integer.parseInt((matcher.group()));
            sum += number;
        }

        System.out.println("Сумма найденных чисел: " + sum);
    }
}