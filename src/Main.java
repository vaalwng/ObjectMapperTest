import bean.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            File file = new File("src/resources/sample.json");

            List<Book> listOfBooks = mapper.readValue(Files.readAllBytes(Paths.get("src/resources/sample.json")), new TypeReference<List<Book>>() {});

            for(Book book : listOfBooks) {
                System.out.println(book.getTitle());
                System.out.println(book.getIsbn());
                System.out.println(book.getYear());
                System.out.println(book.getAuthors());
                System.out.println("----");
            }
            System.out.println("\n");

            /* ------------------------------------------------------------------------------------ */

            JsonNode sample = mapper.readValue(file, JsonNode.class);
            System.out.println(sample.toPrettyString());

            System.out.println("\n");

            JsonNode book2 = sample.get(1);
            ((ObjectNode) book2).put("title", "C++ Book");
            System.out.println(sample.toPrettyString());
            System.out.println("\n");

            /* ------------------------------------------------------------------------------------ */

            JsonNode authors = sample.get(0).get("authors");
            ArrayNode author = (ArrayNode) authors;
            System.out.println(authors);
            System.out.println(author);
            System.out.println("\n");

            /* ------------------------------------------------------------------------------------ */

            List<String> authorsList = mapper.readValue(authors.toPrettyString(), new TypeReference<List<String>>(){});

            System.out.println(authorsList);
            System.out.println(authorsList.contains("John Doe"));
            System.out.println(authorsList.contains("John Did"));

            System.out.println(authors.get(0).asText());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}