package hexlet.code.service;

import hexlet.code.database.repository.LabelRepository;
import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.label.LabelDTO;
import hexlet.code.dto.label.LabelUpdateDTO;
import hexlet.code.exception.NotFoundException;
import hexlet.code.exception.ResourceAlreadyExistsException;
import hexlet.code.mapper.LabelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    private final LabelMapper labelMapper;

    @Override
    public List<LabelDTO> getAllLabels() {
        return labelRepository.findAll().stream()
                .map(labelMapper::map)
                .toList();
    }

    @Override
    public LabelDTO getLabelById(Long id) {
        return labelMapper.map(labelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Label with ID " + id + " not found")));
    }

    @Override
    @Transactional
    public LabelDTO createLabel(LabelCreateDTO labelCreateDTO) {
        if (labelRepository.findByName(labelCreateDTO.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException("Label " + labelCreateDTO.getName() + " already exists");
        }

        return labelMapper.map(labelRepository.save(labelMapper.map(labelCreateDTO)));
    }

    @Override
    @Transactional
    public LabelDTO updateLabel(Long id, LabelUpdateDTO labelUpdateDTO) {
        var label = labelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Label with ID " + id + " not found"));
        labelMapper.update(labelUpdateDTO, label);
        return labelMapper.map(labelRepository.save(label));
    }

    @Override
    @Transactional
    public void deleteLabel(Long id) {
        var label = labelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Label with ID " + id + " not found"));
        labelRepository.delete(label);
    }
}
