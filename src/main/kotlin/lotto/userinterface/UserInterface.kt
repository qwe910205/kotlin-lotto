package lotto.userinterface

import camp.nextstep.edu.missionutils.Console
import lotto.service.dto.LottoDto
import java.math.BigInteger
import java.text.DecimalFormat


object UserInterface {

    private const val PURCHASE_AMOUNT_LIMIT = 2_000_000_000
    private val decimalFormat = DecimalFormat("#,###")

    fun askPurchaseAmount(): Int {
        println(REQUEST_PURCHASE_AMOUNT)
        val readValue = Console.readLine()
        validatePurchaseAmount(readValue)
        val purchaseAmount = BigInteger(readValue)
        return purchaseAmount.toInt()
    }

    private fun validatePurchaseAmount(readValue: String) {
        require(readValue.all { it.isDigit() }) { PURCHASE_AMOUNT_NOT_DIGIT }
        val purchaseAmount = BigInteger(readValue)
        require(purchaseAmount <= BigInteger(PURCHASE_AMOUNT_LIMIT.toString())) {
            String.format(
                PURCHASE_AMOUNT_EXCESS_LIMIT, decimalFormat.format(PURCHASE_AMOUNT_LIMIT)
            )
        }
        require(
            purchaseAmount.mod(BigInteger("1000")).equals(BigInteger.ZERO)
        ) { PURCHASE_AMOUNT_NOT_DIVISIBLE_BY_1000 }
    }

    fun showPurchaseResult(lotteries: List<LottoDto>) {
        println(String.format(PURCHASE_RESULT, lotteries.size))
        lotteries.forEach { println(it.numbers()) }
    }

    fun askWinningNumbers(): List<Int> {
        val readValue = Console.readLine()
        validateWinningNumbers(readValue)
        return readValue.split(",").map { it.trim() }.map { it.toInt() }
    }

    private fun validateWinningNumbers(readValue: String) {
        require(readValue.split(",").size == 6) { WINNING_NUMBERS_SIZE_NOT_6 }
        val splitValues = readValue.split(",").map { it.trim() }
        require(splitValues.all { it.matches(Regex("\\d{1,2}")) && it.toInt() in 1..45 }) { WINNING_NUMBERS_NOT_IN_BETWEEN_1_AND_45 }
        require(splitValues.toSet().size == splitValues.size) { WINNING_NUMBERS_DUPLICATED }
    }
}