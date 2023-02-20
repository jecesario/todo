package com.easytech.todo.service;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.domain.reposity.SubtaskRepository;
import com.easytech.todo.domain.service.SubtaskServiceImpl;
import com.easytech.todo.rest.controller.dto.SubtaskRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.easytech.todo.service.utils.TestsUtils.SUBTASK_ID;
import static com.easytech.todo.service.utils.TestsUtils.subtaskMock;
import static com.easytech.todo.service.utils.TestsUtils.subtaskRequestMock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubtaskServiceImplTest {

    @InjectMocks
    SubtaskServiceImpl subtaskService;

    @Mock
    SubtaskRepository subtaskRepository;

    @Test
    public void shouldReturnListOfSubtasks() {
        //arrange
        Subtask subtaskMock = subtaskMock();
        when(subtaskRepository.findAll()).thenReturn(List.of(subtaskMock));

        //action
        List<Subtask> subtaskList = subtaskService.findAll();

        //assert
        assertThat(subtaskList).isEqualTo(List.of(subtaskMock));
    }

    @Test
    public void shouldCreateSubtask() {
        //arrange
        SubtaskRequest subtaskRequestMock = subtaskRequestMock();
        Subtask subtaskMock = subtaskMock();
        when(subtaskRepository.save(any(Subtask.class))).thenReturn(subtaskMock);

        //action
        Subtask subtask = subtaskService.create(subtaskRequestMock);

        //assert
        assertThat(subtask).isEqualTo(subtaskMock);

    }

    @Test
    public void shouldUpdateSubtask() {
        //arrange
        SubtaskRequest subtaskRequestMock = subtaskRequestMock();
        Subtask subtaskMock = subtaskMock();

        when(subtaskRepository.findById(SUBTASK_ID)).thenReturn(Optional.of(subtaskMock));
        when(subtaskRepository.save(any(Subtask.class))).thenReturn(subtaskMock);

        //action
        Subtask subtask = subtaskService.update(SUBTASK_ID, subtaskRequestMock);

        //assert
        assertThat(subtask).isEqualTo(subtaskMock);

    }

    @Test
    public void shouldDeleteSubtask() {
        //arrange
        SubtaskRequest subtaskRequestMock = subtaskRequestMock();
        Subtask subtaskMock = subtaskMock();

        when(subtaskRepository.findById(SUBTASK_ID)).thenReturn(Optional.of(subtaskMock));
        doNothing().when(subtaskRepository).deleteById(SUBTASK_ID);

        //action
        subtaskService.delete(SUBTASK_ID);

        //assert
        verify(subtaskRepository, times(1)).deleteById(SUBTASK_ID);

    }
}
