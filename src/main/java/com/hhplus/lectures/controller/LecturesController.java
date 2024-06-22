package com.hhplus.lectures.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lectures")
public class LecturesController {

    // 특강 신청 API
    @PostMapping("/apply")
    public void postLectures() {

    }

    // 특강 신청 완료 여부 조회 API
    @GetMapping("/application/{userId}")
    public boolean getLecturesCompleteStatus() {

        return true;
    }

    // 특강 선택 API
    @GetMapping("")
    public void getLecturesChoice() {

    }
}
