package org.example

class LottoService {
    fun parseInput(input: String): Lotto {
        return Lotto(
            input.split(",")
                .map { it.trim() }
                .mapNotNull { it.toIntOrNull() }
        );
    }

    fun generateWinningNumber(): Lotto {
        return Lotto((1..45).shuffled().take(6).sorted());
    }

    fun generateBonusNumber(winningNumbers: Lotto): Int {
        val excluded = winningNumbers.getNumbers().toSet()
        val candidates = (1..45).filter { it !in excluded }
        return candidates.random()
    }
}