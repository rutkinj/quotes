package quotes;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;

import java.io.*;

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
}
