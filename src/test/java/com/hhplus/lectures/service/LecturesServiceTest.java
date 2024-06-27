package com.hhplus.lectures.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.lectures.controller.dto.LecturesDto;
import com.hhplus.lectures.controller.dto.LecturesManagementDto;
import com.hhplus.lectures.controller.dto.Request.LecturesReqDto;
import com.hhplus.lectures.domain.entity.Lectures.LecturesHistory;
import com.hhplus.lectures.repository.LecturesHistoryRepository;
import com.hhplus.lectures.repository.LecturesManagementRepository;
import com.hhplus.lectures.repository.LecturesRepository;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LecturesServiceTest {

    @Autowired
    LecturesService lecturesService;
    @Autowired
    LecturesRepository lecturesRepository;
    @Autowired
    LecturesManagementRepository lecturesManagementRepository;
    @Autowired
    LecturesHistoryRepository lecturesHistoryRepository;

    @DisplayName("선착순 특강 신청 동시성 테스트")
    @Test
    public void postLecturesTest() throws InterruptedException {
        List<LecturesDto> lecturesDtoList = lecturesService.getLectures();
        int numberOfExecute = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfExecute);
        Long managementId = lecturesDtoList.get(0).getManagementDtoList().get(0).getManagementId();

        for (int i = 0; i < numberOfExecute; i++) {
            Long id = Long.valueOf(i);
            executorService.execute(() -> {
                try {
                    lecturesService.postLectures(new LecturesReqDto(managementId, id));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        Long exitCount = lecturesManagementRepository.findByManagementId(managementId).get().getCount();
        System.out.println("exit count : " + exitCount);
        assertThat(exitCount).isEqualTo(30L);
    }

//    private LecturesService lecturesService = Mockito.mock(LecturesService.class);

//    @DisplayName("특강 신청")
//    @Test
//    public void postLecturesTest() throws Exception {
//        // given
//        LecturesReqDto reqDto = new LecturesReqDto(1L, 1L);
//        List<LecturesManagementDto> lecturesManagementDtoList = new ArrayList<>();
//        lecturesManagementDtoList.add(new LecturesManagementDto(1L, 1L, LocalDateTime.of(2024, 6, 28, 12, 0), 1L, 30L));
//        LecturesDto lecturesDto = new LecturesDto(1L, "특강1", "이석범", lecturesManagementDtoList);
//
//        // when
//        when(lecturesService.postLectures(reqDto)).thenReturn(lecturesDto);
//
//        // then
//        assertThat(lecturesService.postLectures(reqDto).getLecturesId()).isEqualTo(1L);
//        assertThat(lecturesService.postLectures(reqDto).getTitle()).isEqualTo("특강1");
//        assertThat(lecturesService.postLectures(reqDto).getInstructor()).isEqualTo("이석범");
//        assertThat(lecturesService.postLectures(reqDto).getManagementDtoList().get(0).getManagementId()).isEqualTo(1L);
//        assertThat(lecturesService.postLectures(reqDto).getManagementDtoList().get(0).getLecturesId()).isEqualTo(1L);
//        assertThat(lecturesService.postLectures(reqDto).getManagementDtoList().get(0).getLecturesDate()).isEqualTo("2024-06-28T12:00:00");
//        assertThat(lecturesService.postLectures(reqDto).getManagementDtoList().get(0).getCount()).isEqualTo(1L);
//        assertThat(lecturesService.postLectures(reqDto).getManagementDtoList().get(0).getMaxCount()).isEqualTo(30L);
//    }
//
//    @DisplayName("특강 목록")
//    @Test
//    public void getLecturesTest() {
//        // given
//        List<LecturesDto> lecturesDtoList = new ArrayList<>();
//        List<LecturesManagementDto> lecturesManagementDtoList = new ArrayList<>();
//        lecturesManagementDtoList.add(new LecturesManagementDto(1L, 1L, LocalDateTime.of(2024, 6, 28, 12, 0), 0L, 30L));
//        lecturesDtoList.add(new LecturesDto(1L, "특강1", "이석범", lecturesManagementDtoList));
//
//        // when
//        when(lecturesService.getLectures()).thenReturn(lecturesDtoList);
//
//        // then
//        assertThat(lecturesService.getLectures().get(0).getLecturesId()).isEqualTo(1L);
//        assertThat(lecturesService.getLectures().get(0).getTitle()).isEqualTo("특강1");
//        assertThat(lecturesService.getLectures().get(0).getInstructor()).isEqualTo("이석범");
//        assertThat(lecturesService.getLectures().get(0).getManagementDtoList().get(0).getManagementId()).isEqualTo(1L);
//        assertThat(lecturesService.getLectures().get(0).getManagementDtoList().get(0).getLecturesId()).isEqualTo(1L);
//        assertThat(lecturesService.getLectures().get(0).getManagementDtoList().get(0).getLecturesDate()).isEqualTo("2024-06-28T12:00:00");
//        assertThat(lecturesService.getLectures().get(0).getManagementDtoList().get(0).getCount()).isEqualTo(0L);
//        assertThat(lecturesService.getLectures().get(0).getManagementDtoList().get(0).getMaxCount()).isEqualTo(30L);
//    }
//
//    @DisplayName("특강 신청 완료 여부 조회")
//    @Test
//    public void getLecturesCompleteStatusTest() throws Exception {
//        // given
//        Long userId = 1L;
//        List<LecturesHistoryDto> lecturesHistoryDtoList = new ArrayList<>();
//        lecturesHistoryDtoList.add(new LecturesHistoryDto(1L, 1L, userId, RegistStatus.SUCCESS, LocalDateTime.of(2024, 6, 28, 12, 0)));
//
//        // when
//        when(lecturesService.getLecturesCompleteStatus(userId)).thenReturn(lecturesHistoryDtoList);
//
//        // then
//        assertThat(lecturesService.getLecturesCompleteStatus(userId).get(0).getHistoryId()).isEqualTo(1L);
//        assertThat(lecturesService.getLecturesCompleteStatus(userId).get(0).getManagementId()).isEqualTo(1L);
//        assertThat(lecturesService.getLecturesCompleteStatus(userId).get(0).getUserId()).isEqualTo(userId);
//        assertThat(lecturesService.getLecturesCompleteStatus(userId).get(0).getStatus()).isEqualTo(RegistStatus.SUCCESS);
//        assertThat(lecturesService.getLecturesCompleteStatus(userId).get(0).getCreatedDt()).isEqualTo("2024-06-28T12:00:00");
//    }

}
