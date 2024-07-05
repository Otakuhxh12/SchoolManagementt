package com.school;

import com.school.models.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Create instances of Student and Teacher
        Student student = new Student("S001", "John Doe", new Date(), "john.doe@example.com");
        Teacher teacher = new Teacher("T001", "Jane Smith", "jane.smith@example.com", "Math");

        // Create a Course and enroll the Student
        Course course = new Course("C001", "Mathematics", teacher, "MWF 10:00-11:00");
        student.enrollInCourse(course);
        course.addStudent(student);
        teacher.assignHomework(course, "Homework 1");

        // Output to confirm the actions
        System.out.println("Student enrolled in course: " + course.getCourseName());

        // Test viewing class roster
        teacher.viewClassRoster(course);

        // Test dropping the course
        student.dropCourse(course);
        course.removeStudent(student);
        System.out.println("Student dropped from course: " + course.getCourseName());

        // Test paying fees
        student.payFees(500.0);
        System.out.println("Fees paid: " + student.getFeesPaid());

        // Test viewing grades (assuming grades are set)
        student.viewGrades();

        // Test grading assignments
        teacher.gradeAssignments(course);

        // Create Classroom and test its methods
        Classroom classroom = new Classroom("R101", "101", 30);
        classroom.assignTeacher(teacher);
        classroom.scheduleClasses("MWF 10:00-11:00");
        classroom.viewAvailability();

        // Test GradingSystem methods
        GradingSystem gradingSystem = new GradingSystem();
        gradingSystem.recordExamResults(student, course, "A");
        System.out.println(gradingSystem.calculateGPA(student));
        gradingSystem.issueTranscripts(student);
    }
}
