package com.hhplus.lectures.controller;

import com.hhplus.lectures.controller.dto.Request.LecturesReqDto;
import com.hhplus.lectures.controller.dto.Response.LecturesPostResDto;
import com.hhplus.lectures.controller.dto.Response.LecturesResDto;
import com.hhplus.lectures.controller.dto.Response.LecturesStatusResDto;
import com.hhplus.lectures.service.LecturesService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LecturesController {

    private final LecturesService lecturesService;

    // 특강 신청 API
    @PostMapping("/apply")
    public ResponseEntity<LecturesPostResDto> postLectures(@RequestBody @Valid LecturesReqDto reqDto) throws Exception {
        LecturesPostResDto lecturesPostResDto = new LecturesPostResDto();
        return ResponseEntity.ok(lecturesPostResDto.of(lecturesService.postLectures(reqDto)));
    }

    // 특강 목록 API
    @GetMapping("")
    public ResponseEntity<List<LecturesResDto>> getLectures() {
        LecturesResDto lecturesResDto = new LecturesResDto();
        return ResponseEntity.ok(lecturesResDto.of(lecturesService.getLectures()));
    }

    // 특강 신청 완료 여부 조회 API
    @GetMapping("/application/{userId}")
    public ResponseEntity<List<LecturesStatusResDto>> getLecturesCompleteStatus(@PathVariable Long userId)
        throws Exception {
        LecturesStatusResDto lecturesStatusResDto = new LecturesStatusResDto();
        return ResponseEntity.ok(lecturesStatusResDto.of(lecturesService.getLecturesCompleteStatus(userId)));
    }

}
