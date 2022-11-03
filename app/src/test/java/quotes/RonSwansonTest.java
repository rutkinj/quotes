package quotes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.HttpURLConnection;

public class RonSwansonTest {

    @Test void testForHTTPConnection() throws IOException {
        HttpURLConnection connection = RonSwansonAPI.createConnection("http://ron-swanson-quotes.herokuapp.com/v2/quotes");
        assertEquals("Test", RonSwansonAPI.readFromConnection(connection));
    }

}
