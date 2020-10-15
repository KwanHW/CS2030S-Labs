import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class KeyableMap<V extends Keyable> implements Keyable {
    private final String key;
    private final Map<String,V> keyMap;

    public KeyableMap(String key) {
        this(key,new HashMap<String,V>());
    }

    public KeyableMap(String key,Map<String,V> keyMap) {
        this.key = key;
        this.keyMap = keyMap;
    }

    public Optional<V> get(String key) {
        return Optional.ofNullable(this.keyMap.get(key));
    }

    // KeyableMap is MUTABLE
    public KeyableMap<V> put(V item) {
        this.keyMap.put(item.getKey(),item);
        return this;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        List<String> entries = new ArrayList<>();
        this.keyMap.forEach((k,v) -> entries.add(v.toString()));
        return String.format("%s: {%s}",this.getKey(),String.join(", ",entries));
    }
}
    
