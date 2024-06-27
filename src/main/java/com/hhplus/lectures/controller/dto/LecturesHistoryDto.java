package com.hhplus.lectures.controller.dto;

import com.hhplus.lectures.common.type.RegistStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LecturesHistoryDto {
    private Long historyId;
    private Long managementId;
    private Long userId;
    private RegistStatus status;
    private LocalDateTime createdDt;
}
