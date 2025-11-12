package org.example

class LottoOutputView {
    fun welcomeMessageOutput() {
        println("=============================");
        println("행운복권 상점에 오신걸 환영합니다🍀");
        println("=============================");
        println();
    }

    fun outputWinningNumber(winningNumber: Lotto, bonusNumber: Int) {
        println("\n=============================");
        println("당첨번호🍀");
        winningNumber.getNumbers().forEach { number ->
            print("$number ")
        }
        println("\n\n보너스번호✨")
        println("$bonusNumber");
        println("=============================");
    }
}