package sg.edu.ntu.taskflow_api.model; // Declares the model package.

import lombok.AllArgsConstructor; // Imports Lombok's all-arguments constructor annotation.
import lombok.Getter; // Imports Lombok's getter annotation.
import lombok.NoArgsConstructor; // Imports Lombok's no-arguments constructor annotation.
import lombok.Setter; // Imports Lombok's setter annotation.

@Getter // Generates getter methods for all fields.
@Setter // Generates setter methods for all fields.
@NoArgsConstructor // Generates an empty constructor needed when Spring converts JSON to an object.
@AllArgsConstructor // Generates a constructor containing every field.
public class Task { // Defines a plain Task data object.

    private Long id; // Stores the unique task ID.
    private String title; // Stores the task title.
    private Boolean completed=false; // Stores whether the task is complete.
}
