package com.hhplus.lectures.controller.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LecturesReqDto {
    @NotNull
    @Positive
    private Long managementId;
    @NotNull
    @Positive
    private Long userId;
}
