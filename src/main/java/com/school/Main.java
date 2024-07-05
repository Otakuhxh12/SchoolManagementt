package com.school;

import com.school.models.*;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DB.initializeDatabase();
//        for (int i = 1; i <= 5; i++) {
//            addNewStudent("S00" + i, "Student " + i, "200" + i + "-01-01", "student" + i + "@example.com");
//            addNewTeacher("T00" + i, "Teacher " + i, "teacher" + i + "@example.com", "Subject " + i);
//            addNewCourse("C00" + i, "Course " + i, "T00" + i, "Schedule " + i);
//            addNewClassroom("R10" + i, "10" + i, 20 + i);
//            addNewGradingSystemEntry("S00" + i);
//        }

        try (Connection conn = Config.getConnection()) {
            // Test methods for each model class
//            testStudentFunctionality(conn, "C001", "S001");
//            testStudentFunctionality(conn, "C002", "S001");
            testStudentFunctionality(conn, "C004", "S002");

//            testTeacherFunctionality(conn, "C001");
//            testCourseFunctionality(conn);
//            testClassroomFunctionality(conn, "T001");
//            testGradingSystemFunctionality(conn, "C001");
        } catch (SQLException e) {
            e.printStackTrace();
        }




//        // Create instances of Student and Teacher
//        Student student = new Student("S001", "John Doe", new Date(), "john.doe@example.com");
//        Teacher teacher = new Teacher("T001", "Jane Smith", "jane.smith@example.com", "Math");
//
//        // Create a Course and enroll the Student
//        Course course = new Course("C001", "Mathematics", teacher, "MWF 10:00-11:00");
//        student.enrollInCourse(course);
//        course.addStudent(student);
//        teacher.assignHomework(course, "Homework 1");
//
//        // Output to confirm the actions
//        System.out.println("Student enrolled in course: " + course.getCourseName());
//
//        // Test viewing class roster
//        teacher.viewClassRoster(course);
//
//        // Test dropping the course
//        student.dropCourse(course);
//        course.removeStudent(student);
//        System.out.println("Student dropped from course: " + course.getCourseName());
//
//        // Test paying fees
//        student.payFees(500.0);
//        System.out.println("Fees paid: " + student.getFeesPaid());
//
//        // Test viewing grades (assuming grades are set)
//        student.viewGrades();
//
//        // Test grading assignments
//        teacher.gradeAssignments(course);
//
//        // Create Classroom and test its methods
//        Classroom classroom = new Classroom("R101", "101", 30);
//        classroom.assignTeacher(teacher);
//        classroom.scheduleClasses("MWF 10:00-11:00");
//        classroom.viewAvailability();
//
//        // Test GradingSystem methods
//        GradingSystem gradingSystem = new GradingSystem();
//        gradingSystem.recordExamResults(student, course, "A");
//        System.out.println(gradingSystem.calculateGPA(student));
//        gradingSystem.issueTranscripts(student);
    }

    private static void testStudentFunctionality(Connection conn, String courseId, String studentId) throws SQLException {
        List<Student> students = retrieveStudentsFromDatabase(conn);
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                Course course = retrieveCourseFromDatabase(conn, courseId);
                student.enrollInCourse(course);
                student.viewGrades();
            }
        }
    }

    private static void testTeacherFunctionality(Connection conn, String courseId) throws SQLException {
        List<Teacher> teachers = retrieveTeachersFromDatabase(conn);
        for (Teacher teacher : teachers) {
            System.out.println("Teacher Name: " + teacher.getName());

            Course course = retrieveCourseFromDatabase(conn, courseId); // Replace with actual course retrieval logic
            teacher.assignHomework(course, "Homework 1");
            teacher.viewClassRoster(course);
            // Add more tests as needed
        }
    }

    private static void testCourseFunctionality(Connection conn) throws SQLException {
        List<Course> courses = retrieveCoursesFromDatabase(conn);
        for (Course course : courses) {
            System.out.println("Course Name: " + course.getCourseName());

            // Test update schedule functionality
            course.updateSchedule("New Schedule");
            // Add more tests as needed
        }
    }

    private static void testClassroomFunctionality(Connection conn, String teacherId) throws SQLException {
        List<Classroom> classrooms = retrieveClassroomsFromDatabase(conn);
        for (Classroom classroom : classrooms) {
            System.out.println("Classroom Number: " + classroom.getRoomNumber());

            // Test assign teacher and schedule classes functionality
            Teacher teacher = retrieveTeacherFromDatabase(conn, teacherId); // Replace with actual teacher retrieval logic
            classroom.assignTeacher(teacher);
            classroom.scheduleClasses("New Schedule");
            // Add more tests as needed
        }
    }

    private static void testGradingSystemFunctionality(Connection conn, String courseId) throws SQLException {
        List<Student> students = retrieveStudentsFromDatabase(conn);
        GradingSystem gradingSystem = new GradingSystem(); // Initialize grading system
        for (Student student : students) {
            System.out.println("Student Name: " + student.getName());

            // Test record exam results, calculate GPA, and issue transcripts functionality
            Course course = retrieveCourseFromDatabase(conn, courseId); // Replace with actual course retrieval logic
            gradingSystem.recordExamResults(student, course, "A");
            System.out.println("GPA: " + gradingSystem.calculateGPA(student));
            gradingSystem.issueTranscripts(student);
            // Add more tests as needed
        }
    }

    // Methods to retrieve data from database

    private static List<Student> retrieveStudentsFromDatabase(Connection conn) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String name = rs.getString("name");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String contactInformation = rs.getString("contactInformation");
                students.add(new Student(studentID, name, dateOfBirth, contactInformation));
            }
        }
        return students;
    }

    private static List<Teacher> retrieveTeachersFromDatabase(Connection conn) throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM teachers");
            while (rs.next()) {
                String teacherID = rs.getString("teacherID");
                String name = rs.getString("name");
                String contactInformation = rs.getString("contactInformation");
                String subjectExpertise = rs.getString("subjectExpertise");
                teachers.add(new Teacher(teacherID, name, contactInformation, subjectExpertise));
            }
        }
        return teachers;
    }

    private static List<Course> retrieveCoursesFromDatabase(Connection conn) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
            while (rs.next()) {
                String courseID = rs.getString("courseID");
                String courseName = rs.getString("courseName");
                String teacherID = rs.getString("teacherID");
                String schedule = rs.getString("schedule");
                Teacher teacher = retrieveTeacherFromDatabase(conn, teacherID); // Retrieve teacher object
                courses.add(new Course(courseID, courseName, teacher, schedule));
            }
        }
        return courses;
    }

    private static List<Classroom> retrieveClassroomsFromDatabase(Connection conn) throws SQLException {
        List<Classroom> classrooms = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM classrooms");
            while (rs.next()) {
                String classroomID = rs.getString("classroomID");
                String roomNumber = rs.getString("roomNumber");
                int capacity = rs.getInt("capacity");
                classrooms.add(new Classroom(classroomID, roomNumber, capacity));
            }
        }
        return classrooms;
    }

    private static Teacher retrieveTeacherFromDatabase(Connection conn, String teacherId) throws SQLException {
        // Implement method to retrieve a specific teacher from the database
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM teachers WHERE teacherID = ?")) {
            stmt.setString(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String contactInformation = rs.getString("contactInformation");
                String subjectExpertise = rs.getString("subjectExpertise");
                return new Teacher(teacherId, name, contactInformation, subjectExpertise);
            } else {
                throw new SQLException("Teacher not found for ID: " + teacherId);
            }
        }
    }

    private static Course retrieveCourseFromDatabase(Connection conn, String courseId) throws SQLException {
        // Implement method to retrieve a specific course from the database
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM courses WHERE courseID = ?")) {
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String courseName = rs.getString("courseName");
                String teacherID = rs.getString("teacherID");
                String schedule = rs.getString("schedule");
                Teacher teacher = retrieveTeacherFromDatabase(conn, teacherID); // Retrieve teacher object
                return new Course(courseId, courseName, teacher, schedule);
            } else {
                throw new SQLException("Course not found for ID: " + courseId);
            }
        }
    }

    private static void addNewStudent(String studentID, String name, String dateOfBirth, String contactInformation) {
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO students (studentID, name, dateOfBirth, contactInformation) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, studentID);
            stmt.setString(2, name);
            stmt.setDate(3, java.sql.Date.valueOf(dateOfBirth));;
            stmt.setString(4, contactInformation);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewTeacher(String teacherID, String name, String contactInformation, String subjectExpertise) {
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO teachers (teacherID, name, contactInformation, subjectExpertise) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, teacherID);
            stmt.setString(2, name);
            stmt.setString(3, contactInformation);
            stmt.setString(4, subjectExpertise);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new teacher was inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewCourse(String courseID, String courseName, String teacherID, String schedule) {
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO courses (courseID, courseName, teacherID, schedule) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, courseID);
            stmt.setString(2, courseName);
            stmt.setString(3, teacherID);
            stmt.setString(4, schedule);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new course was inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewClassroom(String classroomID, String roomNumber, int capacity) {
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO classrooms (classroomID, roomNumber, capacity) VALUES (?, ?, ?)")) {
            stmt.setString(1, classroomID);
            stmt.setString(2, roomNumber);
            stmt.setInt(3, capacity);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new classroom was inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewGradingSystemEntry(String studentID) {
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO grading_system (studentID, grades, transcripts, exams) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, studentID);
            // For simplicity, insert placeholder values or handle as required
            stmt.setString(2, "");
            stmt.setString(3, "");
            stmt.setString(4, "");
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new grading system entry was inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

