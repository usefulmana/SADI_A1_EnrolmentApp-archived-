package sadi.course;

import sadi.cor.Chain;

import java.util.ArrayList;
import java.util.List;

public class CourseListA implements CourseList, Chain {
    private String semester = "A";
    private static List<Course> courseList = new ArrayList<>();
    private static CourseListA INSTANCE = new CourseListA();
    private Chain nextInChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void addCourse(List<Course> courses){
        List<Course> tempList = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getAvailability().contains("A")) {
                courseList.add(courses.get(i));
            } else {
                tempList.add(courses.get(i));
            }
        }
        nextInChain.addCourse(tempList);
    }

    private CourseListA(){}

    public static CourseListA getINSTANCE() {
        return INSTANCE;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public static void printCourseList() {
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(courseList.get(i).getCourseId() + " - " + courseList.get(i).getCourseName() + " - " +
                    courseList.get(i).getCourseId());
        }
    }

    public static Course courseSearch (String courseID){
        for (int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseId().equals(courseID))
            {
                return courseList.get(i);
            }
        }
        return null;
    }

    @Override
    public void removeCourse(List<Course> courses, String courseID) {
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getAvailability().contains(this.semester) && courses.get(i).getAvailability().equals(courseID))
            {
                courseList.remove(courses.get(i));
            }
        }
    }

    @Override
    public String toString() {
        return "CourseListA{" +
                "courseList=" + courseList +
                '}';
    }
}