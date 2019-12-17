package com.hamming;

import java.util.ArrayList;
import java.util.Scanner;

public class Project {
    private String titel;
    private boolean active;
    private String note;
    ArrayList<Task> tasks = new ArrayList<>();

    public Project(String titel, boolean active, String note){
        this.titel = titel;
        this.active = active;
        this.note = note;
    }


    @Override
    public String toString() {
        if(this.active){
            return "Project title: " + this.titel + "\n" +
                    "  Active: Yes" + "\n" +
                    "  Project note: " + this.note + "\n";
        }else{
            return "Project title: " + this.titel + "\n" +
                    "Active: No" + "\n" +
                    "Project note: " + this.note + "\n";
        }
    }

    public void addTask() {
        System.out.println("What will the name of the task be?");
        Scanner sc = new Scanner(System.in);
        String taskName = sc.nextLine();
        System.out.println("What will the Due date be?");
        String dueDate = sc.nextLine();
        while(!dateHelper.dateCorrect(dueDate)){
            System.out.println("Due date isnt correct please type it in like this \n dd-mm-yyyy / 21-12-2019");
            dueDate = sc.nextLine();
        }
        System.out.println("What will the Defer date be?");
        String deferDate = sc.nextLine();
        if(deferDate.equals("") || deferDate.equals(" ")){
            //Do nothing
        }
        else{
            while(dateHelper.dateCorrect(deferDate)){
                System.out.println("Defer date isnt correct please type it in like this \n dd-mm-yyyy / 21-12-2019");
                deferDate = sc.nextLine();
            }
        }
        System.out.println("What will the task note be?");
        String taskNote = sc.nextLine();
        tasks.add(new Task(taskName,dueDate,deferDate,taskNote,true));
    }

    public String printProject(){
        if(this.active){
            return "Title: " + this.titel + " | Active: Yes | Note: " + this.note;
        }
        else{
            return "Title: " + this.titel + " | Active: No | Note: " + this.note;
        }
    }

    public boolean hasTask() {
        if(tasks.size() != 0){
            //it has tasks
            return true;
        }
        else{
            //it does not have any tasks
            return false;
        }
    }

    public String printTasks() {
        StringBuilder sb = new StringBuilder();
        int Index = 0;
        for(Task t : tasks){
            Index++;
            if(Index != tasks.size()){
                sb.append("\t \t |--" + t.printTask() + "\n");
            }else{
                sb.append("\t \t |--" + t.printTask());
            }
        }
        return sb.toString();
    }
}
