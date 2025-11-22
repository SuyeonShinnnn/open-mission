import org.example.ErrorMessage
import org.example.LottoService
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
}