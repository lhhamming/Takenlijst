package com.hamming;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProjectManagementSysteem {
    private final ArrayList<Folder> folders = new ArrayList<>();
    private final Gson gson = new Gson();
    private boolean run = true;
    private boolean folderExists = false;

    public void start() {
        // Read from file
        File save = new File("save.json");
        if (save.exists()) {
            try {
                byte[] bytes = Files.readAllBytes(save.toPath());
                folders.addAll(Arrays.asList(gson.fromJson(new String(bytes), Folder[].class)));
                if (folders.size() > 0) folderExists = true;
            } catch (IOException e) {
                System.err.println("Failed to read from save!");
            }
        }

        while (run) {
            System.out.println("Welcome to the Task management system.");
            System.out.println("Please choose an option");
            System.out.println("1. Create a Folder");
            System.out.println("2. Create a project in a folder");
            System.out.println("3. Create a Task in a project");
            System.out.println("4. Print full Task tree");
            System.out.println("0. Exit");
            Scanner userInput = new Scanner(System.in);
            System.out.print("> ");
            while (!userInput.hasNextInt()) {
                System.out.println("Please type a number");
                System.out.print("> ");
                userInput.next();
            }
            System.out.println();
            menuSelection(userInput.nextInt());
            System.out.println();
            if (run) {
                // Save to json
                String json = gson.toJson(folders);
                try {
                    FileWriter writer = new FileWriter(save);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Failed to save!");
                    e.printStackTrace();
                }
            }
        }
    }

    private void menuSelection(int selection) {
        switch (selection) {
            case 1:
                createFolder();
                folderExists = true;
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

            case 0:
                run = false;
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Please make a selection from the menu");
                break;
        }
    }

    private void printFolderTreeFull() {
        StringBuilder sb = new StringBuilder();
        sb.append("FOLDERS\n");
        if (folders.size() != 0) {
            //if there is something in the folder go on
            for (int i = 0; i < folders.size(); i++) {
                Folder folder = folders.get(i);
                if (i != folders.size() - 1) {
                    sb.append("├── ").append(folder.printName());
                    folder.printFolderTreeFull(sb, "│   ");
                } else {
                    sb.append("└── ").append(folder.printName());
                    folder.printFolderTreeFull(sb, "    ");
                }
            }
            System.out.println(sb.toString());
        }
    }

    private void createFolder() {
        System.out.println("What will the folder name be?");
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        String folderName = sc.nextLine();
        folders.add(new Folder(folderName));
    }

    private void createProject() {
        if (folderExists) {
            Folder f = getFolderFromUserInput();
            f.createProject();
        } else {
            //A folder doesnt exist
            Scanner sc = new Scanner(System.in);
            System.out.println("There don't seem to be any project folders please fill a name for this folder");
            System.out.print("> ");
            String folderName = sc.nextLine();
            folders.add(new Folder(folderName));
            System.out.println(folders);
            Folder f = folders.get(0);
            f.createProject();
        }
    }

    private void addTaskToProject() {
        if (folders.size() > 1) {
            if (hasFolderProjects()) {
                int Index = 0;
                for (Folder f : folders) {
                    Index++;
                    //There are projects in this folder show it to the user.
                    if (hasThisFolderProjects(f)) {
                        System.out.println(Index + ". " + f.printName());
                    }
                }
                Folder f = getFolderFromUserInputMaxIndex(Index);
                f.createTaskForProject();
            } else {
                System.out.println("There aren't any folders with projects");
            }
        } else {
            if (folders.size() > 0) {
                Folder f = folders.get(0);
                //Go to the first folder
                f.addTaskToProject();
            } else {
                //Check if a folder contains a project
                checkFolder();
            }
        }
    }

    private boolean hasThisFolderProjects(Folder f) {
        return f.hasProject();
    }

    private boolean hasFolderProjects() {
        for (Folder f : folders) {
            if (f.hasProject()) {
                return true;
            }
        }
        return false;
    }

    private Folder getFolderFromUserInput() {
        Scanner sc = new Scanner(System.in);
        int index = 0;
        System.out.println("Choose a folder to create a project in");
        for (Folder f : folders) {
            index++;
            System.out.println(index + ". " + f.printName());
        }
        //this variable is temporary. Because we need to initialize a int first before we can use it.
        int tempNumber = 0;
        do {
            System.out.print("> ");
            if (!sc.hasNextInt()) {
                System.out.println("Enter a number");
                sc.next();
            } else {
                tempNumber = sc.nextInt();
            }

        } while (isIntegerAndOutOfBounds(tempNumber));

        return folders.get(tempNumber - 1);
    }

    private Folder getFolderFromUserInputMaxIndex(int index) {
        Scanner sc = new Scanner(System.in);
        //this variable is temporary. Because we need to initialize a int first before we can use it.
        int tempNumber = 0;
        do {
            System.out.print("> ");
            if (!sc.hasNextInt()) {
                System.out.println("Enter a number");
            } else {
                tempNumber = sc.nextInt();
            }

        } while (isIntegerAndOutOfBoundsMaxIndex(tempNumber, index));

        return folders.get(tempNumber - 1);
    }


    private void checkFolder() {
        for (Folder f : folders) {
            if (f.hasProject()) {
                //Do something
                System.out.println("It has a project?");
            } else {
                System.out.println("There doesnt seem te be any projects in the folder. Please create one first");
            }
        }
    }

    private boolean isIntegerAndOutOfBounds(int userInput) {
        if (userInput > folders.size() || userInput <= 0) {
            System.out.println("The selected number is bigger than the folder size");
            return true;
        }
        return false;
    }

    private boolean isIntegerAndOutOfBoundsMaxIndex(int tempNumber, int maxIndex) {
        if (tempNumber > maxIndex || tempNumber <= 0) {
            System.out.println("The selected number is bigger than the folder size");
            return true;
        } else return false;
    }
}
