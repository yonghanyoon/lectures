package com.hhplus.lectures.repository.entity;

import com.hhplus.lectures.common.type.RegistStatus;
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

    public com.hhplus.lectures.domain.LecturesHistory toDto() {
        return com.hhplus.lectures.domain.LecturesHistory.builder()
                                                         .historyId(this.historyId)
                                                         .managementId(this.managementId)
                                                         .userId(this.userId)
                                                         .status(this.status)
                                                         .createdDt(this.createdDt)
                                                         .build();
    }

    public LecturesHistory toEntity(com.hhplus.lectures.domain.LecturesHistory dto) {
        return com.hhplus.lectures.repository.entity.LecturesHistory.builder()
                                                                    .historyId(dto.getHistoryId())
                                                                    .managementId(dto.getManagementId())
                                                                    .userId(dto.getUserId())
                                                                    .status(dto.getStatus())
                                                                    .createdDt(dto.getCreatedDt())
                                                                    .build();
    }
}
