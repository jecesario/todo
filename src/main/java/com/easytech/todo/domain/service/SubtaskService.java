package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.rest.controller.dto.SubtaskRequest;

import java.util.List;

public interface SubtaskService {

    List<Subtask> findAll();
    Subtask create(SubtaskRequest subtask);
    Subtask update(Long id, SubtaskRequest subtask);
    void delete(Long id);

}
