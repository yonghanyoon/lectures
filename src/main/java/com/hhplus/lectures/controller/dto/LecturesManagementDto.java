package com.hhplus.lectures.controller.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LecturesManagementDto {
    private Long managementId;
    private Long lecturesId;
    private LocalDateTime lecturesDate;
    private Long count;
    private Long maxCount;
}

