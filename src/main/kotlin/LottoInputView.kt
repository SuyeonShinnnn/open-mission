package org.example

class LottoInputView {
    fun inputPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.");
        return readLine()?.toIntOrNull() ?: 0;
    }

    fun inputPurchaseType(): Int {
        println("1. 수동 발급 | 2. 자동 발급");
        return readLine()?.toIntOrNull() ?: 0;
    }
}