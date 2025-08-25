import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String description, boolean isDone) {
        super(description.split(" /by ")[0], isDone);
        String[] parts = description.split(" /by ");
        if (parts.length < 2) {
            throw new TronaldDumpException("Deadline description must include a date/time after '/by'.");
        }
        String dateStr = parts[1].trim();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.deadline = LocalDateTime.parse(dateStr, dateTimeFormat);
        } catch (DateTimeParseException e1) {
            try {
                this.deadline = LocalDate.parse(dateStr, dateFormat).atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new TronaldDumpException("Invalid date format! Please use yyyy-MM-dd or yyyy-MM-dd HHmm.");
            }
        }
    }


    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toStorageString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // If time is midnight, store as date only
        if (deadline.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)) {
            return "D | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " /by " + deadline.toLocalDate().format(dateFormat);
        } else {
            return "D | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " /by " + deadline.format(dateTimeFormat);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatWithTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        DateTimeFormatter outputFormatDateOnly = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if (deadline.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)) {
            return String.format("[D] %s (by: %s)", super.toString(), deadline.toLocalDate().format(outputFormatDateOnly));
        } else {
            return String.format("[D] %s (by: %s)", super.toString(), deadline.format(outputFormatWithTime));
        }
    }
}
