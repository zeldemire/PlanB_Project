package StudentSchedule;

/**
 * Created by Zach on 5/1/16.
 */
public class CourseInfo {
    public String courseName;
    private String courseNumber;
    private int startTime;
    private int endTime;
    private String days;
    private String section;

    public CourseInfo(String courseNumber, String courseName, int startTime, int endTime, String
            days, String section) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.section = section;
    }

    /**
     * Checks to see if the class names match.
     * @param course course to check the class name.
     * @return true if the course names are the same.
     */
    public boolean checkClassName(CourseInfo course) {
        return this.courseName.equals(course.courseName);
    }

    /**
     * Checks to see if the classes overlap in times.
     * @param course course to check against other courses.
     * @return true if the classes overlap, false if not.
     */
    public boolean checkTime(CourseInfo course) {
        if (checkDays(course.days)) {
            if (this.startTime <= course.startTime)
                if (course.startTime <= this.endTime) return true;
            if (this.startTime <= course.endTime)
                if (course.endTime <= this.endTime) return true;
        }
        return false;
    }

    /**
     * Checks to see if classes meet on the same day. If they don't meet on the same day there is
     * no need to check the times.
     * @param days the meeting days for the given class.
     * @return true if the classes meet on the same day fales if not.
     */
    private boolean checkDays(String days) {
        for (int i = 0; i < days.length(); i++) {
            for (int j = 0; j < this.days.length(); j++) {
                if (this.days.charAt(j) == days.charAt(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean isEqual= false;

        if (object != null && object instanceof CourseInfo)
        {
            isEqual = (this.courseName == ((CourseInfo) object).courseName);
        }

        return isEqual;
    }
    public static void main(String[] args) {
    }
}
