package org.example

enum class ErrorMessage(val message: String) {
    INVALID_SIZE("로또 번호는 6개로 구성돼야 합니다."),
    DUPLICATE_NUMBER("로또 번호는 중복될 수 없습니다."),
    INVALID_RANGE("로또 번호는 1-45까지의 숫자만으로 구성돼야 합니다."),
    INVALID_INPUT("숫자를 입력해 주세요."),
    UNEXPECTED_ERROR("예기치 못한 오류가 발생했습니다. 다시 시도해 주세요.");
}