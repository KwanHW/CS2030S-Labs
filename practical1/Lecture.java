public class Lecture extends Lesson {
    public Lecture(String moduleCode,int classID,String venueID,Instructor instructor,int startTime) {
        super(moduleCode,classID,venueID,instructor,startTime,2);
    }

    @Override
    public boolean clashWith(Lesson lesson) {
        if (lesson == this) {
            return true;
        } else if (super.hasSameVenue(lesson)) {
            //Get the end time of this lesson
            return lesson.getStartTime() < super.getEndTime();
        } else {
            return super.hasSameModule(lesson);
        }
    }

    @Override
    public String toString() {
        return String.format("%s L%s @ %s [%s] %d--%d",super.getModuleCode(),super.getClassID(),super.getVenueID(),super.getInstructorName(),super.getStartTime(),super.getEndTime());
    }
}
