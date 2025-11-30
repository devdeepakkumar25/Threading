class MyThread extends Thread {
    public void run() {
        int i = 1;
        while (true) {
            System.out.println(i + " Hello");
            i++;

        }
    }
}

class MyRunnable implements Runnable {

    public void run() {
        int i = 1;
        while (true) {
            System.out.println(i + " Hello");
            i++;

        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        MyRunnable t = new MyRunnable();
        Thread th = new Thread(t);
        th.start();

        int i = 0;
        while (true) {

            System.out.println(i + " World");
            i++;

        }
    }
}

// public class ThreadTest implements Runnable {
// public void run() {
// int i = 1;
// while (true) {
// System.out.println(i + " Hello");
// i++;

// }
// }

// public static void main(String[] args) {

// ThreadTest t = new ThreadTest();
// Thread th = new Thread(t);
// th.start();

// int i = 0;
// while (true) {

// System.out.println(i + " World");
// i++;

// }
// }
// }

// public class ThreadTest {
// public static void main(String[] args) {
// MyThread t = new MyThread();
// t.start();

// int i = 0;
// while (true) {

// System.out.println(i + " World");
// i++;

// }
// }
// }

// public class ThreadTest extends Thread {
// public void run() {
// int i = 1;
// while (true) {
// System.out.println(i + " Hello");
// i++;

// }
// }

// public static void main(String[] args) {

// ThreadTest t = new ThreadTest();
// t.start();

// int i = 0;
// while (true) {

// System.out.println(i + " World");
// i++;

// }
// }
// }
