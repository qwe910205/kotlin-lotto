package lotto.userinterface

private const val HEADER = "[ERROR] "
const val PURCHASE_AMOUNT_NOT_DIGIT = HEADER + "로또 구입 금액은 숫자여야 합니다."
const val PURCHASE_AMOUNT_EXCESS_LIMIT = HEADER + "로또 구입 금액은 %s원을 초과할 수 없습니다."
const val PURCHASE_AMOUNT_NOT_DIVISIBLE_BY_1000 = HEADER + "로또 구입 금액은 1000으로 나누어 떨어져야 합니다."
const val WINNING_NUMBERS_SIZE_NOT_6 = HEADER + "당첨 번호는 6개여야 합니다."
const val WINNING_NUMBERS_NOT_IN_BETWEEN_1_AND_45 = HEADER + "당첨 번호는 1에서 45 사이의 숫자여야 합니다."
const val WINNING_NUMBERS_DUPLICATED = HEADER + "당첨 번호는 모두 서로 달라야 합니다."