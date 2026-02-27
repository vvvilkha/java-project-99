package hexlet.code.service;

import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.label.LabelDTO;
import hexlet.code.dto.label.LabelUpdateDTO;

import java.util.List;

public interface LabelService {
    List<LabelDTO> getAllLabels();

    LabelDTO getLabelById(Long id);

    LabelDTO createLabel(LabelCreateDTO labelCreateDTO);

    LabelDTO updateLabel(Long id, LabelUpdateDTO labelUpdateDTO);

    void deleteLabel(Long id);
}
