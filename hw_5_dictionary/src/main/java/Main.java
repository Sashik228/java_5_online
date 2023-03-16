import java.util.Collection;
public class Main {
    public static void main(String [] Args){
        Dictionary<String, Integer> dictionary = new Dictionary<String, Integer>();
        Dictionary<String, Integer> dictionary2 = new Dictionary<String, Integer>();

        dictionary.put("one", 1);
        dictionary.put("two", 2);
        dictionary.put("three", 3);

        System.out.print("1)");
        System.out.println("\tSize of dictionary: " + dictionary.size());
        System.out.println();

        System.out.print("2)");
        System.out.println("\tContains key 'two': " + dictionary.containsKey("two"));
        System.out.println();

        System.out.print("3)");
        System.out.println("\tContains value 4: " + dictionary.containsValue(4));
        System.out.println();

        System.out.print("4)");
        System.out.println("\tValue for key 'one': " + dictionary.get("one"));
        System.out.println("\tValue for key 'two': " + dictionary.get("two"));
        System.out.println();

        dictionary.remove("two");

        dictionary2.putAll(dictionary);
        System.out.print("5)");
        System.out.println("\tdictionary = " + dictionary);
        System.out.println("\tdictionary2 = " + dictionary2);
        System.out.println();

        Collection<Integer> values = dictionary.values();
        System.out.print("6)");
        for (Integer value : values) {
            System.out.println("\tValue: " + value);
        }
        System.out.println();
        dictionary.clear();
        System.out.print("7)");
        System.out.println("\tdictionary = " + dictionary);
        System.out.println("\tDictionary is empty: " + dictionary.isEmpty());
        System.out.println();

        dictionary.put("one", 1);
        dictionary.put("two", 2);
        dictionary.put("three", 3);
        System.out.print("8)");
        System.out.println("\tdictionary = " + dictionary);
        System.out.println();

        System.out.print("9)");
        System.out.println("\tDictionary is empty: " + dictionary.isEmpty());
        System.out.println("\tDictionary 2 is empty: " +dictionary2.isEmpty());

    }

}
