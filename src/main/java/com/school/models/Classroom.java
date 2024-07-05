package com.school.models;

public class Classroom {
    private String classroomID;
    private String roomNumber;
    private int capacity;

    public Classroom(String classroomID, String roomNumber, int capacity) {
        this.classroomID = classroomID;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public void assignTeacher(Teacher teacher) {
        // Logic to assign teacher
        System.out.println("Teacher " + teacher.getName() + " assigned to classroom " + roomNumber);
    }

    public void scheduleClasses(String schedule) {
        // Logic to schedule classes
        System.out.println("Classes scheduled for room " + roomNumber + " at " + schedule);
    }

    public void viewAvailability() {
        // Logic to view availability
        System.out.println("Availability for room " + roomNumber + " is checked.");
    }

    public String getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(String classroomID) {
        this.classroomID = classroomID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
