class MyData {
    private int value;
    private boolean flag = true; // true = empty, producer turn; false = full, consumer turn

    public synchronized void set(int v) {
        while (!flag) { // wait while data is not yet consumed
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        value = v;
        flag = false; // now full
        notify();
    }

    public synchronized int get() {
        while (flag) { // wait while there is no data
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int x = value;
        flag = true; // empty again
        notify();
        return x;
    }
}

class Producer extends Thread {
    private final MyData data;

    public Producer(MyData d) {
        data = d;
    }

    public void run() {
        int count = 1;
        while (true) {
            data.set(count);
            System.out.println("Producer " + count);
            count++;
        }
    }
}

class Consumer extends Thread {
    private final MyData data;

    public Consumer(MyData d) {
        data = d;
    }

    public void run() {
        while (true) {
            int value = data.get();
            System.out.println("Customer " + value);
        }
    }
}

public class InterProcessDemo {
    public static void main(String[] args) {
        MyData data = new MyData();
        Producer p = new Producer(data);
        Consumer c = new Consumer(data);
        p.start();
        c.start();
    }
}

// class MyData {
// int value;

// public void set(int v) {
// value = v;
// }

// public int get() {
// int x = 0;
// x = value;

// return x;
// }
// }

// class Producer extends Thread {
// MyData data;

// public Producer(MyData d) {
// data = d;
// }

// public void run() {
// int count = 1;

// while (true) {
// data.set(count);
// System.out.println("Producer " + count);
// count++;
// }
// }

// }

// class Consumer extends Thread {
// MyData data;

// public Consumer(MyData d) {
// data = d;
// }

// public void run() {
// int value;
// while (true) {
// value = data.get();
// System.out.println("Customer " + value);
// }
// }
// }

// public class InterProcessDemo {

// public static void main(String[] args) {

// MyData data = new MyData();

// Producer p = new Producer(data);

// Consumer c = new Consumer(data);

// p.start();
// c.start();

// }
// }
