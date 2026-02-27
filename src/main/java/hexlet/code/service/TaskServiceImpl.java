package hexlet.code.service;

import hexlet.code.database.entity.Task;
import hexlet.code.database.repository.TaskRepository;
import hexlet.code.dto.task.TaskCreateDTO;
import hexlet.code.dto.task.TaskDTO;
import hexlet.code.dto.task.TaskParamsDTO;
import hexlet.code.dto.task.TaskUpdateDTO;
import hexlet.code.exception.NotFoundException;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final TaskSpecification taskSpecification;

    @Override
    public List<TaskDTO> getAllTasks(TaskParamsDTO params) {
        var spec = taskSpecification.build(params);
        return taskRepository.findAll(spec).stream().map(taskMapper::map).toList();
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        return taskMapper.map(taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id=%d not found".formatted(id))));
    }

    @Override
    public TaskDTO createTask(TaskCreateDTO taskCreateDTO) {
        Task task = taskMapper.map(taskCreateDTO);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskUpdateDTO taskUpdateDTO) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id=%d not found".formatted(id)));
        taskMapper.update(taskUpdateDTO, task);
        return taskMapper.map(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id=%d not found".formatted(id)));
        taskRepository.delete(task);
    }
}
