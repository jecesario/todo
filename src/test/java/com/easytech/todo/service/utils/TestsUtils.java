package com.easytech.todo.service.utils;

import com.easytech.todo.domain.model.Subtask;
import com.easytech.todo.rest.controller.dto.SubtaskRequest;

public class TestsUtils {

    public static Long SUBTASK_ID = 1L;
    public static String SUBTASK_TITLE = "example title";
    public static Boolean SUBTASK_STATUS = false;

    public static Subtask subtaskMock() {
        return Subtask.builder()
                .withId(SUBTASK_ID)
                .withTitle(SUBTASK_TITLE)
                .withStatus(SUBTASK_STATUS)
                .build();
    }

    public static SubtaskRequest subtaskRequestMock() {
        return SubtaskRequest.builder()
                .withTitle(SUBTASK_TITLE)
                .withStatus(SUBTASK_STATUS)
                .build();
    }

}
