package sg.edu.ntu.taskflow_api.controller; // Declares the controller package.

import java.util.List; // Imports the List interface.
import org.springframework.http.HttpStatus; // Imports HTTP status constants.
import org.springframework.http.ResponseEntity; // Imports the HTTP response wrapper.
import org.springframework.web.bind.annotation.DeleteMapping; // Imports the DELETE mapping annotation.
import org.springframework.web.bind.annotation.GetMapping; // Imports the GET mapping annotation.
import org.springframework.web.bind.annotation.PathVariable; // Imports the path-variable annotation.
import org.springframework.web.bind.annotation.PostMapping; // Imports the POST mapping annotation.
import org.springframework.web.bind.annotation.PutMapping; // Imports the PUT mapping annotation.
import org.springframework.web.bind.annotation.RequestBody; // Imports the request-body annotation.
import org.springframework.web.bind.annotation.RequestMapping; // Imports the base-path annotation.
import org.springframework.web.bind.annotation.RestController; // Imports the REST controller annotation.
import sg.edu.ntu.taskflow_api.model.Task; // Imports the Task model.
import sg.edu.ntu.taskflow_api.service.TaskService; // Imports the service class.

@RestController // Marks this class as a REST controller that returns JSON responses.
@RequestMapping("/api/tasks") // Sets the base URL path for every endpoint in this controller.
public class TaskController { // Defines the web layer for task endpoints.

    private final TaskService taskService; // Stores the service dependency.

    public TaskController(TaskService taskService) { // Uses constructor injection to receive the service.
        this.taskService = taskService; // Saves the injected service for later use.
    }

    @GetMapping // Maps GET /api/tasks requests.
    public ResponseEntity<List<Task>> getAllTasks() { // Handles requests to get all tasks.
        List<Task> tasks = taskService.findAllTasks(); // Gets all tasks from the service.
        return ResponseEntity.ok(tasks); // Returns the tasks with HTTP 200 OK.
    }

    @GetMapping("/{id}") // Maps GET /api/tasks/{id} requests.
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) { // Reads the ID from the URL.
        Task task = taskService.findTaskById(id); // Gets the task from the service.

        if (task == null) { // Checks whether a task was found.
            return ResponseEntity.notFound().build(); // Returns HTTP 404 Not Found when it does not exist.
        }

        return ResponseEntity.ok(task); // Returns the task with HTTP 200 OK.
    }

    @PostMapping // Maps POST /api/tasks requests.
    public ResponseEntity<Task> createTask(@RequestBody Task task) { // Reads a Task object from the JSON body.
        Task createdTask = taskService.createTask(task); // Creates the task through the service.
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask); // Returns the task with HTTP 201 Created.
    }

    @PutMapping("/{id}") // Maps PUT /api/tasks/{id} requests.
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) { // Reads the ID and JSON body.
        Task updatedTask = taskService.updateTask(id, task); // Updates the task through the service.

        if (updatedTask == null) { // Checks whether the task existed.
            return ResponseEntity.notFound().build(); // Returns HTTP 404 Not Found when it does not exist.
        }

        return ResponseEntity.ok(updatedTask); // Returns the updated task with HTTP 200 OK.
    }

    @DeleteMapping("/{id}") // Maps DELETE /api/tasks/{id} requests.
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) { // Reads the ID from the URL.
        boolean deleted = taskService.deleteTask(id); // Deletes the task through the service.

        if (!deleted) { // Checks whether the task existed.
            return ResponseEntity.notFound().build(); // Returns HTTP 404 Not Found when it does not exist.
        }

        return ResponseEntity.ok().build(); // Returns HTTP 200 OK with no response body.
    }

    @PutMapping("/{id}/complete") // Maps PUT /api/tasks/{id}/complete requests.
    public ResponseEntity<Task> markTaskAsComplete(@PathVariable Long id) { // Reads the ID from the URL.
        Task completedTask = taskService.markTaskAsComplete(id); // Marks the task as complete through the service.

        if (completedTask == null) { // Checks whether the task existed.
            return ResponseEntity.notFound().build(); // Returns HTTP 404 Not Found when it does not exist.
        }

        return ResponseEntity.ok(completedTask); // Returns the completed task with HTTP 200 OK.
    }
}
