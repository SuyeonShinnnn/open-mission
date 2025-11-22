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
}