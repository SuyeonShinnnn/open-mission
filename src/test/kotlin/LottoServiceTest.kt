import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoServiceTest {

    private val service = LottoService();

    @Test
    fun `구매 금액이 5000단위가 아니면 예외 발생`() {
        val e = assertThrows<IllegalArgumentException> {
            service.validatePurchaseAmount(5200)
        }
        assertEquals(e.message, ErrorMessage.INVALID_AMOUNT_INPUT.message);
    }

    @Test
    fun `구매 금액이 5000원 이하일 때 예외 발생`() {
        val e = assertThrows<IllegalArgumentException> {
            service.validatePurchaseAmount(1000)
        }
        assertEquals(e.message, ErrorMessage.INVALID_AMOUNT_INPUT.message)
    }

    @Test
    fun `구매 금액이 5000원 단위일 때 정상 실행`() {
        assertDoesNotThrow {
            service.validatePurchaseAmount(10000)
        }
    }

    @Test
    fun `구매 타입이 1또는 2가 아닐 때 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            service.validatePurchaseType(3)
        }
    }

    @Test
    fun `구매 타입이 1일 때 정상 실행`() {
        assertDoesNotThrow {
            service.validatePurchaseType(1)
        }
    }

    @Test
    fun `구매 타입이 2일 때 정상 실행`() {
        assertDoesNotThrow {
            service.validatePurchaseType(2)
        }
    }

    @Test
    fun `로또 번호 수동 입력 숫자 외의 입력이 들어올 때 예외 발생`() {
        assertThrows<NumberFormatException> {
            service.parseInput("1, a, 3, 4, 5, 6")
        }
    }

    @Test
    fun `로또 번호 수동 입력시 6개 미만의 숫자가 입력됐을 때 예외 발생`() {
        val e = assertThrows<IllegalArgumentException> {
            service.parseInput("1,2,3,4,5")
        }
        assertEquals(ErrorMessage.INVALID_SIZE.message, e.message)
    }

    @Test
    fun `로또 번호 수동 입력시 1-45 숫자 범위를 벗어날 때 예외 발생`() {
        val e = assertThrows<IllegalArgumentException> {
            service.parseInput("1,2,3,4,5,46")
        }
        assertEquals(ErrorMessage.INVALID_RANGE.message, e.message)
    }

    @Test
    fun `로또 번호 수동 입력 조건 만족시 정상 실행`() {
        assertDoesNotThrow {
            service.parseInput("1, 2, 3, 4, 5, 6")
        }
    }

    @Test
    fun `로또 번호 자동 생성 테스트`() {
        val lotto = service.generateLottoNumbers()

        assertEquals(6, lotto.getNumbers().size)
        assertTrue(lotto.getNumbers().all { it in 1..45 })
        assertEquals(lotto.getNumbers().size, lotto.getNumbers().toSet().size)
    }

    @Test
    fun `보너스 번호는 당첨 번호와 겹칠 수 없음`() {
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = service.generateBonusNumber(winning)

        assertFalse(winning.getNumbers().contains(bonus))
    }

    @Test
    fun `로또 등수 계산`() {
        val issued = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),  // 1등
            Lotto(listOf(1, 2, 3, 4, 5, 7))   // 2등
        )
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = 7
        val map = service.matchNumbers(issued, winning, bonus)

        assertEquals(1, map[WinningRank.FIRST]?.size)
        assertEquals(1, map[WinningRank.SECOND]?.size)
    }
}