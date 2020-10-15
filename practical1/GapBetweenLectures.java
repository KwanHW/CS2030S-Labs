import java.util.Iterator;

public class GapBetweenLectures implements Constraint {
    public GapBetweenLectures() {
    }

    @Override
    public boolean test(Schedule schedule) {
        for (Lesson l1 : schedule) {
            if (l1 instanceof Tutorial) {
                continue;
            }
            int endTime = l1.getEndTime();
            String l1ocation = l1.getVenueID();

            for (Lesson l2 : schedule) {
                if (l2 instanceof Tutorial || !l1.hasSameVenue(l2) || l2 == l1) {
                    continue;
                }

                if (!this.hasGap(endTime, l2.getStartTime())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasGap(int endTime, int startTime) {
        return startTime - endTime != 0;
    }
}
