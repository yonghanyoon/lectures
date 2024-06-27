package com.hhplus.lectures.common.type;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegistStatus {
    SUCCESS("1", "성공"),
    CANCEL("2", "취소");

    private final String code;
    private final String desc;

    public static RegistStatus of(String code) {
        return Arrays.stream(RegistStatus.values())
                     .filter(registStatus -> code.equals(registStatus.getCode()))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("[RegistStatus] 존재하지 않는 코드입니다. { " + code + " }"));
    }
}
