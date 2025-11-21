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

    fun outputResult(result: Map<WinningRank, List<Lotto>>) {
        println("\n당첨 결과🏆")
        println("-----------")

        val sortedResult = result.toSortedMap(compareBy { it.matchCount })  // 낮은 등수부터

        for ((rank, lottoList) in sortedResult) {
            println("${rank.rank}등 당첨")
            for (lotto in lottoList) {
                println("당첨된 로또: ${lotto.getNumbers()}")
            }
            println("-----------")
        }
    }
}