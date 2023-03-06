public class Metod {
    public static String reverse(String string) {
        {
            char[] charArrOfString = string.toCharArray();
            int end = charArrOfString.length - 1;
            for (int i = 0; i < charArrOfString.length / 2; i++) {
                char temp;
                temp = charArrOfString[end];
                charArrOfString[end] = charArrOfString[i];
                charArrOfString[i] = temp;
                end--;
            }
            return new String(charArrOfString);
        }
    }
    public static String reverse(String string, String dest) {
        int index = string.indexOf(dest);
        if (index > 0) {
            String reversedString = reverse(dest);
            return string.replace(dest, reversedString);
        }
        return "Помилка";
    }
    public static String reverse(String string, int firstIndex, int
            lastIndex) {
        if (firstIndex >= 0 && lastIndex > 0 && lastIndex < string.length()
                && firstIndex < lastIndex) {
            String toReverse = string.substring(firstIndex, lastIndex + 1);
            String reversedString = reverse(toReverse);
            return string.replace(toReverse, reversedString);
        }
        return "помилка";
    }
}
