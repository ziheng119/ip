public class Deadline extends Task{
    private String deadline;

    public Deadline(String description) {
        super(description.split(" /by ")[0]);
        String[] parts = description.split(" /by ");
        this.deadline = parts[1];
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
