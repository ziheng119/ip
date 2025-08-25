// src/main/java/Storage.java
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Storage {
    private static final String DIR = "data";
    private static final String FILE = "tronalddump.txt";
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get(DIR, FILE);
        createFileIfMissing();
    }

    private void createFileIfMissing() {
        try {
            if (!Files.exists(Paths.get(DIR))) { // If directory doesn't exist, create it
                Files.createDirectory(Paths.get(DIR));
            }
            if (!Files.exists(filePath)) { // If file doesn't exist, create it
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(Task.fromStorageString(line));
                } catch (Exception e) {
                    System.out.println("Corrupted line skipped: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    // Data format: TYPE | isDone | description (includes all information aka deadlines, event times)

    public void save(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toStorageString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}