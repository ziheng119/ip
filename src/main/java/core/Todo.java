package core;

/**
 * Represents a Todo task with a description.
 */
public class Todo extends Task{

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return String representation of the Todo task for display purposes.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    /**
     * @return String representation of the Todo task for storage purposes.
     */
    @Override
    public String toStorageString() {
        return "T | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription();
    }
}
