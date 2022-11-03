package quotes;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;

import quotes.Quotes;

import static org.junit.jupiter.api.Assertions.*;

public class QuotesTest {
    @Test
    void testQuoteParser() {
        Quotes q = new Quotes();
        try {
            File f = new File("src/main/resources/quotes.json");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
            assertFalse(q.parseJson(isr).isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void doesJsonFileExist() {
        Quotes q = new Quotes();
        try {
            InputStream is = q.readFileFromResources("quotes.json");
            assertDoesNotThrow(()->q.openFileReaderOnResourceStream(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test void testForSwansonJson() throws IOException {
        HttpURLConnection connection = RonSwansonAPI.createConnection("http://ron-swanson-quotes.herokuapp.com/v2/quotes");
        String result = RonSwansonAPI.readFromConnection(connection);
        assertEquals("test", Quotes.swansonJson(result));
    }
}
