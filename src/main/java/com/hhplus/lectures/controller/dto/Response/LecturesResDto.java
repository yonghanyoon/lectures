package com.hhplus.lectures.controller.dto.Response;

import com.hhplus.lectures.domain.Lectures;
import com.hhplus.lectures.domain.LecturesManagement;
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
    private LecturesManagement management;
    private String title;
    private String instructor;

    public List<LecturesResDto> of(List<Lectures> dto) {
        List<LecturesResDto> lecturesResDtoList = new ArrayList<>();
        for (Lectures lecturesDto : dto) {
            for (LecturesManagement lecturesManagementDto : lecturesDto.getManagementDtoList()) {
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
