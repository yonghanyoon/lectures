package com.hhplus.lectures.controller.dto.Response;

import com.hhplus.lectures.common.type.RegistStatus;
import com.hhplus.lectures.domain.LecturesHistory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LecturesStatusResDto {
    private Long managementId;
    private RegistStatus status;
    private LocalDateTime createdDt;

    public List<LecturesStatusResDto> of(List<LecturesHistory> dto) {
        List<LecturesStatusResDto> lecturesStatusResDtoList = new ArrayList<>();
        for (LecturesHistory lecturesHistoryDto : dto) {
            lecturesStatusResDtoList.add(LecturesStatusResDto.builder()
                                             .managementId(lecturesHistoryDto.getManagementId())
                                             .status(lecturesHistoryDto.getStatus())
                                             .createdDt(lecturesHistoryDto.getCreatedDt())
                                             .build());
        }
        return lecturesStatusResDtoList;
    }
}
