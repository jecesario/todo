package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.domain.reposity.SubtaskRepository;
import com.easytech.todo.exceptions.ObjectNotFoundException;
import com.easytech.todo.rest.controller.dto.SubtaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskServiceImpl implements SubtaskService {

    private SubtaskRepository subtaskRepository;

    public SubtaskServiceImpl(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public List<Subtask> findAll() {
        return subtaskRepository.findAll();
    }

    @Override
    public Subtask create(SubtaskRequest subtaskRequest) {
        Subtask subtask = new Subtask();
        subtask.setTitle(subtaskRequest.getTitle());
        subtask.setStatus(subtaskRequest.getStatus());
        return subtaskRepository.save(subtask);
    }

    @Override
    public Subtask update(Long id, SubtaskRequest subtaskRequest) {
        Subtask subtask = subtaskRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Subtask com o id: %s não encontrada", id)));
        subtask.setTitle(subtaskRequest.getTitle());
        subtask.setStatus(subtaskRequest.getStatus());
        return subtaskRepository.save(subtask);
    }

    @Override
    public void delete(Long id) {
        if(!subtaskRepository.findById(id).isPresent()) {
            throw new ObjectNotFoundException(String.format("Subtask com o id: %s não encontrada", id));
        }
        subtaskRepository.deleteById(id);
    }
}
