package hexlet.code.service;

import hexlet.code.dto.taskStatus.TaskStatusCreateDTO;
import hexlet.code.dto.taskStatus.TaskStatusDTO;
import hexlet.code.dto.taskStatus.TaskStatusUpdateDTO;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatusDTO> getAllTaskStatuses();

    TaskStatusDTO getTaskStatusById(Long id);

    TaskStatusDTO createTaskStatus(TaskStatusCreateDTO taskStatusDTO);

    TaskStatusDTO updateTaskStatus(long id, TaskStatusUpdateDTO taskStatusDTO);

    void deleteTaskStatus(long id);
}
