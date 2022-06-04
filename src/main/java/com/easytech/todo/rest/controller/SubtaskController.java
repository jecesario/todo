package com.easytech.todo.rest.controller;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.domain.service.SubtaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "subtask")
public class SubtaskController {

    private SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping
    public ResponseEntity<List<Subtask>> findAll() {
        List<Subtask> subtasks = subtaskService.findAll();
        return ResponseEntity.ok(subtasks);
    }

    @PostMapping
    public ResponseEntity<Subtask> create(@RequestBody Subtask subtask) {
        Subtask subtaskPersisted = subtaskService.create(subtask);
        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskPersisted);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Subtask> update(@PathVariable Long id, @RequestBody Subtask subtask) {
        Subtask subtaskPersisted = subtaskService.update(id, subtask);

        if(subtaskPersisted.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subtaskPersisted);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subtaskService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
