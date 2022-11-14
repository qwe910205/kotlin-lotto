package lotto.domain

import lotto.service.LottoService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode

internal class `WinningStatistics 클래스의` {
    @Nested
    inner class `생성자는` {
        @Nested
        inner class `로또들과 당첨 번호를 받으면` {
            private val lotteries = listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                Lotto(listOf(1, 2, 3, 4, 5, 7)),
                Lotto(listOf(1, 2, 3, 4, 5, 8)),
                Lotto(listOf(1, 2, 3, 4, 7, 8)),
                Lotto(listOf(1, 2, 3, 7, 8, 9))
            )
            private val winningNumber = WinningNumber(listOf(1, 2, 3, 4, 5, 6), 7)

            @Test
            fun `당첨 등수 당 로또들의 당첨 횟수를 저장한다`() {
                val winningStatistics = WinningStatistics(lotteries, winningNumber)

                val winningCounts = winningStatistics.winningCounts()

                assertThat(winningCounts[WinningResult.FIRST]).isEqualTo(1)
                assertThat(winningCounts[WinningResult.SECOND]).isEqualTo(1)
                assertThat(winningCounts[WinningResult.THIRD]).isEqualTo(1)
                assertThat(winningCounts[WinningResult.FOURTH]).isEqualTo(1)
                assertThat(winningCounts[WinningResult.FIFTH]).isEqualTo(1)
                assertThat(winningCounts[WinningResult.NO_LUCK]).isEqualTo(0)
            }

            @Test
            fun `소수점 2의 자리에서 반올림한 수익률을 저장한다`() {
                val winningStatistics = WinningStatistics(lotteries, winningNumber)
                val totalRevenue = BigDecimal(WinningResult.FIRST.winnings)
                    .plus(BigDecimal(WinningResult.SECOND.winnings))
                    .plus(BigDecimal(WinningResult.THIRD.winnings))
                    .plus(BigDecimal(WinningResult.FOURTH.winnings))
                    .plus(BigDecimal(WinningResult.FIFTH.winnings))
                val purchaseAmount = BigDecimal(lotteries.size).multiply(BigDecimal(LottoShop.lottoPrice()))

                val yields = winningStatistics.yields()

                assertThat(yields).isEqualTo(totalRevenue.multiply(BigDecimal(100)).divide(purchaseAmount, 1, RoundingMode.HALF_EVEN))
            }
        }
    }
}