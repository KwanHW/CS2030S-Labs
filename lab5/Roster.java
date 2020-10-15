import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Roster extends KeyableMap<Student> {
    public Roster(String key) {
        super(key);
    }

    @Override
    public Roster put(Student s) {
        super.put(s);
        return this;
    }

    public String getGrade(String student,String module,String assessment) {
        return this.get(student)
            .flatMap(x->x.get(module)) // Returns Optional[Module]
            .flatMap(x->x.get(assessment)) //Returns Optional[Assessment]
            .map(Assessment::getGrade) //Returns the grade 
            .orElse(String.format("No such record: %s %s %s",student, module,assessment)); // else return a failed message
    }

    public String getGrade(String[] line) {
        return this.getGrade(line[0],line[1],line[2]);
    }
}

