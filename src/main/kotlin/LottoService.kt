package org.example

class LottoService {
    fun validatePurchaseAmount(amount: Int) {
        if (amount % 5000 != 0) {
            throw IllegalArgumentException("[ERROR] 로또는 5,000원 단위로 구매 가능합니다.");
        }
    }

    fun validatePurchaseType(type: Int) {
        if(type != 1 && type != 2) {
            throw IllegalArgumentException("[ERROR] 수동 발급은 숫자 1을, 자동 발급은 숫자 2를 입력해 주세요.");
        }
    }

    fun parseInput(input: String): Lotto {
        return Lotto(
            input.split(",")
                .map { it.trim() }
                .mapNotNull { it.toIntOrNull() }
        );
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
        issuedLottoNumbers: List<Lotto>, winningNumbers: Lotto, bonusNumber: Int
    ): Map<WinningRank, Int> {

        val resultMap = mutableMapOf<WinningRank, Int>()

        for (issued in issuedLottoNumbers) {
            val matchCount = issued.getNumbers().count { it in winningNumbers.getNumbers() }
            val hasBonus = bonusNumber in issued.getNumbers()

            val rank = WinningRank.valueOf(matchCount, hasBonus)

            if (rank != null) {
                resultMap[rank] = resultMap.getOrDefault(rank, 0) + 1
            }
        }

        return resultMap
    }
}