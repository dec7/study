package recipe.ch5.r12

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

internal class R12SandboxKtTest {

    @Test
    fun `LocalDate is a range`() {
        val startDate = LocalDate.now()
        val midDate = startDate.plusDays(3)
        val endDate = startDate.plusDays(5)

        val dateRange = startDate..endDate

        assertAll(
            { assertTrue(startDate in dateRange) },
            { assertTrue(midDate in dateRange) },
            { assertTrue(endDate in dateRange) },
            { assertTrue(startDate.minusDays(1) !in dateRange) },
            { assertTrue(endDate.plusDays(1) !in dateRange) }
        )
    }

    @Test
    fun `use LocalDate as a procession`() {
        val startDate = LocalDate.now()
        val endDate = startDate.plusDays(5)

        val dateRange = startDate..endDate

        dateRange.forEachIndexed { index, localDate ->
            assertEquals(localDate, startDate.plusDays(index.toLong()))
        }

        val dateList = dateRange.map { it.toString() }
        assertEquals(6, dateList.size)
    }

    @Test
    fun `use LocalDate as a procession with a step`() {
        val startDate = LocalDate.now()
        val endDate = startDate.plusDays(5)

        val dateRange = startDate..endDate step 2
        dateRange.forEachIndexed { index, localDate ->
            assertEquals(localDate, startDate.plusDays(index.toLong() * 2))
        }

        val dateList = dateRange.map { it.toString() }
        assertEquals(3, dateList.size)
    }
}