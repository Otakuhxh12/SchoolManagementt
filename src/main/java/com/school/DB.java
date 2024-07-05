package com.school;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    public static void initializeDatabase() {
        try (Connection conn = Config.getConnection();
             Statement stmt = conn.createStatement()) {
            // Create database if not exists
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS school_management_system");

            // Use database
            stmt.executeUpdate("USE school_management_system");

            // Create tables if not exists
            createStudentsTable(stmt);
            createTeachersTable(stmt);
            createCoursesTable(stmt);
            createClassroomsTable(stmt);
            createGradingSystemTable(stmt);
            createStudentCoursesTable(stmt);

            System.out.println("Database schema initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createStudentsTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students ("
                + "studentID VARCHAR(10) PRIMARY KEY,"
                + "name VARCHAR(100) NOT NULL,"
                + "dateOfBirth DATE,"
                + "contactInformation VARCHAR(100)"
                + ")");
    }

    private static void createTeachersTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS teachers ("
                + "teacherID VARCHAR(10) PRIMARY KEY,"
                + "name VARCHAR(100) NOT NULL,"
                + "contactInformation VARCHAR(100),"
                + "subjectExpertise VARCHAR(100)"
                + ")");
    }

    private static void createCoursesTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS courses ("
                + "courseID VARCHAR(10) PRIMARY KEY,"
                + "courseName VARCHAR(100) NOT NULL,"
                + "teacherID VARCHAR(10),"
                + "schedule VARCHAR(100),"
                + "FOREIGN KEY (teacherID) REFERENCES teachers(teacherID)"
                + ")");
    }

    private static void createClassroomsTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS classrooms ("
                + "classroomID VARCHAR(10) PRIMARY KEY,"
                + "roomNumber VARCHAR(10) NOT NULL,"
                + "capacity INT"
                + ")");
    }

    private static void createGradingSystemTable(Statement stmt) throws SQLException {
        // Assuming grades, transcripts, exams are managed within a single table for simplicity
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS grading_system ("
                + "studentID VARCHAR(10) PRIMARY KEY,"
                + "grades VARCHAR(10),"
                + "transcripts VARCHAR(100),"
                + "exams VARCHAR(100)"
                + ")");
    }

    private static void createStudentCoursesTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS student_courses ("
                + "studentID VARCHAR(10),"
                + "courseID VARCHAR(10),"
                + "FOREIGN KEY (studentID) REFERENCES students(studentID),"
                + "FOREIGN KEY (courseID) REFERENCES courses(courseID),"
                + "PRIMARY KEY (studentID, courseID)"
                + ")");
    }
}
