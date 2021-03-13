package com.hamming;

import java.util.ArrayList;
import java.util.Scanner;

public class Folder {
    ArrayList<Project> projects = new ArrayList<>();
    private final String name;

    public Folder(String name) {
        this.name = name;
    }

    public void createProject() {
        System.out.println("What will be the name of the project?");
        Scanner sc = new Scanner(System.in);
        String projectName = sc.nextLine();
        System.out.println("What will be the note of the project?");
        String projectNote = sc.nextLine();
        /*
        System.out.println("Will the project be active or non-active?  \n please type it like this: \n Yes for active  or No for not active");
        */
        projects.add(new Project(projectName, true, projectNote));
    }

    public void addTaskToProject() {
        Scanner sc = new Scanner(System.in);
        if (projects.size() > 1) {
            int index = 0;
            System.out.println("select a project to add an task to");
            for (Project p : projects) {
                index++;
                System.out.println(index + ". " + p.toString());
            }
            int tempNumber = 0;
            do {
                if (!sc.hasNextInt()) {
                    System.out.println("Enter a number");
                    sc.next();
                } else {
                    tempNumber = sc.nextInt();
                }

            } while (isOutOfBounds(tempNumber));
            Project p = projects.get(tempNumber - 1);
            p.addTask();

        } else {
            if (projects.size() == 1) {
                Project p = projects.get(0);
                p.addTask();
            } else {
                System.out.println("There doesnt seem to be an project. please create on first.");
            }

        }
    }

    public void createTaskForProject() {
        int index = 0;
        if (projects.size() > 1) {
            Scanner sc = new Scanner(System.in);

            for (Project p : projects) {
                index++;
                System.out.println(index + ". " + p.getTitle());
            }
            int test = 0;
            do {
                if (!sc.hasNextInt()) {
                    System.out.println("Enter a number");
                } else {
                    test = sc.nextInt();
                }

            } while (isOutOfBounds(test));
            index = test - 1;
        } else if (projects.size() == 0) {
            System.out.println("First create a project to add an task to");
            return;
        }
        Project p = projects.get(index);
        p.addTask();
    }

    private boolean isOutOfBounds(int userInput) {
        if (userInput > projects.size() || userInput <= 0) {
            System.out.println("The selected number is bigger than the folder size");
            return true;
        }
        return false;
    }

    public void printFolderTreeFull(StringBuilder sb, String prefix) {
        if (projects.size() != 0) {
            sb.append("\n");
            //There is something in the projects list continue
            for (int i = 0; i < projects.size(); i++) {
                if (i != projects.size() - 1) {
                    print(i, sb, prefix + "├── ", prefix + "│   ");
                } else {
                    print(i, sb, prefix + "└── ", prefix + "    ");
                }
            }
        }
    }

    /**
     * @see #printFolderTreeFull(StringBuilder sb, String prefix)
     */
    private void print(int pos, StringBuilder sb, String prefix, String childrenPrefix) {
        Project project = projects.get(pos);
        project.printProject();
        sb.append(prefix);
        sb.append(project.printProject());
        sb.append("\n");

        ArrayList<Task> tasks = project.getTasks();
        int amountTasks = tasks.size();
        for (int i = 0; i < amountTasks; i++) {
            Task task = tasks.get(i);
            sb
                    .append(childrenPrefix)
                    .append(i != amountTasks - 1 ? "├── " : "└── ")
                    .append(task.printTask())
                    .append("\n");
        }
    }

    public boolean hasProject() {
        //In the folder there is a project
        //the folder does not contain a project.
        return projects.size() > 0;
    }


    @Override
    public String toString() {
        return "Title: " + this.name;

    }

    public String printName() {
        return this.name;
    }
}
