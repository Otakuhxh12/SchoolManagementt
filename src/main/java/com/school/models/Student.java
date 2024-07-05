package com.school.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.school.Config;
import java.sql.SQLException;
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
        saveEnrollment(course);
    }

    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
        removeEnrollment(course);
    }



    public void viewGrades() {
        // Logic to view grades
//        System.out.println("Viewing grades for: " + name);
        for (Course course : getEnrolledCoursesFromDB()) {
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

    private void saveEnrollment(Course course) {
        String sql = "INSERT INTO student_courses (studentID, courseID) VALUES (?, ?)";
        try (Connection conn = Config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.studentID);
            pstmt.setString(2, course.getCourseID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeEnrollment(Course course) {
        String sql = "DELETE FROM student_courses WHERE studentID = ? AND courseID = ?";
        try (Connection conn = Config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.studentID);
            pstmt.setString(2, course.getCourseID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Course> getEnrolledCoursesFromDB() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.courseID, c.courseName, c.teacherID, c.schedule, t.name AS teacherName, t.contactInformation, t.subjectExpertise " +
                "FROM courses c " +
                "JOIN student_courses sc ON c.courseID = sc.courseID " +
                "JOIN teachers t ON c.teacherID = t.teacherID " +
                "WHERE sc.studentID = ?";
        try (Connection conn = Config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.studentID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getString("teacherID"), rs.getString("teacherName"),
                        rs.getString("contactInformation"), rs.getString("subjectExpertise"));
                Course course = new Course(rs.getString("courseID"), rs.getString("courseName"), teacher, rs.getString("schedule"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }


}
