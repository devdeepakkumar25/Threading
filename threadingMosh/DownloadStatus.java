package threadingMosh;

public class DownloadStatus {

    private boolean isDone;
    private int totalBytes;
    private int totalFiles;

    private final Object totalBytesLock = new Object();
    private final Object totalFilesLock = new Object();

    public boolean isDone() {
        return isDone;
    }

    public void done() {
        isDone = true;
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    public void incrementTotalFiles() {
        synchronized (totalFilesLock) {
            totalFiles++;
        }
    }

    public int getTotalBytes() {
        return totalBytes;
    }

    public void incrementTotalBytes() {
        synchronized (totalBytesLock) {
            totalBytes++;
        }
    }
}

// package threadingMosh;

// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;

// public class DownloadStatus {
// private int totalBytes;
// private Lock lock = new ReentrantLock();

// public int getTotalBytes() {
// return totalBytes;
// }

// public void incrementTotalBytes() {
// lock.lock();
// try {

// totalBytes++;
// } finally {

// lock.unlock();
// }
// }
// }
