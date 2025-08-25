public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String toStorageString();

    public static Task fromStorageString(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                return new Todo(parts[2], parts[1].equals("1"));
            case "D":
                return new Deadline(parts[2], parts[1].equals("1"));
            case "E":
                return new Event(parts[2], parts[1].equals("1"));
            default:
                throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
