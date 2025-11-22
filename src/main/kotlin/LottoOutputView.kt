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
        if (type == 1) {
            println("\n** 로또 번호를 수동으로 발급합니다 **");
            println("번호를 입력해 주세요. (예: 1, 2, 3, 4, 5, 6)");
        } else if (type == 2) {
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

    fun outputResult(result: WinningResult) {
        println("\n당첨 결과🏆")
        println("-----------")

        outputWonNumbers(result)

        println("-----------")
        println("총 ${"%,d".format(result.totalReward)}원 수령")
        println("수익률: ${"%,.2f".format(result.revenueRate)}%")
    }

    fun outputWonNumbers(result: WinningResult) {
        if (result.winningTickets.isEmpty()) {
            return println("당첨된 로또가 없습니다.")
        }
        val sortedRanks = WinningRank.values().sortedBy { it.reward }.reversed()

        for (rank in sortedRanks) {
            val tickets = result.winningTickets[rank] ?: continue

            println("${rank.rank}등 당첨")
            for (ticket in tickets) {
                println("당첨된 로또: ${ticket.getNumbers()}")
            }
        }
    }
}