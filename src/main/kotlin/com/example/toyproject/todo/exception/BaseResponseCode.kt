package com.example.toyproject.todo.exception

import org.springframework.http.HttpStatus

enum class BaseResponseCode(public val status: HttpStatus, public val message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "Todo를 찾을 수 없습니다."),
    OK(HttpStatus.OK, "요청 성공");
}

