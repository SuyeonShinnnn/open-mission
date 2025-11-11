package org.example

class LottoService {
    fun parseInput(input: String): Lotto {
        return Lotto(
            input.split(",")
                .map { it.trim() }
                .mapNotNull { it.toIntOrNull() }
        );
    }
}