package org.example

import java.text.NumberFormat
import java.util.*

class LottoOutputView {
    fun welcomeMessageOutput() {
        println("=============================");
        println("행운복권 상점에 오신걸 환영합니다🍀");
        println("=============================");
        println();
    }

    fun printIssueTitle(type: Int) {
        if(type == 1) {
            println("\n** 로또 번호를 수동으로 발급합니다 **");
            println("번호를 입력해 주세요. (예: 1, 2, 3, 4, 5, 6)");
        } else if(type == 2) {
            println("\n** 로또 번호를 자동으로 발급합니다 **");
        }
    }

    fun outputWinningNumber(winningNumber: Lotto, bonusNumber: Int) {
        println("\n당첨번호🍀");
        println("${winningNumber.getNumbers()} + $bonusNumber")
    }

    fun outputIssuedLottoNumbers(numbers: List<Lotto>?) {
        println("\n발급한 로또 번호");
        if (numbers != null) {
            numbers.forEach { numbers -> println("${numbers.getNumbers()}") }
        };
    }

    fun outputResult(result: Map<WinningRank, Int>) {
        println("\n당첨 통계")
        println("----------")

        val formatter = NumberFormat.getNumberInstance(Locale.KOREA)
        val sorted = result.toSortedMap(compareBy { it.ordinal })

        for (rank in WinningRank.values().reversed()) {
            val count = sorted.getOrDefault(rank, 0)
            val bonusText = if (rank.bonus) ", 보너스 볼 일치" else ""
            val rewardText = formatter.format(rank.reward)

            println("${rank.matchCount}개 일치$bonusText (${rewardText}원) - ${count}개")
        }
    }
}