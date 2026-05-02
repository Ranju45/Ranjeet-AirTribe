package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    // Default constructor
    public Student() {
        super();
        this.active = true;
    }

    // Constructor without email (overloading)
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, null);
        this.batch = batch;
        this.active = true;
    }

    // Full constructor
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " [" + batch + "]";
    }

    // Getters and Setters
    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Student{ID=" + getId() + ", Name=" + getDisplayName()
                + ", Email=" + getEmail() + ", Active=" + active + "}";
    }
}
