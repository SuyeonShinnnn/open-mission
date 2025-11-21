package org.example

enum class WinningRank(
    val rank: Int,
    val matchCount: Int,
    val bonus: Boolean,
    val reward: Int
) {
    FIRST(1, 6, false, 2_000_000_000),
    SECOND(2, 5, true, 30_000_000),
    THIRD(3, 5, false, 1_500_000),
    FOURTH(4, 4, false, 50_000),
    FIFTH(5, 3, false, 5_000);

    companion object {
        fun valueOf(matchCount: Int, hasBonus: Boolean): WinningRank? {
            return when {
                matchCount == 6 && !hasBonus -> FIRST
                matchCount == 5 && hasBonus -> SECOND
                matchCount == 5 && !hasBonus -> THIRD
                matchCount == 4 && !hasBonus -> FOURTH
                matchCount == 3 && !hasBonus -> FIFTH
                else -> null
            }
        }
    }
}