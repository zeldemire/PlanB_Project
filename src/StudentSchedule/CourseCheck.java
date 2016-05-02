package StudentSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zach on 5/1/16.
 */
public class CourseCheck {

    private List<String> preReqs = new ArrayList<>();
    private List<String> studentPreReqs = new ArrayList<>();
    private Map<String, List<String>> classesWithPreReq = new HashMap<>();
    private List<CourseInfo> studentSchedule = new ArrayList<>();

    public CourseCheck() {
        initPreReq();
        initClassWithPreReq();
    }

    public void addStudentPreReq(String preReq) {
        studentPreReqs.add(preReq);
    }
    private void initPreReq() {
        preReqs.add("174");
        preReqs.add("201");
        preReqs.add("271");
        preReqs.add("274");
        preReqs.add("278");
        preReqs.add("283");
        preReqs.add("383");
        preReqs.add("385");
        preReqs.add("386");
        preReqs.add("448");
    }

    private void initClassWithPreReq() {
        List<String> preReq1 = new ArrayList<>();
        List<String> preReq2 = new ArrayList<>();
        List<String> preReq3 = new ArrayList<>();
        List<String> preReq4 = new ArrayList<>();
        List<String> preReq5 = new ArrayList<>();
        List<String> preReq6 = new ArrayList<>();
        List<String> preReq7 = new ArrayList<>();
        List<String> preReq8 = new ArrayList<>();
        List<String> preReq9 = new ArrayList<>();
        List<String> preReq10 = new ArrayList<>();
        List<String> preReq11 = new ArrayList<>();
        List<String> preReq12 = new ArrayList<>();

        preReq1.add("174");
        classesWithPreReq.put("201", preReq1);
        classesWithPreReq.put("271", preReq1);
        classesWithPreReq.put("471", preReq1);

        preReq2.add("271");
        classesWithPreReq.put("212", preReq2);
        classesWithPreReq.put("278", preReq2);
        classesWithPreReq.put("283", preReq2);

        preReq3.add("274");
        classesWithPreReq.put("385", preReq3);
        classesWithPreReq.put("464", preReq3);
        classesWithPreReq.put("465", preReq3);
        classesWithPreReq.put("486", preReq3);
        classesWithPreReq.put("386", preReq3);
        classesWithPreReq.put("473", preReq3);
        classesWithPreReq.put("474", preReq3);

        preReq4.add("278");
        classesWithPreReq.put("443", preReq4);

        //385
        preReq5.add("385");
        classesWithPreReq.put("485", preReq5);

        //386
        preReq6.add("386");
        classesWithPreReq.put("487", preReq6);

        preReq7.add("448");
        classesWithPreReq.put("449", preReq7);

        //201
        preReq8.add("201");
        classesWithPreReq.put("311", preReq8);
        classesWithPreReq.put("321", preReq8);
        classesWithPreReq.put("322", preReq8);

        preReq9.add("274");
        preReq9.add("278");
        classesWithPreReq.put("381", preReq9);

        preReq10.add("201");
        preReq10.add("274");
        classesWithPreReq.put("448", preReq10);
        classesWithPreReq.put("211", preReq10);

        preReq11.add("283");
        preReq11.add("274");
        classesWithPreReq.put("383", preReq11);
        classesWithPreReq.put("451", preReq11);

        preReq12.add("283");
        preReq12.add("383");
        classesWithPreReq.put("467", preReq12);
    }

    public boolean addClass(CourseInfo course) {
        if (checkPreReq(course) || !classesWithPreReq.containsKey(course.courseName)) {
            if (studentSchedule.isEmpty()) {
                studentSchedule.add(course);
                return true;
            } else if (!studentSchedule.contains(course)) {
                for (int i = 0; i < studentSchedule.size(); i++) {
                    if (!course.checkTime(studentSchedule.get(i))) {
                        studentSchedule.add(course);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkPreReq(CourseInfo course) {
        if (classesWithPreReq.containsKey(course.courseName)){
            for (int i = 0; i < classesWithPreReq.get(course.courseName).size(); i++) {
                if (studentPreReqs.contains(classesWithPreReq.get(course.courseName).get(i))) {
                    return true;
                }
            }
        }
        return false;
    }
    private void removeClass(CourseInfo course) {
        studentSchedule.remove(course);
    }

// TODO add a prereq arrayList method to setup.

    public static void main(String[] args) {
        CourseInfo c1 = new CourseInfo("1", "381", 1, 2, "MWF", "A");
        CourseCheck cc = new CourseCheck();
        cc.addStudentPreReq("201");
        cc.addStudentPreReq("274");

        System.out.println(cc.addClass(c1));
    }
}
