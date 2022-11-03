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

    public ArrayList<Quote> parseJson(JsonReader jr) throws IOException {
        jr.beginArray();
        ArrayList<Quote> output = new ArrayList<>();
        while (jr.hasNext()) {
            jr.beginObject();
            jr.nextName();
            jr.beginArray();
            ArrayList<String> tagVals = new ArrayList<>();
            while (jr.hasNext()) {
                tagVals.add(jr.nextString());
            }
            jr.endArray();
            jr.nextName();
            String authorVal = jr.nextString();
            jr.nextName();
            String likesVal = jr.nextString();
            jr.nextName();
            String text = jr.nextString();
            jr.endObject();

            Quote q = new Quote(tagVals, authorVal, likesVal, text);
            output.add(q);
        }
        jr.endArray();
        return output;
    }
    public void openFileReaderOnResourceStream(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        Gson gson = new Gson();
        JsonReader jsonRdr = gson.newJsonReader(isr);
        ArrayList<Quote> js = parseJson(jsonRdr);
        int rand = (int) Math.floor(Math.random() * js.size());
        Quote q = js.get(rand);
        System.out.println(q.author + ":\n" + q.text);
    }
}
