package com.hhplus.lectures.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LecturesManagement {
    private Long managementId;
    private Long lecturesId;
    private LocalDateTime lecturesDate;
    private Long count;
    private Long maxCount;
}

