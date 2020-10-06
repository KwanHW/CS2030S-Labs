public abstract class Lesson {
    private final String moduleCode;
    private final int classID;
    private final String venueID;
    private final int startTime;
    private final int duration;
    private final Instructor instructor;

    public Lesson(String moduleCode,int classID,String venueID,Instructor instructor, int startTime,int duration) {
        this.moduleCode = moduleCode;
        this.classID = classID;
        this.venueID = venueID;
        this.instructor = instructor;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public int getClassID() {
        return this.classID;
    }

    public String getVenueID() {
        return this.venueID;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public String getInstructorName() {
        return this.instructor.getName();
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.startTime + this.duration;
    }

    public boolean hasSameInstructor(Lesson lesson) {
        return this.instructor.equals(lesson.getInstructor());
    }

    public boolean hasSameModule(Lesson lesson) {
        return this.moduleCode == lesson.getModuleCode();
    }

    public boolean hasSameVenue(Lesson lesson) {
        return this.venueID == lesson.getVenueID();
    }

    public abstract boolean clashWith(Lesson lesson);

}
