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

    /**
     * @param task Task to add to the TaskList.
     * @throws IllegalArgumentException if the task is null.
     */
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        tasks.add(task);
    }

    /**
     * @param index Index of the task to retrieve (0-based).
     * @return The Task at the specified index.
     * @throws IndexOutOfBoundsException
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index " + (index + 1) + " is out of bounds");
        }
        return tasks.get(index);
    }

    /**
     * @param index Index of the task to remove (0-based).
     * @return The removed Task.
     * @throws IndexOutOfBoundsException
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index " + (index + 1) + " is out of bounds");
        }
        return tasks.remove(index);
    }

    /**
     * @param index Index of the task to mark (0-based).
     * @throws IndexOutOfBoundsException
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        getTask(index).markAsDone();
    }

    /**
     * @param index Index of the task to unmark (0-based).
     * @throws IndexOutOfBoundsException
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        getTask(index).markAsNotDone();
    }

    /**
     * @return Length of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * @return Boolean indicating if the TaskList is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * @return ArrayList of all tasks in the TaskList.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Return a copy to prevent external modification
    }

    /**
     * Searches for tasks containing the specified keyword (case-insensitive) in their descriptions.
     * Returns a new TaskList containing the matching tasks.
     * @param keyword String keyword to search for in task descriptions.
     * @return TaskList of tasks matching the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
