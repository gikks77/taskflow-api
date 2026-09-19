package sg.edu.ntu.taskflow_api.repository; // Declares the repository package.

import java.util.ArrayList; // Imports ArrayList for returning tasks as a list.
import java.util.HashMap; // Imports HashMap for in-memory task storage.
import java.util.List; // Imports the List interface.
import java.util.Map; // Imports the Map interface.
import org.springframework.stereotype.Repository; // Imports the repository annotation.
import sg.edu.ntu.taskflow_api.model.Task; // Imports the Task model.

@Repository // Registers this class as a Spring repository component.
public class TaskRepository { // Defines the data-access layer for tasks.

    private final Map<Long, Task> tasks = new HashMap<>(); // Creates a non-null map to store tasks by ID.
    private Long nextId = 1L; // Stores the next ID that will be assigned to a new task.

    public List<Task> findAll() { // Returns all stored tasks.
        return new ArrayList<>(tasks.values()); // Converts the map values into a list and returns it.
    }

    public Task findById(Long id) { // Finds one task using its ID.
        return tasks.get(id); // Returns the matching task or null if it does not exist.
    }

    public Task save(Task task) { // Creates or updates a task.
        if (task.getId() == null) { // Checks whether this is a new task without an ID.
            task.setId(nextId); // Assigns the next available ID to the task.
            nextId++; // Increases the counter for the next new task.
        }

        tasks.put(task.getId(), task); // Adds or replaces the task in the map.
        return task; // Returns the saved task.
    }

    public boolean existsById(Long id) { // Checks whether a task exists for the given ID.
        return tasks.containsKey(id); // Returns true when the map contains the ID.
    }

    public void deleteById(Long id) { // Deletes one task using its ID.
        tasks.remove(id); // Removes the task from the map.
    }
}
