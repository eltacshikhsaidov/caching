package io.shixseyidrin.caching.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Response {
    SUCCESS(1, "success"),
    ERROR(2, "error");

    private final Integer code;
    private final String message;
}
