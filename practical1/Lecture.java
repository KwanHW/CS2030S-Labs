public class Lecture extends Lesson {
    public Lecture(String moduleCode,int classID,String venueID,Instructor instructor,int startTime) {
        super(moduleCode,classID,venueID,instructor,startTime,2);
    }

    public boolean hasOverlap(Lesson lesson) {
        /*
        if (super.getEndTime() - lesson.getStartTime() == 1) {
            return true;
        } else {  
            return (lesson instanceof Lecture) ?
                super.getStartTime() - lesson.getStartTime() == 1:
                false;
        }
        */
        return (super.getEndTime() - lesson.getStartTime() == 1) ? true :
                (lesson instanceof Lecture) ? super.getStartTime() - lesson.getStartTime() == 1 :false;
    }

    @Override
    public boolean clashWith(Lesson lesson) {
        if (lesson == this) {
            return true;
        } 
        
        if (super.hasSameVenue(lesson) && super.hasSameStartTime(lesson)) {
            return true;
        } else if (super.hasSameVenue(lesson)) {
            return this.hasOverlap(lesson);
        } else if (super.hasSameStartTime(lesson)) {
            return super.hasSameModule(lesson);
        } else {
            return this.hasOverlap(lesson) && super.hasSameModule(lesson);
        }
    }

    @Override
    public String toString() {
        return String.format("%s L%s @ %s [%s] %d--%d",super.getModuleCode(),super.getClassID(),super.getVenueID(),super.getInstructorName(),super.getStartTime(),super.getEndTime());
    }
}
