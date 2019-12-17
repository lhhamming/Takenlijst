package com.hamming;

import java.util.ArrayList;
import java.util.Scanner;

public class Projectmanagementsysteem {

    boolean run = true;
    boolean folderExisits = false;

    ArrayList<Folder> folders = new ArrayList<>();

    public void start() {

        while(run){
            System.out.println("Welcome to the Task mangement system.");
            System.out.println("Please choose an option");
            System.out.println("1. Create a Folder");
            System.out.println("2. Create a project in a folder");
            System.out.println("3. Create a Task in a project");
            System.out.println("4. Print full Task tree");
            Scanner userInput = new Scanner(System.in);
            while (!userInput.hasNextInt()){
                System.out.println("Please type a number");
                userInput.next();
            }
            menuSelection(userInput.nextInt());
        }
    }

    private void menuSelection(int selection) {
        switch (selection){
            case 1:
                createFolder();
                folderExisits = true;
                break;
            case 2:
                createProject();
                break;

            case 3:
                addTaskToProject();
                break;


            case 4://Prints out the entire project tree
                printFolderTreeFull();
                break;

            default:
                System.out.println("Please make a selection from the menu");
                break;
        }
    }

    private void printFolderTreeFull() {
        if(folders.size() != 0){
            //if there is something in the folder go on
            for(Folder f : folders){
                System.out.println("- " + f.printName());
                f.printFolderTreeFull();
            }
        }
    }

    private void createFolder() {
        System.out.println("What will the folder name be?");
        Scanner sc = new Scanner(System.in);
        String folderName = sc.nextLine();
        folders.add(new Folder(folderName));
    }

    private void createProject() {
        Scanner sc = new Scanner(System.in);
        if(folderExisits){
            Folder f = getFolderFromUserInput();
            f.createProject();
        }
        else {
            //A folder doesnt exist
            System.out.println("There doesnt seem to be any project folders please fill a name for this folder");
            String foldername = sc.next();
            folders.add(new Folder(foldername));
            System.out.println(folders);
            Folder f = folders.get(0);
            f.createProject();
        }
    }

    private void addTaskToProject() {
        if(folders.size() > 1){
            if(hasFolderProjects()){
                int Index= 0;
                for (Folder f : folders){
                 Index++;
                 //There are projects in this folder show it to the user.
                    if(hasThisFolderProjects(f)){
                        System.out.println(Index + ". " + f.toString() );
                    }
                }
                Folder f = getFolderFromUserInputMaxIndex(Index);
                f.createTaskForProject();
            }
            else{
                System.out.println("There arent any folders with an project");
            }

        }
        else{
            if(folders.size() > 0){
                Folder f = folders.get(0);
                //Ga naar de eerst folder
                f.addTaskToPoject();
            }
            else{
                //Check if a folder contains a project
                checkFolder();
            }

        }
    }

    private boolean hasThisFolderProjects(Folder f) {
        if(f.hasProject()){
            return true;
        }
        else return false;
    }

    private boolean hasFolderProjects() {
        for (Folder f : folders){
            if(f.hasProject()){
                return true;
            }
        }
        return false;
    }

    private Folder getFolderFromUserInput() {
        Scanner sc = new Scanner(System.in);
        int index = 0;
        System.out.println("Choose a folder to create a project on");
        for(Folder f : folders){
            index++;
            System.out.println(index + "." + f.toString());
        }
        //this variable is temporary. Because we need to initialize a int first before we can use it.
        int tempNumber = 0;
        do{
            if(!sc.hasNextInt()){
                System.out.println("Enter a number");
                sc.next();
            }else{
                tempNumber = sc.nextInt();
            }

        }while(isIntegerAndOutOfBounds(tempNumber));
        Folder f = folders.get(tempNumber-1);

        return f;
    }

    private Folder getFolderFromUserInputMaxIndex(int index) {
        Scanner sc = new Scanner(System.in);
        //this variable is temporary. Because we need to initialize a int first before we can use it.
        int tempNumber = 0;
        do{
            if(!sc.hasNextInt()){
                System.out.println("Enter a number");
            }else{
                tempNumber = sc.nextInt();
            }

        }while(isIntegerAndOutOfBoundsMaxIndex(tempNumber, index));
        Folder f = folders.get(tempNumber-1);

        return f;
    }



    private void checkFolder() {
        for(Folder f : folders){
            if(f.hasProject()){
                //Do something
                System.out.println("It has a project?");
            }else{
                System.out.println("There doesnt seem te be any projects in the folder. Please create one first");
            }
        }
    }

    private boolean isIntegerAndOutOfBounds(int userInput) {
        if(userInput > folders.size() || userInput <= 0){
            System.out.println("The selected number is bigger than the folder size");
            return true;
        }
        return false;
    }

    private boolean isIntegerAndOutOfBoundsMaxIndex(int tempNumber, int maxIndex) {
        if(tempNumber > maxIndex || tempNumber <= 0){
            System.out.println("The selected number is bigger than the folder size");
            return true;
        }
        else return false;
    }
}
