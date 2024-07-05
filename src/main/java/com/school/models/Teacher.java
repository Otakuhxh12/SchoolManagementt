package com.school.models;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String teacherID;
    private String name;
    private String contactInformation;
    private String subjectExpertise;
    private List<Course> assignedCourses;

    public Teacher(String teacherID, String name, String contactInformation, String subjectExpertise) {
        this.teacherID = teacherID;
        this.name = name;
        this.contactInformation = contactInformation;
        this.subjectExpertise = subjectExpertise;
        this.assignedCourses = new ArrayList<>();
    }

    public void assignHomework(Course course, String homework) {
        // Logic to assign homework
        System.out.println("Homework assigned for course: " + course.getCourseName());
    }

    public void gradeAssignments(Course course) {
        // Logic to grade assignments
        System.out.println("Assignments graded for course: " + course.getCourseName());
    }

    public void viewClassRoster(Course course) {
        // Logic to view class roster
        System.out.println("Class roster for course: " + course.getCourseName());
        for (Student student : course.getStudents()) {
            System.out.println(student.getName());
        }
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getSubjectExpertise() {
        return subjectExpertise;
    }

    public void setSubjectExpertise(String subjectExpertise) {
        this.subjectExpertise = subjectExpertise;
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public void setAssignedCourses(List<Course> assignedCourses) {
        this.assignedCourses = assignedCourses;
    }
}
