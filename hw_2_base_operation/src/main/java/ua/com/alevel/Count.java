package ua.com.alevel;
import java.util.HashSet;
public class Count {
    public void countOfLetters(String userLine) {
        StringBuilder alphabetic = new StringBuilder("");
        for (int i = 0; i < userLine.length(); i++) {
            if (Character.isLetter(userLine.charAt(i))) {
                alphabetic.append((userLine.charAt(i)));
            }
        }

        char[] userLineToCharArray = alphabetic.toString().toCharArray();
        HashSet<Character> characterHashSet = new HashSet<>();
        for (char c : userLineToCharArray) {
            characterHashSet.add(c);
        }

        for (Character character : characterHashSet) {
            int occurrence = 0;
            for (char c : userLineToCharArray) {
                if (character == c) {
                    occurrence++;
                }
            }
            System.out.println(character + " -> " + occurrence);
        }
    }
}