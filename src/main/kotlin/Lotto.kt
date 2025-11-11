package org.example

class Lotto {
    private val numbers: List<Int>;

    constructor(numbers: List<Int>) {
        validateNumber(numbers);
        this.numbers = numbers;
    }

    fun validateNumber(numbers: List<Int>) {
        if (numbers.size != 6) {
            throw IllegalArgumentException("6개 아님");
        }
        if (HashSet(numbers).size != 6) {
            throw IllegalArgumentException("중복");
        }
        if (numbers.filter { it in 1..45 }.size != 6) {
            throw IllegalArgumentException("로또 번호는 1-45까지의 숫자만 입력 가능");
        }
    }

    fun getNumbers(): List<Int> {
        return numbers;
    }
}