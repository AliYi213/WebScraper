package lt.viko.ali.yahlali.scraper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsPool {
    public static void main(String[] args) {

        String[] urls = {"https://www.skype.com/en/"};

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (String url : urls) {
            executor.submit(new WebScraper(url));
        }
        executor.shutdown();
    }
}
