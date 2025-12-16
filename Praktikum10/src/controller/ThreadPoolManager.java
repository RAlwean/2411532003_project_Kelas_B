package controller;

import model.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {

    private ExecutorService executor;

    public void createPool(int nThreads) {
        executor = Executors.newFixedThreadPool(nThreads);
    }

    public void submit(Task task) {
        executor.submit(task);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
