public class Tutorial extends Lesson {
    public Tutorial(String moduleCode,int classID,String venueID,Instructor instructor,int startTime) {
        super(moduleCode,classID,venueID,instructor,startTime,1);
    }

    @Override
    public boolean clashWith(Lesson lesson) {
        if (lesson == this) {
            return true;
        } else if (super.hasSameModule(lesson) && lesson instanceof Tutorial) {
            // Can be arrange concurrently if lesson and instructor is different
            return (super.hasSameVenue(lesson) ||  super.hasSameInstructor(lesson));
        } else {
            return lesson.getStartTime() < super.getEndTime() ;
        }
    }

    @Override
    public String toString() {
        return String.format("%s T%s @ %s [%s] %d--%d",super.getModuleCode(),super.getClassID(),super.getVenueID(),super.getInstructorName(),super.getStartTime(),super.getEndTime());
    }
}
