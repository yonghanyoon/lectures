package com.hhplus.lectures.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public com.hhplus.lectures.domain.Lectures toDto() {
        return com.hhplus.lectures.domain.Lectures.builder()
                                                  .lecturesId(this.lecturesId)
                                                  .title(this.title)
                                                  .instructor(this.instructor)
                                                  .build();
    }

    public Lectures toEntity(com.hhplus.lectures.domain.Lectures dto) {
        return com.hhplus.lectures.repository.entity.Lectures.builder()
                                                             .lecturesId(dto.getLecturesId())
                                                             .title(dto.getTitle())
                                                             .lecturesId(dto.getLecturesId())
                                                             .build();
    }
}
