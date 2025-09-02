package storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
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
            List<String> lines = Files.readAllLines(this.filePath);
            ArrayList<Task> tasks = new ArrayList<>();
            for (String line: lines) {
                if (line.isBlank()) {
                    continue;
                }
            }
            return tasks;
        }

    }


}
