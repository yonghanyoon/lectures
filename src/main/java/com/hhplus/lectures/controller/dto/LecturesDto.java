package com.hhplus.lectures.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LecturesDto {
    private Long lecturesId;
    private String title;
    private String instructor;
    private List<LecturesManagementDto> managementDtoList;
}
