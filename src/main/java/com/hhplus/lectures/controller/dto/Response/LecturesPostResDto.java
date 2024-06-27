package com.hhplus.lectures.controller.dto.Response;

import com.hhplus.lectures.controller.dto.LecturesDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LecturesPostResDto {
    private String title;
    private String instructor;
    private LocalDateTime lecturesDate;
    private Long count;
    private Long maxCount;

    public LecturesPostResDto of(LecturesDto dto) {
        return LecturesPostResDto.builder()
            .title(dto.getTitle())
            .instructor(dto.getInstructor())
            .lecturesDate(dto.getManagementDtoList().stream().findFirst().get().getLecturesDate())
            .count(dto.getManagementDtoList().stream().findFirst().get().getCount())
            .maxCount(dto.getManagementDtoList().stream().findFirst().get().getMaxCount())
            .build();
    }
}
