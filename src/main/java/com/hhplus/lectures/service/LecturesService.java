package com.hhplus.lectures.service;

import com.hhplus.lectures.common.type.RegistStatus;
import com.hhplus.lectures.controller.dto.LecturesDto;
import com.hhplus.lectures.controller.dto.LecturesHistoryDto;
import com.hhplus.lectures.controller.dto.LecturesManagementDto;
import com.hhplus.lectures.controller.dto.Request.LecturesReqDto;
import com.hhplus.lectures.controller.dto.Response.LecturesPostResDto;
import com.hhplus.lectures.domain.entity.Lectures.Lectures;
import com.hhplus.lectures.domain.entity.Lectures.LecturesHistory;
import com.hhplus.lectures.domain.entity.Lectures.LecturesManagement;
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
    public LecturesDto postLectures(LecturesReqDto reqDto) throws Exception {
        if (lecturesHistoryRepository.findByUserIdAndManagementId(
            reqDto.getUserId(), reqDto.getManagementId()).isPresent()) {
            throw new Exception("이미 신청한 특강입니다.");
        }

        LecturesManagement lecturesManagement = lecturesManagementRepository.findByManagementId(reqDto.getManagementId()).get();
        lecturesManagement.increaseEnrollCount();
        LecturesManagementDto lecturesManagementDto = lecturesManagement.toDto();

        lecturesHistoryRepository.save(
            new LecturesHistory().toEntity(new LecturesHistoryDto().builder()
                                                                   .managementId(lecturesManagementDto.getManagementId())
                                                                   .userId(reqDto.getUserId())
                                                                   .status(RegistStatus.SUCCESS)
                                                                   .createdDt(LocalDateTime.now())
                                                                   .build()));

        LecturesDto lecturesDto = lecturesRepository.findByLecturesId(lecturesManagementDto.getLecturesId()).get().toDto();
        List<LecturesManagementDto> lecturesManagementDtoList = new ArrayList<>();
        lecturesManagementDtoList.add(lecturesManagementDto);
        lecturesDto.setManagementDtoList(lecturesManagementDtoList);

        return lecturesDto;
    }

    // 특강 목록
    public List<LecturesDto> getLectures() {
        List<LecturesDto> lecturesDtoList = lecturesRepository.findAll()
                                                              .stream()
                                                              .map(Lectures::toDto)
                                                              .collect(
                                                                  Collectors.toList());
        if (lecturesDtoList.size() != 0) {
            for (LecturesDto lecturesDto : lecturesDtoList) {
                lecturesDto.setManagementDtoList(
                    lecturesManagementRepository.findAllByLecturesIdAndLecturesDateAfter(
                        lecturesDto.getLecturesId(), LocalDateTime.now()).stream().map(
                        LecturesManagement::toDto).collect(Collectors.toList()));
            }
        }

        return lecturesDtoList;
    }

    // 특강 신청 완료 여부 조회
    public List<LecturesHistoryDto> getLecturesCompleteStatus(Long userId) throws Exception {
        List<LecturesHistoryDto> lecturesHistoryDtoList = lecturesHistoryRepository.findAllByUserIdAndStatus(
            userId, RegistStatus.SUCCESS).stream().map(LecturesHistory::toDto).collect(
            Collectors.toList());
        
        if (lecturesHistoryDtoList.size() == 0) {
            throw new Exception("신청 하지 않음!!");
        }



        return lecturesHistoryDtoList;
    }
}
