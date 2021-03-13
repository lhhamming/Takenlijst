package com.hamming;

public class Task {
    private final String title;
    private final String deferDate;
    private final String dueDate;
    private final String note;
    private final boolean active;

    public Task(String title, String deferDate, String dueDate, String note, boolean active) {
        this.title = title;
        this.deferDate = deferDate;
        this.dueDate = dueDate;
        this.note = note;
        this.active = active;
    }


    @Override
    public String toString() {
        return "Task title:" + this.title + "\n" +
                "Task defer date:" + this.deferDate + "\n" +
                "Task due date:" + this.dueDate + "\n" +
                "Task note:" + this.note + "\n" +
                "Task active: " + (this.active ? "Yes" : "No") + "\n";
    }

    public String printTask() {
        return String.format("Title: %s | Active: %s", this.title, this.active ? "Yes" : "No");
    }
}
