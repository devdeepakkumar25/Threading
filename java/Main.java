class MyData {
    private int value;
    private boolean flag = true;

    public synchronized void set(int v) {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        value = v;
        flag = false;
        notifyAll();
    }

    public synchronized int get() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int x = value;
        flag = true;
        notifyAll();
        return x;
    }
}

class Producer extends Thread {
    MyData data;

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
    MyData data;

    public Consumer(MyData d) {
        data = d;
    }

    public void run() {
        int value;
        while (true) {
            value = data.get();
            System.out.println("Consumer   " + value);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        MyData data = new MyData();

        Producer p = new Producer(data);
        Consumer c = new Consumer(data);

        p.start();
        c.start();
    }
}
