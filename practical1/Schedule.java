import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Schedule {
    private final List<Lesson> lessonList;

    public Schedule() {
        this.lessonList = new ArrayList<Lesson>();
    }

    public Schedule(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public Schedule add(Lesson lesson) {
        // Check if clashes with any lesson
        for (Lesson l : this.lessonList) {
            // If there is a clash just return object as it is
            if (l.clashWith(lesson)) {
                return this;
            }
        }

        List<Lesson> newList = new ArrayList<>(this.lessonList);
        newList.add(lesson);
        newList.sort(new Comparator<Lesson>() {
            public int compare(Lesson l1,Lesson l2) {
                if (l1.getStartTime() == l2.getStartTime()) {
                    return -1;
                }else if (l1.getStartTime() < l2.getStartTime()) {
                    return -1;
                } else {
                    return 1;
                } 
            }
        });
        return new Schedule(newList);
    }

    @Override
    public String toString() {
        String msg = "";
        for (Lesson l : this.lessonList) {
            msg += l.toString();
            msg += "\n";
        }

        return msg;
    }
}

