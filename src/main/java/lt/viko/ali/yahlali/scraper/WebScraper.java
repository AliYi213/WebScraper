package lt.viko.ali.yahlali.scraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebScraper implements Runnable {
    private final String url;

    public WebScraper(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            URL urlObj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
            in.close();

            String text = response.toString().replaceAll("\\<.*?\\>", "").replaceAll("\\s+", " ");
            System.out.println("Extracted Text: " + text);

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

