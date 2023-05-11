package bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@EqualsAndHashCode @Getter @ToString
@SuppressWarnings("ClassCanBeRecord")
public class Book {
    private final String title;
    private final String isbn;
    private final Integer year;
    private final List<String> authors;

    @JsonCreator
    public Book(
            @JsonProperty("title") final String title,
            @JsonProperty("isbn") final String isbn,
            @JsonProperty("year") final Integer year,
            @JsonProperty("authors") final List<String> authors
    ) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.authors = authors;
    }

    public String getTitle() {
        return this.title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Integer getYear() {
        return this.year;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

}
