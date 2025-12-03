package threadingMosh;

public class ThreadDemo {

    public void show() {

        var status = new DownloadStatus();

        var thread1 = new Thread(new DownloadFileTask(status));

        var thread2 = new Thread(() -> {
            while (!status.isDone()) {
                try {

                    status.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(status.getTotalBytes());
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        demo.show();
    }
}

// package threadingMosh;

// import java.util.ArrayList;
// import java.util.List;

// public class ThreadDemo {

// public void show() {

// var status = new DownloadStatus();
// List<Thread> threads = new ArrayList<>();
// List<DownloadFileTask> tasks = new ArrayList<>();

// for (var i = 0; i < 10; i++) {
// var task = new DownloadFileTask();
// tasks.add(task);

// var thread = new Thread(task);
// thread.start();
// threads.add(thread);
// }

// // join must be OUTSIDE the creation loop
// for (var thread : threads) {
// try {
// thread.join();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }

// var totalBytes = tasks.stream().map(t ->
// t.getStatus().getTotalBytes()).reduce(Integer::sum);

// System.out.println(totalBytes);
// }

// public static void main(String[] args) {
// ThreadDemo demo = new ThreadDemo();
// demo.show();
// }
// }

// package threadingMosh;

// import java.util.ArrayList;
// import java.util.List;

// public class ThreadDemo {

// public void show() {

// var status = new DonloadStatus();
// List<Thread> threads = new ArrayList<>();

// for (var i = 0; i < 10; i++) {

// var thread = new Thread(new DownloadFileTask(status));
// thread.start();
// threads.add(thread);
// }

// // join must be OUTSIDE the creation loop
// for (var thread : threads) {
// try {
// thread.join();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }

// System.out.println(status.getTotalBytes());
// }

// public static void main(String[] args) {
// ThreadDemo demo = new ThreadDemo();
// demo.show();
// }
// }

// package threadingMosh;

// public class ThreadDemo {

// public void show() {

// System.out.println("Current Thread: " + Thread.currentThread().getName());

// for (int i = 0; i < 10; i++) {

// Thread thread = new Thread(new DownloadFileTask());
// thread.start();

// try {
// // Pause the main thread for 1 second
// Thread.sleep(1000);

// // Optional: interrupt the worker thread
// thread.interrupt();

// } catch (InterruptedException e) {
// System.out.println("Main thread interrupted");
// }

// System.out.println("File is ready to be scanned");
// }
// }

// public static void main(String[] args) {

// ThreadDemo demo = new ThreadDemo(); // ✔ Proper object creation
// demo.show(); // ✔ Proper method invocation
// }
// }

// package threadingMosh;

// public class ThreadDemo {

// public static void show() {

// System.out.println(Thread.currentThread().getName());

// for (var i = 0; i < 10; i++) {

// Thread thread = new Thread(new DownloadFileTask());
// thread.start();
// try {

// // thread.join();
// thread.sleep(1000);

// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// System.out.println("File is ready to be scanned");
// }
// }

// public static void main(String[] args) {
// ThreadDemo.show(); // call the method here

// }
// }

// package threadingMosh;

// public class ThreadDemo {

// public static void show() {

// System.out.println(Thread.currentThread().getName());

// for (var i = 0; i < 10; i++) {

// Thread thread = new Thread(new DownloadFileTask());
// thread.start();
// }
// }

// public static void main(String[] args) {
// }

// }
