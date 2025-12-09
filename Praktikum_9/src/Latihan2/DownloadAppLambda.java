package Latihan2;

public class DownloadAppLambda {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> simulateDownload("File-1"));
        Thread t2 = new Thread(() -> simulateDownload("File-2"));
        Thread t3 = new Thread(() -> simulateDownload("File-3"));

        t1.start();
        t2.start();
        t3.start();

        System.out.println("\nDownloading...");
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Semua file selesai diunduh!");

        System.out.println("\nStatus akhir:");
        System.out.println(t1.getName() + " : " + t1.getState());
        System.out.println(t2.getName() + " : " + t2.getState());
        System.out.println(t3.getName() + " : " + t3.getState());
    }

    private static void simulateDownload(String fileName) {
        for (int i = 10; i <= 100; i += 10) {
            System.out.println(fileName + " progress: " + i + "%");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(fileName + " selesai diunduh!");
    }
}