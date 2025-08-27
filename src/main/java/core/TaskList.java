package core;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("core.Task cannot be null");
        }
        tasks.add(task);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("core.Task index " + (index + 1) + " is out of bounds");
        }
        return tasks.get(index);
    }

    public Task removeTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("core.Task index " + (index + 1) + " is out of bounds");
        }
        return tasks.remove(index);
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        getTask(index).markAsDone();
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        getTask(index).markAsNotDone();
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Return a copy to prevent external modification
    }
    
    public void clear() {
        tasks.clear();
    }
}
