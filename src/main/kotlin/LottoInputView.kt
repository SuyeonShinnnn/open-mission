package org.example

class LottoInputView {
    fun inputPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.");
        return readLine()?.toIntOrNull() ?: 0;
    }

    fun inputPurchaseType(): Int {
        println("\n1. 수동 발급 | 2. 자동 발급");
        return readLine()?.toIntOrNull() ?: 0;
    }

    fun inputManualLottoNumbers(count: Int): String {
        print("\n${count}번째 로또 번호 입력: ");
        return readLine() ?: "";
    }
}