package recipe.ch11

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Ch11R1KtTest {

    @Test
    fun `comparison of KotlinVersion instances work`() {
        val v12 = KotlinVersion(major = 1, minor = 2)
        val v1341 = KotlinVersion(1, 3, 41)

        assertAll(
            { assertTrue(v12 < KotlinVersion.CURRENT) },
            { assertTrue(v1341 <= KotlinVersion.CURRENT) },
            { assertEquals(KotlinVersion(1, 3, 41), v1341) }
        )
    }

    @Test
    fun `currency version is at least`() {
        assertTrue(KotlinVersion.CURRENT.isAtLeast(major = 1, minor = 3))
        assertTrue(KotlinVersion.CURRENT.isAtLeast(major = 1, minor = 3, patch = 40))
    }
}