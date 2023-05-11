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

            // convert JSON file to map
            List<Book> listOfBooks = mapper.readValue(Files.readAllBytes(Paths.get("resources/sample.json")), new TypeReference<List<Book>>() {});

            for(Book book : listOfBooks) {
                System.out.println(book.getTitle());
                System.out.println(book.getIsbn());
                System.out.println(book.getYear());
                System.out.println(book.getAuthors());
                System.out.println("----");
            }
            System.out.println("\n");

            File file = new File("resources/sample.json");
            JsonNode sample = mapper.readValue(file, JsonNode.class);

            JsonNode book2 = sample.get(1);
            ((ObjectNode) book2).put("title", "C++ Book");
            System.out.println(sample.toPrettyString());

            JsonNode authors = sample.get(0).get("authors");
            ArrayNode author = (ArrayNode) authors;

            System.out.println(authors);
            System.out.println(author);

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