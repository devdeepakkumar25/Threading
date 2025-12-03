package threadingMosh;

public class DownloadFileTask implements Runnable {

    private DownloadStatus status;

    public DownloadFileTask(DownloadStatus status) {
        this.status = status;
    }

    public DownloadStatus getStatus() {
        return status;
    }

    @Override
    public void run() {
        System.out.println("Downloading a file: " + Thread.currentThread().getName());

        for (var i = 0; i < 1_000_000; i++) {

            if (Thread.currentThread().isInterrupted())
                return;

            status.incrementTotalBytes();
        }

        status.done();
        synchronized (status) {

            status.notify();
        }

        System.out.println("Download complete: " + Thread.currentThread().getName());
    }
}

// package threadingMosh;

// public class DownloadFileTask implements Runnable {

// private DownloadStatus status;

// // public DownloadFileTask(DonloadStatus status) {
// // this.status = status;
// // }
// public DownloadFileTask() {
// this.status = new DownloadStatus();
// }

// public DownloadStatus getStatus() {
// return status;

// }

// @Override
// public void run() {
// System.out.println("Downloading a file: " +
// Thread.currentThread().getName());

// for (var i = 0; i < 10_000; i++) {
// if (Thread.currentThread().isInterrupted())
// return;

// status.incrementTotalBytes();
// }

// System.out.println("Download complete: " + Thread.currentThread().getName());
// }
// }

// package threadingMosh;

// public class DownloadFileTask implements Runnable {

// private DonloadStatus status;

// public DownloadFileTask(DonloadStatus status) {
// this.status = status;
// }

// @Override
// public void run() {
// System.out.println("Downloading a file: " +
// Thread.currentThread().getName());

// for (var i = 0; i < 10_000; i++) {
// if (Thread.currentThread().isInterrupted())
// return;

// status.incrementTotalBytes();
// }

// System.out.println("Download complete: " + Thread.currentThread().getName());
// }
// }

// package threadingMosh;

// public class DownloadFileTask implements Runnable {

// @Override
// public void run() {
// System.out.println("Downloading a file: " +
// Thread.currentThread().getName());
// // try {

// // Thread.sleep(5000);
// // } catch (InterruptedException e) {
// // e.printStackTrace();
// // }

// for (var i = 0; i < Integer.MAX_VALUE; i++) {
// if (Thread.currentThread().isInterrupted())
// break;
// System.out.println("Downloading byte " + i);
// }

// System.out.println("Download complete: " + Thread.currentThread().getName());
// }
// }

// package threadingMosh;

// public class DownloadFileTask implements Runnable {

// @Override
// public void run() {
// System.out.println("Downloading a file: " +
// Thread.currentThread().getName());
// try {

// Thread.sleep(5000);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }

// System.out.println("Download complete: " + Thread.currentThread().getName());
// }
// }
