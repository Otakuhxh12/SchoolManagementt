package com.school.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student {
    private String studentID;
    private String name;
    private Date dateOfBirth;
    private String contactInformation;
    private List<Course> enrolledCourses;
    private double feesPaid;

    public Student(String studentID, String name, Date dateOfBirth, String contactInformation) {
        this.studentID = studentID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.contactInformation = contactInformation;
        this.enrolledCourses = new ArrayList<>();
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public void viewGrades() {
        // Logic to view grades
        System.out.println("Viewing grades for: " + name);
        for (Course course : enrolledCourses) {
            System.out.println("Grades for course: " + course.getCourseName());
            // Assuming course has a method to get grades for this student
            // System.out.println(course.getGrades(this));
        }
    }

    public void payFees(double amount) {
        feesPaid += amount;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public double getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(double feesPaid) {
        this.feesPaid = feesPaid;
    }
}
