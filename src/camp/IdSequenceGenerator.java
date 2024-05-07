package camp;

import java.util.HashMap;
import java.util.Map;

public class IdSequenceGenerator {
    private Map<String, Integer> indexMap = new HashMap<>();

    public String generate(String type) {
        indexMap.put(type, indexMap.getOrDefault(type, 0) + 1);
        return type + indexMap.get(type);
    }
}
