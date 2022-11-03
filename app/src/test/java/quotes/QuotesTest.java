package quotes;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import quotes.Quotes;

import static org.junit.jupiter.api.Assertions.*;

public class QuotesTest {
    @Test
    void testQuoteParser() {
        Quotes q = new Quotes();
        try {
            File f = new File("src/main/resources/quotes.json");
            FileReader fr = new FileReader(f);
            Gson gson = new Gson();
            JsonReader jr = gson.newJsonReader(fr);
            assertFalse(q.parseJson(jr).isEmpty());
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
