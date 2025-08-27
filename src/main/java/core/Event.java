package core;

import util.TronaldDumpException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String description, boolean isDone) {
        super(description.split(" /from ")[0], isDone);
        String[] fromSplit = description.split(" /from ");
        if (fromSplit.length < 2) {
            throw new TronaldDumpException("Event description must include both start date/time after '/from' and end date/time after '/to'.");
        }
        String[] toSplit = fromSplit[1].split(" /to ");
        if (toSplit.length < 2) {
            throw new TronaldDumpException("Event description must include both start date/time after '/from' and end date/time after '/to'.");
        }
        String startStr = toSplit[0].trim();
        String endStr = toSplit[1].trim();
        this.startDateTime = parseDateTime(startStr);
        this.endDateTime = parseDateTime(endStr);
    }

    private LocalDateTime parseDateTime(String dateStr) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDateTime.parse(dateStr, dateTimeFormat);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(dateStr, dateFormat).atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new TronaldDumpException("Invalid date format! Please use yyyy-MM-dd or yyyy-MM-dd HHmm.");
            }
        }
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toStorageString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startDateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
                ? startDateTime.toLocalDate().format(dateFormat)
                : startDateTime.format(dateTimeFormat);
        String end = endDateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
                ? endDateTime.toLocalDate().format(dateFormat)
                : endDateTime.format(dateTimeFormat);
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " /from " + start + " /to " + end;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatWithTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        DateTimeFormatter outputFormatDateOnly = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String start = startDateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
                ? startDateTime.toLocalDate().format(outputFormatDateOnly)
                : startDateTime.format(outputFormatWithTime);
        String end = endDateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
                ? endDateTime.toLocalDate().format(outputFormatDateOnly)
                : endDateTime.format(outputFormatWithTime);
        return String.format("[E] %s (from: %s to: %s)", super.toString(), start, end);
    }
}