public class Tutorial extends Lesson {
    public Tutorial(String moduleCode,int classID,String venueID,Instructor instructor,int startTime) {
        super(moduleCode,classID,venueID,instructor,startTime,1);
    }

    @Override
    public boolean clashWith(Lesson lesson) {
        if (lesson == this) {
            return true;
        }

        if (super.hasSameVenue(lesson) && super.hasSameStartTime(lesson)) {
            return true;
        } else if (super.hasSameStartTime(lesson)) {
                return (lesson instanceof Lecture && super.hasSameModule(lesson)) ||  super.hasSameInstructor(lesson);
        } else {
            return (lesson instanceof Lecture) ? super.getStartTime() - lesson.getStartTime() == 1: false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s T%s @ %s [%s] %d--%d",super.getModuleCode(),super.getClassID(),super.getVenueID(),super.getInstructorName(),super.getStartTime(),super.getEndTime());
    }
}
