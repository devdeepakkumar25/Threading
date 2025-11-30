class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
        // setPriority(Thread.MAX_PRIORITY);
        // setPriority(Thread.MIN_PRIORITY);
        // setPriority(Thread.MIN_PRIORITY + 2);
    }

    public void run() {
        int count = 1;
        while (true) {
            System.out.println(count++);
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class ThreadTest1 {
    public static void main(String[] args) {

        MyThread t = new MyThread("My Thread 1");
        System.out.println("ID " + t.getId());
        System.out.println("Name " + t.getName());
        System.out.println("Priority " + t.getPriority());
        t.start();
        t.interrupt();
        System.out.println("State " + t.getState());
        System.out.println("State " + t.getState().RUNNABLE);

        System.out.println("Is ALive " + t.isAlive());
    }

}

// class MyThread extends Thread {

// // Constructor to set thread name
// public MyThread(String name) {
// super(name);
// }

// // Thread task
// public void run() {
// try {
// for (int i = 1; i <= 3; i++) {
// System.out.println(getName() + " is running: " + i);
// Thread.sleep(500);
// }
// } catch (InterruptedException e) {
// System.out.println(getName() + " was interrupted.");
// }
// }
// }

// public class ThreadTest1 {
// public static void main(String[] args) {

// MyThread t = new MyThread("My Thread 1");

// // Getting thread information before start
// System.out.println("ID : " + t.getId());
// System.out.println("Name : " + t.getName());
// System.out.println("Priority: " + t.getPriority());
// System.out.println("State : " + t.getState()); // NEW state

// // Start the thread
// t.start();

// // After starting the thread
// System.out.println("State after start: " + t.getState());
// System.out.println("Is Alive? : " + t.isAlive());
// }
// }

// class MyThread extends Thread {
// public MyThread(String name) {
// super(name);
// }
// }

// public class ThreadTest1 {
// public static void main(String[] args) {

// MyThread t = new MyThread("My Thread 1");
// System.out.println("ID " + t.getId());
// System.out.println("Name " + t.getName());
// System.out.println("Priority " + t.getPriority());
// t.start();
// System.out.println("State " + t.getState());
// System.out.println("State " + t.getState().RUNNABLE);

// System.out.println("Is ALive " + t.isAlive());
// }

// }

// class MyRun implements Runnable {
// public void run() {
// int i = 1;
// while (i <= 5) {
// System.out.println(Thread.currentThread().getName() + " : " + i);
// i++;
// }
// }
// }

// public class ThreadTest1 {
// public static void main(String[] args) {

// Thread t = new Thread(new MyRun(), "My Name");

// t.start(); // Start the thread
// }
// }
