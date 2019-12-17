package com.hamming;

public class Task {
    private String titel;
    private String deferDate;
    private String dueDate;
    private String note;
    private boolean active;

    public Task(String titel, String deferDate, String dueDate, String note, boolean active){
        this.titel = titel;
        this.deferDate = deferDate;
        this.dueDate = dueDate;
        this.note = note;
        this.active = active;
    }


    @Override
    public String toString() {
        if(this.active){
            return "Task titel:" + this.titel + "\n" +
                    "Task defer date:" + this.titel + "\n" +
                    "Task due date:" + this.titel + "\n" +
                    "Task note:" + this.titel + "\n" +
                    "Task active: yes" + "\n";
        }
        return "Task titel:" + this.titel + "\n" +
               "Task defer date:" + this.titel + "\n" +
               "Task due date:" + this.titel + "\n" +
               "Task note:" + this.titel + "\n" +
               "Task active: no"+ "\n";
    }

    public String printTask() {
        if(this.active){
            return "Title: " + this.titel + "| Active: Yes";
        }
        else{
            return "Title: " + this.titel + "|  Active: No";
        }
    }
}
