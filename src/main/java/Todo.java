public class Todo extends Task{

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public String toStorageString() {
        return "T | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription();
    }
}
