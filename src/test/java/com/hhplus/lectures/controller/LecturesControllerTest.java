package com.hhplus.lectures.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.lectures.common.type.RegistStatus;
import com.hhplus.lectures.domain.Lectures;
import com.hhplus.lectures.domain.LecturesHistory;
import com.hhplus.lectures.domain.LecturesManagement;
import com.hhplus.lectures.controller.dto.Request.LecturesReqDto;
import com.hhplus.lectures.service.LecturesService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(LecturesController.class)
public class LecturesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LecturesService lecturesService;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("특강 신청")
    @Test
    public void postLectures() throws Exception {
        // given
        LecturesReqDto reqDto = new LecturesReqDto(1L, 1L);
        List<LecturesManagement> lecturesManagementDtoList = new ArrayList<>();
        lecturesManagementDtoList.add(new LecturesManagement(1L, 1L, LocalDateTime.of(2024, 6, 28, 12, 0), 0L, 30L));
        Lectures lecturesDto = new Lectures(1L, "특강1", "이석범", lecturesManagementDtoList);

        // when
        when(lecturesService.postLectures(reqDto)).thenReturn(lecturesDto);

        // then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/lectures/apply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqDto)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("특강1"))
            .andExpect(jsonPath("$.instructor").value("이석범"))
            .andExpect(jsonPath("$.lecturesDate").value("2024-06-28T12:00:00"));

    }

    @DisplayName("특강 목록 조회")
    @Test
    public void getLecturesTest() throws Exception {
        // given
        List<LecturesManagement> lecturesManagementDtoList = new ArrayList<>();
        lecturesManagementDtoList.add(LecturesManagement.builder()
                                                        .managementId(1L)
                                                        .lecturesId(1L)
                                                        .lecturesDate(LocalDateTime.of(2024, 6, 28, 12, 0))
                                                        .count(0L)
                                                        .maxCount(30L)
                                                        .build());
        List<Lectures> lecturesDto = new ArrayList<>();
        lecturesDto.add(new Lectures(1L, "특강1", "이석범", lecturesManagementDtoList));

        // when
        when(lecturesService.getLectures()).thenReturn(lecturesDto);

        // then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/lectures"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].lecturesId").value(1L))
            .andExpect(jsonPath("$[0].title").value("특강1"))
            .andExpect(jsonPath("$[0].instructor").value("이석범"))
            .andExpect(jsonPath("$[0].management.managementId").value(1L))
            .andExpect(jsonPath("$[0].management.lecturesId").value(1L))
            .andExpect(jsonPath("$[0].management.lecturesDate").value("2024-06-28T12:00:00"))
            .andExpect(jsonPath("$[0].management.count").value(0L))
            .andExpect(jsonPath("$[0].management.maxCount").value(30L));
    }

    @DisplayName("특강 신청 완료 여부 조회")
    @Test
    public void getLecturesCompleteStatus() throws Exception {
        // given
        Long userId = 1L;
        List<LecturesHistory> lecturesHistoryDtoList = new ArrayList<>();
        lecturesHistoryDtoList.add(new LecturesHistory(1L, 1L, userId, RegistStatus.SUCCESS, LocalDateTime.of(2024, 6, 28, 12, 0)));

        // when
        when(lecturesService.getLecturesCompleteStatus(userId)).thenReturn(lecturesHistoryDtoList);

        // then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/lectures/application/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].managementId").value(1L))
            .andExpect(jsonPath("$[0].status").value("SUCCESS"))
            .andExpect(jsonPath("$[0].createdDt").value("2024-06-28T12:00:00"));
    }
}
