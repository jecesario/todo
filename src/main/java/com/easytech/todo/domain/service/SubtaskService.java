package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.Subtask;

import java.util.List;

public interface SubtaskService {

    List<Subtask> findAll();
    Subtask create(Subtask subtask);
    Subtask update(Long id, Subtask subtask);
    void delete(Long id);

}
