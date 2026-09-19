package sg.edu.ntu.taskflow_api.service; // Declares the service package.

import java.util.List; // Imports the List interface.
import org.springframework.stereotype.Service; // Imports the service annotation.
import sg.edu.ntu.taskflow_api.model.Task; // Imports the Task model.
import sg.edu.ntu.taskflow_api.repository.TaskRepository; // Imports the repository class.

@Service // Registers this class as a Spring service component.
public class TaskService { // Defines the business-logic layer for tasks.

    private final TaskRepository taskRepository; // Stores the repository dependency.

    public TaskService(TaskRepository taskRepository) { // Uses constructor injection to receive the repository.
        this.taskRepository = taskRepository; // Saves the injected repository for later use.
    }

    public List<Task> findAllTasks() { // Gets every task.
        return taskRepository.findAll(); // Delegates the request to the repository.
    }

    public Task findTaskById(Long id) { // Gets one task by ID.
        return taskRepository.findById(id); // Delegates the request to the repository.
    }

    public Task createTask(Task task) { // Creates a new task.
        task.setId(null); // Ignores any ID sent by the client so the repository generates it.
        task.setCompleted(false); // Makes every newly created task incomplete.
        return taskRepository.save(task); // Saves and returns the new task.
    }

    public Task updateTask(Long id, Task task) { // Updates an existing task.
        if (!taskRepository.existsById(id)) { // Checks whether the requested task exists.
            return null; // Returns null when there is no task to update.
        }

        task.setId(id); // Ensures the URL ID is used instead of an ID from the request body.
        return taskRepository.save(task); // Saves and returns the updated task.
    }

    public boolean deleteTask(Long id) { // Deletes an existing task.
        if (!taskRepository.existsById(id)) { // Checks whether the requested task exists.
            return false; // Returns false when there is no task to delete.
        }

        taskRepository.deleteById(id); // Removes the task from storage.
        return true; // Confirms that the deletion was successful.
    }

    public Task markTaskAsComplete(Long id) { // Marks one task as completed.
        Task task = taskRepository.findById(id); // Finds the task using its ID.

        if (task == null) { // Checks whether the task was found.
            return null; // Returns null when there is no matching task.
        }

        task.setCompleted(true); // Changes the task status to completed.
        return taskRepository.save(task); // Saves and returns the completed task.
    }
}
