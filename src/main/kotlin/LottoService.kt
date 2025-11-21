package org.example

class LottoService {
    fun validatePurchaseAmount(amount: Int) {
        if (amount % 5000 != 0) {
            throw IllegalArgumentException("[ERROR] 로또는 5,000원 단위로 구매 가능합니다.");
        }
        if (amount < 5000) {
            throw IllegalArgumentException("[ERROR] 5000원부터 구입 가능합니다.")
        }
    }

    fun validatePurchaseType(type: Int) {
        if (type != 1 && type != 2) {
            throw IllegalArgumentException("[ERROR] 수동 발급은 숫자 1을, 자동 발급은 숫자 2를 입력해 주세요.");
        }
    }

    fun parseInput(input: String): Lotto {
        return Lotto(
            input.split(",")
                .map { it.trim().toInt() }
        )
    }

    fun generateLottoNumbers(): Lotto {
        return Lotto((1..45).shuffled().take(6).sorted());
    }

    fun generateBonusNumber(winningNumbers: Lotto): Int {
        val excluded = winningNumbers.getNumbers().toSet()
        val candidates = (1..45).filter { it !in excluded }
        return candidates.random()
    }

    fun matchNumbers(
        issuedLottoNumbers: List<Lotto>,
        winningNumbers: Lotto,
        bonusNumber: Int
    ): Map<WinningRank, List<Lotto>> {

        val resultMap = mutableMapOf<WinningRank, MutableList<Lotto>>()

        for (issued in issuedLottoNumbers) {
            val matchCount = issued.getNumbers().count { it in winningNumbers.getNumbers() }
            val hasBonus = bonusNumber in issued.getNumbers()
            val rank = WinningRank.valueOf(matchCount, hasBonus)

            rank?.let {
                resultMap.getOrPut(it) { mutableListOf() }.add(issued)
            }
        }
        return resultMap
    }

    fun getLottoResult(
        issuedLottoNumbers: List<Lotto>,
        winningNumbers: Lotto,
        bonusNumber: Int,
        purchaseAmount: Int
    ): WinningResult {

        val winningTickets = matchNumbers(issuedLottoNumbers, winningNumbers, bonusNumber)

        val totalReward = winningTickets.entries.sumOf { (rank, tickets) ->
            rank.reward * tickets.size
        }

        val revenueRate =
            if (purchaseAmount == 0) 0.0
            else (totalReward.toDouble() / purchaseAmount) * 100

        return WinningResult(
            totalReward = totalReward,
            revenueRate = revenueRate,
            winningTickets = winningTickets
        )
    }

}