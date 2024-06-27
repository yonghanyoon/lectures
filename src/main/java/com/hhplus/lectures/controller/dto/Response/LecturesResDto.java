package com.hhplus.lectures.controller.dto.Response;

import com.hhplus.lectures.controller.dto.LecturesDto;
import com.hhplus.lectures.controller.dto.LecturesManagementDto;
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
public class LecturesResDto {
    private Long lecturesId;
    private LecturesManagementDto management;
    private String title;
    private String instructor;

    public List<LecturesResDto> of(List<LecturesDto> dto) {
        List<LecturesResDto> lecturesResDtoList = new ArrayList<>();
        for (LecturesDto lecturesDto : dto) {
            for (LecturesManagementDto lecturesManagementDto : lecturesDto.getManagementDtoList()) {
                lecturesResDtoList.add(LecturesResDto.builder()
                                                     .lecturesId(lecturesDto.getLecturesId())
                                                     .management(lecturesManagementDto)
                                                     .title(lecturesDto.getTitle())
                                                     .instructor(lecturesDto.getInstructor())
                                                     .build());
            }
        }
        return lecturesResDtoList;
    }
}
