package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.domain.reposity.SubtaskRepository;
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
        Optional<Subtask> subtaskFound = subtaskRepository.findById(id);
        if(subtaskFound.isEmpty()) {
           return new Subtask();
        }
        subtask.setId(subtaskFound.get().getId());
        return subtaskRepository.save(subtask);
    }

    @Override
    public void delete(Long id) {
        subtaskRepository.deleteById(id);
    }
}
