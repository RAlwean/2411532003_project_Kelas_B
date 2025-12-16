package model;

public class Task implements Runnable {

    private final int taskId;
    private final TaskListener listener;

    public interface TaskListener {
        void onTaskStatusUpdated(int taskId, String status);
        void onTaskLog(String log);
    }

    public Task(int id, TaskListener listener) {
        this.taskId = id;
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.onTaskStatusUpdated(taskId, "Running");
        listener.onTaskLog("Task #" + taskId + " dimulai");

        try {
            long time = 1000 + (long)(Math.random() * 2000);
            Thread.sleep(time);
            listener.onTaskLog("Task #" + taskId + " selesai (waktu: " + time + "ms)");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        listener.onTaskStatusUpdated(taskId, "Completed ✓");
    }
}
