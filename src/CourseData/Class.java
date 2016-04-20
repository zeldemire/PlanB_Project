package CourseData;

import CourseData.Class;
import Main.Constants;

/**
 * Class to handle the creation of classes.
 */
public class Class implements Comparable<Class> {

	Course course; //Course associated with the class.
	public String CRN_Number; //Course registration number
	String section; //Section of the class.
	public int startTime; //Start time of the class.
	public int endTime; //End time of the class.
	Instructor instructor; //INstructor of the class.
	
	public boolean hasConflict = false; //Boolean that determines if there is a conflicting final.
	
	
	private int meetingDays; //Days that the classes meet.

	Category category; //Category of the class.

	/**
	 * Constructor for the Class.
	 * @param course Course of the class.
	 * @param instructor Instructor of the class.
	 * @param CRN_Number Course registration number for the specific class.
	 * @param section Section of the class.
	 * @param startTime Time that the class starts.
	 * @param endTime Time that the class ends.
	 * @param days Days that the class meets.
	 */
	public Class(Course course, Instructor instructor, String CRN_Number,
			String section, int startTime, int endTime, String days) {

		this.course = course;
		this.instructor = instructor;
		this.CRN_Number = CRN_Number;
		this.section = section;
		this.startTime = startTime;
		this.endTime = endTime;
		this.meetingDays = 0;

		addMeetingDays(days);

		if (this.course == null || this.instructor == null
				|| this.CRN_Number == "" || this.section == ""
				|| this.startTime < 0 || this.startTime > 2400
				|| this.endTime < 0 || this.endTime > 2400
				|| this.endTime <= this.startTime || days == "") {

			System.out
					.println("Error: Invalid or blank input sent to a Class constructor");
			throw new IllegalArgumentException();

		}

	}
	
	/**
	 * Accessor method for the course.
	 * @return Course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Mutator method for the categories.
	 * @param cat Category to be set.
	 */
	public void setCategory(Category cat) {
		this.category = cat;
	}

	/**
	 * Accessor method for the category.
	 * @return Category
	 */
	public Category getCategory() {
		return this.category;
	}
	
	/**
	 * Checks if the times and days of the category matches with the class.
	 * @param cat
	 * @return
	 */
	public boolean matches(Category cat){
		return cat.matches(this);
		
	}
	

	// Note: Equals does not include meeting days. A class is still equal if
	// they have all the same properties but have different meeting days
	/**
	 * Checks to see if two classes are the same class.
	 */
	public boolean equals(Object obj) {

		if (obj instanceof Class) {

			Class test = (Class) obj;
			return test.CRN_Number.equalsIgnoreCase(this.CRN_Number);

		} else {

			return false;
		}
	}

	/**
	 * CompareTo method to compare classes.
	 */
	public int compareTo(Class o) {

//		return this.toString().toLowerCase()
//				.compareTo(o.toString().toLowerCase());
		return this.startTime - o.startTime;
		
	}

//	/**
//	 * ToString methods that returns a string without meeting days.
//	 * @return String without meeting days.
//	 */
//	public String toStringWithOutMeetingDays() {
//
//		return course.shortName + this.section + " from " + this.startTime + " to " + this.endTime + " ("
//				+ this.CRN_Number + ")";
//
//	}

	/**
	 * ToString method that returns all the information of the class.
	 */
	public String toString() {

		return course.toString(this.section) + " with " + instructor.toString()
				+ " from " + this.startTime + " to " + this.endTime + " on "
				+ this.getMeetingDays() + " with final at "+ getFinalInfo() + "(" + this.CRN_Number + ")";

	}
	
	/**
	 * ToString method that returns all information except for the infor of the final.
	 * @return
	 */
	public String toStringReportHTMLRow() {

		return ((this.hasConflict)?("<tr style=\"font-weight:bold;\"><td>***</td>"):("<tr><td></td>")) +
				"<td>" + course.shortName + this.section + "</td>" +
				"<td> " + Constants.timeToString(this.startTime) + " - " + Constants.timeToString(this.endTime) + " </td> " +
				"<td>" + this.getMeetingDays() + "</td>" +
				"<td>" + this.instructor.lastName + ", " + this.instructor.firstName + "</td>" +
				"<td>(" + this.CRN_Number + ")</td></tr>";

	}
	
	/**
	 * Gets the final meeting times of the class if the class has a category.
	 * @return String of the time and day the final for the class is unless it does not have a category.
	 */
	private String getFinalInfo(){
		
		if(this.category != null)
			return this.getCategory().finalTime+" on "+ this.getCategory().finalDay;
		else
			return "NO FINAL SET";
	}
	
	/**
	 * Checks to see of two meeting times are at the same time
	 */
	public boolean matchesMeetingTime(int m){

		return (this.meetingDays & m) == this.meetingDays;
		
	}

	
	/**
	 * Adds the days that the classes meets in the constructor.
	 * @param days String of days that the classes meets.
	 */
	public void addMeetingDays(String days) {

		for (int i = 0; i < days.length(); i++) {

			char x = days.charAt(i);

			switch (x) {

			case 'M':
				this.meetingDays = this.meetingDays | 16;
				break;
			case 'T':
				this.meetingDays = this.meetingDays | 8;
				break;
			case 'W':
				this.meetingDays = this.meetingDays | 4;
				break;
			case 'R':
				this.meetingDays = this.meetingDays | 2;
				break;
			case 'F':
				this.meetingDays = this.meetingDays | 1;
				break;
			default:
				continue;
			}

		}
	}

	/**
	 * Gets the meeting days of the class.
	 * @return String of of the meeting days of the class.
	 */
	public String getMeetingDays() {

		String ret = "";

		if( (this.meetingDays & 16) == 16){
			ret += "M";
		}
		if( (this.meetingDays & 8) == 8){
			ret += "T";
		}
		if( (this.meetingDays & 4) == 4){
			ret += "W";
		}
		if( (this.meetingDays & 2) == 2){
			ret += "R";
		}
		if( (this.meetingDays & 1) == 1){
			ret += "F";
		}
		
		return ret;
		//return Integer.toBinaryString(this.meetingDays);

	}

	//Main method to test the Class class.
	public static void main(String arg[]) {
		
		

		Subject testSub = new Subject("CSE");
		Subject testSub2 = new Subject("cSe");

		Course testCourse = new Course(testSub, "Learning about Programming",
				"101");

		Course testCourse2 = new Course(testSub2, "Learning about PrOgramming",
				"101");

		Course testCourse3 = new Course(testSub, "Learning about Programming",
				"102");

		Class testClass = new Class(testCourse, new Instructor("Krump, Norm"),
				"555111", " AB", 1200, 1330, "THJXHTX. 9");



	}

}