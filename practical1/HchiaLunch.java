import java.util.Iterator;

public class HchiaLunch implements Constraint {
    public HchiaLunch() {
    }

    @Override
    public boolean test(Schedule schedule) {
        Iterator<Lesson> lessonIt = schedule.iterator();

        while (lessonIt.hasNext()) {
            Lesson l = lessonIt.next();       
            if (this.isHChia(l.getInstructorName()) && this.duringLunch(l.getStartTime())) {
                return false;
            }
        }
        return true;
    }

    public boolean duringLunch(int time) {
        return 14 <= time && time < 16;
    }

    public boolean isHChia(String name) {
        return name == "hchia";
    }
}
