package recipe.ch9

import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class C9R3KtTest {

    @Test
    fun `use test factory method`() {
        val defaultBook = createBook()
        val customBook = createBook(isbn = "new isbn")
    }

    fun createBook(
        isbn: String = "isbn",
        title: String = "title",
        author: String = "author",
        published: LocalDate = LocalDate.parse("2017-08-26")
    ) = Book(isbn, title, author, published)

    fun createMultiAuthorBook(
        isbn: String = "isbn",
        title: String = "title",
        authors: List<String> = listOf("author1", "author2"),
        published: LocalDate = LocalDate.parse("2017-08-26")
    ) = MultiAuthorBook(isbn, title, authors, published)
}

data class MultiAuthorBook(
    val isbn: String,
    val title: String,
    val authors: List<String>,
    val published: LocalDate
)

