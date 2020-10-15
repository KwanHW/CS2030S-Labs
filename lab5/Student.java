import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Student extends KeyableMap<Module> {
    public Student(String key) {
        super(key);
    }

    @Override
    public Student put(Module m) {
        super.put(m);
        return this;
    }
}

