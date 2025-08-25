public class Event extends Task{
    private String startDateTime;
    private String endDateTime;

    public Event(String description, boolean isDone) {
        super(description.split(" /from ")[0], isDone);
        String[] getEndTime = description.split(" /to ");
        this.endDateTime = getEndTime[getEndTime.length - 1];
        String[] getStartTime = getEndTime[0].split(" /from ");
        this.startDateTime = getStartTime[getStartTime.length - 1];
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toStorageString() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " /from " + this.startDateTime + " /to " + this.endDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), startDateTime, endDateTime);
    }
}
