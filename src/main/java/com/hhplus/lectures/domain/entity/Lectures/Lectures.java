package com.hhplus.lectures.domain.entity.Lectures;

import com.hhplus.lectures.controller.dto.LecturesDto;
import jakarta.persistence.Column;
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
public class Lectures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturesId;
    private String title;
    private String instructor;

    public LecturesDto toDto() {
        return LecturesDto.builder()
                          .lecturesId(this.lecturesId)
                          .title(this.title)
                          .instructor(this.instructor)
                          .build();
    }

    public Lectures toEntity(LecturesDto dto) {
        return Lectures.builder()
                       .lecturesId(dto.getLecturesId())
                       .title(dto.getTitle())
                       .lecturesId(dto.getLecturesId())
                       .build();
    }
}
