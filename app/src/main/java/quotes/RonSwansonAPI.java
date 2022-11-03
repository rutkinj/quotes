package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RonSwansonAPI {

    static Gson gson = new Gson();

    static public HttpURLConnection createConnection(String api) throws MalformedURLException, IOException {
        URL swansonURL = new URL(api);
        HttpURLConnection swansonURLConnection = (HttpURLConnection) swansonURL.openConnection();
        swansonURLConnection.setRequestMethod("GET");
        return swansonURLConnection;
    }

    static public String readFromConnection(HttpURLConnection connection) throws IOException {
        String swansonData = null;
        if (connection.getResponseCode() < 299) {
            InputStreamReader swansonInputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader swansonBufferedReader = new BufferedReader(swansonInputStreamReader);
            swansonData = swansonBufferedReader.readLine();
        } else {
            InputStream error = connection.getErrorStream();
            if (error.available() > 0) throw new IOException();
        }
        return swansonData;
    }




}
