package quotes;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Quotes {

    private record Quote(ArrayList<String> tags, String author, String likes, String text) {}

    public InputStream readFileFromResources(String name) throws IOException {
        return this.getClass().getClassLoader().getResourceAsStream(name);
    }

    public ArrayList<Quote> parseJson(InputStreamReader isr) throws IOException {
        Gson gson = new Gson();
        JsonReader jr = gson.newJsonReader(isr);
        ArrayList<Quote> quotes = new ArrayList<>();
        jr.beginArray();
        while (jr.hasNext()) {
            Quote q = gson.fromJson(jr, Quote.class);
            quotes.add(q);
        }
        jr.endArray();
        return quotes;
    }

    public void openFileReaderOnResourceStream(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        ArrayList<Quote> js = parseJson(isr);
        int rand = (int) Math.floor(Math.random() * js.size());
        Quote q = js.get(rand);
        System.out.println(q.author + ":\n" + q.text);
    }

    public static String swansonJson(String input) {
        Gson gson = new Gson();
        ArrayList jr = gson.fromJson(input, ArrayList.class);
        return jr.get(0).toString();
    }

}
