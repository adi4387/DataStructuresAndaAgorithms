package cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MyCache<K, V> {

    private final int size;
    private final Map<K, V> cache = new LinkedHashMap<>();

    public MyCache(int size) {
        this.size = size;
    }

    public V add(K key, V value) {
        if(isCacheFull()) {
            evict();
        }
        return cache.put(key, value);
    }

    private boolean isCacheFull() {
        return cache.size() > size;
    }

    public V get(K key) {
        return cache.get(key);
    }

    public V remove(K key) {
        return cache.remove(key);
    }

    private V evict() {
        Set<K> entries = cache.keySet();
        return cache.remove(entries.iterator().next());
    }
}
