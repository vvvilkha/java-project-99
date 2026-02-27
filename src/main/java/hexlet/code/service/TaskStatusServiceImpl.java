package hexlet.code.service;


import hexlet.code.database.repository.TaskStatusRepository;
import hexlet.code.dto.taskStatus.TaskStatusCreateDTO;
import hexlet.code.dto.taskStatus.TaskStatusDTO;
import hexlet.code.dto.taskStatus.TaskStatusUpdateDTO;
import hexlet.code.exception.NotFoundException;
import hexlet.code.exception.ResourceAlreadyExistsException;
import hexlet.code.mapper.TaskStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;

    private final TaskStatusMapper taskStatusMapper;

    @Override
    public List<TaskStatusDTO> getAllTaskStatuses() {
        return taskStatusRepository.findAll().stream()
                .map(taskStatusMapper::map)
                .toList();
    }

    @Override
    public TaskStatusDTO getTaskStatusById(Long id) {
        return taskStatusMapper.map(taskStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TaskStatus with id " + id + " not found!")));
    }

    @Override
    public TaskStatusDTO createTaskStatus(TaskStatusCreateDTO taskStatusDTO) {
        if (taskStatusRepository.findBySlug(taskStatusDTO.getSlug()).isPresent()) {
            throw new ResourceAlreadyExistsException("Status " + taskStatusDTO.getName() + " already exists");
        }

        return taskStatusMapper.map(taskStatusRepository.save(taskStatusMapper.map(taskStatusDTO)));
    }

    @Override
    public TaskStatusDTO updateTaskStatus(long id, TaskStatusUpdateDTO taskStatusDTO) {
        var taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TaskStatus with id " + id + " not found!"));
        taskStatusMapper.update(taskStatusDTO, taskStatus);
        return taskStatusMapper.map(taskStatusRepository.save(taskStatus));
    }

    @Override
    public void deleteTaskStatus(long id) {
        var taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TaskStatus with id " + id + " not found!"));
        taskStatusRepository.delete(taskStatus);
    }
}
