package ua.com.alevel;
import java.util.Scanner;

public class Lessons {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер урока: ");
        int lesson = scanner.nextInt();

        int hours = 9 + ((lesson - 1) * 45 + (lesson - 1) / 2 * 5) / 60;
        int minutes = ((lesson - 1) * 45 + (lesson - 1) / 2 * 5) % 60;

        System.out.println("Урок почнеться в" + " " + hours + " " + minutes);
    }
}