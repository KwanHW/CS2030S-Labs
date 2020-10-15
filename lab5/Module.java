import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Module extends KeyableMap<Assessment> {
    public Module(String key) {
       super(key); 
    }

    @Override
    public Module put(Assessment a) {
        super.put(a);
        return this;
    }
}

