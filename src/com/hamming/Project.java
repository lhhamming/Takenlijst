package com.hamming;

import java.util.ArrayList;
import java.util.Scanner;

public class Project {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final String title;
    private final boolean active;
    private final String note;

    public Project(String title, boolean active, String note) {
        this.title = title;
        this.active = active;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Project title: " + this.title + "\n" +
                "  Active: " + (this.active ? "Yes" : "No") + "\n" +
                "  Project note: " + this.note + "\n";
    }

    public String getTitle() {
        return title;
    }

    public void addTask() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What will the name of the task be?");
        System.out.print("> ");
        String taskName = sc.nextLine();
        System.out.println("What will the due date be?");
        System.out.print("> ");
        String dueDate = sc.nextLine().trim();
        while (!DateHelper.dateCorrect(dueDate)) {
            System.out.println("Due date isn't correct please type it in like this \n dd-mm-yyyy / 21-12-2019");
            dueDate = sc.nextLine();
        }
        System.out.println("What will the defer date be? (Leave empty to skip)");
        System.out.print("> ");
        String deferDate = sc.nextLine().trim();
        if (!deferDate.equals("")) {
            while (DateHelper.dateCorrect(deferDate)) {
                System.out.println("Defer date isn't correct please type it in like this \n dd-mm-yyyy / 21-12-2019");
                deferDate = sc.nextLine();
            }
        } else {
            deferDate = dueDate;
        }
        System.out.println("What will the task note be?");
        System.out.print("> ");
        String taskNote = sc.nextLine();
        tasks.add(new Task(taskName, dueDate, deferDate, taskNote, true));
    }

    public String printProject() {
        return String.format("Title: %s | Active: %s | Note: %s", this.title, this.active ? "Yes" : "No", this.note);
    }

    public boolean hasTask() {
        //it has tasks
        //it does not have any tasks
        return tasks.size() != 0;
    }

    public String printTasks() {
        StringBuilder sb = new StringBuilder();
        int Index = 0;
        for (Task t : tasks) {
            Index++;
            sb.append("\t \t |--").append(t.printTask());
            if (Index != tasks.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
