package com.hhplus.lectures.domain.entity.Lectures;

import com.hhplus.lectures.common.type.RegistStatus;
import com.hhplus.lectures.controller.dto.LecturesHistoryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class LecturesHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;
    private Long managementId;
    private Long userId;
    private RegistStatus status;
    @CreatedDate
    private LocalDateTime createdDt;

    public LecturesHistoryDto toDto() {
        return LecturesHistoryDto.builder()
                                 .historyId(this.historyId)
                                 .managementId(this.managementId)
                                 .userId(this.userId)
                                 .status(this.status)
                                 .createdDt(this.createdDt)
                                 .build();
    }

    public LecturesHistory toEntity(LecturesHistoryDto dto) {
        return LecturesHistory.builder()
                              .historyId(dto.getHistoryId())
                              .managementId(dto.getManagementId())
                              .userId(dto.getUserId())
                              .status(dto.getStatus())
                              .createdDt(dto.getCreatedDt())
                              .build();
    }
}
