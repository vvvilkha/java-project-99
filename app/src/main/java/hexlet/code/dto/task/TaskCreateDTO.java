package hexlet.code.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
public class TaskCreateDTO {

    private int index;

    @JsonProperty("assignee_id")
    private long assigneeId;

    private String title;

    private String content;

    @NotBlank
    private String status;

    private Set<Long> taskLabelIds = new HashSet<>();
}
