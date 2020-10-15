import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Schedule implements Iterable<Lesson> {
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
                if (l1.hasSameStartTime(l2)) {
                    return (l1.hasSameModule(l2)) ? l1.getClassID() - l2.getClassID():l1.getModuleCode().hashCode() - l2.getModuleCode().hashCode();
                } else {
                    return l1.getStartTime() - l2.getStartTime();
                }
            }
        });
        return new Schedule(newList);
    }

    @Override
    public Iterator<Lesson> iterator() {
        return this.lessonList.iterator();
    }

    @Override
    public String toString() {
        String msg = "";
        Iterator<Lesson> lessonIt = this.lessonList.iterator();
        while (lessonIt.hasNext()) {
            Lesson l = lessonIt.next();
            msg += l.toString();
            if (lessonIt.hasNext()) { 
                msg+="\n";
            }
        }
        return msg;
    }
}

