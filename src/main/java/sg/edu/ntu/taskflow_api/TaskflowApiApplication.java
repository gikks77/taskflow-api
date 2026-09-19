package sg.edu.ntu.taskflow_api; // Declares the base package for the application.

import org.springframework.boot.SpringApplication; // Imports the class that starts Spring Boot.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Imports the main Spring Boot annotation.

@SpringBootApplication // Enables auto-configuration and component scanning for this application.
public class TaskflowApiApplication { // Defines the main application class.

    public static void main(String[] args) { // Defines the Java entry point.
        SpringApplication.run(TaskflowApiApplication.class, args); // Starts the Spring Boot application.
    }
}
