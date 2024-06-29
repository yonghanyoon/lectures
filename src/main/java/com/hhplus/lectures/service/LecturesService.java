package com.hhplus.lectures.service;

import com.hhplus.lectures.common.type.RegistStatus;
import com.hhplus.lectures.domain.Lectures;
import com.hhplus.lectures.domain.LecturesHistory;
import com.hhplus.lectures.domain.LecturesManagement;
import com.hhplus.lectures.controller.dto.Request.LecturesReqDto;
import com.hhplus.lectures.repository.LecturesHistoryRepository;
import com.hhplus.lectures.repository.LecturesManagementRepository;
import com.hhplus.lectures.repository.LecturesRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LecturesService {

    private final LecturesRepository lecturesRepository;
    private final LecturesManagementRepository lecturesManagementRepository;
    private final LecturesHistoryRepository lecturesHistoryRepository;

    // 특강 신청
    @Transactional
    public Lectures postLectures(LecturesReqDto reqDto) throws Exception {
        if (lecturesHistoryRepository.findByUserIdAndManagementId(
            reqDto.getUserId(), reqDto.getManagementId()).isPresent()) {
            throw new Exception("이미 신청한 특강입니다.");
        }

        com.hhplus.lectures.repository.entity.LecturesManagement lecturesManagement = lecturesManagementRepository.findByManagementIdWithPessimisticLock(reqDto.getManagementId());
        lecturesManagement.increaseEnrollCount();
        LecturesManagement lecturesManagementDto = lecturesManagement.toDto();

        lecturesHistoryRepository.save(
            new com.hhplus.lectures.repository.entity.LecturesHistory().toEntity(new LecturesHistory().builder()
                                                                                                      .managementId(lecturesManagementDto.getManagementId())
                                                                                                      .userId(reqDto.getUserId())
                                                                                                      .status(RegistStatus.SUCCESS)
                                                                                                      .createdDt(LocalDateTime.now())
                                                                                                      .build()));

        Lectures lecturesDto = lecturesRepository.findByLecturesId(lecturesManagementDto.getLecturesId()).get().toDto();
        List<LecturesManagement> lecturesManagementDtoList = new ArrayList<>();
        lecturesManagementDtoList.add(lecturesManagementDto);
        lecturesDto.setManagementDtoList(lecturesManagementDtoList);

        return lecturesDto;
    }

    // 특강 목록
    public List<Lectures> getLectures() {
        List<Lectures> lecturesDtoList = lecturesRepository.findAll()
                                                           .stream()
                                                           .map(
                                                               com.hhplus.lectures.repository.entity.Lectures::toDto)
                                                           .collect(
                                                                  Collectors.toList());
        if (lecturesDtoList.size() != 0) {
            for (Lectures lecturesDto : lecturesDtoList) {
                lecturesDto.setManagementDtoList(
                    lecturesManagementRepository.findAllByLecturesIdAndLecturesDateAfter(
                        lecturesDto.getLecturesId(), LocalDateTime.now()).stream().map(
                        com.hhplus.lectures.repository.entity.LecturesManagement::toDto).collect(Collectors.toList()));
            }
        }

        return lecturesDtoList;
    }

    // 특강 신청 완료 여부 조회
    public List<LecturesHistory> getLecturesCompleteStatus(Long userId) throws Exception {
        List<LecturesHistory> lecturesHistoryDtoList = lecturesHistoryRepository.findAllByUserIdAndStatus(
            userId, RegistStatus.SUCCESS).stream().map(
            com.hhplus.lectures.repository.entity.LecturesHistory::toDto).collect(
            Collectors.toList());
        
        if (lecturesHistoryDtoList.size() == 0) {
            throw new Exception("신청 하지 않음!!");
        }



        return lecturesHistoryDtoList;
    }
}
