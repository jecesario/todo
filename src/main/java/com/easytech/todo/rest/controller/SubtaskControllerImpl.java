package com.easytech.todo.rest.controller;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.domain.service.SubtaskService;
import com.easytech.todo.rest.controller.dto.SubtaskRequest;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "subtask")
public class SubtaskControllerImpl implements SubtaskController {

    private SubtaskService subtaskService;

    public SubtaskControllerImpl(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping
    public ResponseEntity<List<Subtask>> findAll() {
        List<Subtask> subtasks = subtaskService.findAll();
        return ResponseEntity.ok(subtasks);
    }

    @PostMapping
    public ResponseEntity<Subtask> create(@RequestBody @Valid SubtaskRequest subtaskRequest) {
        Subtask subtaskPersisted = subtaskService.create(subtaskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskPersisted);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Subtask> update(@PathVariable Long id, @RequestBody @Valid SubtaskRequest subtaskRequest) {
        Subtask subtaskPersisted = subtaskService.update(id, subtaskRequest);
        return ResponseEntity.ok(subtaskPersisted);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subtaskService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
