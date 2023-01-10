
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    static final int requestsCount = 100;
    static int errorCount = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String url = "http://localhost:8081/api/tickets/4";
        int n = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Future<Integer>[] results = new Future[n];
        long start = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            RequestMultithreading requestMultithreading = new RequestMultithreading(requestsCount / n, url);
            results[i] = executorService.submit(requestMultithreading);
            Thread.sleep(50);
        }
        executorService.shutdown();

        for (Future<Integer> result : results) {
            errorCount += result.get();
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("Average duration: " + duration / requestsCount / 1000 + " s");
        System.out.println(errorCount + " from " + requestsCount + " requests failed");
    }
}