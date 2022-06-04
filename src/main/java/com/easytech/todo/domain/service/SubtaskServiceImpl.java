package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.domain.reposity.SubtaskRepository;
import com.easytech.todo.exceptions.ObjectNotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Subtask create(Subtask subtask) {
        return subtaskRepository.save(subtask);
    }

    @Override
    public Subtask update(Long id, Subtask subtask) {
        Subtask subtaskFound = findSubtaskById(id);

        subtask.setId(subtaskFound.getId());
        return subtaskRepository.save(subtask);
    }

    @Override
    public void delete(Long id) {
        findSubtaskById(id);
        subtaskRepository.deleteById(id);
    }

    private Subtask findSubtaskById(Long id) {
        return subtaskRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Subtask com o id: %s não encontrada", id)));
    }
}
