
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
public class Dictionary<K, V> {
    private final Hashtable<K, V> hashtable;
    //Hashtable — это структура данных для хранения пар ключей и их значений,
    //основанная на хешировании и реализации интерфейса Map.

    public Dictionary() {
        hashtable = new Hashtable<K, V>();
    }

    public int size() {
        return hashtable.size();
    }

    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    public boolean containsKey(K key) {
        return hashtable.containsKey(key);
    }

    public boolean containsValue(V value) {
        return hashtable.containsValue(value);
    }

    public V get(K key) {
        return hashtable.get(key);
    }

    public boolean put(K key, V value) {
        if (hashtable.containsKey(key)) {
            return false;
        } else {
            hashtable.put(key, value);
            return true;
        }
    }

    public boolean remove(K key) {
        if (hashtable.containsKey(key)) {
            hashtable.remove(key);
            return true;
        } else {
            return false;
        }
    }

    public boolean putAll(Dictionary<K, V> dictionary) {
        boolean bool = false;
        for (K key : dictionary.keySet()) {
            if (!hashtable.containsKey(key)) {
                hashtable.put(key, dictionary.get(key));
                bool = true;
            }
        }
        return bool;
    }

    public boolean clear() {
        if (hashtable.isEmpty()) {
            return false;
        } else {
            hashtable.clear();
            return true;
        }
    }

    public Set<K> keySet() {
        return hashtable.keySet();
    }

    public Collection<V> values() {
        return hashtable.values();
    }

    @Override
    public String toString() {
        return "Dictionary {" +
                "hashtable = " + hashtable +
                '}';
    }
}