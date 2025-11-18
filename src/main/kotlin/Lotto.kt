package org.example

class Lotto {
    private val numbers: List<Int>;

    constructor(numbers: List<Int>) {
        validateNumber(numbers);
        this.numbers = numbers;
    }

    fun validateNumber(numbers: List<Int>) {
        if (numbers.size != 6) {
            throw IllegalArgumentException(ErrorMessage.INVALID_SIZE.message);
        }
        if (HashSet(numbers).size != 6) {
            throw IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBER.message);
        }
        if (numbers.filter { it in 1..45 }.size != 6) {
            throw IllegalArgumentException(ErrorMessage.INVALID_RANGE.message);
        }
    }

    fun getNumbers(): List<Int> {
        return numbers;
    }
}