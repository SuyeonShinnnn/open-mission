package org.example

data class WinningResult(
    val totalReward: Int,
    val revenueRate: Double,
    val winningTickets: Map<WinningRank, List<Lotto>>
)
