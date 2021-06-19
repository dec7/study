package recipe.ch8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

internal class C8R5KtTest {

    @Test
    fun `use delegate for Project`() {
        val project = Project(
            mutableMapOf(
                "name" to "Learn Kotlin",
                "priority" to 5,
                "completed" to true
            )
        )

        assertAll(
            { assertEquals("Learn Kotlin", project.name) },
            { assertEquals(5, project.priority) },
            { assertTrue(project.completed) }
        )
    }

    @Test
    fun `use wrong delegate for Project`() {
        val project = Project(
            mutableMapOf(
                "name" to 5,
                "priority" to "Learn Kotlin",
                "complete" to true
            )
        )

        assertAll(
            { assertThrows<ClassCastException> { project.name } },
            { assertThrows<ClassCastException> { project.priority } },
            { assertThrows<NoSuchElementException> { project.completed } }
        )
    }

    @Test
    fun `create project from map parsed from JSON string`() {
        val project = Project(getMapFromJSON())

        assertAll(
            { assertEquals("Learn Kotlin", project.name) },
            { assertEquals(5, project.priority) },
            { assertTrue(project.completed) }
        )
    }
}