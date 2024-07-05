package com.school.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradingSystem {
    private Map<Student, Map<Course, String>> grades;

    public GradingSystem() {
        this.grades = new HashMap<>();
    }

    public void recordExamResults(Student student, Course course, String grade) {
        grades.putIfAbsent(student, new HashMap<>());
        grades.get(student).put(course, grade);
        System.out.println("Recorded grade " + grade + " for student " + student.getName() + " in course " + course.getCourseName());
    }

    public String calculateGPA(Student student) {
        // Logic to calculate GPA
        Map<Course, String> studentGrades = grades.get(student);
        if (studentGrades == null) {
            return "No grades available for student.";
        }

        int totalPoints = 0;
        int totalCourses = studentGrades.size();

        for (String grade : studentGrades.values()) {
            totalPoints += gradeToPoints(grade);
        }

        return "GPA: " + ((double) totalPoints / totalCourses);
    }

    public void issueTranscripts(Student student) {
        // Logic to issue transcripts
        System.out.println("Issuing transcript for student " + student.getName());
    }

    private int gradeToPoints(String grade) {
        switch (grade) {
            case "A": return 4;
            case "B": return 3;
            case "C": return 2;
            case "D": return 1;
            case "F": return 0;
            default: return 0;
        }
    }
}
