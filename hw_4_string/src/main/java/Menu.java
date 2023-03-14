import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вітаю в СтрінгУтілсах");
        System.out.println("Приклад кежульного реверсу");
        System.out.println("Ulibok tebe Ded makar --> rakam deD ebet kobilU");
        System.out.println("Виберіть опцію:");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            crud(bf, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("кежуал реверс натисніть ->  1");
        System.out.println("строковий реверс натисніть -> 2");
        System.out.println("реверс по порядковому індексу натисніть -> 3");
        System.out.println("вихід натисніть 0");
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> usualReverse(reader);
            case "2" -> substringReverse(reader);
            case "3" -> reverseWithIndexes(reader);
            case "0" -> System.exit(0);
        }
        menu();
    }

    private void substringReverse(BufferedReader reader) throws IOException {
        System.out.println("Введіть строку");
        String userString = reader.readLine();
        System.out.println("виберіть частину яку перевернути");
        String substringToReverse = reader.readLine();
        System.out.println(Method.reverse(userString, substringToReverse));
    }

    public void usualReverse(BufferedReader reader) throws IOException {
        System.out.println("Введіть строку");
        System.out.println(Method.reverse(reader.readLine()));
    }

    public void reverseWithIndexes(BufferedReader reader) throws IOException {
        System.out.println("Введіть строку");
        String userString = reader.readLine();
        System.out.println("З якого індекса ");
        int firstIndex = Integer.parseInt(reader.readLine());
        System.out.println("По який ");
        int secondIndex = Integer.parseInt(reader.readLine());
        System.out.println(Method.reverse(userString, firstIndex, secondIndex));
    }
}
