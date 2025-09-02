package storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import TaskList.Task;

/**
 * This class represents Storage
 * It provides methods to check if the directory and file exists
 *
 * @author Rachel Wong
 */
public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Check if the parent directory exists, create empty file if missing.
     *
     * @throws IOException if grandparent directory is missing
     */
    public void init() throws IOException {
        Path parentPath = this.filePath.getParent();
        if (parentPath != null && Files.notExists(parentPath)) {
            Files.createDirectories(parentPath);
        }
        if (Files.notExists(this.filePath)) {
            Files.createFile(this.filePath);
        }
    }

    /**
     * Load tasks from hard disk.
     *
     * @returns ArrayList<Task></Task>
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        if (Files.notExists(this.filePath)) {
            return new ArrayList<>();
        } else {
            List<String> lines = Files.readAllLines(this.filePath, StandardCharsets.UTF_8);
            ArrayList<Task> tasks = new ArrayList<>();
            for (String line: lines) {
                if (line.isBlank()) {
                    continue;
                } else {
                    tasks.add(Task.fromStorageString(line));
                }
            }
            return tasks;
        }

    }

    /**
     * Save all tasks to disk.
     */
    public void saveAllTasks(ArrayList<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>(tasks.size());
        for (Task t: tasks) {
            lines.add(t.toStorageString());
        }
        // Write to a temporary file
        Path temp = Files.createTempFile(this.filePath.getParent(), "vicky_", ".tmp");
        Files.write(temp, lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        Files.move(temp, this.filePath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }

}
