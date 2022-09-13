package com.example.toyproject.todo.exception

class CustomException(baseResponseCode: BaseResponseCode) : RuntimeException() {
    val baseResponseCode: BaseResponseCode = baseResponseCode
}