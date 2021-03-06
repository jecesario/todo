package com.easytech.todo.rest.controller;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.rest.controller.dto.SubtaskRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SubtaskController {

    @GetMapping
    public ResponseEntity<List<Subtask>> findAll();
    @PostMapping
    public ResponseEntity<Subtask> create(@RequestBody SubtaskRequest subtaskRequest);

    @PutMapping(value = "/{id}")
    public ResponseEntity<Subtask> update(@PathVariable Long id, @RequestBody SubtaskRequest subtaskRequest);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id);

}
