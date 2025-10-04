package com.Shivam.Service;

import com.Shivam.Model.Task;
import com.Shivam.Repository.TaskRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private static int id=0;

    private TaskRepository repo = new TaskRepository();

    public Task addTask(String description) throws IOException {
        List<Task> tasks=repo.loadTask();

        String now= LocalDateTime.now().toString();
        int nextId = tasks.stream()
                .mapToInt(t -> t.getId())
                .max()
                .orElse(0) + 1;
        Task newTask=new Task(nextId,description,"todo",now,now);

        tasks.add(newTask);

        repo.saveTasks(tasks);

        return newTask;

    }

    public boolean updateTask(int id, String newDescription) throws IOException {
        List<Task> tasks = repo.loadTask();
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDescription( newDescription);
                t.setUpdatedAt(LocalDateTime.now().toString());
                repo.saveTasks(tasks);
                return true;
            }
        }
        return false; // task not found
    }

    public boolean deleteTask(int id) throws IOException {
        List<Task> tasks = repo.loadTask();

        for (Task t : tasks) {
            if (t.getId() == id) {
                tasks.remove(t);
                return true;
            }
        }
        return false;
    }

    public boolean markInProgress(int id) throws IOException {
        return updateStatus(id, "in-progress");
    }
    // Mark a task as Done
    public boolean markDone(int id) throws IOException {
        return updateStatus(id, "done");
    }

    // Helper for updating status
    private boolean updateStatus(int id, String status) throws IOException {
        List<Task> tasks = repo.loadTask();
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus(status);
                t.setUpdatedAt( LocalDateTime.now().toString());
                repo.saveTasks(tasks);
                return true;
            }
        }
        return false;
    }

    // List tasks by status
    public List<Task> listByStatus(String status) throws IOException {
        List<Task> tasks = repo.loadTask();
        List<Task> filtered = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getStatus().equals(status)) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    // List all tasks
    public List<Task> listAll() throws IOException {
        return repo.loadTask();
    }
}
