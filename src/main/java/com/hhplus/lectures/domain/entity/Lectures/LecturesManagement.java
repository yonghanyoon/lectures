package com.hhplus.lectures.domain.entity.Lectures;

import com.hhplus.lectures.controller.dto.LecturesManagementDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LecturesManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managementId;
    private Long lecturesId;
    private LocalDateTime lecturesDate;
    private Long count;
    private Long maxCount;

    public void increaseEnrollCount() throws Exception {
        if (maxCount == count) {
            throw new Exception("정원 초과!!!");
        }
        count++;
    }

    public LecturesManagementDto toDto() {
        return LecturesManagementDto.builder()
                                    .managementId(this.managementId)
                                    .lecturesId(this.lecturesId)
                                    .lecturesDate(this.lecturesDate)
                                    .count(this.count)
                                    .maxCount(this.maxCount)
                                    .build();
    }

    public LecturesManagement toEntity(LecturesManagementDto dto) {
        return LecturesManagement.builder()
                                 .managementId(dto.getManagementId())
                                 .lecturesId(dto.getLecturesId())
                                 .lecturesDate(dto.getLecturesDate())
                                 .count(dto.getCount())
                                 .maxCount(dto.getMaxCount())
                                 .build();
    }
}
