class MyThread extends Thread {
    public void run() {
        int count = 1;
        while (true) {
            System.out.println("My Thread " + count++);

        }
    }
}

public class ThreadTest2 {
    public static void main(String[] args) {

        MyThread t = new MyThread();
        t.start();

        int count = 1;
        while (true) {
            System.out.println("Main " + count++);
            Thread.yield();

        }

    }

}

// class MyThread extends Thread {
// public void run() {
// int count = 1;
// while (true) {
// System.out.println(count++);

// }
// }
// }

// public class ThreadTest2 {
// public static void main(String[] args) {

// MyThread t = new MyThread();
// t.setDaemon(true);
// t.start();
// Thread maiThread = Thread.currentThread();
// try {

// maiThread.join();
// } catch (InterruptedException e) {
// System.out.println(e);
// }

// }

// }

// class MyThread extends Thread {
// public void run() {
// int count = 1;
// while (true) {
// System.out.println(count++);

// }
// }
// }

// public class ThreadTest2 {
// public static void main(String[] args) {

// MyThread t = new MyThread();
// t.setDaemon(true);
// t.start();
// try {
// Thread.sleep(100);
// } catch (Exception e) {
// }

// }

// }
