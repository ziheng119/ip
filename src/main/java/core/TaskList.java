package core;

import java.util.ArrayList;

/**
 * Manages a list of tasks, providing methods to add, remove, mark, unmark, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * @param task The task you want to add to the TaskList.
     */
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("core.Task cannot be null");
        }
        tasks.add(task);
    }

    /**
     * Get the task at the specified index.
     * Index is 0-based.
     * @param index Index of the task you want to get from the TaskList.
     * @return Task at the specified index.
     * @throws IndexOutOfBoundsException
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index " + (index + 1) + " is out of bounds");
        }
        return tasks.get(index);
    }

    /**
     * @param index Index of the task you want to remove from the TaskList.
     * @return Task that was removed.
     * @throws IndexOutOfBoundsException
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index " + (index + 1) + " is out of bounds");
        }
        return tasks.remove(index);
    }

    /**
     * @param index Index of the task you want to mark as done in the TaskList.
     * @throws IndexOutOfBoundsException
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        getTask(index).markAsDone();
    }

    /**
     * @param index Index of the task you want to unmark as not done in the TaskList.
     * @throws IndexOutOfBoundsException
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        getTask(index).markAsNotDone();
    }

    /**
     * @return Size of the TaskList.
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
}
