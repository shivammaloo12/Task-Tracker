package com.Shivam;

import com.Shivam.Model.Task;
import com.Shivam.Repository.TaskRepository;
import com.Shivam.Service.TaskService;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TaskCLI {
    public static void main(String[] args) throws IOException {
        TaskService service = new TaskService();

        if (args.length == 0) {
            printHelp();
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Usage: add <description>");
                } else {
                    String description = args[1];
                    Task t = service.addTask(description);
                    System.out.println("Task added successfully (ID: " + t.getId() + ")");
                }
                break;

            case "update":
                if (args.length < 3) {
                    System.out.println("Usage: update <id> <new description>");
                } else {
                    int id = Integer.parseInt(args[1]);
                    String newDesc = args[2];
                    boolean ok = service.updateTask(id, newDesc);
                    System.out.println(ok ? "Task updated successfully." : "Task not found.");
                }
                break;

            case "delete":
                if (args.length < 2) {
                    System.out.println("Usage: delete <id>");
                } else {
                    int id = Integer.parseInt(args[1]);
                    boolean ok = service.deleteTask(id);
                    System.out.println(ok ? "Task deleted." : "Task not found.");
                }
                break;

            case "mark-in-progress":
                if (args.length < 2) {
                    System.out.println("Usage: mark-in-progress <id>");
                } else {
                    int id = Integer.parseInt(args[1]);
                    boolean ok = service.markInProgress(id);
                    System.out.println(ok ? "Task marked as In-Progress." : "Task not found.");
                }
                break;

            case "mark-done":
                if (args.length < 2) {
                    System.out.println("Usage: mark-done <id>");
                } else {
                    int id = Integer.parseInt(args[1]);
                    boolean ok = service.markDone(id);
                    System.out.println(ok ? "Task marked as Done." : "Task not found.");
                }
                break;

            case "list":
                if (args.length == 1) {
                    printTasks(service.listAll());
                } else {
                    String status = args[1];
                    printTasks(service.listByStatus(status));
                }
                break;

            default:
                printHelp();
                break;
        }
    }

    private static void printTasks(java.util.List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task t : tasks) {
                System.out.println(t.getId() + ": " + t.getDescription() + " [" + t.getStatus() + "]");
            }
        }
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("  add <description>");
        System.out.println("  update <id> <new description>");
        System.out.println("  delete <id>");
        System.out.println("  mark-in-progress <id>");
        System.out.println("  mark-done <id>");
        System.out.println("  list");
        System.out.println("  list <status>");
    }

}