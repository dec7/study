package recipe.ch9

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.arrayContainingInAnyOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate
import java.time.Month

internal class C9R2KtTest {

    @Test
    fun `test book the hard way`() {
        val book = Book(
            "isbn", "title", "author",
            LocalDate.of(2013, Month.SEPTEMBER, 30)
        )
        assertThat(book.isbn, `is`("isbn"))
        assertThat(book.title, `is`("title"))
        assertThat(book.author, `is`("author"))
        assertThat(book.published, `is`(LocalDate.of(2013, Month.SEPTEMBER, 30)))
    }

    @Test
    fun `junit5 assertAll`() {
        val book = Book(
            "isbn", "title", "author",
            LocalDate.of(2013, Month.SEPTEMBER, 30)
        )
        assertAll(
            "check all properties of a book",
            { assertThat(book.isbn, `is`("isbn")) },
            { assertThat(book.title, `is`("title")) },
            { assertThat(book.author, `is`("author")) },
            { assertThat(book.published, `is`(LocalDate.of(2013, Month.SEPTEMBER, 30))) },
        )
    }

    @Test
    fun `use data class`() {
        val book = Book(
            "isbn", "title", "author",
            LocalDate.of(2013, Month.SEPTEMBER, 30)
        )
        val expected = Book(
            isbn = "isbn1",
            title = "title",
            author = "author1",
            published = LocalDate.of(2013, Month.SEPTEMBER, 30)
        )

        assertThat(book, `is`(expected))
    }

    @Test
    fun `check all elements in list`() {
        val found = arrayOf(
            Book("isbn1", "title1", "author1", LocalDate.of(2013, Month.SEPTEMBER, 30)),
            Book("isbn2", "title2", "author2", LocalDate.of(2013, Month.SEPTEMBER, 30)),
        )

        val expected = arrayOf(
            Book("isbn1", "title1", "author1", LocalDate.of(2013, Month.SEPTEMBER, 30)),
            Book("isbn2", "title2", "author2", LocalDate.of(2013, Month.SEPTEMBER, 30)),
        )

        assertThat(found, arrayContainingInAnyOrder(*expected))
    }
}