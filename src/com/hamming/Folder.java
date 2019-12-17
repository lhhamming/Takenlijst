package com.hamming;

import java.util.ArrayList;
import java.util.Scanner;

public class Folder {
    private String name;

    public Folder(String name){
        this.name = name;
    }

    ArrayList<Project> projects = new ArrayList<>();


    public void createProject(){
        System.out.println("What will be the name of the project?");
        Scanner sc = new Scanner(System.in);
        String projectName = sc.nextLine();
        System.out.println("What will be the note of the project?");
        String projectNote = sc.nextLine();
        /*
        System.out.println("Will the project be active or non-active?  \n please type it like this: \n Yes for active  or No for not active");
        */
        projects.add(new Project(projectName,true,projectNote));
    }

    public void addTaskToPoject(){
        Scanner sc = new Scanner(System.in);
        if(projects.size() > 1){
            int index =0;
            System.out.println("select a project to add an task to");
            for(Project p : projects){
                index++;
                System.out.println(index + ". " + p.toString());
            }
            int tempNumber = 0;
            do{
                if(!sc.hasNextInt()){
                    System.out.println("Enter a number");
                    sc.next();
                }else{
                    tempNumber = sc.nextInt();
                }

            }while(isOutofBounds(tempNumber));
            Project p = projects.get(tempNumber-1);
            p.addTask();

        }else{
            if(projects.size() == 1){
                Project p = projects.get(0);
                p.addTask();
            }else{
                System.out.println("There doesnt seem to be an project. please create on first.");
            }

        }
    }

    public void createTaskForProject() {
        if(this.projects.size() >= 0){
            int index = 0;
            Scanner sc = new Scanner(System.in);

            for (Project p : projects){
                index++;
                System.out.println(index + ". " + p.toString());
            }
            int test = 0;
            do{
                if(!sc.hasNextInt()){
                    System.out.println("Enter a number");
                }else{
                    test = sc.nextInt();
                }

            }while(isOutofBounds(test));
            Project p = projects.get(test-1);
            p.addTask();
        }
        System.out.println("First create a project to add an task to");
    }

    private boolean isOutofBounds(int userInput) {
        if(userInput > projects.size() || userInput <= 0){
            System.out.println("The selected number is bigger than the folder size");
            return true;
        }
        return false;
    }

    public void printFolderTreeFull() {
        if(projects.size() != 0){
            //There is something in the projects list continue
            for (Project p : projects){
                System.out.println("\t |- " + p.printProject());
                if(p.hasTask()){
                    System.out.println("\t \t |--" + p.printTasks());
                }
            }
        }
    }

    public boolean hasProject() {
        if(projects.size() > 0){
            //In the folder there is a project
            return true;
        }
        else{
            //the folder does not contain a project.
            return false;
        }
    }


    @Override
    public String toString() {
        return "Titel: " + this.name + "\n";

    }

    public String printName() {
        return this.name;
    }
}
